package com.epam.courses.jf.xml.ws;

import com.epam.courses.jf.xml.ws.wsclient.HelloService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.ws.Endpoint;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HelloTest {

    private Endpoint endpoint;

    @Before
    public void sayHello() throws Exception {
        String url = "http://localhost:1234/hello";
        endpoint = Endpoint.publish(url, new Hello());
        System.out.println("Service started @ " + url + "?wsdl");
    }

    @Test
    public void aVoid() {
        final HelloService helloService = new HelloService();
        final com.epam.courses.jf.xml.ws.wsclient.Hello hello = helloService.getHelloPort();
        assertThat(hello.sayHello("Henry"), is("Hello, Henry"));
    }

    @After
    public void destroy() {
        endpoint.stop();
    }
}
