package ivan.vatlin.services;

import ivan.vatlin.dao.UserDao;
import ivan.vatlin.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
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

    public List<User> getUsersBySearch(String text, String searchBy) {
        return userDao.getUsersBySearch(text, searchBy);
    }

    public List<User> getUsersByPage(int pageNumber, int usersPerPage) {
        int startPosition = (pageNumber - 1) * usersPerPage;
        return userDao.getUsersByPage(startPosition, usersPerPage);
    }

    public int getNumberOfUsers() {
        return userDao.getNumberOfUsers();
    }

    @Transactional
    public int registerUser(User user) {
        if (getUserByUserName(user.getUserName()) == null) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            return userDao.createUser(user);
        }
        return -1;
    }
}
