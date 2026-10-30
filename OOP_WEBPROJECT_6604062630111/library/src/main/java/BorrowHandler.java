import com.sun.net.httpserver.HttpExchange;
import java.util.Map;

public class BorrowHandler extends BaseHandler {
    public BorrowHandler(LibraryService service) {
        super(service);
    }

    @Override
    protected void process(HttpExchange exchange) throws Exception {
        Map<String, String> form = readForm(exchange);
        service.borrowItem(readId(form), form.get("borrower"));
        sendOk(exchange);
    }
}
