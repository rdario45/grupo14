package modules;

import com.google.inject.AbstractModule;
import infraestructure.batchs.ChronProcesarArchivos;


public class TareasProgramadas extends AbstractModule {

    @Override
    protected void configure() {
        bind(ChronProcesarArchivos.class).asEagerSingleton();
    }
}
