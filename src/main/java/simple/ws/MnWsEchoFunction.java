package simple.ws;

import com.agorapulse.micronaut.aws.apigateway.ws.MessageSender;
import com.agorapulse.micronaut.aws.apigateway.ws.MessageSenderFactory;
import com.agorapulse.micronaut.aws.apigateway.ws.event.RequestContext;
import com.agorapulse.micronaut.aws.apigateway.ws.event.WebSocketRequest;
import com.agorapulse.micronaut.aws.apigateway.ws.event.WebSocketResponse;
import io.micronaut.function.FunctionBean;
import io.micronaut.function.executor.FunctionInitializer;

import javax.inject.Inject;
import java.util.function.Function;

@FunctionBean("mn-ws-echo")
public class MnWsEchoFunction extends FunctionInitializer implements Function<WebSocketRequest, WebSocketResponse> {

    @Inject
    MessageSenderFactory factory;

    @Override
    public WebSocketResponse apply(WebSocketRequest event) {
        RequestContext ctx = event.getRequestContext();
        MessageSender sender = factory.create(ctx);
        String connectionId = ctx.getConnectionId();

        switch (ctx.getEventType()) {
            case CONNECT:
                // e.g. register new connection in cache
                break;
            case MESSAGE:
                System.out.println(event.getBody());
                sender.send(connectionId, event.getBody());
                break;
            case DISCONNECT:
                // e.g unregister connection from cache
                break;
        }
        return WebSocketResponse.OK;
    }
}
