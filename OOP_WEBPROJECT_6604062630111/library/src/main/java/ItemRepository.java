import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository implements Repository<LibraryItem> {
    private final Database database;

    public ItemRepository(Database database) {
        this.database = database;
    }

    @Override
    public List<LibraryItem> findAll() {
        List<LibraryItem> result = new ArrayList<>();
        try (Connection c = database.connect();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery("SELECT * FROM items ORDER BY id")) {
            while (rs.next()) {
                result.add(map(rs));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    @Override
    public LibraryItem findById(int id) {
        try (Connection c = database.connect();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM items WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        throw new IllegalArgumentException("Item not found");
    }

    @Override
    public void save(LibraryItem item) {
        if (item.getId() == 0) {
            insert(item);
        } else {
            update(item);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection c = database.connect();
             PreparedStatement ps = c.prepareStatement("DELETE FROM items WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private void insert(LibraryItem item) {
        String sql = "INSERT INTO items(type, title, detail, available, borrower) VALUES(?, ?, ?, ?, ?)";
        try (Connection c = database.connect();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getType());
            ps.setString(2, item.getTitle());
            ps.setString(3, item.getDetail());
            ps.setInt(4, item.isAvailable() ? 1 : 0);
            ps.setString(5, item.getBorrower());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    item.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private void update(LibraryItem item) {
        String sql = "UPDATE items SET available = ?, borrower = ? WHERE id = ?";
        try (Connection c = database.connect();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, item.isAvailable() ? 1 : 0);
            ps.setString(2, item.getBorrower());
            ps.setInt(3, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private LibraryItem map(ResultSet rs) throws SQLException {
        return ItemFactory.create(
                rs.getString("type"),
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("detail"),
                rs.getInt("available") == 1,
                rs.getString("borrower"));
    }
}
