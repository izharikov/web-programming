package fpmi.db.dao.jpa;

import fpmi.db.entities.Book;
import fpmi.db.entities.Form;
import fpmi.db.entities.Role;
import fpmi.db.entities.User;
import fpmi.utils.bean.BeanUtils;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Base JPA class utils
 *
 * @author Ihar Zharykau
 */

public class JpaDaoBase {
    public JpaDaoBase(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void start() {
    }

    /**
     * closing entityManager
     */
    public void end() {
        entityManager.close();
    }

    private EntityManager entityManager;

    /**
     * Read entity by id
     *
     * @param id    id of entity
     * @param clazz Class of entity
     * @param <T>   Class of entity
     * @return entity object
     */
    public <T> T read(int id, Class<T> clazz) {
        return entityManager.find(clazz, id);
    }

    /**
     * Persist object in database
     *
     * @param object object to persist
     * @param <T>    Class of object
     * @return id of created object
     */
    public <T> int create(T object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.flush();
        entityManager.getTransaction().commit();
        Integer id = BeanUtils.callGet(object, "id");
        if (id == null) {
            id = -1;
        }
        return id;
    }

    /**
     * Update entity object
     *
     * @param object object to update
     * @param <T>    Class of object
     */
    public <T> void update(T object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    /**
     * Delete object from database
     *
     * @param object object to delete
     * @param <T>    Class of object
     */
    public <T> void delete(T object) {
        entityManager.getTransaction().begin();
        entityManager.remove(object);
        entityManager.getTransaction().commit();
    }

    /**
     * Delete object by id
     *
     * @param id    id of object
     * @param clazz Class of object
     * @param <T>   Class of object
     */
    public <T> void delete(int id, Class<T> clazz) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(clazz, id));
        entityManager.getTransaction().commit();
    }

    /**
     * Check user exists
     *
     * @param user user
     * @return true - if exists, else - otherwise
     */
    public boolean checkUserExist(User user) {
        Query q = entityManager.createNamedQuery("userExists");
        q.setParameter("name", user.getName());
        q.setParameter("password", user.getPassword());
        q.setParameter("role", user.getRole());
        return (Long) q.getResultList().get(0) > 0;
    }

    /**
     * Query all entities by class
     *
     * @param clazz Class of entity
     * @param <T>   Class of entity
     * @return Collection of entities
     */
    public <T> Collection<T> queryAll(Class<T> clazz) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> allRoot = cq.from(clazz);
        cq.select(allRoot);
        TypedQuery<T> q = entityManager.createQuery(cq);
        return q.getResultList();
    }

    /**
     * Query for depts users
     *
     * @param days count of days
     * @return list of users
     */
    @SuppressWarnings("unchecked")
    public List<User> deptsUsers(int days) {
        Query q = entityManager.createNamedQuery("deptsQuery");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -days);
        q.setParameter("date", calendar.getTime());
        return q.getResultList();
    }

    /**
     * query books by author id
     *
     * @param id id of author
     * @return all books of author
     */
    @SuppressWarnings("unchecked")
    public Collection<Book> booksByAuthor(int id) {
        Query q = entityManager.createNamedQuery("booksByAuthor");
        q.setParameter("id", id);
        return q.getResultList();
    }

    /**
     * Give book to user
     *
     * @param book      book
     * @param user      user
     * @param librarian librarian
     */
    public void giveBookToUser(Book book, User user, User librarian) {
        Form form = new Form();
        Calendar calendar = Calendar.getInstance();
        form.setStartDate(calendar.getTime());
        calendar.add(Calendar.MONTH, 1);
        form.setEndDate(calendar.getTime());
        form.setUser(user);
        form.setLibrarian(librarian);
        form.setBook(book);
        create(form);
    }

    /**
     * Query all users by role
     *
     * @param role role of user
     * @return user of specified role
     */
    @SuppressWarnings("unchecked")
    public Collection<User> queryUsersByRole(Role role) {
        Query q = entityManager.createNamedQuery("userByRole");
        q.setParameter("role", role);
        return q.getResultList();
    }

    /**
     * query user id by login
     *
     * @param login login of user
     * @return id of user
     */
    public int getUserId(String login) {
        Query q = entityManager.createNamedQuery("userIdByName");
        q.setParameter("name", login);
        return (Integer) q.getResultList().get(0);
    }
}
