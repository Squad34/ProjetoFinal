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
public class DoacaoEquipamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int tipoEquipamento;
	private int estadoEquipamento;
	private boolean necessitaRetirada;
	@DateTimeFormat(iso=ISO.DATE)
	private Date dataColeta;
	private boolean equipamentoDisponivel;
	private boolean equipamentoDoado;
	private String comentario;
	@ManyToOne
    private Usuario usuario;
}
