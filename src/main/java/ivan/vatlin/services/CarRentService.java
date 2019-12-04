package ivan.vatlin.services;

import ivan.vatlin.dto.Car;
import ivan.vatlin.pagination.PageInfo;
import ivan.vatlin.repositories.CarRepository;
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
    private CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public List<Car> getCarsBySearch(String text, String searchByParam) {
        switch (searchByParam) {
            case "brand":
                return carRepository.findByCarSpecificationBrandContaining(text);
            case "model":
                return carRepository.findByCarSpecificationModelContaining(text);
            case "year_made":
                try {
                    Integer year = Integer.valueOf(text);
                    return carRepository.findByCarSpecificationYearMadeContaining(year);
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
        Page<Car> carPage = carRepository.findAll(PageRequest.of(pageNumber, carsPerPage));
        return new PageInfo<>(carPage.getContent(), carPage.getTotalPages());
    }

    @Override
    public long getNumberOfCars() {
        return carRepository.count();
    }
}
