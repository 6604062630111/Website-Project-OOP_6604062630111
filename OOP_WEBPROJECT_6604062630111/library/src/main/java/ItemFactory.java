public class ItemFactory {
    private ItemFactory() {
    }

    public static LibraryItem create(String type, int id, String title, String detail, boolean available, String borrower) {
        switch (type) {
            case "BOOK":
                return new Book(id, title, detail, available, borrower);
            case "MAGAZINE":
                return new Magazine(id, title, detail, available, borrower);
            case "DVD":
                return new Dvd(id, title, detail, available, borrower);
            default:
                throw new IllegalArgumentException("Unknown item type: " + type);
        }
    }
}
