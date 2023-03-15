package ies.europa.main;

import java.util.ArrayList;



class Partido {

	String nombre;
	private int votos;
	private int votosDiputados;

	public Partido(String nombre, int votos) {

			
		this.nombre = nombre;
		this.votos = votos;
	 this.votosDiputados = 0;
		
	}
	
	public Partido(String nombre, int votos,int votosDiputados) {

		
		this.nombre = nombre;
		this.votos = votos;
	 this.votosDiputados = votosDiputados;
		
	}
	
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

	@Override
	public boolean equals(Object a) {
		if(nombre.equalsIgnoreCase(((Partido)a).nombre)) {
			return true;
		} else {
			return false;
		}}
	
	public int compareTo(Partido supermercado) {
		return (this.votosDiputados < supermercado.getVotosDiputados() ? -1 : (this.votos == supermercado.getVotosDiputados() ? 0 : 1));

	}

	@Override
	public String toString() {
		return "Partido [nombre=" + nombre + ", votos=" + votos + ", votosDiputados=" + votosDiputados + "]";
	}

	public int getVotosDiputados() {
		return votosDiputados;
	}

	public void setVotosDiputados(int votosDiputados) {
		this.votosDiputados = votosDiputados;
	}

}
