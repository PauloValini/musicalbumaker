package br.com.paulomayumi.musictoalbum.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info(new Info()
                .title("Music To Album Api")
                .version("1.0")
                .description("API Music To Album")
                .termsOfService("https://www.fatecjales.edu.br")
                .license(
                        new License().name("Apache License 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                )
        );
    }



}