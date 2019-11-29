package ivan.vatlin.repository;

import ivan.vatlin.dto.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    List<User> findAll();

    User findByUserName(String userName);

    List<User> findByUserNameLike(String userName);

    List<User> findByFirstNameLike(String firstName);

    List<User> findBySecondNameLike(String secondName);

    boolean existsByUserName(String userName);
}
