package ivan.vatlin.dao;

import ivan.vatlin.dto.Car;
import ivan.vatlin.enums.CarStatus;

import java.util.List;

public interface CarDao {
    List<Car> getAllCars();

    Car getCarById(long id);

    Car getCarByRegNumber(String regNumber);

    List<Car> getCarsByStatus(CarStatus carStatus);

    List<Car> getCarsWithPriceEqual(double price);

    List<Car> getCarsWithPriceGreaterThan(double price);

    List<Car> getCarsWithPriceLessThan(double price);

    List<Car> getCarsByPage(int pageNumber, int carsPerPage);

    int getNumberOfCars();

    int addCar(Car car);

    int removeCar(long id);

    int updateCarPrice(long id, double price);
}
