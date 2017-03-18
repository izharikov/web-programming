package fpmi.business.ejb;

import fpmi.business.repository.jpa.JpaLibraryRepository;
import fpmi.db.dao.jpa.JpaDaoBase;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation library repository for EJB purpose
 *
 * @author Ihar Zharykau
 */
@Stateless(mappedName = "ejbLibraryRepository")
public class EjbLibraryRepository extends JpaLibraryRepository implements LibraryRepositoryLocal, LibraryRepositoryRemote {

    /**
     * Entity manager to work with JPA
     */
    @PersistenceContext(unitName = "Catalog")
    private EntityManager entityManager;

    public EjbLibraryRepository() {
    }

    /**
     * After initializing {@link EjbLibraryRepository#entityManager}, init {@link JpaDaoBase} object
     */
    @PostConstruct
    public void init() {
        daoBase = new JpaDaoBase(entityManager);
    }

    /**
     * Empty start method, because we don't need to init EntityManager manually
     */
    @Override
    public void start() {
    }

    /**
     * Empty end method, because we don't need to close EntityManager manually
     */
    @Override
    public void end() {
    }
}
