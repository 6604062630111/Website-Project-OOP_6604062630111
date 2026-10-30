import com.sun.net.httpserver.HttpExchange;
import java.util.Map;

public class ReturnHandler extends BaseHandler {
    public ReturnHandler(LibraryService service) {
        super(service);
    }

    @Override
    protected void process(HttpExchange exchange) throws Exception {
        Map<String, String> form = readForm(exchange);
        service.returnItem(readId(form));
        sendOk(exchange);
    }
}
