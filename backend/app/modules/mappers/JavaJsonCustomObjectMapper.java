package modules.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.jackson.datatype.VavrModule;
import play.libs.Json;

public class JavaJsonCustomObjectMapper {
    JavaJsonCustomObjectMapper() {
        ObjectMapper mapper = Json.newDefaultMapper()
          .registerModule(new VavrModule());
        Json.setObjectMapper(mapper);
    }
}
