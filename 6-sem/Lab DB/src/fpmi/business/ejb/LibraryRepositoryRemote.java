package fpmi.business.ejb;

import fpmi.business.repository.LibraryRepository;

import javax.ejb.Remote;

/**
 * Remote EJB
 *
 * @author Ihar Zharykau
 */
@Remote
public interface LibraryRepositoryRemote extends LibraryRepository {
}
