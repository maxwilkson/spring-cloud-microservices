package br.com.maxribeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.maxribeiro.model.Book;
import br.com.maxribeiro.proxy.CambioProxy;
import br.com.maxribeiro.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Book API")
@RestController
@RequestMapping("book-service")
public class BookController {

	@Autowired
	private Environment environment;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CambioProxy cambioProxy;

	@Operation(summary = "Get a book by its ID")
	@GetMapping("/{id}/{currency}")
	public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {

		return bookRepository.findById(id).map(book -> {

			var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);

			book.setPrice(cambio.getConvertedValue());

			var port = environment.getProperty("local.server.port");
			book.setEnvironment("Book Port:" + port + " Cambio Port: " + cambio.getEnvironment());
			return book;

		}).orElseThrow(() -> new RuntimeException("Book not found"));

	}
}
