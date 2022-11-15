package br.com.recode.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RequisicaoEquipamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int necessidade;
	private int tipoEquipamento;
	private boolean possuiEquipamento;
	private boolean divideEquipamento;
	private int rendaFamiliar;
	private int pontos;
	private boolean necessitaRetirada;
	private boolean requisicaoAtendida;
	@DateTimeFormat(iso = ISO.DATE)
	private Date dataEntrega;
	private String comentario;
	@ManyToOne
	private Usuario usuario;

	public void calcularPontos() {
		
		int tempPontos = 0;

		switch (this.necessidade) {
		case 1:
			tempPontos += 1;
			break;
		case 2:
			tempPontos += 2;
			break;
		case 3:
			tempPontos += 1;
			break;
		default:
			break;
		}

		if (!this.possuiEquipamento)
			tempPontos += 2;
		
		if (!this.divideEquipamento)
			tempPontos += 1;
		
		switch (this.rendaFamiliar) {
		case 1:
			tempPontos += 2;
			break;
		case 2:
			tempPontos += 2;
			break;
		case 3:
			tempPontos += 1;
			break;
		case 4:
			tempPontos += 1;
			break;
		default:
			break;
		}
		this.pontos = tempPontos;
	}
}
