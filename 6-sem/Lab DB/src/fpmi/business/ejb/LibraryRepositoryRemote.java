package fpmi.business.ejb;

import fpmi.business.repository.LibraryRepository;

import javax.ejb.Remote;

/**
 * @author Ihar Zharykau
 */
@Remote
public interface LibraryRepositoryRemote extends LibraryRepository{
}
