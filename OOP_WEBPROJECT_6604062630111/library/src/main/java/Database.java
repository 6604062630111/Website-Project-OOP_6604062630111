import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Database instance;
    private final String url;

    private Database(String url) {
        this.url = url;
        createTables();
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database("jdbc:sqlite:library.db");
        }
        return instance;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }

    private void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS items ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "type TEXT NOT NULL, "
                + "title TEXT NOT NULL, "
                + "detail TEXT NOT NULL, "
                + "available INTEGER NOT NULL DEFAULT 1, "
                + "borrower TEXT)";
        try (Connection c = connect(); Statement s = c.createStatement()) {
            s.execute(sql);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot create tables", e);
        }
    }
}
