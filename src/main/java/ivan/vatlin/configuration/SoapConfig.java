package ivan.vatlin.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapConfig extends WsConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SoapConfig.class);

    private static final String NAMESPACE_CARS = "https://www.ivan.vatlin/cars";
    private static final String NAMESPACE_CAR_SPECIFICATION = "https://www.ivan.vatlin/car_specification";

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);

        LOGGER.info("messageDispatcherServlet initialized");

        return new ServletRegistrationBean(servlet, "/soap/*");
    }

    @Bean(name = "cars")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema carsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CarDetailsPort");
        wsdl11Definition.setLocationUri("/soap");
        wsdl11Definition.setTargetNamespace(NAMESPACE_CARS);
        wsdl11Definition.setSchema(carsSchema);

        LOGGER.info("defaultWsdl11Definition initialized");

        return wsdl11Definition;
    }

    @Bean
    public XsdSchema carsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("cars.xsd"));
    }

    @Bean(name = "carSpecification")
    public DefaultWsdl11Definition defaultWsdl11DefinitionCarSpecification(XsdSchema carSpecificationSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CarSpecificationDetailsPort");
        wsdl11Definition.setLocationUri("/soap");
        wsdl11Definition.setTargetNamespace(NAMESPACE_CAR_SPECIFICATION);
        wsdl11Definition.setSchema(carSpecificationSchema);

        LOGGER.info("defaultWsdl11DefinitionCarSpecification initialized");

        return wsdl11Definition;
    }

    @Bean
    public XsdSchema carSpecificationSchema() {
        return new SimpleXsdSchema(new ClassPathResource("car_specification.xsd"));
    }
}
