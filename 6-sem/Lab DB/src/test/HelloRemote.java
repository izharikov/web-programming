package test;

import javax.ejb.Remote;

/**
 * @author Ihar Zharykau
 */
@Remote
public interface HelloRemote {
    String sayHello();
}
