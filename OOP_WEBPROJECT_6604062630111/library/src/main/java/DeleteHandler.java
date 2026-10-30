import com.sun.net.httpserver.HttpExchange;
import java.util.Map;

public class DeleteHandler extends BaseHandler {
    public DeleteHandler(LibraryService service) {
        super(service);
    }

    @Override
    protected void process(HttpExchange exchange) throws Exception {
        Map<String, String> form = readForm(exchange);
        service.removeItem(readId(form));
        sendOk(exchange);
    }
}
