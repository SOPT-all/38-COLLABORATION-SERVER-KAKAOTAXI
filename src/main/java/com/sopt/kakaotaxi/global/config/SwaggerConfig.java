package com.sopt.kakaotaxi.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("SOPT KakaoTaxi")
						.description("LET'S SOPT 38기 합동 세미나 안드로이드 4조 카카오택시")
						.version("v1"));
	}
}
