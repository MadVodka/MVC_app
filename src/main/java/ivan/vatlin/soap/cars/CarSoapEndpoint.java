package ivan.vatlin.soap.cars;

import ivan.vatlin.dao.jdbc.ICarDao;
import ivan.vatlin.dto.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CarSoapEndpoint {
    private static final String NAMESPACE = "https://www.ivan.vatlin/cars";

    @Autowired
    private ICarDao iCarDao;

    @PayloadRoot(namespace = NAMESPACE, localPart = "CarDetailsRequest")
    @ResponsePayload
    public CarDetailsResponse getCars(@RequestPayload CarDetailsRequest request) {
        List<Car> cars = null;
        Search search = request.getSearch();
        if (search != null) {
            if (search.by == SearchBy.BRAND) {
                cars = iCarDao.getCarsBySearch(search.value, "brand");
            } else if (search.by == SearchBy.MODEL) {
                cars = iCarDao.getCarsBySearch(search.value, "model");
            } else if (search.by == SearchBy.YEAR_MADE) {
                cars = iCarDao.getCarsBySearch(search.value, "year_made");
            }
        } else if (request.getAll() != null) {
            cars = iCarDao.getAllCars();
        }

        CarDetailsResponse carDetailsResponse = new CarDetailsResponse();
        carDetailsResponse.cars = cars;
        return carDetailsResponse;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "CarCreateRequest")
    @ResponsePayload
    public CarCreateResponse createCar(@RequestPayload CarCreateRequest request) {
        Car car = request.getCar();
        boolean result = iCarDao.createCar(car);

        CarCreateResponse carCreateResponse = new CarCreateResponse();
        carCreateResponse.setResult(result);
        return carCreateResponse;
    }
}
