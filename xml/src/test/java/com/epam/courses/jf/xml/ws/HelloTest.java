package com.epam.courses.jf.xml.ws;

import com.epam.courses.jf.xml.ws.client.HelloService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.ws.Endpoint;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HelloTest {

    private Endpoint helloServiceEndPoint;

    @Before
    public void setUp() throws Exception {
        helloServiceEndPoint = Endpoint.publish("http://localhost:1212/hello", new Hello());
    }

    @After
    public void tearDown() throws Exception {
        helloServiceEndPoint.stop();
    }

    @Test
    public void sayHello() throws Exception {
        assertThat(new HelloService().getHelloPort().sayHello("Henry"), is("Hello, Henry"));
    }

}
