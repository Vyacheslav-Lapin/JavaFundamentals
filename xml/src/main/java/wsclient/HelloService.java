package wsclient;

import com.hegel.core.functions.ExceptionalBiFunction;
import com.hegel.core.functions.ExceptionalFunction;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.URL;

@WebServiceClient(name = "HelloService",
        targetNamespace = "http://ws.xml.jf.courses.epam.com/",
        wsdlLocation = "http://localhost:1212/hello?wsdl")
public class HelloService extends Service {

    private static final WebServiceClient WEB_SERVICE_CLIENT_ANNOTATION = HelloService.class.getDeclaredAnnotation(WebServiceClient.class);
    private static final String TARGET_NAMESPACE = WEB_SERVICE_CLIENT_ANNOTATION.targetNamespace();
    private static final String LOCAL_NAME = WEB_SERVICE_CLIENT_ANNOTATION.name();
    private static final String WSDL_LOCATION = WEB_SERVICE_CLIENT_ANNOTATION.wsdlLocation();

    private static final String ENDPOINT_NAME = ExceptionalBiFunction.getOrThrowUnchecked(
            HelloService.class::getDeclaredMethod, "getHelloPort", WebServiceFeature[].class
    ).getDeclaredAnnotation(WebEndpoint.class).name();

    private static final URL HELLOSERVICE_WSDL_LOCATION = ExceptionalFunction.getOrThrowUnchecked(URL::new, WSDL_LOCATION);
    private static final QName HELLOSERVICE_QNAME = new QName(TARGET_NAMESPACE, LOCAL_NAME);

    HelloService(WebServiceFeature... features) {
        super(HELLOSERVICE_WSDL_LOCATION, HELLOSERVICE_QNAME, features);
    }

    @WebEndpoint(name = "HelloPort")
    Hello getHelloPort(WebServiceFeature... features) {
        return getPort(new QName(TARGET_NAMESPACE, ENDPOINT_NAME), Hello.class, features);
    }
}
