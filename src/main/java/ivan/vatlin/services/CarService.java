package ivan.vatlin.services;

import ivan.vatlin.dto.Car;
import ivan.vatlin.pagination.PageInfo;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();

    Car getCarById(long id);

    List<Car> getCarsBySearch(String text, String searchByParam);

    PageInfo<Car> getCarPageInfo(Integer pageNumber);

    PageInfo<Car> getCarPageInfo(Integer pageNumber, int carsPerPage);

    long getNumberOfCars();

    boolean addCar(Car car);
}
