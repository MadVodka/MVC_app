package ivan.vatlin.services;

import ivan.vatlin.dto.User;
import ivan.vatlin.pagination.PageInfo;
import ivan.vatlin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("userJpa")
public class UserRentService implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public boolean userNameExist(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public List<User> getUsersBySearch(String text, String searchBy) {
        switch (searchBy) { // by ID field search should be added
            case "id":
                try {
                    Long id = Long.valueOf(text);
                    return userRepository.findByIdContaining(id);
                } catch (NumberFormatException e) {
                    return Collections.emptyList();
                }
            case "user_name":
                return userRepository.findByUserNameContaining(text);
            case "first_name":
                return userRepository.findByFirstNameContaining(text);
            case "second_name":
                return userRepository.findBySecondNameContaining(text);
            default:
                return Collections.emptyList();
        }
    }

    @Override
    public PageInfo<User> getUsersByPage(int pageNumber, int usersPerPage) {
        pageNumber--;
        Page<User> userPage = userRepository.findAll(PageRequest.of(pageNumber, usersPerPage));
        return new PageInfo<>(userPage.getContent(), userPage.getTotalPages());
    }

    @Override
    public long getNumberOfUsers() {
        return userRepository.count();
    }

    @Override
    public long registerUser(User user) {
        User saveUser = userRepository.save(user);
        if (saveUser == null) {
            return -1;
        }
        return 1;
    }
}
