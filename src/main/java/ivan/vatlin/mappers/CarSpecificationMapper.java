package ivan.vatlin.mappers;

import ivan.vatlin.dto.CarSpecification;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarSpecificationMapper implements RowMapper<CarSpecification> {
    public CarSpecification mapRow(ResultSet resultSet, int i) throws SQLException {
        CarSpecification carSpecification = new CarSpecification();

        carSpecification.setId(resultSet.getLong("id"))
                .setBrand(resultSet.getString("brand"))
                .setModel(resultSet.getString("model"))
                .setYearMade(resultSet.getShort("year_made"));

        return carSpecification;
    }
}
