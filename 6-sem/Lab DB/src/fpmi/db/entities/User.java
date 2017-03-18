package fpmi.db.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User entity
 *
 * @author Ihar Zharykau
 */
@Entity
@Table(name = "user_tbl")
@NamedQueries({
        @NamedQuery(name = "userExists",
                query = "SELECT count (u) from User u " +
                        "where u.name = :name and u.password = :password and u.role = :role"),
        @NamedQuery(name = "userByRole", query = "select u from User u where u.role = :role"),
        @NamedQuery(name = "userIdByName", query = "select u.id from User u where u.name = :name")
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(columnDefinition = "enum('USUAL','ADMIN')", name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name=" + name +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return role == user.role;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    public static void main(String... args) throws Exception {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Catalog");
        EntityManager entitymanager = emfactory.createEntityManager();
        Author user = entitymanager.find(Author.class, 1);
        System.out.println(user);
    }
}
