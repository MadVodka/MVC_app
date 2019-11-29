package ivan.vatlin.services;

import ivan.vatlin.dao.CarDao;
import ivan.vatlin.dto.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarBaseService implements CarService{
    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    @Override
    public Car getCarById(long id) {
        try {
            return carDao.getCarById(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Car> getCarsBySearch(String text, String searchByParam) {
        return carDao.getCarsBySearch(text, searchByParam);
    }

    @Override
    public List<Car> getCarsByPage(int pageNumber, int carsPerPage) {
        int startPosition = (pageNumber - 1) * carsPerPage;
        return carDao.getCarsByPage(startPosition, carsPerPage);
    }

    @Override
    public int getNumberOfCars() {
        return carDao.getNumberOfCars();
    }
}
