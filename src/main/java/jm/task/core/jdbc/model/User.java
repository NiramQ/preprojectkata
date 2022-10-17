package jm.task.core.jdbc.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Usersmod")
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private String lastName;
    private Byte age;

    public User() {
    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return this.age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            User user = (User)o;
            return Objects.equals(this.name, user.getName()) && Objects.equals(this.lastName, user.getLastName()) && Objects.equals(this.age, user.age) && Objects.equals(this.id, user.getId());
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = 31 * this.toString().hashCode();
        return result;
    }

    public String toString() {
        Long x = this.getId();
        return "" + x + "# " + this.getName() + " " + this.getLastName() + "(" + this.getAge() + ")";
    }
}