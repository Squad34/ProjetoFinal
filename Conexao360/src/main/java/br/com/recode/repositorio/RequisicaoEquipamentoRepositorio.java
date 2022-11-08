package br.com.recode.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.recode.model.RequisicaoEquipamento;
import br.com.recode.model.Usuario;

public interface RequisicaoEquipamentoRepositorio extends JpaRepository<RequisicaoEquipamento, Long> {

	List<RequisicaoEquipamento> findAllByUsuario(Usuario usuario);
}