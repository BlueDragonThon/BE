package com.example.template.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Spring에서 설정 클래스로 사용됨을 명시
public class SwaggerConfig {

    @Bean // Spring 컨텍스트에서 OpenAPI 객체를 빈으로 등록
    public OpenAPI openApiConfig() {

        // Swagger UI에서 API 문서의 정보를 설정
        Info info = new Info()
                .title("My API") // API의 제목 설정
                .description("This is the API documentation for My API") // API의 설명 설정
                .version("1.0.0"); // API의 버전 설정

        String jwtSchemeName = "JWT TOKEN";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme("bearer")
                        .bearerFormat("JWT"));

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
