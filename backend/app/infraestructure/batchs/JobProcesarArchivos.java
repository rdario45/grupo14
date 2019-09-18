package infraestructure.batchs;

import com.typesafe.config.Config;
import domain.Design;
import domain.DesignStatus;
import infraestructure.services.AmazonEmailClient;
import infraestructure.services.DesignService;
import infraestructure.services.ImageResizer;
import infraestructure.services.SendEmailSSL;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import play.Logger;

import javax.inject.Inject;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;


public class JobProcesarArchivos {

    private final AmazonEmailClient clientSES;
    private final DesignService designService;
    private final Config config;

    @Inject
    public JobProcesarArchivos(AmazonEmailClient clientSES, DesignService designService, Config config) {
        this.clientSES = clientSES;
        this.designService = designService;
        this.config = config;
    }

    public void execute() {
        // this.clientSES.send("rdario45@gmail.com", "url");

        String startBatchDateTime = DateTime.now(DateTimeZone.forID("America/Bogota")).toString();
        Logger.info(">>> CORRIENDO TAREA PROGRAMADA at:["+startBatchDateTime+"]" );
        List<Design> designs = designService.getPendingDesigns();

        if ( designs.size() > 0 ) {
            Logger.info("> DISEÑOS A PROCESAR [" + designs.size() + "]: {");

            ImageResizer resizeImg = new ImageResizer();
            SendEmailSSL sendEmailSSL = createEmailSSL();

            String workdir = config.getString("files.workdir");
            designs.forEach(design -> {
                String targetDir = workdir + "resized/" + design.getFolder();
                Path fileDir = createFileDir(targetDir);
                String targetFile = fileDir.toString() + "/" + design.getFileName();

                String startDesignDateTime = DateTime.now(DateTimeZone.forID("America/Bogota")).toString();
                processDesign(designService, resizeImg, sendEmailSSL, design, targetFile);
                String finishDesignDateTime = DateTime.now(DateTimeZone.forID("America/Bogota")).toString();

                Logger.info("* ID:["+design.getId()+"] START:["+startDesignDateTime+"] FINISH:["+finishDesignDateTime+"]");
            });

            String finishBatchDateTime = DateTime.now(DateTimeZone.forID("America/Bogota")).toString();
            Logger.info("} FINALIZACION PROCESAMIENTO BATCH DE ["+designs.size()+"] REGISTROS, INICIADO EN:["+startBatchDateTime+"] FINALIZADO EN:["+finishBatchDateTime+"]"  );
        }

    }

    private static void processDesign(DesignService designService, ImageResizer resizeImg, SendEmailSSL sendEmailSSL, Design design, String targetFile) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        String textToAdd = design.getEmail() + " \n\r " + formatter.format(design.getUploadDate().toDate());
        boolean sendMail = resizeImg.processImage(
          design.getOriginalPath(),
          targetFile,
          800,
          600,
          true,
          textToAdd);
        design.setResizedPath(targetFile+".png");
        design.setStatus(DesignStatus.AVAILABLE);
        if (sendMail) {
            sendEmailSSL.send(design.getEmail(),
              "Su diseño ha sido aprobado",
              "<html><b>Su diseño se encuentra aprobado y ya puede ser visualizado en la plataforma.</b><br><br>" +
                "Puede visualizar los diseños en la siguiente URL: <a href=''/> " +
                "<html/>");
        }
        designService.updateDesign(design);
    }

    private static Path createFileDir(String targetFile) {
        File file = new File(targetFile);
        file.mkdirs();
        return Paths.get(targetFile);
    }

    private static SendEmailSSL createEmailSSL() {
        SendEmailSSL sendEmailSSL = new SendEmailSSL();
        sendEmailSSL.connect("designMatch14@gmail.com", "rvfkzapfyonjrvbz");
        return sendEmailSSL;
    }
}
