package br.com.recode.servico;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.recode.model.Doacao;
import br.com.recode.repositorio.DoacaoRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoacaoServico{
	
	private final DoacaoRepositorio doacaoRepositorio;
	
	@Transactional
	public Doacao efetivarDoacao(Doacao doacao) {	
		return doacaoRepositorio.save(doacao);
	}
	
	public List<Doacao> listarTodosLista(){
		return doacaoRepositorio.findAll();
	}

}
