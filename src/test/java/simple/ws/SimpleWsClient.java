package simple.ws;

import io.micronaut.function.client.FunctionClient;
import io.micronaut.http.annotation.Body;
import io.reactivex.Single;
import javax.inject.Named;

@FunctionClient
public interface SimpleWsClient {

    @Named("simple-ws")
    Single<SimpleWSRequest> apply(@Body SimpleWSRequest body);

}
