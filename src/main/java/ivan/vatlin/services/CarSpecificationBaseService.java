package ivan.vatlin.services;

import ivan.vatlin.dao.CarSpecificationDao;
import ivan.vatlin.dto.CarSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarSpecificationBaseService {
    @Autowired
    private CarSpecificationDao carSpecificationDao;

    public List<CarSpecification> getCarSpecifications() {
        return carSpecificationDao.getCarSpecifications();
    }

    public CarSpecification getCarSpecificationById(long id) {
        try {
            return carSpecificationDao.getCarSpecificationById(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public CarSpecification getCarSpecificationByWholeInfo(CarSpecification carSpecification) {
        try {
            return carSpecificationDao.getCarSpecificationByWholeInfo(carSpecification);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<CarSpecification> getCarSpecificationByBrand(String brand) {
        return carSpecificationDao.getCarSpecificationByBrand(brand);
    }

    public List<CarSpecification> getCarSpecificationByYear(int year) {
        return carSpecificationDao.getCarSpecificationByYear(year);
    }

    public int createCarSpecification(CarSpecification carSpecification) {
        if (getCarSpecificationByWholeInfo(carSpecification) == null) {
            return carSpecificationDao.createCarSpecification(carSpecification);
        }
        return -1;
    }

    public int deleteCarSpecification(long id) {
        return carSpecificationDao.deleteCarSpecification(id);
    }
}
