public class Dvd extends LibraryItem {
    public Dvd(int id, String title, String director, boolean available, String borrower) {
        super(id, title, director, available, borrower);
    }

    @Override
    public String getType() {
        return "DVD";
    }

    @Override
    public int getLoanDays() {
        return 3;
    }

    @Override
    public String getDetailLabel() {
        return "Director";
    }
}
