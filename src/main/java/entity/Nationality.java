package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Nationality {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nationality_name")
    private String nationalityName;
    @OneToMany(mappedBy = "nationalityByNationalityId")
    private Collection<User> usersById;

    public Nationality(int nationality_id, String nationalityStr) {
        this.id = nationality_id;
        this.nationalityName = nationalityStr;
    }

    public Nationality() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nationality that = (Nationality) o;

        if (id != that.id) return false;
        if (nationalityName != null ? !nationalityName.equals(that.nationalityName) : that.nationalityName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nationalityName != null ? nationalityName.hashCode() : 0);
        return result;
    }

    public Collection<User> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<User> usersById) {
        this.usersById = usersById;
    }
}
