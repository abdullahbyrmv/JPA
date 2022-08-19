package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Skill {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "skill_name")
    private String skillName;
    @OneToMany(mappedBy = "skillBySkillId")
    private Collection<UserSkill> userSkillsById;

    public Skill(int skillId, String skillName) {
        this.id = skillId;
        this.skillName = skillName;
    }

    public Skill() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        if (id != skill.id) return false;
        if (skillName != null ? !skillName.equals(skill.skillName) : skill.skillName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (skillName != null ? skillName.hashCode() : 0);
        return result;
    }

    public Collection<UserSkill> getUserSkillsById() {
        return userSkillsById;
    }

    public void setUserSkillsById(Collection<UserSkill> userSkillsById) {
        this.userSkillsById = userSkillsById;
    }
}
