package infraestructure.batchs;

import com.typesafe.config.Config;
import domain.Company;
import domain.Design;
import domain.DesignStatus;
import infraestructure.repository.company.CompanyMapper;
import infraestructure.repository.company.CompanyRepository;
import infraestructure.repository.company.records.CompanyRecord;
import infraestructure.services.EmailClientService;
import infraestructure.services.DesignService;
import infraestructure.services.ImageResizer;
import io.vavr.control.Option;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import play.Logger;

import javax.inject.Inject;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class JobProcesarArchivos {

    public static final String SUBJECT = "Su diseño ha sido aprobado";
    public static final String MESSAGE = String.join(
      System.getProperty("line.separator"),
      "<h1>Su diseño ha sido procesado!</h1>" +
      "<p>Su diseño se encuentra aprobado y ya puede ser visualizado en la plataforma.</p>" +
      "<p>Puede visualizar los diseños en la siguiente URL: <a href=\"{{url}}\"/>{{url}}</a></p>");

    private final Config config;
    private final CompanyRepository companyRepository;
    private final EmailClientService clientSES;
    private final DesignService designService;

    @Inject
    public JobProcesarArchivos(Config config, EmailClientService clientSES, DesignService designService, CompanyRepository companyRepository) {
        this.clientSES = clientSES;
        this.designService = designService;
        this.config = config;
        this.companyRepository = companyRepository;
    }

    public void execute() {
        String startBatchDateTime = DateTime.now(DateTimeZone.forID("America/Bogota")).toString();
        Logger.info("> CORRIENDO TAREA PROGRAMADA at:[" + startBatchDateTime + "]");

        List<Design> designs = designService.getPendingDesigns();
        if (designs.size() > 0) {
            AtomicInteger i = new AtomicInteger(1);
            int l = designs.size();

            Logger.info(">> DISEÑOS A PROCESAR [" + l + "]: {");
            String workdir = config.getString("files.workdir");
            designs.forEach(design -> {

                Option<CompanyRecord> company = companyRepository.getCompanyByDesignId(design.getId());
                Company company_ =  CompanyMapper.fromRecordToCompany(company.get());
                design.setCompany(company_);
                String targetDir = workdir + "resized/" + design.getFolder();
                Path fileDir = createFileDir(targetDir);
                String targetFile = fileDir.toString() + "/" + design.getFileName();

                String startDesignDateTime = DateTime.now(DateTimeZone.forID("America/Bogota")).toString();
                Design designProcessed = processDesign(design, targetFile);
                sendEmail(designProcessed);
                String finishDesignDateTime = DateTime.now(DateTimeZone.forID("America/Bogota")).toString();

                Logger.info(">>> N:["+ i.getAndIncrement() +"/"+l+"] ID:["+design.getId()+"] STARTED:["+startDesignDateTime+"] FINISHED:["+finishDesignDateTime+"]");
            });

            String finishBatchDateTime = DateTime.now(DateTimeZone.forID("America/Bogota")).toString();
            Logger.info("} FINALIZACION PROCESAMIENTO BATCH N:["+designs.size()+"], STARTED:["+startBatchDateTime+"] FINISHED:["+finishBatchDateTime+"]");
        }

    }

    private Design processDesign(Design design, String targetFile) {
        ImageResizer resizeImg = new ImageResizer();
        String textToAdd = createTextToAdd(design);
        String originalPath = design.getOriginalPath();
        resizeImg.processImage(
          originalPath,
          targetFile,
          800,
          600,
          true,
          textToAdd);
        design.setResizedPath(targetFile + ".png");
        design.setStatus(DesignStatus.AVAILABLE);
        designService.updateDesign(design);
        return design;
    }

    private void sendEmail(Design design) {
        if (this.config.getBoolean("email.default.enable")) {
            clientSES.send(design.getEmail(), SUBJECT, MESSAGE.replace("{{url}}",design.getCompany().getUrl()));
        } else {
            Logger.debug("Envio de email desabilitado.");
        }
    }

    private static String createTextToAdd(Design design) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        return design.getEmail() + " \n\r " + formatter.format(design.getUploadDate().toDate());
    }

    private static Path createFileDir(String targetFile) {
        File file = new File(targetFile);
        file.mkdirs();
        return Paths.get(targetFile);
    }
}