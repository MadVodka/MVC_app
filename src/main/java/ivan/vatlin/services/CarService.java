package ivan.vatlin.services;

import ivan.vatlin.dto.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();

    Car getCarById(long id);

    List<Car> getCarsBySearch(String text, String searchByParam);

    List<Car> getCarsByPage(int pageNumber, int carsPerPage);

    long getNumberOfCars();
}
