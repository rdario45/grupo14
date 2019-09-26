package infraestructure.batchs;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import play.Logger;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class ChronProcesarArchivos {

    private final ActorSystem actorSystem;
    private final ExecutionContext executionContext;
    private final JobProcesarArchivos procesarArchivos;

    @Inject
    public ChronProcesarArchivos(ActorSystem actorSystem,
                                 ExecutionContext executionContext,
                                 Config config,
                                 JobProcesarArchivos procesarArchivos) {
        this.actorSystem = actorSystem;
        this.executionContext = executionContext;
        this.procesarArchivos = procesarArchivos;

        if( isScheduledTaskEnabled(config) ) {
            this.initialize();
        } else {
            Logger.debug("Worker is disabled");
        }
    }

    private boolean isScheduledTaskEnabled(Config config) {
        return config.getBoolean("worker.enable");
    }

    private void initialize() {
        actorSystem.scheduler().schedule(
          Duration.create(10, TimeUnit.SECONDS), // inital delay
          Duration.create(60, TimeUnit.SECONDS), // interval
          procesarArchivos::execute,
          executionContext
        );
    }

}
