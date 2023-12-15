package py.com.baa.snc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

import py.com.baa.snc.rest.ApiPaths;


@Configuration
@EnableSwagger
@Profile(value = { "default" })
public class SwaggerConfig {

	private SpringSwaggerConfig config;

	@Autowired
	public void setConfig(SpringSwaggerConfig config) {
		this.config = config;
	}

	@Bean
	public SwaggerSpringMvcPlugin getSwagger() {
		return new SwaggerSpringMvcPlugin(this.config).apiInfo(apiInfo()).enable(true).useDefaultResponseMessages(false)
				.includePatterns(ApiPaths.API_PATTERN);
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("MIBI MONITORING", "API for MIBIMONITORING", "MIBIMONITORING API terms of service",
				"zimple-ti@zimple.com.py", "MIBIMONITORING API Licence Type", "MIBIMONITORING API License URL");
	}
}