package ivan.vatlin.dao;

import ivan.vatlin.dto.Car;
import ivan.vatlin.mappers.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(value = "database.api", havingValue = "jdbc")
public class CarDao implements ICarDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Car> getAllCars() {
        String sql = "select c.id, cs.brand, cs.model, cs.year_made, c.reg_number, c.price_per_day, c.status " +
                "from cars as c inner join cars_specification as cs on c.cars_spec_id = cs.id";
        return jdbcTemplate.query(sql, new CarMapper());
    }

    @Override
    public Car getCarById(long id) {
        String sql = "select c.id, cs.brand, cs.model, cs.year_made, c.reg_number, c.price_per_day, c.status " +
                "from cars as c inner join cars_specification as cs on c.cars_spec_id = cs.id where c.id = ?";
        return jdbcTemplate.queryForObject(sql, new CarMapper(), id);
    }

    @Override
    public List<Car> getCarsByPage(int pageNumber, int carsPerPage) {
        String sql = "select c.id, cs.brand, cs.model, cs.year_made, c.reg_number, c.price_per_day, c.status " +
                "from cars as c inner join cars_specification as cs on c.cars_spec_id = cs.id limit ?, ?";
        return jdbcTemplate.query(sql, new CarMapper(), pageNumber, carsPerPage);
    }

    @Override
    public List<Car> getCarsBySearch(String text, String searchByParam) {
        String textPattern = "%" + text + "%";
        String sql = "select c.id, cs.brand, cs.model, cs.year_made, c.reg_number, c.price_per_day, c.status " +
                "from cars as c inner join cars_specification as cs on c.cars_spec_id = cs.id where " + searchByParam + " like ?";
        return jdbcTemplate.query(sql, new CarMapper(), textPattern);
    }

    @Override
    public int getNumberOfCars() {
        String sql = "select count(*) from cars";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
