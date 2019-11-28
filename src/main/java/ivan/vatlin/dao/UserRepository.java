package ivan.vatlin.dao;

import ivan.vatlin.dto.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUserName(String userName);

    List<User> findByFirstNameLike(String firstName);

    List<User> findBySecondNameLike(String secondName);
}
