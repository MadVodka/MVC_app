package ivan.vatlin.repositories;

import ivan.vatlin.dto.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @Query("select u from User u")
    List<User> findAll();

    User findByUserName(String userName);

    // write universal method for search with @Query
//    @Query("SELECT f FROM Foo f WHERE LOWER(f.name) = LOWER(:name)")
//    Foo retrieveByName(@Param("name") String name);

    List<User> findByUserNameLike(String userName);

    List<User> findByFirstNameLike(String firstName);

    List<User> findBySecondNameLike(String secondName);

    boolean existsByUserName(String userName);
}
