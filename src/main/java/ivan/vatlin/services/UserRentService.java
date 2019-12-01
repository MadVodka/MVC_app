package ivan.vatlin.services;

import ivan.vatlin.dto.User;
import ivan.vatlin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service()
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
            case "user_name":
                return userRepository.findByUserNameLike(text);
            case "first_name":
                return userRepository.findByFirstNameLike(text);
            case "second_name":
                return userRepository.findBySecondNameLike(text);
            default:
                return Collections.emptyList();
        }
    }

    @Override
    public List<User> getUsersByPage(int pageNumber, int usersPerPage) {
        return userRepository.findAll(PageRequest.of(pageNumber, usersPerPage)).getContent();
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
