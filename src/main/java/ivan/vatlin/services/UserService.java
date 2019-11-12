package ivan.vatlin.services;

import ivan.vatlin.dao.UserDao;
import ivan.vatlin.dto.User;
import ivan.vatlin.enums.UserRole;
import ivan.vatlin.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public List<User> getOrderedUsers() {
        return userDao.getOrderedUsers();
    }

    public User getUserById(long id) {
        try {
            return userDao.getUserById(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User getUserByUserName(String userName) {
        try {
            return userDao.getUserByUserName(userName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean userNameExist(String userName) {
        return getUserByUserName(userName) != null;
    }

    public List<User> getUsersByPage(int pageNumber, int usersPerPage) {
        int startPosition = (pageNumber - 1) * usersPerPage;
        return userDao.getUsersByPage(startPosition, usersPerPage);
    }

    public List<User> getClients() {
        return userDao.getUsersByRole(UserRole.USER);
    }

    public List<User> getAdmins() {
        return userDao.getUsersByRole(UserRole.ADMIN);
    }

    @Transactional
    public int registerUser(User user) {
        if (getUserByUserName(user.getUserName()) == null) {
            return userDao.createUser(user);
        }
        return -1;
    }

    public int activateUser(long id) {
        return userDao.updateUserStatus(id, UserStatus.ACTIVE);
    }

    public int blockUser(long id) {
        return userDao.updateUserStatus(id, UserStatus.BLOCKED);
    }

    public int deleteUser(long id) {
        return userDao.deleteUser(id);
    }
}
