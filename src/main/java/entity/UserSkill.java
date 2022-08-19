package entity;

import javax.persistence.*;

@Entity
@Table(name = "user_skill", schema = "resume")
public class UserSkill {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "skill_id")
    private Integer skillId;
    @Basic
    @Column(name = "power")
    private Integer power;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User userByUserId;
    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Skill skillBySkillId;

    public UserSkill(int userSkillId, User user, Skill skill, int power) {
        this.id = userSkillId;
        this.userByUserId = user;
        this.skillBySkillId = skill;
        this.power = power;
    }

    public UserSkill() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSkill userSkill = (UserSkill) o;

        if (id != userSkill.id) return false;
        if (userId != null ? !userId.equals(userSkill.userId) : userSkill.userId != null) return false;
        if (skillId != null ? !skillId.equals(userSkill.skillId) : userSkill.skillId != null) return false;
        if (power != null ? !power.equals(userSkill.power) : userSkill.power != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (skillId != null ? skillId.hashCode() : 0);
        result = 31 * result + (power != null ? power.hashCode() : 0);
        return result;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public Skill getSkillBySkillId() {
        return skillBySkillId;
    }

    public void setSkillBySkillId(Skill skillBySkillId) {
        this.skillBySkillId = skillBySkillId;
    }
}
