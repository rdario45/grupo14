package infraestructure.batchs;

import akka.actor.ActorSystem;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class ChronProcesarArchivos {

    private final ActorSystem actorSystem;
    private final ExecutionContext executionContext;

    @Inject
    public ChronProcesarArchivos(ActorSystem actorSystem,
                                 ExecutionContext executionContext) {
        this.actorSystem = actorSystem;
        this.executionContext = executionContext;
        this.initialize();
    }

    private void initialize() {
        actorSystem.scheduler().schedule(
          Duration.create(10, TimeUnit.SECONDS), // inital delay
          Duration.create(30, TimeUnit.SECONDS), // interval
          () -> JobProcesarArchivos.execute(),
          executionContext
        );
    }

}
