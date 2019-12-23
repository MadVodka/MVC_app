package ivan.vatlin.services;

import ivan.vatlin.dao.jdbc.ICarDao;
import ivan.vatlin.dto.Car;
import ivan.vatlin.pagination.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(value = "database.api", havingValue = "jdbc", matchIfMissing = true)
public class CarBaseService implements CarService {
    @Autowired
    private ICarDao carDao;

    @Value("${entity.per.page}")
    private int carsPerPage;

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
    public PageInfo<Car> getCarPageInfo(Integer pageNumber) {
        return getCarPageInfo(pageNumber, carsPerPage);
    }

    @Override
    public PageInfo<Car> getCarPageInfo(Integer pageNumber, int carsPerPage) {
        if (pageNumber == null) {
            pageNumber = 1;
        }

        int startPosition = (pageNumber - 1) * carsPerPage;
        List<Car> carsByPage = carDao.getCarsByPage(startPosition, carsPerPage);

        long numberOfUsers = getNumberOfCars();
        int numberOfPages = (int) Math.ceil(numberOfUsers * 1.0 / carsPerPage);
        return new PageInfo<>(carsByPage, numberOfPages);
    }

    @Override
    public long getNumberOfCars() {
        return carDao.getNumberOfCars();
    }

    @Override
    public boolean addCar(Car car) {
        Car resultCar = carDao.addCar(car);
        return resultCar != null;
    }
}
