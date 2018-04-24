package booklib.jar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import service.BookDTO;
import service.IBooksBeanRemote;

/**
 * 
 * Standalone jar application to access remote EJB interface.
 * The JNDI lookup used (the dependency injection can be used with web based client only.).
 * !!Important!! when EJB is on Glasfish implementation
 * Add extern library gf-client.jar as:
 * C:\Users\raitis\dev\4netbeans\payara41\glassfish\lib\gf-client.jar
 * @author raitis
 */
public class BookLibJar {

    /**
     * @param args the command line arguments
     * @throws javax.naming.NamingException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws NamingException, FileNotFoundException, IOException {

        Properties props = new Properties();
            props.load(new FileInputStream("jndi.properties"));
            
        // java:global/BookLibEA/BookLibEA-ejb/BooksBean!service.IBooksBeanRemote
        InitialContext context = new InitialContext(props);
        IBooksBeanRemote bookEjb = 
                (IBooksBeanRemote) context.lookup("service.IBooksBeanRemote");
        List<BookDTO> books = bookEjb.r_findAll();
        System.out.println("books: "+books);
    }
    
}
