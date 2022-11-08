package br.com.recode.servico;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.recode.model.DoacaoEquipamento;
import br.com.recode.model.Usuario;
import br.com.recode.repositorio.DoacaoEquipamentoRepositorio;
import lombok.RequiredArgsConstructor;

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
}
