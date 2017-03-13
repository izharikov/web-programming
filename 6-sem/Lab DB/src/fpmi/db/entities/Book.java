package fpmi.db.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Ihar Zharykau
 */
@Entity
@Table(name = "book_tbl")
@NamedQuery(name = "booksByAuthor",
        query = "select b from Book b where b.author.id = :id")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "count_of_books")
    private int countOfBooks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Author author;

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

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public int getCountOfBooks() {
        return countOfBooks;
    }

    public void setCountOfBooks(int countOfBooks) {
        this.countOfBooks = countOfBooks;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countOfBooks=" + countOfBooks +
                ", catalog=" + (getCatalog() != null ? getCatalog().getName() : "null") +
                ", author=" + (getAuthor() != null ? getCatalog().getName() : "null")  +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (countOfBooks != book.countOfBooks) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
//        if (catalog != null ? !catalog.equals(book.catalog) : book.catalog != null) return false;
        return author != null ? author.equals(book.author) : book.author == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + countOfBooks;
        result = 31 * result + (catalog != null ? catalog.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
