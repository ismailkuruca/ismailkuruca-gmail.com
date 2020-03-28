package simple.ws;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class SimpleWsFunctionTest {

    @Inject
    SimpleWsClient client;

    @Test
    public void testFunction() throws Exception {
    	SimpleWSRequest body = new SimpleWSRequest();
    	body.setName("simple-ws");
        assertEquals("simple-ws", client.apply(body).blockingGet().getName());
    }
}
