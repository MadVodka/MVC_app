package ivan.vatlin.services;

import ivan.vatlin.dto.User;
import ivan.vatlin.pagination.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(long id);

    User getUserByUserName(String userName);

    boolean userNameExist(String userName);

    List<User> getUsersBySearch(String text, String searchBy);

    PageInfo<User> getUsersByPage(int pageNumber, int usersPerPage);

    long getNumberOfUsers();

    @Transactional
    long registerUser(User user);
}
