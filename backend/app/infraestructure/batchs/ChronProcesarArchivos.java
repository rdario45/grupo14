package infraestructure.batchs;

import akka.actor.ActorSystem;
import infraestructure.services.DesignService;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class ChronProcesarArchivos {

    private final ActorSystem actorSystem;
    private final ExecutionContext executionContext;
    private final DesignService designService;
    private final JobProcesarArchivos procesarArchivos;

    @Inject
    public ChronProcesarArchivos(ActorSystem actorSystem,
                                 ExecutionContext executionContext,
                                 DesignService designService,
                                 JobProcesarArchivos procesarArchivos) {
        this.actorSystem = actorSystem;
        this.executionContext = executionContext;
        this.designService = designService;
        this.procesarArchivos = procesarArchivos;
        this.initialize();
    }

    private void initialize() {
        actorSystem.scheduler().schedule(
          Duration.create(10, TimeUnit.SECONDS), // inital delay
          Duration.create(3600, TimeUnit.SECONDS), // interval
          procesarArchivos::execute,
          executionContext
        );
    }

}
