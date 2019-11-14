package ivan.vatlin.dao;

import ivan.vatlin.dto.User;
import ivan.vatlin.enums.UserRole;
import ivan.vatlin.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        String sql = "select * from users";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public User getUserById(long id) {
        String sql = "select * from users where id = ?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
    }

    public User getUserByUserName(String userName) {
        String sql = "select * from users where user_name = ?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), userName);
    }

    public List<User> getUsersByRole(UserRole userRole) {
        String sql = "select * from users where role = ?";
        return jdbcTemplate.query(sql, new UserMapper(), userRole.toString());
    }

    public List<User> getUsersBySearch(String text, String searchByParam) {
        String textPattern = "%" + text + "%";
        String sql = "select * from users where " + searchByParam + " like ?";
        return jdbcTemplate.query(sql, new UserMapper(), textPattern);
    }

    public List<User> getUsersByPage(int pageNumber, int usersPerPage) {
        String sql = "select * from users limit ?, ?";
        return jdbcTemplate.query(sql, new UserMapper(), pageNumber, usersPerPage);
    }

    public int getNumberOfUsers() {
        String sql = "select count(*) from users";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int createUser(User user) {
        String sql = "insert users (user_name, password, first_name, second_name, role) values (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getFirstName(),
                user.getSecondName(), user.getUserRole().toString());
    }
}
