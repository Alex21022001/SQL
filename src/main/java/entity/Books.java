package entity;

public class Books extends Entity {

    private int id;
    private String title;
    private int year;
    private int quantity;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

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
