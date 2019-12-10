package ivan.vatlin.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
@ComponentScan("ivan.vatlin.soap.cars")
public class SoapConfig extends WsConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SoapConfig.class);

        private static final String NAMESPACE = "https://www.ivan.vatlin/cars";

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
        wsdl11Definition.setLocationUri("/soap/cars");
        wsdl11Definition.setTargetNamespace(NAMESPACE);
        wsdl11Definition.setSchema(carsSchema);

        LOGGER.info("defaultWsdl11Definition initialized");

        return wsdl11Definition;
    }

    @Bean
    public XsdSchema carsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("cars.xsd"));
    }
}
