package br.com.recode.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder	
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	RequisicaoEquipamento requisicaoEquipamento;
	@ManyToOne
	DoacaoEquipamento doacaoEquipamento;
}
