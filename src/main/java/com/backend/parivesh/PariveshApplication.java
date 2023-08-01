package com.backend.parivesh;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients(basePackages = {"com.backend.*"})
@SpringBootApplication
@ComponentScan(basePackages = { "com.backend.*" })
@EntityScan("com.backend.*")
@EnableScheduling
public class PariveshApplication {

	public static void main(String[] args) {
		SpringApplication.run(PariveshApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return  new RestTemplate();
	}
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("Auth-jwt",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
								.in(SecurityScheme.In.HEADER).name("Authorization")))
				.info(new Info().title("User Registration API").version("snapshot"))
				.addSecurityItem(new SecurityRequirement().addList("Auth-jwt"));
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}

