package ivan.vatlin.dao.jpa;

import ivan.vatlin.dto.CarSpecification;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ConditionalOnProperty(value = "database.api", havingValue = "jpa")
public interface CarSpecificationJpaDao extends CrudRepository<CarSpecification, Long> {
    List<CarSpecification> findAll();
}
