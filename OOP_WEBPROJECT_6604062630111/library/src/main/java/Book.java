public class Book extends LibraryItem {
    public Book(int id, String title, String author, boolean available, String borrower) {
        super(id, title, author, available, borrower);
    }

    @Override
    public String getType() {
        return "BOOK";
    }

    @Override
    public int getLoanDays() {
        return 14;
    }

    @Override
    public String getDetailLabel() {
        return "Author";
    }
}
