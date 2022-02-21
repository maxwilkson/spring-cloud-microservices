package br.com.maxribeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maxribeiro.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
