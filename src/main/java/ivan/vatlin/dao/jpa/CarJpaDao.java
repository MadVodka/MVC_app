package ivan.vatlin.dao.jpa;

import ivan.vatlin.dto.Car;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(value = "database.api", havingValue = "jpa")
public interface CarJpaDao extends PagingAndSortingRepository<Car, Long> {
    List<Car> findAll();

    List<Car> findByCarSpecificationBrandContaining(String brand);

    List<Car> findByCarSpecificationModelContaining(String model);

    @Query("select c from Car c where cast(c.carSpecification.yearMade as string) like %:yearMade%")
    List<Car> findByCarSpecificationYearMadeContaining(@Param("yearMade") Integer yearMade);
}
