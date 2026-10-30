public class Magazine extends LibraryItem {
    public Magazine(int id, String title, String issue, boolean available, String borrower) {
        super(id, title, issue, available, borrower);
    }

    @Override
    public String getType() {
        return "MAGAZINE";
    }

    @Override
    public int getLoanDays() {
        return 7;
    }

    @Override
    public String getDetailLabel() {
        return "Issue";
    }
}
