package com.epam.courses.jf.javase01.mypackage.ws;

import javax.xml.ws.Endpoint;

public class ServiceStarter {
    public static void main(String[] args) {
        String url = "http://localhost:1212/hello";
        Endpoint.publish(url, new Hello());
        System.out.println("Service started @ " + url);
    }
}
