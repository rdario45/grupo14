package modules;

import com.google.inject.AbstractModule;
import modules.mappers.JavaJsonCustomObjectMapper;

public class JavaJsonModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(JavaJsonCustomObjectMapper.class).asEagerSingleton();
    }
}