package com.sp.apis.pdf_generator_api.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI apiInfo() {
    return new OpenAPI()
        .info(new Info()
            .title("PDF Generator API")
            .version("1.0")
            .description("Production-ready PDF API"));
  }
}