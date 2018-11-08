package pl.edu.wat.trainingManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"pl.edu.wat.trainingManager"})
@EnableJpaRepositories("pl.edu.wat.trainingManager")
@EntityScan("pl.edu.wat.trainingManager")
@PropertySources({
        @PropertySource(value={"classpath:hibernate.properties"}),
        @PropertySource(value={"classpath:application.properties"}),
        @PropertySource(value={"file:${jwtSecretPath}"})
})
public class WebApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(WebApplication.class)
                .properties(
                       /* "spring.config.name:application,hibernate",
                        "spring.config.location:classpath:/main/resources/,classpath:/main/resources/"*/
                ).build().run(args);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        System.out.println(environment.getProperty("test"));
        System.out.println(environment.getProperty("systemEnvProperty"));


    }

}