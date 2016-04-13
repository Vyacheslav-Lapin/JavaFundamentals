package com.epam.courses.jf.xml.ws.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Action;

@WebService(name = "Hello", targetNamespace = "http://epam.com/courses/jf/xml/ws")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Hello {

    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://epam.com/courses/jf/xml/ws/Hello/sayHelloRequest", output = "http://epam.com/courses/jf/xml/ws/Hello/sayHelloResponse")
    String sayHello( @WebParam(name = "name", partName = "name") String name);
}
