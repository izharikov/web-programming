package fpmi.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Author entity
 *
 * @author Ihar Zharykau
 */
@Entity
@Table(name = "author_tbl")
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + getBooks() +
                '}';
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
