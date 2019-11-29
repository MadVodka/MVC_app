package ivan.vatlin.services;

import ivan.vatlin.dto.Car;

import java.util.List;

public class CarRentService implements CarService {
    @Override
    public List<Car> getAllCars() {
        return null;
    }

    @Override
    public Car getCarById(long id) {
        return null;
    }

    @Override
    public List<Car> getCarsBySearch(String text, String searchByParam) {
        return null;
    }

    @Override
    public List<Car> getCarsByPage(int pageNumber, int carsPerPage) {
        return null;
    }

    @Override
    public int getNumberOfCars() {
        return 0;
    }
}
