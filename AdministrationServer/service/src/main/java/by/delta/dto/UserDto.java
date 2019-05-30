package by.delta.dto;

import by.delta.dto.abstractdto.AbstractDto;
import by.delta.model.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends AbstractDto {

    private static final String BIRTHDAY_DATE_FORMAT = "yyyy-MM-dd";

    public UserDto() {
    }

    private String nickName;

    private String name;

    private String surName;

    private String patronymic;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String email;

    @JsonFormat(pattern = BIRTHDAY_DATE_FORMAT)
    private LocalDate birthDay;

    private String sex;

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
    public String toString() {
        return "UserDto{" +
                "nickName='" + nickName + '\'' +
                ", surName='" + surName + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthDay=" + birthDay +
                ", sex='" + sex + '\'' +
                ", role=" + role +
                '}';
    }
}