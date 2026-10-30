import com.sun.net.httpserver.HttpExchange;
import java.util.Map;

public class AddHandler extends BaseHandler {
    public AddHandler(LibraryService service) {
        super(service);
    }

    @Override
    protected void process(HttpExchange exchange) throws Exception {
        Map<String, String> form = readForm(exchange);
        service.addItem(form.get("type"), form.get("title"), form.get("detail"));
        sendOk(exchange);
    }
}
