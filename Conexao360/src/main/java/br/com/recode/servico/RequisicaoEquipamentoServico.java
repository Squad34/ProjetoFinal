package br.com.recode.servico;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.recode.model.RequisicaoEquipamento;
import br.com.recode.model.Usuario;
import br.com.recode.repositorio.RequisicaoEquipamentoRepositorio;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class RequisicaoEquipamentoServico{
	
	private final RequisicaoEquipamentoRepositorio requisicaoEquipamentoRepositorio;
	
	@Transactional
	public RequisicaoEquipamento cadastrarRequisicaoEquipamento(RequisicaoEquipamento requisicaoEquipamento) {	
		return requisicaoEquipamentoRepositorio.save(requisicaoEquipamento);
	}
	
	public List<RequisicaoEquipamento> listarTodosUsuario(Usuario usuario){
		return requisicaoEquipamentoRepositorio.findAllByUsuario(usuario);
	}
	
	public List<RequisicaoEquipamento> listarTodosLista(){
		return requisicaoEquipamentoRepositorio.findAll();
	}
	
	public List<RequisicaoEquipamento> listarTodosNecessitamDoacao(){
		return requisicaoEquipamentoRepositorio.requisicoesNaoAtendidas();
	}
	
	public void alterarRequisicaoParaAtendida(RequisicaoEquipamento requisicaoEquipamento) {
		requisicaoEquipamentoRepositorio.alterarRequisicaoParaAtendida(requisicaoEquipamento);
	}
	
	public RequisicaoEquipamento buscarPorId(Long id) {
		return requisicaoEquipamentoRepositorio.getOne(id);
	}
	
	public List<RequisicaoEquipamento> listarFiltro(String estadoRequisicao, String tipoEquipamento){
		log.info("estadoReq = " + estadoRequisicao);
		log.info("tipoEq = " + tipoEquipamento);
		if(estadoRequisicao.equals("all") && tipoEquipamento.equals("all")) {
			log.info("Manda tudo de tudo");
			return requisicaoEquipamentoRepositorio.findAllDesc();
		}
		if(estadoRequisicao.equals("all")) {
			log.info("manda todos filtrado tipo equipamento");
			return requisicaoEquipamentoRepositorio.findAllByTipoEquipamento(tipoEquipamento);
		}
		if(tipoEquipamento.equals("all")) {
			log.info("manda todos filtrado estadoRequisicao");
			return requisicaoEquipamentoRepositorio.findAllByEstadoRequisicao(estadoRequisicao);
		}
		log.info("filtra mais");
		return requisicaoEquipamentoRepositorio.filtrarRequisicoes(estadoRequisicao, tipoEquipamento);
	}
}
