package com.epam.courses.jf.xml.ws.client;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;

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
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.
     *     Supported features not in the <code>features</code> parameter will have their default values.
     */
    @WebEndpoint(name = "HelloPort")
    private Hello getHelloPort(WebServiceFeature... features) {
        return getPort(new QName(targetNamespace, getLocalPart()), Hello.class, features);
    }

    private String getLocalPart() {
        try {
            return HelloService.class.getDeclaredMethod("getHelloPort", WebServiceFeature[].class)
                    .getAnnotation(WebEndpoint.class).name();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static Hello getHello() {

        final WebServiceClient webServiceClient = HelloService.class.getAnnotation(WebServiceClient.class);

        targetNamespace = webServiceClient.targetNamespace();
        final QName helloServiceQName = new QName(targetNamespace, webServiceClient.name());
        final URL helloServiceWsdlLocation = getHelloServiceWsdlLocation(webServiceClient.wsdlLocation());
        return new HelloService(helloServiceWsdlLocation, helloServiceQName).getHelloPort();
    }

    private static URL getHelloServiceWsdlLocation(String wsdlLocation) {
        try {
            return new URL(wsdlLocation);
        } catch (MalformedURLException ex) {
            throw new WebServiceException(ex);
        }
    }
}
