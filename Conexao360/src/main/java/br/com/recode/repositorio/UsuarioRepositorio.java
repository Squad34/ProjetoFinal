package br.com.recode.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.recode.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);
	
	@Modifying
	@Transactional
	@Query(value = "Update usuario set "
			+ "nome = :#{#u.nome}, "
			+ "sobrenome = :#{#u.sobrenome}, "
			+ "rg = :#{#u.rg}, "
			+ "cpf = :#{#u.cpf}, "
			+ "telefone = :#{#u.telefone}, "
			+ "estado = :#{#u.estado}, "
			+ "cidade = :#{#u.cidade}, "
			+ "cep = :#{#u.cep}, "
			+ "endereco = :#{#u.endereco}, "
			+ "complemento = :#{#u.complemento} "
			+ "WHERE id = :#{#u.id}", nativeQuery = true)
	void atualizarUsuario(@Param("u") Usuario usuario);
}