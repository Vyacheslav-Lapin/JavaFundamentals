package com.epam.courses.jf.xml.ws;

import com.epam.courses.jf.xml.ws.client.HelloService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.ws.Endpoint;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HelloTest {

    private Endpoint publish;
    private com.epam.courses.jf.xml.ws.client.Hello hello;

    @Before
    public void prepare() {
        publish = Endpoint.publish("http://localhost:1212/hello", new Hello());
    }

    @Test
    public void sayHello() throws Exception {
        assertThat(new Hello().sayHello("John Doe"), is("Hello, John Doe"));
    }

    @Test
    public void sayHelloByRPC() {
        assertThat(HelloService.getHello().sayHello("Henry"), is("Hello, Henry"));
    }

    @After
    public void destroy() {
        publish.stop();
    }
}
