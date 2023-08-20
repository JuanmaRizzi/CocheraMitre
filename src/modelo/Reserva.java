package modelo;

import java.time.LocalDate;

public class Reserva {
	
	private Integer id;
	private LocalDate DataE;
	private LocalDate DataS;
	private String valor;
	private String formaDePago;
	
	public Reserva(LocalDate dataE, LocalDate dataS, String valor, String formaDePago) {
		
		DataE = dataE;
		DataS = dataS;
		this.valor = valor;
		this.formaDePago = formaDePago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataE() {
		return DataE;
	}

	public void setDataE(LocalDate dataE) {
		DataE = dataE;
	}

	public LocalDate getDataS() {
		return DataS;
	}

	public void setDataS(LocalDate dataS) {
		DataS = dataS;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	
	
	
	

}
