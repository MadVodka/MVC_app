package ivan.vatlin.dao.jdbc;

import ivan.vatlin.dto.User;
import ivan.vatlin.enums.UserRole;
import ivan.vatlin.enums.UserStatus;
import ivan.vatlin.exceptions.DaoUpdateFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@ConditionalOnProperty(value = "database.api", havingValue = "jdbc", matchIfMissing = true)
@ConditionalOnBean(JdbcTemplate.class)
@Repository
public class UserDao implements IUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
        User user = new User();

        user.setId(resultSet.getLong("id"))
                .setUserName(resultSet.getString("user_name"))
                .setPassword(resultSet.getString("password"))
                .setFirstName(resultSet.getString("first_name"))
                .setSecondName(resultSet.getString("second_Name"))
                .setUserRole(UserRole.valueOf(resultSet.getString("role")))
                .setUserStatus(UserStatus.valueOf(resultSet.getString("status")));

        return user;
    };

    @Override
    public List<User> getAllUsers() {
        String sql = "select * from users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public User getUserById(long id) {
        String sql = "select * from users where id = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, id);
    }

    @Override
    public User getUserByUserName(String userName) {
        String sql = "select * from users where user_name = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, userName);
    }

    @Override
    public long getUserIdByUserName(String userName) {
        String sql = "select id from users where user_name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Long.TYPE, userName);
        } catch (NullPointerException e) {
            return -1;
        }
    }

    public List<User> getUsersByRole(UserRole userRole) {
        String sql = "select * from users where role = ?";
        return jdbcTemplate.query(sql, userRowMapper, userRole.toString());
    }

    @Override
    public List<User> getUsersBySearch(String text, String searchByParam) {
        String textPattern = "%" + text + "%";
        String sql = "select * from users where " + searchByParam + " like ?";
        return jdbcTemplate.query(sql, userRowMapper, textPattern);
    }

    @Override
    public List<User> getUsersByPage(int pageNumber, int usersPerPage) {
        String sql = "select * from users limit ?, ?";
        return jdbcTemplate.query(sql, userRowMapper, pageNumber, usersPerPage);
    }

    @Override
    public int getNumberOfUsers() {
        String sql = "select count(*) from users";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int createUser(User user) throws DaoUpdateFailException {
        String sql = "insert users (user_name, password, first_name, second_name, role) values (?, ?, ?, ?, ?)";
        int updateResult = jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getFirstName(),
                user.getSecondName(), user.getUserRole().toString());
        if (updateResult < 1) {
            throw new DaoUpdateFailException();
        }
        return updateResult;
    }
}
