package Main;

import dao.UserInterface;
import entity.User;

public class DatabaseMain {

    public static void main(String[] args) {
        UserInterface userDao = Context.instanceUserDao();
//        User u = new User(14, "test", "test", "test", "test", "test", null, null, null, null, null, null, null);
//        userDao.addUser(u);
        User u = userDao.findByEmail("abayramov14100@ada.edu.az");
        System.out.println(u);
    }
}
