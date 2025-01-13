package notepadingo.application;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("/resources")
public class AppConfig extends ResourceConfig {

    public AppConfig() {
        packages("notepadingo.controller");


        register(JacksonFeature.class);
    }
}