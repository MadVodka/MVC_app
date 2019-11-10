package ivan.vatlin.dao;

import ivan.vatlin.dto.User;
import ivan.vatlin.enums.UserRole;
import ivan.vatlin.enums.UserStatus;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    List<User> getOrderedUsers();

    User getUserById(long id);

    User getUserByUserName(String userName);

    List<User> getUsersByRole(UserRole userRole);

    List<User> getUsersByPage(int pageNumber, int usersPerPage);

    int createUser(User user);

    int updateUserStatus(long id, UserStatus userStatus);

    int deleteUser(long id);
}
