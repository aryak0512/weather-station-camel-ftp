package com.example.weather.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleFtpRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("ftp://{{ftp.user}}@{{ftp.host}}/{{ftp.path}}?antInclude=*202411*.axf&idempotent=true&binary=true&idempotentRepository=#idempotentRepository")
                .routeId("FTP-route")
                .to("bean:fileProcessor?method=processFile(${body}, ${header.CamelFileName})");
    }
}
