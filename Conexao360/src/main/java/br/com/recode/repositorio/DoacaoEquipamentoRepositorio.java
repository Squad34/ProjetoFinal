package br.com.recode.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.recode.model.DoacaoEquipamento;
import br.com.recode.model.Usuario;

public interface DoacaoEquipamentoRepositorio extends JpaRepository<DoacaoEquipamento, Long> {

	List<DoacaoEquipamento> findAllByUsuario(Usuario usuario);
}