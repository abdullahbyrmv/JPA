package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "profile_description")
    private String profileDescription;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "birthdate")
    private Date birthdate;
    @Basic
    @Column(name = "birthplace_id")
    private Integer birthplaceId;
    @Basic
    @Column(name = "nationality_id")
    private Integer nationalityId;
    @Basic
    @Column(name = "old_company_name")
    private String oldCompanyName;
    @Basic
    @Column(name = "begin_date")
    private Date beginDate;
    @Basic
    @Column(name = "end_date")
    private Date endDate;
    @Basic
    @Column(name = "job_description")
    private String jobDescription;
    @Basic
    @Column(name = "employment_id")
    private Integer employmentId;
    @Basic
    @Column(name = "password")
    private String password;
    @ManyToOne
    @JoinColumn(name = "employment_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private EmploymentHistory employmentHistoryByEmploymentId;
    @ManyToOne
    @JoinColumn(name = "birthplace_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Country countryByBirthplaceId;
    @ManyToOne
    @JoinColumn(name = "nationality_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Nationality nationalityByNationalityId;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<UserSkill> userSkillsById;

    public User(int id, String name, String surname, String phone, String email, String profileDescription, String address, Date birthdate, Nationality nationality, Country birthplace, Date begin_date, Date end_date, String description) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.profileDescription = profileDescription;
        this.address = address;
        this.birthdate = birthdate;
        this.nationalityByNationalityId = nationality;
        this.countryByBirthplaceId = birthplace;
        this.beginDate = begin_date;
        this.endDate = end_date;
        this.jobDescription = description;
    }

    public User() {
    }

    public User(int userId) {
        this.id = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getBirthplaceId() {
        return birthplaceId;
    }

    public void setBirthplaceId(Integer birthplaceId) {
        this.birthplaceId = birthplaceId;
    }

    public Integer getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getOldCompanyName() {
        return oldCompanyName;
    }

    public void setOldCompanyName(String oldCompanyName) {
        this.oldCompanyName = oldCompanyName;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Integer getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(Integer employmentId) {
        this.employmentId = employmentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (profileDescription != null ? !profileDescription.equals(user.profileDescription) : user.profileDescription != null)
            return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (birthdate != null ? !birthdate.equals(user.birthdate) : user.birthdate != null) return false;
        if (birthplaceId != null ? !birthplaceId.equals(user.birthplaceId) : user.birthplaceId != null) return false;
        if (nationalityId != null ? !nationalityId.equals(user.nationalityId) : user.nationalityId != null)
            return false;
        if (oldCompanyName != null ? !oldCompanyName.equals(user.oldCompanyName) : user.oldCompanyName != null)
            return false;
        if (beginDate != null ? !beginDate.equals(user.beginDate) : user.beginDate != null) return false;
        if (endDate != null ? !endDate.equals(user.endDate) : user.endDate != null) return false;
        if (jobDescription != null ? !jobDescription.equals(user.jobDescription) : user.jobDescription != null)
            return false;
        if (employmentId != null ? !employmentId.equals(user.employmentId) : user.employmentId != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (profileDescription != null ? profileDescription.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (birthplaceId != null ? birthplaceId.hashCode() : 0);
        result = 31 * result + (nationalityId != null ? nationalityId.hashCode() : 0);
        result = 31 * result + (oldCompanyName != null ? oldCompanyName.hashCode() : 0);
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (jobDescription != null ? jobDescription.hashCode() : 0);
        result = 31 * result + (employmentId != null ? employmentId.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public EmploymentHistory getEmploymentHistoryByEmploymentId() {
        return employmentHistoryByEmploymentId;
    }

    public void setEmploymentHistoryByEmploymentId(EmploymentHistory employmentHistoryByEmploymentId) {
        this.employmentHistoryByEmploymentId = employmentHistoryByEmploymentId;
    }

    public Country getCountryByBirthplaceId() {
        return countryByBirthplaceId;
    }

    public void setCountryByBirthplaceId(Country countryByBirthplaceId) {
        this.countryByBirthplaceId = countryByBirthplaceId;
    }

    public Nationality getNationalityByNationalityId() {
        return nationalityByNationalityId;
    }

    public void setNationalityByNationalityId(Nationality nationalityByNationalityId) {
        this.nationalityByNationalityId = nationalityByNationalityId;
    }

    public Collection<UserSkill> getUserSkillsById() {
        return userSkillsById;
    }

    public void setUserSkillsById(Collection<UserSkill> userSkillsById) {
        this.userSkillsById = userSkillsById;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }
}
