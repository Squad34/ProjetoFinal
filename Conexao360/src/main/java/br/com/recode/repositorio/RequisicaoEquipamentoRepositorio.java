package br.com.recode.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.recode.model.RequisicaoEquipamento;
import br.com.recode.model.Usuario;

public interface RequisicaoEquipamentoRepositorio extends JpaRepository<RequisicaoEquipamento, Long> {

	List<RequisicaoEquipamento> findAllByUsuario(Usuario usuario);
	
	@Query(value = "select * from requisicao_equipamento order by pontos desc" , nativeQuery = true)
	List<RequisicaoEquipamento> findAllDesc();
	
	@Query(value = "select * from requisicao_equipamento"
			+ " where requisicao_atendida = 0 order by pontos desc" , nativeQuery = true)
	List<RequisicaoEquipamento> requisicoesNaoAtendidas();
	
	@Query(value = "select * from requisicao_equipamento where tipo_equipamento=:t order by pontos desc" , nativeQuery = true)
	List<RequisicaoEquipamento> findAllByTipoEquipamento(@Param("t")String tipoEquipamento);
	
	@Query(value = "select * from requisicao_equipamento where requisicao_atendida=:a order by pontos desc" , nativeQuery = true)
	List<RequisicaoEquipamento> findAllByEstadoRequisicao(@Param("a")String requisicaoAtendida);
	
	@Query(value = "select * from requisicao_equipamento where "
			+ "requisicao_atendida=:a and tipo_equipamento=:t order by pontos desc" , nativeQuery = true)
	List<RequisicaoEquipamento> filtrarRequisicoes(@Param("a")String requisicaoAtendida, @Param("t")String tipoEquipamento);
	
	
	@Modifying
	@Transactional
	@Query(value = "update requisicao_equipamento set requisicao_atendida = 1"
			+ " where id=:#{#r.id}", nativeQuery = true)
	void alterarRequisicaoParaAtendida(@Param("r") RequisicaoEquipamento requisicaoEquipamento);
}