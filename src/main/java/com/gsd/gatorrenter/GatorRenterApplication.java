package com.gsd.gatorrenter;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.JerseyResourceContext;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Created by Intesar Haider on 3/1/2017.
 */
public class GatorRenterApplication extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public GatorRenterApplication() {
        register(RequestContextFilter.class);
        register(JerseyResourceContext.class);
        packages("com.gsd.gatorrenter");
    }
}