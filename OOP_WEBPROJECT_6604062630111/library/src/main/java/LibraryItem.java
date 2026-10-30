public abstract class LibraryItem {
    private int id;
    private String title;
    private String detail;
    private boolean available;
    private String borrower;

    protected LibraryItem(int id, String title, String detail, boolean available, String borrower) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.available = available;
        this.borrower = borrower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getBorrower() {
        return borrower;
    }

    public void borrow(String name) {
        if (!available) {
            throw new IllegalStateException("This item is already borrowed");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Borrower name is required");
        }
        available = false;
        borrower = name.trim();
    }

    public void giveBack() {
        if (available) {
            throw new IllegalStateException("This item is not borrowed");
        }
        available = true;
        borrower = null;
    }

    public abstract String getType();

    public abstract int getLoanDays();

    public abstract String getDetailLabel();
}
