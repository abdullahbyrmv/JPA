package Main;

import userDaoImpl.*;

public class Context {
    public static CountryDaoImpl instanceCountryDao() {
        return new CountryDaoImpl();
    }

    public static EmploymentHistoryDaoImpl instanceEmploymentHistoryDao() {
        return new EmploymentHistoryDaoImpl();
    }

    public static SkillDaoImpl instanceSkillDao() {
        return new SkillDaoImpl();
    }

    public static UserDaoImpl instanceUserDao() {
        return new UserDaoImpl();
    }

    public static UserSkillDaoImpl instanceUserSkillDao() {
        return new UserSkillDaoImpl();
    }

    public static NationalityDaoImpl instanceNationalityDao() {
        return new NationalityDaoImpl();
    }
}
