package test.jpa;

import fpmi.db.entities.Author;
import fpmi.db.entities.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Ihar Zharykau
 */
@Stateless(mappedName = "jpaRepository")
public class JpaRepositoryImpl implements JpaRepositoryLocal, JpaRepositoryRemote {
    @PersistenceContext(unitName="Catalog")
    private EntityManager entityManager;

    @Override
    public List<Book> allBooks() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> allRoot = cq.from(Book.class);
        cq.select(allRoot);
        TypedQuery<Book> q = entityManager.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public List<Author> allAuthors() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        Root<Author> allRoot = cq.from(Author.class);
        cq.select(allRoot);
        TypedQuery<Author> q = entityManager.createQuery(cq);
        return q.getResultList();
    }
}
