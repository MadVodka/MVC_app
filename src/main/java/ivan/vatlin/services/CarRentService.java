package ivan.vatlin.services;

import ivan.vatlin.dto.Car;
import ivan.vatlin.pagination.PageInfo;
import ivan.vatlin.dao.jpa.CarJpaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@ConditionalOnProperty(value = "database.api", havingValue = "jpa")
public class CarRentService implements CarService {
    @Autowired
    private CarJpaDao carJpaDao;

    @Override
    public List<Car> getAllCars() {
        return carJpaDao.findAll();
    }

    @Override
    public Car getCarById(long id) {
        return carJpaDao.findById(id).orElse(null);
    }

    @Override
    public List<Car> getCarsBySearch(String text, String searchByParam) {
        switch (searchByParam) {
            case "brand":
                return carJpaDao.findByCarSpecificationBrandContaining(text);
            case "model":
                return carJpaDao.findByCarSpecificationModelContaining(text);
            case "year_made":
                try {
                    Integer year = Integer.valueOf(text);
                    return carJpaDao.findByCarSpecificationYearMadeContaining(year);
                } catch (NumberFormatException e) {
                    return Collections.emptyList();
                }
            default:
                return Collections.emptyList();
        }
    }

    @Override
    public PageInfo<Car> getCarPageInfo(int pageNumber, int carsPerPage) {
        pageNumber--;
        Page<Car> carPage = carJpaDao.findAll(PageRequest.of(pageNumber, carsPerPage));
        return new PageInfo<>(carPage.getContent(), carPage.getTotalPages());
    }

    @Override
    public long getNumberOfCars() {
        return carJpaDao.count();
    }
}
