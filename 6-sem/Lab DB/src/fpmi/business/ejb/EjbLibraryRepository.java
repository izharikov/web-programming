package fpmi.business.ejb;

import fpmi.business.repository.jpa.JpaLibraryRepository;
import fpmi.db.dao.jpa.JpaDaoBase;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ihar Zharykau
 */
@Stateless(mappedName="ejbLibraryRepository")
public class EjbLibraryRepository extends JpaLibraryRepository implements LibraryRepositoryLocal, LibraryRepositoryRemote {

    @PersistenceContext(unitName="Catalog")
    private EntityManager entityManager;

    public EjbLibraryRepository() {
    }

    @PostConstruct
    public void init(){
        daoBase = new JpaDaoBase(entityManager);
    }

    @Override
    public void start() {
    }

    @Override
    public void end() {
    }
}
