import java.util.List;

public class LibraryService {
    private final Repository<LibraryItem> repository;

    public LibraryService(Repository<LibraryItem> repository) {
        this.repository = repository;
    }

    public List<LibraryItem> listItems() {
        return repository.findAll();
    }

    public void addItem(String type, String title, String detail) {
        if (title == null || title.isBlank() || detail == null || detail.isBlank()) {
            throw new IllegalArgumentException("Title and detail are required");
        }
        LibraryItem item = ItemFactory.create(type, 0, title.trim(), detail.trim(), true, null);
        repository.save(item);
    }

    public void borrowItem(int id, String borrower) {
        LibraryItem item = repository.findById(id);
        item.borrow(borrower);
        repository.save(item);
    }

    public void returnItem(int id) {
        LibraryItem item = repository.findById(id);
        item.giveBack();
        repository.save(item);
    }

    public void removeItem(int id) {
        repository.delete(id);
    }
}
