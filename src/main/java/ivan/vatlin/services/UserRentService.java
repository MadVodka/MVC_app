package ivan.vatlin.services;

import ivan.vatlin.dto.User;
import ivan.vatlin.pagination.PageInfo;
import ivan.vatlin.dao.jpa.UserJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@ConditionalOnProperty(value = "database.api", havingValue = "jpa")
public class UserRentService implements UserService {
    @Autowired
    private UserJpaDao userJpaDao;

    @Override
    public List<User> getAllUsers() {
        return userJpaDao.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userJpaDao.findById(id).orElse(null);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userJpaDao.findByUserName(userName);
    }

    @Override
    public boolean userNameExist(String userName) {
        return userJpaDao.existsByUserName(userName);
    }

    @Override
    public List<User> getUsersBySearch(String text, String searchBy) {
        switch (searchBy) { // by ID field search should be added
            case "id":
                try {
                    Long id = Long.valueOf(text);
                    return userJpaDao.findByIdContaining(id);
                } catch (NumberFormatException e) {
                    return Collections.emptyList();
                }
            case "user_name":
                return userJpaDao.findByUserNameContaining(text);
            case "first_name":
                return userJpaDao.findByFirstNameContaining(text);
            case "second_name":
                return userJpaDao.findBySecondNameContaining(text);
            default:
                return Collections.emptyList();
        }
    }

    @Override
    public PageInfo<User> getUsersByPage(int pageNumber, int usersPerPage) {
        pageNumber--;
        Page<User> userPage = userJpaDao.findAll(PageRequest.of(pageNumber, usersPerPage));
        return new PageInfo<>(userPage.getContent(), userPage.getTotalPages());
    }

    @Override
    public long getNumberOfUsers() {
        return userJpaDao.count();
    }

    @Override
    public long registerUser(User user) {
        User saveUser = userJpaDao.save(user);
        if (saveUser == null) {
            return -1;
        }
        return 1;
    }
}
