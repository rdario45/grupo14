package infraestructure.batchs;

import domain.Design;
import domain.DesignStatus;
import infraestructure.services.DesignService;

import org.joda.time.DateTime;
import play.Logger;

import javax.inject.Inject;
import java.io.File;
import java.util.Date;
import java.util.List;


public class JobProcesarArchivos {



    public static void execute(DesignService designService){
        Logger.info(">>> CORRIENDO TAREA PROGRAMADA >>>");
        Logger.debug(DateTime.now().toString());
        List<Design> designs =  designService.getPendingDesigns();
        Logger.info(">>> Se enconntraron " +  designs.size() +" diseños >>>");
        ImageResizer resizeImg = new ImageResizer();
        SendEmailSSL sendEmailSSL = new SendEmailSSL();
            sendEmailSSL.connect("designMatch14@gmail.com","rvfkzapfyonjrvbz");
        int i = 0;
        for (Design design: designs ){

            String targetFile = design.getOriginalPath().substring(0,design.getOriginalPath().lastIndexOf("/")+1) + "resized/";
            Logger.info(">>> targetDirectory " +  targetFile +" >>>");
            File f =new File (targetFile);
            boolean created = f.mkdir();
            Logger.info(">>> created " +  created +" >>>");
            targetFile += design.getOriginalPath().substring(design.getOriginalPath().lastIndexOf("/")+1,
                                                                design.getOriginalPath().lastIndexOf("."));
            Logger.info(">>> targetFile " +  targetFile +" >>>");

            //"/home/diego/Imágenes/resized/res"+ i++ + ".png";
            resizeImg.processImage(design.getOriginalPath(), targetFile,
                    800,600,true,design.getEmail() + design.getUploadDate());
            design.setStretchedPath(targetFile);
            design.setDesignStatus(DesignStatus.AVAILABLE);
            sendEmailSSL.send(design.getEmail(),
                    "Su diseño ha sido aprobado",
                    "Su diseño se encuentra aprobado y ya puede ser visualizado en la plataforma.");
            designService.updateDesign(design);
        }

        //Actualizar los diseños*/


    }
}
