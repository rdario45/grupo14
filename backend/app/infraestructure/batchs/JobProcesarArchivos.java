package infraestructure.batchs;

import domain.Design;
import domain.DesignStatus;
import infraestructure.services.DesignService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import play.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;


public class JobProcesarArchivos {


    public static void execute(DesignService designService) {
        Logger.info(">>> CORRIENDO TAREA PROGRAMADA >>>");
        Logger.debug(DateTime.now().toString());
        List<Design> designs = designService.getPendingDesigns();

        Logger.info(">>> Se enconntraron " + designs.size() + " diseños >>>");
        ImageResizer resizeImg = new ImageResizer();
        SendEmailSSL sendEmailSSL = new SendEmailSSL();
        sendEmailSSL.connect("designMatch14@gmail.com", "rvfkzapfyonjrvbz");

       

        for (Design design : designs) {

            String targetFile = design.getOriginalPath().substring(0, design.getOriginalPath().lastIndexOf("/") + 1) + "resized/";
			Logger.info(">>> " + design.getOriginalPath() + ">>>");
			Logger.info(">>> " + targetFile + ">>>");
            File f = new File(targetFile);
            f.mkdir();

            targetFile += design.getOriginalPath().substring(design.getOriginalPath().lastIndexOf("/") + 1,
              design.getOriginalPath().lastIndexOf("."));

            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
            boolean sendMail = resizeImg.processImage(design.getOriginalPath(), targetFile,
              800, 600, true, design.getEmail() + " \n\r " + formatter.format(design.getUploadDate().toDate()));

            design.setResizedPath(targetFile+".png");
            design.setDesignStatus(DesignStatus.AVAILABLE);
            String url = design.getCompany().getName().toLowerCase().replaceAll("[^a-zA-Z0-9]+","") + "-"
                    + design.getCompany().getId();
            if (sendMail)
				sendEmailSSL.send(design.getEmail(),
				  "Su diseño ha sido aprobado",
				  "<html><b>Su diseño se encuentra aprobado y ya puede ser visualizado en la plataforma.</b><br><br>" +
				  "Puede visualizar los diseños en la siguiente URL: <a href='" + url + "'/> " + url + "</a> " +
				  "<html/>");
            designService.updateDesign(design);
			Logger.debug(">>>>> ID:[" + design.getId() + "] START: [" + formatter.format(design.getUploadDate().toDate()) +
							"] FINISH:[" + DateTime.now(DateTimeZone.forID("America/Bogota")) + "]");
        }


    }
}
