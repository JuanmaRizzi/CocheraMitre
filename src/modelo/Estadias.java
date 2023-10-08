package modelo;

import java.sql.Date;
import java.time.LocalDate;

public class Estadias {

		private Integer id;
		private LocalDate dataE;
		private Integer lugarAsignado; // hay que mostrar los lugare libres o no asignados de alguna forma
		private Object marca;
		private String modelo;
		private String dominio;
		private String titular;
		private String telefono;
		private Integer dias;
		private String valor;
		private Boolean esMensual;
		
	


		public Estadias( LocalDate dataE, Integer lugarAsignado, Object marca, String modelo, String dominio,
				String titular, String telefono, Integer dias, String valor, Boolean esMensual) {
		
			
			this.dataE = dataE;
			this.lugarAsignado = lugarAsignado;
			this.marca = marca;
			this.modelo = modelo;
			this.dominio = dominio;
			this.titular = titular;
			this.telefono = telefono;
			this.dias = dias;
			this.valor = valor;
			this.esMensual = (dias >= 30);
		}


		public Boolean getEsMensual() {
			return esMensual;
		}


		public void setEsMensual(Boolean esMensual) {
			this.esMensual = esMensual;
		}


		public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public LocalDate getDataE() {
			return dataE;
		}


		public void setDataE(LocalDate dataE) {
			this.dataE = dataE;
		}


		public Integer getLugarAsignado() {
			return lugarAsignado;
		}


		public void setLugarAsignado(Integer lugarAsignado) {
			this.lugarAsignado = lugarAsignado;
		}


		public Object getMarca() {
			return marca;
		}


		public void setMarca(Object marca) {
			this.marca = marca;
		}


		public String getModelo() {
			return modelo;
		}


		public void setModelo(String modelo) {
			this.modelo = modelo;
		}


		public String getDominio() {
			return dominio;
		}


		public void setDominio(String dominio) {
			this.dominio = dominio;
		}


		public String getTitular() {
			return titular;
		}


		public void setTitular(String titular) {
			this.titular = titular;
		}


		public String getTelefono() {
			return telefono;
		}


		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}


		public Integer getDias() {
			return dias;
		}


		public void setDias(Integer dias) {
			this.dias = dias;
		}


		public String getValor() {
			return valor;
		}


		public void setValor(String valor) {
			this.valor = valor;
		}

		
		
		
		
		
		
		
}
