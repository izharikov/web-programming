package fpmi.db.entities;

/**
 * Librarian entity. Deprecated: user {@link User} with role {@link Role#ADMIN} instead of this.
 *
 * @author Ihar Zharykau
 */

@Deprecated
public class Librarian extends User {

    private int id;

    private String name;

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
}
