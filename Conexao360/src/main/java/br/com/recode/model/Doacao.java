package br.com.recode.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Doacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	RequisicaoEquipamento requisicaoEquipamento;
	@ManyToOne
	DoacaoEquipamento doacaoEquipamento;
}
