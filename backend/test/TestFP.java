


import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.Test;

import java.util.function.Function;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class TestFP {

    @Test
    public void fromEitherFutureOptionToEither(){
        Either<String, Future<Option<String>>> either = Either.right(Future.successful(Option.of("yes")));

        Either<String, String> finalEither = either.flatMap(future ->
          future.map(option -> option.toEither("option fail"))
            .toEither("future fail")
            .flatMap(Function.identity()));

        assertTrue(finalEither.isRight());
        assertEquals(finalEither.get(), "yes");
    }


}
