package com.epam.courses.jf.xml.ws;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.ws.Endpoint;

import static com.epam.courses.jf.xml.ws.client.HelloService.getHello;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HelloTest {

    private static Endpoint endpoint;
    private static com.epam.courses.jf.xml.ws.client.Hello hello;

    @BeforeClass
    public static void prepare() {
        endpoint = Endpoint.publish("http://localhost:1212/hello", new Hello());
        hello = getHello();
    }

    @Test
    public void sayHello() throws Exception {
        assertThat(new Hello().sayHello("John Doe"), is("Hello, John Doe"));
    }

    @Test
    public void sayHelloRPC() throws Exception {
        assertThat(hello.sayHello("Henry"), is("Hello, Henry"));
    }

    @AfterClass
    public static void destroy() {
        endpoint.stop();
    }
}
