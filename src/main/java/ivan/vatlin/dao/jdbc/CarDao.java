package ivan.vatlin.dao.jdbc;

import ivan.vatlin.dto.Car;
import ivan.vatlin.dto.CarSpecification;
import ivan.vatlin.enums.CarStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
@ConditionalOnProperty(value = "database.api", havingValue = "jdbc")
@CacheConfig(cacheNames = "cars")
public class CarDao implements ICarDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarDao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Car> carRowMapper = (ResultSet resultSet, int row) -> {
        Car car = new Car();
        CarSpecification carSpecification = new CarSpecification();

        carSpecification.setBrand(resultSet.getString("brand"))
                .setModel(resultSet.getString("model"))
                .setYearMade(resultSet.getShort("year_made"));

        car.setId(resultSet.getLong("id"))
                .setCarSpecification(carSpecification)
                .setPricePerDay(resultSet.getDouble("price_per_day"))
                .setRegistrationNumber(resultSet.getString("reg_number"))
                .setCarStatus(CarStatus.valueOf(resultSet.getString("status")));

        return car;
    };

    @Override
    @CachePut
    public List<Car> getAllCars() {
        LOGGER.info("getAllCars invoked");
        String sql = "select c.id, cs.brand, cs.model, cs.year_made, c.reg_number, c.price_per_day, c.status " +
                "from cars as c inner join cars_specification as cs on c.cars_spec_id = cs.id";
        return jdbcTemplate.query(sql, carRowMapper);
    }

    @Override
    @Cacheable
    public Car getCarById(long id) {
        LOGGER.info("getCarById with id {} invoked", id);
        String sql = "select c.id, cs.brand, cs.model, cs.year_made, c.reg_number, c.price_per_day, c.status " +
                "from cars as c inner join cars_specification as cs on c.cars_spec_id = cs.id where c.id = ?";
        return jdbcTemplate.queryForObject(sql, carRowMapper, id);
    }

    @Override
    public List<Car> getCarsByPage(int pageNumber, int carsPerPage) {
        String sql = "select c.id, cs.brand, cs.model, cs.year_made, c.reg_number, c.price_per_day, c.status " +
                "from cars as c inner join cars_specification as cs on c.cars_spec_id = cs.id limit ?, ?";
        return jdbcTemplate.query(sql, carRowMapper, pageNumber, carsPerPage);
    }

    @Override
    public List<Car> getCarsBySearch(String text, String searchByParam) {
        String textPattern = "%" + text + "%";
        String sql = "select c.id, cs.brand, cs.model, cs.year_made, c.reg_number, c.price_per_day, c.status " +
                "from cars as c inner join cars_specification as cs on c.cars_spec_id = cs.id where " + searchByParam + " like ?";
        return jdbcTemplate.query(sql, carRowMapper, textPattern);
    }

    @Override
    public int getNumberOfCars() {
        String sql = "select count(*) from cars";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    @CachePut(key = "#car.id")
    public Car createCar(Car car) {
        String sql = "insert into cars (price_per_day, reg_number, cars_spec_id) values (?, ?, ?)";
        int result;

        try {
            result = jdbcTemplate.update(sql, car.getPricePerDay(), car.getRegistrationNumber(), car.getCarSpecification().getId());
        } catch (Exception e) {
            return null;
        }

        return result > 0 ? car : null;
    }
}
