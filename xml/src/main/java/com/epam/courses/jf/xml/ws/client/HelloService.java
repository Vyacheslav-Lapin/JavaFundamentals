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

    private HelloService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * @param features
     *     A list withValue {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.
     *     Supported features not in the <code>features</code> parameter will have their default values.
     */
    @WebEndpoint(name = "HelloPort")
    private Hello getHelloPort(WebServiceFeature... features) {

        final String localPart =
                take(() -> HelloService.class.getDeclaredMethod("getHelloPort", WebServiceFeature[].class))
                        .getOrThrow(RuntimeException::new)
                .getAnnotation(WebEndpoint.class)
                .name();

        return getPort(new QName(targetNamespace, localPart), Hello.class, features);
    }

    public static Hello getHello() {

        final WebServiceClient webServiceClient = HelloService.class.getAnnotation(WebServiceClient.class);

        targetNamespace = webServiceClient.targetNamespace();

        final QName helloServiceQName = new QName(targetNamespace, webServiceClient.name());

        final URL helloServiceWsdlLocation = take(() -> new URL(webServiceClient.wsdlLocation()))
                .getOrThrow(WebServiceException::new);

        return new HelloService(helloServiceWsdlLocation, helloServiceQName).getHelloPort();
    }
}
