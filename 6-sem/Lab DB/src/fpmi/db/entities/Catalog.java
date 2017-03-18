package fpmi.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Catalog entity
 *
 * @author Ihar Zharykau
 */
@Entity
@Table(name = "catalog_tbl")
public class Catalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "catalog", fetch = FetchType.LAZY)
    private Set<Book> books;

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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Catalog catalog = (Catalog) o;

        if (id != catalog.id) return false;
        if (name != null ? !name.equals(catalog.name) : catalog.name != null) return false;
        return books != null ? books.equals(catalog.books) : catalog.books == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (books != null ? books.hashCode() : 0);
        return result;
    }
}
