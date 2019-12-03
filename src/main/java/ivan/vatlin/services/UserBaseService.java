package ivan.vatlin.services;

import ivan.vatlin.dao.IUserDao;
import ivan.vatlin.dto.User;
import ivan.vatlin.pagination.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Primary
public class UserBaseService implements UserService {
    @Autowired
    private IUserDao userDao;

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
    public PageInfo<User> getUsersByPage(int pageNumber, int usersPerPage) {
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

    @Transactional
    @Override
    public long registerUser(User user) {
        if (getUserByUserName(user.getUserName()) == null) {
            return userDao.createUser(user);
        }
        return -1;
    }
}
