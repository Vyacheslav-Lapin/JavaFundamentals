package com.epam.courses.jf.xml.ws.client;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.URL;

import static com.epam.courses.jf.common.functions.ExceptionalSupplier.take;

@WebServiceClient(name = "HelloService",
        targetNamespace = "http://epam.com/courses/jf/xml/ws",
        wsdlLocation = "http://localhost:1212/hello?wsdl")
public class HelloService extends Service {

    private static String targetNamespace;
    private static Hello hello;

    private HelloService(java.net.URL wsdlDocumentLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlDocumentLocation, serviceName, features);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns Hello
     */
    @WebEndpoint(name = "HelloPort")
    private Hello getHelloPort(WebServiceFeature... features) {

        String localPart = take(() -> HelloService.class.getDeclaredMethod("getHelloPort", WebServiceFeature[].class))
                .map(method -> method.getAnnotation(WebEndpoint.class).name(), e -> e).left();

        return getPort(new QName(targetNamespace, localPart), Hello.class, features);
    }

    public static Hello getHello() {

        final WebServiceClient webServiceClient = HelloService.class.getAnnotation(WebServiceClient.class);

        final URL helloServiceWsdlLocation = take(() -> new URL(webServiceClient.wsdlLocation()))
                .ifRight(WebServiceException::new)
                .left();

        targetNamespace = webServiceClient.targetNamespace();

        final QName helloServiceQName = new QName(targetNamespace, webServiceClient.name());

        return hello != null ? hello
                : (hello = new HelloService(helloServiceWsdlLocation, helloServiceQName).getHelloPort());
    }
}
