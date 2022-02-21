package br.com.maxribeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.maxribeiro.model.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long> {

	Optional<Cambio> findByFromAndTo(String from, String to);

}
