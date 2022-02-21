package br.com.maxribeiro.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Foo Bar API")
@RestController
@RequestMapping("book-service")
public class FooBarController {

	private Logger logger = LoggerFactory.getLogger(FooBarController.class);

	// @Retry(name = "foo-bar", fallbackMethod = "fallback")
	// @CircuitBreaker(name = "default", fallbackMethod = "fallback")
	// @RateLimiter(name = "default")
	@Bulkhead(name = "default")
	@GetMapping("/foo-bar")
	@Operation(summary = "Perform a example request")
	public String fooBar() {
		logger.info("FOO-BAR REQUEST");

		// var response = new
		// RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
		// return response.getBody();
		return "Foo Bar!";

	}

	public String fallback(Exception ex) {
		logger.error("FOO-BAR FALLBACK");
		return "Default Content " + LocalDateTime.now();
	}
}
