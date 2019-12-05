package ivan.vatlin.dao.jdbc;

import ivan.vatlin.dto.Car;

import java.util.List;

public interface ICarDao {
    List<Car> getAllCars();

    Car getCarById(long id);

    List<Car> getCarsByPage(int pageNumber, int carsPerPage);

    List<Car> getCarsBySearch(String text, String searchByParam);

    int getNumberOfCars();
}
