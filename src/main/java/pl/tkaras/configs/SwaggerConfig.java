package pl.tkaras.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(UsernamePasswordAuthenticationToken.class)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(createApiInfo())
                .securitySchemes(singletonList(createSchema()))
                .securityContexts(singletonList(createContext()));
    }

    private ApiInfo createApiInfo() {
        return new ApiInfo("QuizApp Api",
                "",
                "1.0",
                "",
                new Contact("TKaras", "https://github.com/KarasTomasz", "t.karas@onet.pl"),
                "",
                "",
                Collections.emptyList());
    }

    private SecurityScheme createSchema() {
        return new ApiKey("apiKey", "Authorization", "header");
    }

    private SecurityContext createContext() {
        return SecurityContext.builder()
                .securityReferences(createRef())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> createRef(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return singletonList(new SecurityReference("apiKey", authorizationScopes));
    }
}
