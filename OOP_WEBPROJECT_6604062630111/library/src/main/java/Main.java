import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        Database database = Database.getInstance();
        Repository<LibraryItem> repository = new ItemRepository(database);
        LibraryService service = new LibraryService(repository);

        if (service.listItems().isEmpty()) {
            service.addItem("BOOK", "Clean Code", "Robert C. Martin");
            service.addItem("MAGAZINE", "National Geographic", "March 2026");
            service.addItem("DVD", "Inception", "Christopher Nolan");
        }

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new PageHandler(service));
        server.createContext("/api/items", new ListHandler(service));
        server.createContext("/api/add", new AddHandler(service));
        server.createContext("/api/borrow", new BorrowHandler(service));
        server.createContext("/api/return", new ReturnHandler(service));
        server.createContext("/api/delete", new DeleteHandler(service));
        server.start();
        System.out.println("Server running at http://localhost:8080");
    }
}
