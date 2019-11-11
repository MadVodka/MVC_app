package ivan.vatlin.services;

import ivan.vatlin.dao.CarDao;
import ivan.vatlin.dto.Car;
import ivan.vatlin.enums.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarDao carDao;

    @Autowired
    private CarSpecificationService carSpecificationService;

    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    public Car getCarById(long id) {
        try {
            return carDao.getCarById(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Car getCarByRegNumber(String regNumber) {
        try {
            return carDao.getCarByRegNumber(regNumber);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Car> getCarsInUse() {
        return carDao.getCarsByStatus(CarStatus.IN_USE);
    }

    public List<Car> getCarsInMaintenance() {
        return carDao.getCarsByStatus(CarStatus.IN_MAINTAINANCE);
    }

    public List<Car> getCarsWithPriceEqual(double price) {
        return carDao.getCarsWithPriceEqual(price);
    }

    public List<Car> getCarsWithPriceGreaterThan(double price) {
        return carDao.getCarsWithPriceGreaterThan(price);
    }

    public List<Car> getCarsWithPriceLessThan(double price) {
        return carDao.getCarsWithPriceLessThan(price);
    }

    public List<Car> getCarsBySearch(String text, String searchByParam) {
        return carDao.getCarsBySearch(text, searchByParam);
    }

    public List<Car> getCarsByPage(int pageNumber, int carsPerPage) {
        int startPosition = (pageNumber - 1) * carsPerPage;
        return carDao.getCarsByPage(startPosition, carsPerPage);
    }

    public int getNumberOfCars() {
        return carDao.getNumberOfCars();
    }

    /**
     * @param car
     * @return -1 registration number not unique,
     * -2 no such car specification
     */
    public int addCar(Car car) {
        if (getCarByRegNumber(car.getRegistrationNumber()) == null) {
            if (carSpecificationService.getCarSpecificationById(car.getCarSpecification().getId()) != null) {
                return carDao.addCar(car);
            }
            return -2;
        }
        return -1;
    }

    public int removeCar(long id) {
        return carDao.removeCar(id);
    }

    public int updateCarPrice(long id, double price) {
        return carDao.updateCarPrice(id, price);
    }
}
