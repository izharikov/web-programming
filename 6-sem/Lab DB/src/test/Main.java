package test;

import org.apache.log4j.Logger;
import test.jpa.JpaRepositoryRemote;

import javax.naming.InitialContext;

;

/**
 * @author Ihar Zharykau
 */
public class Main {
    private Logger logger = Logger.getLogger(Main.class);
    private HelloRemote helloRemote;
    private JpaRepositoryRemote jpaRepositoryRemote;

    private void connect() {
        try {
            InitialContext ctx = new InitialContext();
            helloRemote = (HelloRemote) ctx.lookup("HelloImpl");
            jpaRepositoryRemote = (JpaRepositoryRemote) ctx.lookup("jpaRepository");
            logger.info(helloRemote.sayHello());
            logger.info("Success\n");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage(), ex);
        }
    }

    private void listAllBooks(){
        logger.info(jpaRepositoryRemote.allBooks() + "\n");
    }

    private void listAllAuthors(){
        logger.info(jpaRepositoryRemote.allAuthors() + "\n");
    }

    public static void main(String[] args) {
        Main x = new Main();
        x.connect();
        x.listAllBooks();
        x.listAllAuthors();
    }
}
