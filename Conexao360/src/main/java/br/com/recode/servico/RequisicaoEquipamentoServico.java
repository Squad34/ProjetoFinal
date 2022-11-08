package br.com.recode.servico;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.recode.model.RequisicaoEquipamento;
import br.com.recode.model.Usuario;
import br.com.recode.repositorio.RequisicaoEquipamentoRepositorio;
import lombok.RequiredArgsConstructor;

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
}
