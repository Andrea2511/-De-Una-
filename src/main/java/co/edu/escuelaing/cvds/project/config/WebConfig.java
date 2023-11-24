package co.edu.escuelaing.cvds.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configura la ubicación de los recursos estáticos y la ruta de acceso
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}