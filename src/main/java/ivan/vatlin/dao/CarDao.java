package ivan.vatlin.dao;

import ivan.vatlin.dto.Car;
import ivan.vatlin.mappers.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Car> getAllCars() {
        String sql = "select c.id, cs.brand, cs.model, cs.year_made, c.reg_number, c.price_per_day, c.status " +
                "from cars as c inner join cars_specification as cs on c.cars_spec_id = cs.id";
        return jdbcTemplate.query(sql, new CarMapper());
    }

    public Car getCarById(long id) {
        String sql = "select c.id, cs.brand, cs.model, cs.year_made, c.reg_number, c.price_per_day, c.status " +
                "from cars as c inner join cars_specification as cs on c.cars_spec_id = cs.id where c.id = ?";
        return jdbcTemplate.queryForObject(sql, new CarMapper(), id);
    }

    public List<Car> getCarsByPage(int pageNumber, int carsPerPage) {
        String sql = "select c.id, cs.brand, cs.model, cs.year_made, c.reg_number, c.price_per_day, c.status " +
                "from cars as c inner join cars_specification as cs on c.cars_spec_id = cs.id limit ?, ?";
        return jdbcTemplate.query(sql, new CarMapper(), pageNumber, carsPerPage);
    }

    public List<Car> getCarsBySearch(String text, String searchByParam) {
        String textPattern = "%" + text + "%";
        String sql = "select c.id, cs.brand, cs.model, cs.year_made, c.reg_number, c.price_per_day, c.status " +
                "from cars as c inner join cars_specification as cs on c.cars_spec_id = cs.id where " + searchByParam + " like ?";
        return jdbcTemplate.query(sql, new CarMapper(), textPattern);
    }

    public int getNumberOfCars() {
        String sql = "select count(*) from cars";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
