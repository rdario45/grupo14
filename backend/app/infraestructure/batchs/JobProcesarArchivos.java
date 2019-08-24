package infraestructure.batchs;

import org.joda.time.DateTime;
import play.Logger;

public class JobProcesarArchivos {

    public static void execute(){
        Logger.info(">>> CORRIENDO TAREA PROGRAMADA >>>");
        Logger.debug(DateTime.now().toString());
    }
}
