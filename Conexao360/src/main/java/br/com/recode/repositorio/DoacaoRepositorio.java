package br.com.recode.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.recode.model.Doacao;

public interface DoacaoRepositorio extends JpaRepository<Doacao, Long> {

}