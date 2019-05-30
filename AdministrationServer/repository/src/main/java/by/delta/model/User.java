package by.delta.model;

import by.delta.model.abstractmodel.AbstractModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User extends AbstractModel {

    public User() {
        //default constructor
    }

    @Column(name = "nick_name", length = 30)
    private String nickName;
    @Column(name = "name", length = 30)
    private String name;
    @Column(name = "surname", length = 50)
    private String surName;
    @Column(name = "patronymic", length = 30)
    private String patronymic;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "birthday")
    private LocalDate birthDay;
    @Column(name = "sex")
    private String sex;
    @Column(name = "role", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(nickName, user.nickName)
                .append(surName, user.surName)
                .append(name, user.name)
                .append(patronymic, user.patronymic)
                .append(password, user.password)
                .append(email, user.email)
                .append(birthDay, user.birthDay)
                .append(sex, user.sex)
                .append(role, user.role)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(nickName)
                .append(surName)
                .append(name)
                .append(patronymic)
                .append(password)
                .append(email)
                .append(birthDay)
                .append(sex)
                .append(role)
                .toHashCode();
    }
}