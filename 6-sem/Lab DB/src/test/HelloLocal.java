package test;

import javax.ejb.Local;

/**
 * @author Ihar Zharykau
 */
@Local
public interface HelloLocal {
    String sayHello();
}
