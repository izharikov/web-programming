package test;

import javax.ejb.Stateless;

/**
 * @author Ihar Zharykau
 */
@Stateless(mappedName = "HelloImpl")
public class HelloImpl implements HelloLocal, HelloRemote {
    @Override
    public String sayHello() {
        return "Hello, world!\n";
    }

}
