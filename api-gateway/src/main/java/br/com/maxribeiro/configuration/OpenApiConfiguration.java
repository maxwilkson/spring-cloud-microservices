package br.com.maxribeiro.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class OpenApiConfiguration {

	@Bean
	@Lazy(false)
	public List<GroupedOpenApi> apis(SwaggerUiConfigParameters config, RouteDefinitionLocator locator) {

//		var apis = new ArrayList<GroupedOpenApi>();
		var definitions = locator.getRouteDefinitions().collectList().block();

		definitions.stream()
			.filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
			.forEach(routeDefinition -> {
				String name = routeDefinition.getId();
				config.addGroup(name);
//				var group = GroupedOpenApi.builder()
//					.pathsToMatch("/" + name + "/**")
//					.group(name)
//					.build();
				//apis.add(group);
			});

		return Collections.emptyList();
	}
}
