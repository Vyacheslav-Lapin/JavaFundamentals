package com.epam.courses.jf.xml.ws;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import static javax.jws.soap.SOAPBinding.Style.RPC;

@WebService(targetNamespace = "http://epam.com/courses/jf/xml/ws")
@SOAPBinding(style = RPC)
public class Hello {
    public String sayHello(@WebParam(name = "name") String name) {
        return "Hello, " + name;
    }
}
