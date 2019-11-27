package ivan.vatlin.dao;

import ivan.vatlin.dto.CarSpecification;

import java.util.List;

public interface ICarSpecificationDao {
    List<CarSpecification> getCarSpecifications();

    CarSpecification getCarSpecificationById(long id);

    CarSpecification getCarSpecificationByWholeInfo(CarSpecification carSpecification);

    List<CarSpecification> getCarSpecificationByBrand(String brand);

    List<CarSpecification> getCarSpecificationByYear(int year);

    int createCarSpecification(CarSpecification carSpecification);

    int deleteCarSpecification(long id);
}
