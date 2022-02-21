package br.com.maxribeiro.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maxribeiro.model.Cambio;
import br.com.maxribeiro.repository.CambioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cambio API")
@RestController
@RequestMapping("cambio-service")
public class CambioController {

	@Autowired
	private Environment env;

	@Autowired
	private CambioRepository cambioRepository;

	private Logger logger = LoggerFactory.getLogger(CambioController.class);

	@Operation(summary = "Convert a monetary value")
	@GetMapping(value = "/{amount}/{from}/{to}")
	public Cambio getCambio(@PathVariable("amount") BigDecimal amount, @PathVariable("from") String from,
			@PathVariable("to") String to) {

		logger.info("getCambio is called with {}, {} and {}.", amount, from, to);

		return cambioRepository.findByFromAndTo(from, to).map(cambio -> {
			var port = env.getProperty("local.server.port");
			cambio.calculateConvertedValue(amount);
			cambio.setEnvironment(port);
			return cambio;

		}).orElseThrow(() -> new RuntimeException("Currency Unsupported."));

	}
}
