package ies.europa.main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Start {

	private static boolean existe(String[] provincias, String prov) {

		for (int i = 0; i < provincias.length; i++) {

			if (provincias[i].equals(prov)) {

				return true;

			}

		}

		return false;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DecimalFormat formato = new DecimalFormat("#.##");
		ArrayList<Integer> todosVotos = new ArrayList<>();
		ArrayList<Partido> partidos = new ArrayList<>();
		ArrayList<Provincia> misprovincias = new ArrayList<>();

		Scanner in = new Scanner(System.in);

		String[] provincias = { "Álava", "Albacete", "Alicante", "Almería", "Asturias", "Ávila", "Badajoz", "Barcelona",
				"Burgos", "Cáceres", "Cádiz", "Cantabria", "Castellón", "Ciudad Real", "Córdoba", "Cuenca", "Gerona",
				"Granada", "Guadalajara", "Guipúzcoa", "Huelva", "Huesca", "Islas Baleares", "Jaén", "La Coruña",
				"La Rioja", "Las Palmas", "León", "Lérida", "Lugo", "Madrid", "Málaga", "Murcia", "Navarra", "Orense",
				"Palencia", "Pontevedra", "Salamanca", "Santa Cruz de Tenerife", "Segovia", "Sevilla", "Soria",
				"Tarragona", "Teruel", "Toledo", "Valencia", "Valladolid", "Vizcaya", "Zamora", "Zaragoza" };

		

		String prov = "";

		System.out.print("Pon las provincias");

		while (!(prov = in.next()).equals("0")) {

			if (existe(provincias, prov)) {

				if (!misprovincias.contains(new Provincia(prov, null))) {

					System.out.print("Numero de diputados");

					int diputados = in.nextInt();

					String partido = "";

					int votos = 0;

					System.out.print("Coloca los partidos y sus votos hasta poner 0");

					while (!(partido = in.next()).equals("0")) {

						if (!partidos.contains(new Partido(partido, 0))) {

							System.out.print("Numero de votos");

							votos = in.nextInt();

							partidos.add(new Partido(partido, votos));

						}

						else {

							System.out.print("Ya esta ese partido");

						}

					}

					partido = "";

					while (!partido.equals("0")) {

						System.out.print("Sobre que partido quieres realizar una accion");

						partido = in.next();

						if (!partido.equals("0")) {

							if (partidos.contains(new Partido(partido, 0))) {

								System.out.print("Que quieres hacer :\n1-Eliminar\n2-Modificar votos");

								int opc = in.nextInt();

								switch (opc) {

								case 1:
									partidos.remove((new Partido(partido, 0)));

									break;

								case 2:

									votos = in.nextInt();

									partidos.get(partidos.indexOf(new Partido(partido, 0))).setVotos(votos);

								}

							}

							else {

								System.out.println("no esta ese partido");

							}

						}

						else {

							System.out.println("Procediendo con los votos...");

						}

					}

					for (int i = 0; i < partidos.size(); i++) {

						for (int j = 1; j <= diputados; j++) {

							todosVotos.add(partidos.get(i).getVotos() / j);

						}

					}
					
					ArrayList<Integer>usado = new ArrayList<>();

					Collections.sort(todosVotos, Comparator.reverseOrder());

					for (int i = 0; i < partidos.size(); i++) {

						for (int j = 0; j < diputados; j++) {

							for (int k = 1; k <= diputados; k++) {

								if (todosVotos.get(j) == partidos.get(i).getVotos() / k && !usado.contains(partidos.get(i).getVotos() / k)) {

									partidos.get(i).setVotosDiputados(partidos.get(i).getVotosDiputados() + 1);

									usado.add(partidos.get(i).getVotos() / k);
								}

							}

						}
						
						usado.clear();

					}

					misprovincias.add(new Provincia(prov, new ArrayList<>(partidos)));
					partidos.clear();
					todosVotos.clear();
				}

				else {

					System.out.print("Repetido!!!");

				}

			}

			else {
				System.out.print("No existe");
			}

		}

		int totalDiputados = 0;
		float totalVotos = 0;

		for (int i = 0; i < misprovincias.size(); i++) {

			misprovincias.get(i).getPartidos();

			System.out.println(misprovincias.get(i).getNombre() + "\n---------\n");

			for (int j = 0; j < misprovincias.get(i).getPartidos().size(); j++) {

				System.out.println(misprovincias.get(i).getPartidos().get(j).getNombre() + "     "
						+ misprovincias.get(i).getPartidos().get(j).getVotosDiputados());

				totalDiputados = totalDiputados + misprovincias.get(i).getPartidos().get(j).getVotosDiputados();

				totalVotos = totalVotos + misprovincias.get(i).getPartidos().get(j).getVotos();

			}

			System.out.println();

		}

		ArrayList<String> partidosUnicos = new ArrayList<>();

		for (int i = 0; i < misprovincias.size(); i++) {

			for (int j = 0; j < misprovincias.get(i).getPartidos().size(); j++) {

				if (!partidosUnicos.contains(misprovincias.get(i).getPartidos().get(j).getNombre())) {

					partidosUnicos.add(misprovincias.get(i).getPartidos().get(j).getNombre());

				}

			}

		}

		System.out.println("---------Porcentaje de partidos a nivel nacional-----------\n");

		for (int i = 0; i < partidosUnicos.size(); i++) {

			float total = 0;

			for (int k = 0; k < misprovincias.size(); k++) {

				for (int j = 0; j < misprovincias.get(k).getPartidos().size(); j++) {

					if (partidosUnicos.get(i).equals(misprovincias.get(k).getPartidos().get(j).getNombre())) {

						total = total + misprovincias.get(k).getPartidos().get(j).getVotos();

					}

				}

			}

			System.out.println(
					partidosUnicos.get(i) + "  " + formato.format((total * 100 / totalVotos))     + "% - Votos totales : " + total);

		}

		System.out.println("--------------------\nTotal de votos :"
				+ ((int) (totalVotos) + "\nTotal de diputados : " + totalDiputados));

	}

}
