package ivan.vatlin.soap.cars;

import ivan.vatlin.dto.Car;
import ivan.vatlin.services.CarService;
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
    private CarService carService;

    @PayloadRoot(namespace = NAMESPACE, localPart = "CarDetailsRequest")
    @ResponsePayload
    public CarDetailsResponse getCars(@RequestPayload CarDetailsRequest request) {
        List<Car> cars = null;
        Search search = request.getSearch();
        if (search != null) {
            if (search.by == SearchBy.YEAR_MADE) {
                cars = carService.getCarsBySearch(search.value, "year_made");
            } else {
                cars = carService.getCarsBySearch(search.value, search.by.value());
            }
        } else if (request.getAll() != null) {
            cars = carService.getAllCars();
        }

        CarDetailsResponse carDetailsResponse = new CarDetailsResponse();
        carDetailsResponse.cars = cars;
        return carDetailsResponse;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "CarCreateRequest")
    @ResponsePayload
    public CarCreateResponse createCar(@RequestPayload CarCreateRequest request) {
        Car car = request.getCar();
        boolean result = carService.addCar(car);

        CarCreateResponse carCreateResponse = new CarCreateResponse();
        carCreateResponse.setResult(result);
        return carCreateResponse;
    }
}
