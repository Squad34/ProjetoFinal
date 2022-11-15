package br.com.recode.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.recode.model.DoacaoEquipamento;
import br.com.recode.model.Usuario;

public interface DoacaoEquipamentoRepositorio extends JpaRepository<DoacaoEquipamento, Long> {

	List<DoacaoEquipamento> findAllByUsuario(Usuario usuario);
	
	@Query(value = "select * from doacao_equipamento"
			+ " where equipamento_disponivel = 1"
			+ " AND equipamento_doado=0", nativeQuery = true)
	List<DoacaoEquipamento> disponiveisParaDoacao();
	
	@Query(value = "select * from doacao_equipamento"
			+ " where equipamento_disponivel = 1"
			+ " AND equipamento_doado=0 AND tipo_equipamento=:t", nativeQuery = true)
	List<DoacaoEquipamento> disponiveisPorTipoDispositivo(@Param("t") int tipoEquipamento);
	
	@Modifying
	@Transactional
	@Query(value = "update doacao_equipamento set equipamento_doado = 1"
			+ " where id=:#{#d.id}", nativeQuery = true)
	void tornarIndisponivel(@Param("d") DoacaoEquipamento doacaoEquipamento);
}