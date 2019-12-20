package ivan.vatlin.dao.jpa;

import ivan.vatlin.dto.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(value = "database.api", havingValue = "jpa")
public interface UserJpaDao extends PagingAndSortingRepository<User, Long> {
    List<User> findAll();

    User findByUserName(String userName);

    @Query("select u from User u where cast(u.id as string) like %:id%")
    List<User> findByIdContaining(@Param("id") Long id);

    List<User> findByUserNameContaining(String userName);

    List<User> findByFirstNameContaining(String firstName);

    List<User> findBySecondNameContaining(String secondName);

    boolean existsByUserName(String userName);
}
