package ivan.vatlin.soap.car_specification;

import ivan.vatlin.dto.CarSpecification;
import ivan.vatlin.services.CarSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CarSpecificationSoapEndpoint {
    private static final String NAMESPACE = "https://www.ivan.vatlin/car_specification";

    @Autowired
    private CarSpecificationService carSpecificationService;

    @PayloadRoot(namespace = NAMESPACE, localPart = "CarSpecificationDetailsRequest")
    @ResponsePayload
    public CarSpecificationDetailsResponse getCarSpecifications(@RequestPayload CarSpecificationDetailsRequest request) {
        List<CarSpecification> carSpecifications = null;
        if (request.getAll() != null) {
            carSpecifications = carSpecificationService.getAllCarSpecifications();
        }

        CarSpecificationDetailsResponse carSpecificationDetailsResponse = new CarSpecificationDetailsResponse();
        carSpecificationDetailsResponse.setCarSpecifications(carSpecifications);
        return carSpecificationDetailsResponse;
    }
}
