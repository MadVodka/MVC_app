package ivan.vatlin.repositories;

import ivan.vatlin.dto.Car;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
    List<Car> findAll();

    List<Car> findByCarSpecificationBrandLike(String brand);

    List<Car> findByCarSpecificationModelLike(String model);

    List<Car> findByCarSpecificationYearMadeLike(String yearMade);
}
