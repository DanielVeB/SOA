package app;

import app.controller.UsersController;
import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/app")
public class App extends Application {


        public App() {
            BeanConfig beanConfig = new BeanConfig();
            beanConfig.setVersion("1.0-SNAPSHOT");
            beanConfig.setSchemes(new String[]{"http"});
            beanConfig.setBasePath("/web/app");
            beanConfig.setResourcePackage("app");
            beanConfig.setScan(true);
        }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();
        resources.add(UsersController.class);
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return resources;
    }

}
