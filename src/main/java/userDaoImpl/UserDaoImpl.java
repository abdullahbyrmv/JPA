package userDaoImpl;

import AbstractDao.abstractDao;
import dao.UserInterface;
import entity.Country;
import entity.Nationality;
import entity.User;

import javax.persistence.EntityManager;
import java.sql.*;
import java.util.ArrayList;
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
        List<User> list = new ArrayList<>();
        try (Connection a = connect()) {
            String sql = "select " +
                    "   user.*, " +
                    "   nationality.nationality_name, " +
                    "   country.name as birthplace " +
                    " from user " +
                    " left join nationality on user.nationality_id = nationality.id " +
                    " left join country on user.birthplace_id = country.id where 1=1";

            if (name != null && !name.trim().isEmpty()) {
                sql += " and user.name = ?";
            }

            if (surname != null && !surname.trim().isEmpty()) {
                sql += " and user.surname = ?";
            }

            if (nationalityId != null) {
                sql += " and user.nationality_id = ?";
            }

            PreparedStatement st = a.prepareStatement(sql);

            int i = 1;

            if (name != null && !name.trim().isEmpty()) {
                st.setString(i, name);
                i++;
            }

            if (surname != null && !surname.trim().isEmpty()) {
                st.setString(i, surname);
                i++;
            }

            if (nationalityId != null) {
                st.setInt(i, nationalityId);
            }

            st.execute();

            ResultSet result = st.getResultSet();
            while (result.next()) {
                User u = getUser(result);
                list.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
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
        User result = null;
        try (Connection c = connect()) {
            PreparedStatement st = c.prepareStatement("select * from user where email=? and password=?");
            st.setString(1, email);
            st.setString(2, password);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                result = SimpleGetUser(res);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = null;
        try (Connection c = connect()) {
            PreparedStatement st = c.prepareStatement("select * from user where email=?");
            st.setString(1, email);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                result = SimpleGetUser(res);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        User u = new User(12, "test", "test", "test", "test", "test", null, null, null, null, null, null, null);
        u.setPassword("12345");
        new UserDaoImpl().addUser(u);
    }
}
