package fpmi.main;

import fpmi.business.ejb.LibraryRepositoryRemote;
import fpmi.business.exception.ExceptionCode;
import fpmi.business.exception.RepositoryException;
import fpmi.business.repository.LibraryRepository;
import fpmi.db.entities.*;
import fpmi.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;

/**
 * @author Ihar Zharykau
 */
public class MainProgram {
    private static Map<ExceptionCode, String> codeMessageMap = new HashMap<>();
    private static LibraryRepository libraryRepository;

    private static Scanner scanner = new Scanner(System.in);
    private static Logger LOGGER = LogManager.getLogger(MainProgram.class);

    private static int currentLibrarianId = -1;

    public static void main(String... args) {
        //  Initial Setup
        InitialContext initialContext = null;
        try {
            initialContext = new InitialContext();
            libraryRepository = (LibraryRepositoryRemote) initialContext.lookup("ejbLibraryRepository");
        } catch (NamingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        codeMessageMap.put(ExceptionCode.DB_SYSTEM_ERROR, "Error while process db statement.");
        codeMessageMap.put(ExceptionCode.USER_INPUT_ERROR, "Please, check your inputs.");
        //  Starting repository
        libraryRepository.start();
        print("Choose role: ");
        print(START_OPTIONS);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                processUser();
                break;
            case 2:
                processLibrarian();
                break;
            case 3:
                exit();
                break;
            default:
                main(args);
        }
    }

    public static void exit(){
        libraryRepository.end();
        System.exit(0);
    }

    private static void processLibrarian() {
        print("Enter login and password:");
        scanner.nextLine();
        String login = scanner.nextLine();
        String password = scanner.nextLine();
        boolean success = false;
        if (StringUtils.isNotEmpty(login) && StringUtils.isNotEmpty(password)) {
            try {
                if (libraryRepository.loginUser(login, password, Role.ADMIN)) {
                    currentLibrarianId = libraryRepository.getUserId(login);
                    success = true;
                    processLibrarianOptions();
                }
            } catch (RepositoryException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        if (!success) {
            LOGGER.debug("Invalid attempt to login librarian");
        }
    }

    private static void processLibrarianOptions() {
        print("Choose Option:");
        print(LIBRARIAN_OPTIONS);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                showUsersWithDepts();
                break;
            case 2:
                showAllBooks();
                break;
            case 3:
                showAllUsers();
                break;
            case 4:
                giveBookToUser();
                break;
            case 5:
                seeAllForms();
                break;
            case 6:
                exit();
        }
        processLibrarianOptions();
    }

    private static void showAllUsers() {
        try {
            Collection<User> users = libraryRepository.getAllUsers();
            users.forEach(user ->
                    print("\t| " + user.getId() + " | " + user.getName()));
        } catch (RepositoryException e) {
            print(codeMessageMap.get(e.getExceptionCode()));
            LOGGER.error(e);
        }
    }

    private static void showUsersWithDepts() {
        List<User> users = null;
        try {
            users = libraryRepository.deptUsers(0);
            users.forEach(user ->
                    print("\t| " + user.getId() + " | " + user.getName()));
        } catch (RepositoryException e) {
            print(codeMessageMap.get(e.getExceptionCode()));
            LOGGER.error(e);
        }
    }

    private static void processUser() {
        print("Choose action:");
        print(USER_ACTION_TO_ENTER_SYSTEM);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                processLoginUser();
                break;
            case 2:
                processRegisterUser();
                break;
            case 3:
                exit();
                break;
            default:
                processUser();
        }
    }

    private static void processLoginUser() {
        print("Enter login and password:");
        scanner.nextLine();
        String login = scanner.nextLine();
        String password = scanner.nextLine();
        try {
            boolean success = libraryRepository.loginUser(login, password);
            if (success) {
                processActionsForLoggedInUsers();
            }
        } catch (RepositoryException e) {
            System.err.print(codeMessageMap.get(e.getExceptionCode()));
            LOGGER.error(e);
        }
    }

    private static void processRegisterUser() {
        print("Enter login and password:");
        scanner.nextLine();
        String login = scanner.nextLine();
        String password = scanner.nextLine();
        try {
            libraryRepository.createNewUser(login, password);
            processActionsForLoggedInUsers();
        } catch (RepositoryException e) {
            System.err.print(codeMessageMap.get(e.getExceptionCode()));
            LOGGER.error(e);
        }
    }

    private static void processActionsForLoggedInUsers() {
        print(LOGGED_IN_USER_ACTIONS);
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                showAllBooks();
                break;
            case 2:
                showAllAuthors();
                break;
            case 3:
                showAllBooksByAuthor();
                break;
            case 4:
                exit();
                break;
        }
        processActionsForLoggedInUsers();
    }

    private static void showAllBooks() {
        try {
            Collection<Book> books = libraryRepository.getAllBooks();
            books.forEach(
                    book ->
                            print("\t| " +
                                    book.getId() + " | " + book.getName() + " | " + book.getCountOfBooks()
                            ));
        } catch (RepositoryException e) {
            LOGGER.error(e);
        }
    }

    private static void showAllAuthors() {
        try {
            Collection<Author> authors = libraryRepository.getAllAuthors();
            authors.forEach(author ->
                    print("\t| " + author.getId() + " | " + author.getName()));
        } catch (RepositoryException e) {
            LOGGER.error(e);
        }
    }

    private static void showAllBooksByAuthor() {
        print("Enter author id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Collection<Book> books = libraryRepository.getBooksByAuthor(id);
            books.forEach(book ->
                    print("\t| " +
                            book.getId() + " | " + book.getName() + " | " + book.getCountOfBooks()));
        } catch (RepositoryException e) {
            LOGGER.error(e);
        }
    }

    private static void giveBookToUser() {
        print("Enter book id, user id:");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        int userId = scanner.nextInt();
        scanner.nextLine();
        int libId = currentLibrarianId;
        try {
            libraryRepository.giveBookToUser(bookId, userId, libId);
        } catch (RepositoryException e) {
            LOGGER.error(e);
        }
    }

    private static void seeAllForms() {
        try {
            Collection<Form> forms = libraryRepository.getAllForms();
            forms.forEach(form ->
                    print("\t|" + form.getId() +
                        " | User: " + form.getUser().getId() + " , " + form.getUser().getName() + " | " +
                    form.getBook().getId() + " , " + form.getBook().getName() + " , " + form.getBook().getCountOfBooks() + " | " +
                    form.getStartDate() + " | " + form.getEndDate()));
        } catch (RepositoryException e) {
            LOGGER.error(e);
        }
    }


    private static final String START_OPTIONS = "1. User\n" +
            "2. Librarian\n" +
            "3. Exit";
    private static final String USER_ACTION_TO_ENTER_SYSTEM = "1. Login\n2. Register\n" +
            "3. Exit";

    private static final String LOGGED_IN_USER_ACTIONS = "1. See all books.\n2. See all authors.\n" +
            "3. See all books by author.\n" +
            "4. Exit";

    private static final String LIBRARIAN_OPTIONS = "1. See users with depts more 1 month.\n" +
            "2. See all books\n" +
            "3. See all users\n" +
            "4. Give book to user.\n" +
            "5. See all forms.\n" +
            "6. Exit";

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MainProgram.class);
    
    private static void print(String message){
        logger.info(message + "\n");
    }
}
