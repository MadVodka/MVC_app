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
        List<Car> cars = iCarDao.getCarsBySearch(request.getBrand(), "brand");

        CarDetailsResponse carDetailsResponse = new CarDetailsResponse();
        carDetailsResponse.setCars(cars);
        return carDetailsResponse;
    }
}
