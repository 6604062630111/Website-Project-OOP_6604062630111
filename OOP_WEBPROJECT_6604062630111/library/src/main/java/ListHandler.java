import com.sun.net.httpserver.HttpExchange;
import java.util.List;

public class ListHandler extends BaseHandler {
    public ListHandler(LibraryService service) {
        super(service);
    }

    @Override
    protected void process(HttpExchange exchange) throws Exception {
        List<LibraryItem> items = service.listItems();
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < items.size(); i++) {
            LibraryItem item = items.get(i);
            if (i > 0) {
                json.append(",");
            }
            json.append("{")
                .append("\"id\":").append(item.getId()).append(",")
                .append("\"type\":\"").append(item.getType()).append("\",")
                .append("\"title\":\"").append(escape(item.getTitle())).append("\",")
                .append("\"detailLabel\":\"").append(item.getDetailLabel()).append("\",")
                .append("\"detail\":\"").append(escape(item.getDetail())).append("\",")
                .append("\"loanDays\":").append(item.getLoanDays()).append(",")
                .append("\"available\":").append(item.isAvailable()).append(",")
                .append("\"borrower\":\"").append(escape(item.getBorrower())).append("\"")
                .append("}");
        }
        json.append("]");
        send(exchange, 200, "application/json", json.toString());
    }
}
