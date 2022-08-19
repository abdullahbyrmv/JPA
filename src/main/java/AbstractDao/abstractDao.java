package AbstractDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class abstractDao {
    public static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";// your database name
        String password = "legendonline1";// your database password
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }

    private static EntityManagerFactory emf = null;

    public EntityManager entityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("default");
        }
        EntityManager em = emf.createEntityManager();
        return em;
    }

    public void closeEmf() {
        emf.close();
    }
}
