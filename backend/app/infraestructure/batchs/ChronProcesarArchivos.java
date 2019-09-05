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

    @Inject
    public ChronProcesarArchivos(ActorSystem actorSystem,
                                 ExecutionContext executionContext,
                                 DesignService designService) {
        this.actorSystem = actorSystem;
        this.executionContext = executionContext;
        this.designService = designService;
        this.initialize();
    }

    private void initialize() {
        actorSystem.scheduler().schedule(
          Duration.create(10, TimeUnit.SECONDS), // inital delay
          Duration.create(60, TimeUnit.SECONDS), // interval
          () -> JobProcesarArchivos.execute(designService),
          executionContext
        );
    }

}
