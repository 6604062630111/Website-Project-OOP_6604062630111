import com.sun.net.httpserver.HttpExchange;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class PageHandler extends BaseHandler {
    public PageHandler(LibraryService service) {
        super(service);
    }

    @Override
    protected void process(HttpExchange exchange) throws Exception {
        try (InputStream in = getClass().getResourceAsStream("/index.html")) {
            String html = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            send(exchange, 200, "text/html", html);
        }
    }
}
