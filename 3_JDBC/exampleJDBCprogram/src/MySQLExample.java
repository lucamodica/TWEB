import java.util.ArrayList;
import dao.*;

/* Questo esempio dimostra come connettersi a un DB MySQL
   e recuperare dati da tabella. Per un completo, che
   mostra vari tipi di connessione, vedere:
   http://www.codejava.net/java-se/jdbc/connect-to-mysql-database-via-jdbc#CodeExample
 */
public class MySQLExample {

    public static void main(String[] args) {
        DAO.registerDriver();

        ArrayList<Persona> person = DAO.queryDB();
        for (Persona p: person){
            System.out.println(p);
        }
        System.out.println("FINE");
    }
}

