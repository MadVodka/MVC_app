package ivan.vatlin.dao;

import ivan.vatlin.dto.CarSpecification;
import ivan.vatlin.mappers.CarSpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarSpecificationDaoImpl implements CarSpecificationDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CarSpecification> getCarSpecifications() {
        String sql = "select * from cars_specification";
        return jdbcTemplate.query(sql, new CarSpecificationMapper());
    }

    public CarSpecification getCarSpecificationById(long id) {
        String sql = "select * from cars_specification where id = ?";
        return jdbcTemplate.queryForObject(sql, new CarSpecificationMapper(), id);
    }

    public CarSpecification getCarSpecificationByWholeInfo(CarSpecification carSpecification) {
        String sql = "select * from cars_specification where brand = ? and model = ? and year_made = ?";
        return jdbcTemplate.queryForObject(sql, new CarSpecificationMapper(),
                carSpecification.getBrand(), carSpecification.getModel(), carSpecification.getYearMade());
    }

    public List<CarSpecification> getCarSpecificationByBrand(String brand) {
        String sql = "select * from cars_specification where brand = ?";
        return jdbcTemplate.query(sql, new CarSpecificationMapper(), brand);
    }

    public List<CarSpecification> getCarSpecificationByYear(int year) {
        String sql = "select * from cars_specification where year_made = ?";
        return jdbcTemplate.query(sql, new CarSpecificationMapper(), year);
    }

    public int createCarSpecification(CarSpecification carSpecification) {
        String sql = "insert into cars_specification (brand, model, year_made) values (?, ?, ?)";
        return jdbcTemplate.update(sql, carSpecification.getBrand(),
                carSpecification.getModel(), carSpecification.getYearMade());
    }

    public int deleteCarSpecification(long id) {
        String sql = "delete from cars_specification where id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
