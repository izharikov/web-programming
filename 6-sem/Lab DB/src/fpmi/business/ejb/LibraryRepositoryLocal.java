package fpmi.business.ejb;

import fpmi.business.repository.LibraryRepository;

import javax.ejb.Local;

/**
 * Local EJB
 *
 * @author Ihar Zharykau
 */
@Local
public interface LibraryRepositoryLocal extends LibraryRepository {
}
