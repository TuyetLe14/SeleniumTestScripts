package browsers;

public class Person {
    String fullName;
    float due;

    public Person(String fullName, float due) {
        this.fullName = fullName;
        this.due = due;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public float getDue() {
        return due;
    }

    public void setDue(float due) {
        this.due = due;}
}
