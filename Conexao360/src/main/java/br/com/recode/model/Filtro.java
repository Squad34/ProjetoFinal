package br.com.recode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder	
@AllArgsConstructor
@NoArgsConstructor
public class Filtro {

	private String estadoRequisicao;
	private String tipoEquipamento;
	
}
