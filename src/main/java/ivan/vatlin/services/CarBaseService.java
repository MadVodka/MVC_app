package ivan.vatlin.services;

import ivan.vatlin.dao.ICarDao;
import ivan.vatlin.dto.Car;
import ivan.vatlin.pagination.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CarBaseService implements CarService {
    @Autowired
    private ICarDao carDao;

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
    public PageInfo<Car> getCarPageInfo(int pageNumber, int carsPerPage) {
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
}
