package ivan.vatlin.dao.jdbc;

import ivan.vatlin.dto.CarSpecification;

import java.util.List;

public interface ICarSpecificationDao {
    List<CarSpecification> getAllCarSpecifications();
}
