package bookstable;

public class Books {
    public Books(int id, String title, int year, int quantity) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.quantity = quantity;
    }

    private int id;
    private String title;
    private int year;
    private int quantity;

    @Override
    public String toString() {
        return "" + "\n" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", quantity=" + quantity;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getQuantity() {
        return quantity;
    }
}
