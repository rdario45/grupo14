package infraestructure.batchs;

import domain.Design;
import org.joda.time.DateTime;
import play.Logger;

import java.util.Date;
import java.util.List;

public class JobProcesarArchivos {

    public static void execute(){
        Logger.info(">>> CORRIENDO TAREA PROGRAMADA >>>");
        Logger.debug(DateTime.now().toString());
        List<Design> designs = null; // OBTENER LISTA DE DISEÑOS de base de datos.
        ImageResizer resizer = new ImageResizer();
        SendEmailSSL sendEmailSSL = new SendEmailSSL();
        sendEmailSSL.connect("diegoatorres@gmail.com","nbwzfrbddlnjnpfa");
        int i = 0;
        for (Design design: designs ){
            String destinyFile = "/home/diego/Imagenes/resized/res"+ i + ".png";
            resizer.processImage(design.getOriginalPath(), destinyFile,
                    800,600,true,design.getEmail() + new Date());
            design.setStretchedPath(destinyFile);
//            design.setStretched(true);
            sendEmailSSL.send(design.getEmail(),
                    "Su diseño ha sido aprobado",
                    "Su diseño se encuentra aprobado y ya puede ser visualizado en la plataforma.");

        }
        //Actualizar los diseños


    }
}
