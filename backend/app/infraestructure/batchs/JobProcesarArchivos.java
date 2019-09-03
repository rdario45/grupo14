package infraestructure.batchs;

import domain.Design;
import domain.DesignStatus;
import infraestructure.services.DesignService;
import org.joda.time.DateTime;
import play.Logger;

import java.util.List;

public class JobProcesarArchivos {



    public static void execute(DesignService designService){
        Logger.info(">>> CORRIENDO TAREA PROGRAMADA >>>");
        Logger.debug(DateTime.now().toString());



        List<Design> designs =  designService.getPendingDesigns();
        Logger.info(">>> Se enconntraron " +  designs.size() +" diseños >>>");
        ImageResizer resizeImg = new ImageResizer();
        SendEmailSSL sendEmailSSL = new SendEmailSSL();
        sendEmailSSL.connect("diegoatorres@gmail.com","nbwzfrbddlnjnpfa");
        int i = 0;
        for (Design design: designs ){
            String destinyFile = "/home/diego/Imágenes/resized/res"+ i++ + ".png";
            resizeImg.processImage(design.getOriginalPath(), destinyFile,
                    800,600,true,design.getEmail() + design.getUploadDate());
            design.setResizedPath(destinyFile);
            design.setDesignStatus(DesignStatus.AVAILABLE);
            //sendEmailSSL.send(design.getEmail(),
            //        "Su diseño ha sido aprobado",
            //        "Su diseño se encuentra aprobado y ya puede ser visualizado en la plataforma.");
            designService.updateDesign(design);
        }

        //Actualizar los diseños*/


    }
}
