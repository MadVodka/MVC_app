package ivan.vatlin.services;

import ivan.vatlin.dao.jpa.CarSpecificationJpaDao;
import ivan.vatlin.dto.CarSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(value = "database.api", havingValue = "jpa")
public class CarSpecificationRentService implements CarSpecificationService {
    @Autowired
    private CarSpecificationJpaDao carSpecificationJpaDao;

    @Override
    public List<CarSpecification> getAllCarSpecifications() {
        return carSpecificationJpaDao.findAll();
    }
}
