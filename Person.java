public class Person {

    int id;
    int gender;
    int time;

    public Person() {
    }

    public Person(int id, int gender, int time) {
        this.id = id;
        this.gender = gender;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", gender=" + gender +
                ", time=" + time +
                '}';
    }
}
