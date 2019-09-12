package infraestructure.batchs;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import infraestructure.services.DesignService;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class    ChronProcesarArchivos {

    private final ActorSystem actorSystem;
    private final ExecutionContext executionContext;
    private final DesignService designService;
    private final Config config;

    @Inject
    public ChronProcesarArchivos(ActorSystem actorSystem,
                                 ExecutionContext executionContext,
                                 DesignService designService,
                                 Config config) {
        this.actorSystem = actorSystem;
        this.executionContext = executionContext;
        this.designService = designService;
        this.config = config;
        this.initialize();
    }

    private void initialize() {
        actorSystem.scheduler().schedule(
          Duration.create(10, TimeUnit.SECONDS), // inital delay
          Duration.create(60, TimeUnit.SECONDS), // interval
          () -> JobProcesarArchivos.execute(designService, config),
          executionContext
        );
    }

}
