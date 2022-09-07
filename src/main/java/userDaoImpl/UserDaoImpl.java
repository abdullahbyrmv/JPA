package userDaoImpl;

import AbstractDao.abstractDao;
import dao.UserInterface;
import entity.Country;
import entity.Nationality;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends abstractDao implements UserInterface {
    private User getUser(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        String surname = result.getString("surname");
        String phone = result.getString("phone");
        String email = result.getString("email");
        String profileDescription = result.getString("profile_description");
        String address = result.getString("address");
        int nationality_id = result.getInt("nationality_id");
        int birthPlace_id = result.getInt("birthplace_id");
        String nationalityStr = result.getString("nationality_name");
        String birthPlaceStr = result.getString("birthplace");
        Date birthdate = result.getDate("birthdate");
        String header = result.getString("old_company_name");
        Date begin_date = result.getDate("begin_date");
        Date end_date = result.getDate("end_date");
        String Description = result.getString("job_description");

        Nationality nationality = new Nationality(nationality_id, nationalityStr);
        Country birthplace = new Country(birthPlace_id, birthPlaceStr);

        return new User(id, name, surname, phone, email, profileDescription, address, birthdate, nationality, birthplace, begin_date, end_date, Description);
    }

    private User SimpleGetUser(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        String surname = result.getString("surname");
        String phone = result.getString("phone");
        String email = result.getString("email");

        User user = new User(id, name, surname, phone, email, null, null, null, null, null, null, null, null);
        user.setPassword(result.getString("password"));
        return user;
    }

    @Override
    public List<User> getAllInfo(String name, String surname, Integer nationalityId) {
        EntityManager em = entityManager();
        String jpql = "select u from User u where 1=1";

        if (name != null && !name.trim().isEmpty()) {
            jpql += " and u.name = :name";
        }

        if (surname != null && !surname.trim().isEmpty()) {
            jpql += "and u.surname = :name";
        }

        if (nationalityId != null) {
            jpql += "and u.nationality.id = :nid";
        }

        Query query = em.createQuery(jpql, User.class);

        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);
        }

        if (surname != null && !surname.trim().isEmpty()) {
            query.setParameter("surname", surname);
        }

        if (nationalityId != null) {
            query.setParameter("nid", nationalityId);
        }

        return query.getResultList();
    }

    @Override
    public boolean updateUser(User u) {
        EntityManager em = entityManager();

        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public boolean removeUser(int id) {
        EntityManager em = entityManager();

        User u = em.find(User.class, id);
        em.getTransaction().begin();
        em.remove(u);
        em.getTransaction().commit();

        em.close();
        return true;
    }

    public User getByID(int userId) {
        EntityManager em = entityManager();

        User u = em.find(User.class, userId);

        em.close();
        return u;
    }

    public boolean addUser(User u) {
        EntityManager em = entityManager();

        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        EntityManager em = entityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> q1 = cb.createQuery(User.class);
        Root<User> postRoot = q1.from(User.class);
        CriteriaQuery<User> q2 = q1.where(cb.equal(postRoot.get("email"), ":e"), cb.equal(postRoot.get("password"), ":p"));

        Query query = em.createQuery(q2);
        query.setParameter(1, email);
        query.setParameter(2, password);
        List<User> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }


//        Query query = em.createQuery("select u from User u where u.email = :e and u.password = :p", User.class);
//        query.setParameter("e", email);
//        query.setParameter("p", password);
//
//        List<User> list = query.getResultList();
//
//        if (list.size() == 1) {
//            return list.get(0);
//        }

        return null;
    }


    // Criteria Builder
//    @Override
//    public User findByEmail(String email) {
//
//        EntityManager em = entityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<User> q1 = cb.createQuery(User.class);
//        Root<User> postRoot = q1.from(User.class);
//        CriteriaQuery<User> q2 = q1.where(cb.equal(postRoot.get("email"), email));
//
//        Query query = em.createQuery(q2);
////        query.setParameter(1, email);
//        List<User> list = query.getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }


    // JPQL
//        @Override
//        public User findByEmail(String email){
//        EntityManager em = entityManager();
//            Query query = em.createQuery("select u from User u where u.email = :e", User.class);
//        query.setParameter("e", email);
//
//        List<User> list = query.getResultList();
//
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//        return null;
//    }


    // Named Query
//    @Override
//    public User findByEmail(String email) {
//
//        EntityManager em = entityManager();
//        Query query = em.createNamedQuery("User.findByEmail",User.class);
//
//        query.setParameter("email", email);
//
//        List<User> list = query.getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//        return null;
//    }


    // Native SQL
    @Override
    public User findByEmail(String email) {

        EntityManager em = entityManager();
        Query query = em.createNativeQuery("select * from user where email = ?", User.class);

        query.setParameter(1, email);

        List<User> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    public static void main(String[] args) {
        User u = new User(12, "test", "test", "test", "test", "test", null, null, null, null, null, null, null);
        u.setPassword("12345");
        new UserDaoImpl().addUser(u);
    }
}
