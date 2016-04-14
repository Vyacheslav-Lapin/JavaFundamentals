package com.epam.courses.jf.xml.ws;

import com.epam.courses.jf.xml.ws.client.HelloService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.ws.Endpoint;

import static org.junit.Assert.assertEquals;

public class HelloTest {

    private static Endpoint endpoint;

    @BeforeClass
    public static void prepare() {
        endpoint = Endpoint.publish("http://localhost:1212/hello", new Hello());
    }

    @Test
    public void sayHello() throws Exception {
        assertEquals("Hello, John Doe", new Hello().sayHello("John Doe"));
    }

    @Test
    public void sayHelloRPC() throws Exception {
        com.epam.courses.jf.xml.ws.client.Hello hello = HelloService.getHello();
        String text = hello.sayHello("Henry");

        assertEquals("Hello, Henry", text);
    }

    @AfterClass
    public static void destroy() {
        endpoint.stop();
    }
}
