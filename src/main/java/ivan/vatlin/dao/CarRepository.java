package ivan.vatlin.dao;

import ivan.vatlin.dto.Car;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
}
