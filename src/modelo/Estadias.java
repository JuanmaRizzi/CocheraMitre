package modelo;

import java.sql.Date;

public class Estadias {

		private Integer id;
		private Date DateE;
		private Date DateS;
		private Object marca;
		private String modelo;
		private String dominio;
		private String titular;
		private String telefono;
		private Integer lugarAsignado; // hay que mostrar los lugare libres o no asignados de alguna forma
		
		public Estadias() {
			
		}

		

		public Estadias(Date dateE, Date dateS, Object marca, String modelo, String dominio, String titular,
				String telefono, Integer lugarAsignado) {
		
			DateE = dateE;
			DateS = dateS;
			this.marca = marca;
			this.modelo = modelo;
			this.dominio = dominio;
			this.titular = titular;
			this.telefono = telefono;
			this.lugarAsignado = lugarAsignado;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
		
		

		public Date getDateE() {
			return DateE;
		}



		public void setDateE(Date dateE) {
			DateE = dateE;
		}



		public Date getDateS() {
			return DateS;
		}



		public void setDateS(Date dateS) {
			DateS = dateS;
		}



		public Object getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
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

		public Integer getLugarAsignado() {
			return lugarAsignado;
		}

		public void setLugarAsignado(Integer lugarAsignado) {
			this.lugarAsignado = lugarAsignado;
		} 
		
		
		
		
		
		
}
