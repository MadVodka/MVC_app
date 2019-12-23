package ivan.vatlin.services;

import ivan.vatlin.dao.jdbc.ICarSpecificationDao;
import ivan.vatlin.dto.CarSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(value = "database.api", havingValue = "jdbc", matchIfMissing = true)
public class CarSpecificationBaseService implements CarSpecificationService{
    @Autowired
    private ICarSpecificationDao iCarSpecificationDao;

    @Override
    public List<CarSpecification> getAllCarSpecifications() {
        return iCarSpecificationDao.getAllCarSpecifications();
    }
}
