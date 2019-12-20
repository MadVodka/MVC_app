package ivan.vatlin.dao.jdbc;

import ivan.vatlin.dto.User;
import ivan.vatlin.enums.UserRole;
import ivan.vatlin.exceptions.DaoUpdateFailException;

import java.util.List;

public interface IUserDao {
    List<User> getAllUsers();

    User getUserById(long id);

    User getUserByUserName(String userName);

    long getUserIdByUserName(String userName);

    List<User> getUsersByRole(UserRole userRole);

    List<User> getUsersBySearch(String text, String searchByParam);

    List<User> getUsersByPage(int pageNumber, int usersPerPage);

    int getNumberOfUsers();

    int createUser(User user) throws DaoUpdateFailException;
}
