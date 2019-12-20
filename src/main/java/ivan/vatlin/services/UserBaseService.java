package ivan.vatlin.services;

import ivan.vatlin.dao.jdbc.IUserDao;
import ivan.vatlin.dto.User;
import ivan.vatlin.exceptions.DaoUpdateFailException;
import ivan.vatlin.pagination.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(value = "database.api", havingValue = "jdbc", matchIfMissing = true)
public class UserBaseService implements UserService {
    @Autowired
    private IUserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${entity.per.page}")
    private int usersPerPage;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        try {
            return userDao.getUserById(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User getUserByUserName(String userName) {
        try {
            return userDao.getUserByUserName(userName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean userNameExist(String userName) {
        return getUserByUserName(userName) != null;
    }

    @Override
    public List<User> getUsersBySearch(String text, String searchBy) {
        return userDao.getUsersBySearch(text, searchBy);
    }

    @Override
    public PageInfo<User> getUsersByPage(Integer pageNumber) {
        return getUsersByPage(pageNumber, usersPerPage);
    }

    @Override
    public PageInfo<User> getUsersByPage(Integer pageNumber, int usersPerPage) {
        if (pageNumber == null) {
            pageNumber = 1;
        }

        int startPosition = (pageNumber - 1) * usersPerPage;
        List<User> usersByPage = userDao.getUsersByPage(startPosition, usersPerPage);

        long numberOfUsers = getNumberOfUsers();
        int numberOfPages = (int) Math.ceil(numberOfUsers * 1.0 / usersPerPage);

        return new PageInfo<>(usersByPage, numberOfPages);
    }

    @Override
    public long getNumberOfUsers() {
        return userDao.getNumberOfUsers();
    }

    @Override
    public long registerUser(User user) {
        if (user == null) {
            throw new NullPointerException();
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        if (getUserByUserName(user.getUserName()) == null) {
            try {
                return userDao.createUser(user);
            } catch (DaoUpdateFailException e) {
                return -1;
            }
        }
        return -1;
    }
}
