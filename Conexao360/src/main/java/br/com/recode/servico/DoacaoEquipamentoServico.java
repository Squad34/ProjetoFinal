package br.com.recode.servico;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.recode.model.DoacaoEquipamento;
import br.com.recode.model.Usuario;
import br.com.recode.repositorio.DoacaoEquipamentoRepositorio;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class DoacaoEquipamentoServico{
	
	private final DoacaoEquipamentoRepositorio doacaoEquipamentoRepositorio;
	
	@Transactional
	public DoacaoEquipamento cadastrarDoacaoEquipamento(DoacaoEquipamento doacaoEquipamento) {	
		return doacaoEquipamentoRepositorio.save(doacaoEquipamento);
	}
	
	public List<DoacaoEquipamento> listarTodosUsuario(Usuario usuario){
		return doacaoEquipamentoRepositorio.findAllByUsuario(usuario);
	}
	
	public List<DoacaoEquipamento> listarTodosLista(){
		return doacaoEquipamentoRepositorio.findAll();
	}
	
	public List<DoacaoEquipamento> listarTodosDisponiveis(){
		return doacaoEquipamentoRepositorio.disponiveisParaDoacao();
	}
	
	public DoacaoEquipamento buscarPorId(Long id) {
		return doacaoEquipamentoRepositorio.getOne(id);
	}
	
	public List<DoacaoEquipamento> listarDisponiveisPorTipoDispositivo(int tipoEquipamento){
		log.info("Tipo de equipamento solicitado=" + tipoEquipamento);
		return doacaoEquipamentoRepositorio.disponiveisPorTipoDispositivo(tipoEquipamento);
	}
	
	@Transactional
	public void tornarIndisponivel(DoacaoEquipamento doacaoEquipamento) {
		doacaoEquipamentoRepositorio.tornarIndisponivel(doacaoEquipamento);
	}
	
	public List<DoacaoEquipamento> listarFiltro(String equipamentoDoado, String tipoEquipamento){
		if(equipamentoDoado.equals("all") && tipoEquipamento.equals("all")) {
			log.info("Manda tudo de tudo");
			return doacaoEquipamentoRepositorio.findAll();
		}
		if(equipamentoDoado.equals("all")) {
			log.info("manda todos filtrado tipo equipamento");
			return doacaoEquipamentoRepositorio.findAllByTipoEquipamento(tipoEquipamento);
		}
		if(tipoEquipamento.equals("all")) {
			log.info("manda todos filtrado estadoRequisicao");
			return doacaoEquipamentoRepositorio.findAllByDisponibilidade(equipamentoDoado);
		}
		log.info("filtra mais");
		return doacaoEquipamentoRepositorio.filtrarDoacoes(equipamentoDoado, tipoEquipamento);
	}
}