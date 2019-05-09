public class User {

    int id;
    String name;
    int pw;

    public User(int id, String name, int pw) {
        this.id = id;
        this.name = name;
        this.pw = pw;
    }

    public String toString() {
        return "ID: " + id + " 이름: " + name;
    }

    public int getID() {
        return id;
    }

    public void setID(int number) {
        this.id = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPW () {
        return pw;
    }

    public void setPW(int pw) {
        this.pw = pw;
    }

    public User() {
    }
}
