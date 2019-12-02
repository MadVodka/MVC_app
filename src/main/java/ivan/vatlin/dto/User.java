package ivan.vatlin.dto;

import ivan.vatlin.enums.UserRole;
import ivan.vatlin.enums.UserStatus;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column
    private String password;

    @Transient
    private String matchingPassword;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.ACTIVE; // by default a new user is active

    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public User setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }


    public UserRole getUserRole() {
        return userRole;
    }

    public User setUserRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public User setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", userRole=" + userRole +
                ", userStatus=" + userStatus +
                '}' + "\n";
    }
}
