package ies.europa.main;

import java.util.ArrayList;

class Provincia {

	private String nombre;
	private ArrayList<Partido> partidos;

	public Provincia(String nombre, ArrayList<Partido> partidos) {
	
		this.nombre = nombre;
	
		this.partidos = partidos;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(ArrayList<Partido> partidos) {
		this.partidos = partidos;
	}

	@Override
	public String toString() {
		return "Provincia [nombre=" + nombre + ", partidos=" + partidos + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		if(nombre.equalsIgnoreCase(((Provincia)o).nombre)) {
			return true;
		} else {
			return false;
		}}
}
