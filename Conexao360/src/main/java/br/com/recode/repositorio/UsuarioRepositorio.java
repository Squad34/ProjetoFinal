package br.com.recode.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.recode.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);
}
