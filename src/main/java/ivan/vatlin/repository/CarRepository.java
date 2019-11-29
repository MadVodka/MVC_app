package ivan.vatlin.repository;

import ivan.vatlin.dto.Car;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
}
