package ivan.vatlin.dao.jdbc;

import ivan.vatlin.dto.CarSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(value = "database.api", havingValue = "jdbc")
public class CarSpecificationDao implements ICarSpecificationDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<CarSpecification> carSpecificationRowMapper = ((resultSet, i) -> {
        CarSpecification carSpecification = new CarSpecification();

        carSpecification.setId(resultSet.getLong("id"))
                .setBrand(resultSet.getString("brand"))
                .setModel(resultSet.getString("model"))
                .setYearMade(resultSet.getShort("year_made"));

        return carSpecification;
    });

    @Override
    public List<CarSpecification> getAllCarSpecifications() {
        String sql = "select * from cars_specification";
        return jdbcTemplate.query(sql, carSpecificationRowMapper);
    }
}
