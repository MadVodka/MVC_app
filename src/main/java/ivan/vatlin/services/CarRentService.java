package ivan.vatlin.services;

import ivan.vatlin.dto.Car;
import ivan.vatlin.pagination.PageInfo;
import ivan.vatlin.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("carJpa")
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
                return carRepository.findByCarSpecificationBrandLike(text);
            case "model":
                return carRepository.findByCarSpecificationModelLike(text);
            case "year_made":
                return carRepository.findByCarSpecificationYearMadeLike(text);
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
