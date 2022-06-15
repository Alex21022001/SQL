package entity;

public class Subscribers extends Entity {
    private int id;

    @Override
    public String toString() {
        return  "\n"+
                "id=" + id +
                ", s_name='" + name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String b_name) {
        this.name = b_name;
    }

    private String name;
}
