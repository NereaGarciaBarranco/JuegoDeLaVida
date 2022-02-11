package JuegoDeLaVida;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta clase presenta un programa que recrea el Juego de la vida diseñado
 * por John Norton Cornway en 1970.
 * 
 * @Author: Nerea García Barranco
 */

public class Main {
	static Scanner entrada;
	/**
	 * Pre: ---
	 * Post: Este método main muestra un menú de opciones para que el usuario pueda 
	 * elegir si quiere jugar al Juego de la vida de manera aleatoria o apagar 
	 * el programa. Desde aquí se llaman a todos los métodos necesarios para que 
	 * se lleve a cabo el Juego de la vida.
	 */
	public static void main(String[] args) throws InterruptedException {
		entrada = new Scanner(System.in);
		String [][] tablero;
		System.out.println("¡Bienvenido al Juego de la vida!");
		while (true) {
			mostrarMenu();
			int menu = entrada.nextInt();
			// Si el usuario introduce 1 se inicia el Juego de la vida
			if (menu == 1) {
				tablero = crearTablero();
				System.out.println("Dime el número de generaciones que quieres ver:");
				while (true) {
					int generaciones = entrada.nextInt();
					if (generaciones > 0) {
						crearGeneraciones(tablero, generaciones);
					} else {
						System.out.println("¡El número de generaciones debe ser superior a 0!");
						System.out.println("Introduce las generaciones de nuevo:");
					}
				}
			// Si el usuario introduce 2 el programa se apaga
			} else if (menu == 2) {
				break;
			} else {
				System.out.println("¡Introduce un dato válido!");
			}
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este método muestra el menú del programa.
	 */
	public static void mostrarMenu() {
		System.out.println("¿Qué deseas hacer?");
		System.out.println("Introduce 1 si quieres jugar en modo aleatorio.");
		System.out.println("Introduce 2 si quieres apagar el programa.");
	}
	
	/**
	 * Pre: ---
	 * Post: Este método pide al usuario el número de filas y columnas que va
	 * a tener el juego de la vida. Con esas cifras el programa crea una tabla 
	 * bidimensional de tipo String donde se generan células [*] con un 20%
	 * de probabilidad.
	 */
	public static String[][] crearTablero() {
		boolean filasCorrectas = false;
		int filas = 0;
		int columnas = 0;
		/*
		 * Pedimos datos de manera infinita al usuario hasta que introduzca
		 * unos datos que sean válidos. 
		 */
		while (true) {
			if (!filasCorrectas) {
				System.out.println("Dime el número de filas del tablero:");
				filas = entrada.nextInt();	
			}
			// Se comprueba que el número de filas sea mayor que 0
			if (filas > 0) {
				filasCorrectas = true;
				System.out.println("Dime el número de columnas del tablero:");
				columnas = entrada.nextInt();
				// Se comprueba que el número de columnas sea mayor que 0
				if (columnas > 0) {
					// Se crea el tablero de juego inicial
					String [][] tablero = new String [filas][columnas];
					/*
					 * Con estos dos bucles anidados se rellena el tablero con un 20%
					 * de probabilidades de que exista vida. Se hace generando números
					 * aleatorios del 0 al 9 (ambos incluidos).
					 */
					for (int i = 0; i < tablero.length; i++) {
						for (int j = 0; j < tablero[i].length; j++) {
							int vida = (int) Math.round(Math.random() * 9);
							if (vida < 2) {
								// Si hay una célula viva se rellena con un asterisco
								tablero[i][j] = "*";
							} else {
								// Si no hay la celda se deja vacía (un espacio)
								tablero[i][j] = " ";
							}
						}
					}
					return tablero;
				} else {
					System.out.println("El número de columnas debe ser mayor que 0.");
				}
			} else {
				System.out.println("El número de filas debe ser mayor que 0.");
				filasCorrectas = false;
			}
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este método recibe por parámetro el tablero inicial del Juego de la vida
	 * y el número de generaciones que tienen que producirse y las genera hasta el 
	 * número de generaciones pedidas o hasta que no queden células en el tablero.
	 * Simultáneamente se guardan datos referidos a cada generación en un objeto de 
	 * tipo Tripleta que se almacenan en un ArrayList.
	 */
	public static void crearGeneraciones(String[][] tablero, int generaciones) 
			throws InterruptedException {
		ArrayList <Tripleta> datos = new ArrayList <Tripleta>();
		Tripleta tripleta;
		// Este bucle va desde 0 hasta el número de generaciones deseadas
		for (int i = 0; i <= generaciones; i++) {
			System.out.println("GENERACIÓN " + i + "\n");
			/* Se espera una pequeña cantidad de tiempo entre tablero y tablero
			 * para poder visualizar mejor el juego de la vida.
			 */
			Thread.sleep(200);
			mostrarTablero(tablero);
			int celulasVivas = 0;
			// Estos bucles se encargan de contar el número total de células vivas
			for (int j = 0; j < tablero.length; j++) {
				for (int k = 0; k < tablero[j].length; k++) {
					if (tablero[j][k].equals("*")) {
						celulasVivas++;
					}
				}
			}
			/* 
			 * Almacenamos en los objetos de tipo Tripleta los datos de cada una de las 
			 * generaciones del juego para que se muestren cuando acabe el juego. Se 
			 * van añadiendo a un ArrayList.
			 */
			if (i == 0) {
				// La primera vez el número de células nuevas es igual al de células vivas
				tripleta = new Tripleta (i, celulasVivas, celulasVivas);
				datos.add(tripleta);
			} else {
				/*
				 * En el resto de generaciones hay que restar a las células vivas el número
				 * de células vivas de la generación anterior.
				 */
				int celulasNuevas = celulasVivas - datos.get(i-1).getNumeroVivas();
				tripleta = new Tripleta (i, celulasVivas, celulasNuevas);
				datos.add(tripleta);
			}
			if (celulasVivas == 0) {
				/* 
				 * Si no queda ninguna célula viva el bucle se termina y se muestra un mensaje
				 * de advertencia.
				 */
				System.out.println("\nColonia extinguida");
				break;
			} else {
				/*
				 * Mientras queden células vivas dentro del tablero se muestra en un mensaje
				 * cuántas quedan.
				 */
				System.out.println("\nNúmero de células vivas: " + celulasVivas);
			}
			/*
			 * Tras reunir todos los datos necesarios de una generación se manda el tablero
			 * a un método que produce la siguiente generación del mismo.
			 */
			tablero = modificarTablero(tablero);
			System.out.println();
		}
		// Cuando el juego termina se muestra la información contenida en las tripletas
		mostrarTripleta(datos);
	}
	
	/**
	 * Pre: --- 
	 * Post: Este método recibe un tablero y genera la próxima generación del mismo
	 * conforme a las normas del Juego de la vida.
	 */
	public static String[][] modificarTablero(String[][] tablero) {
		// Creamos un nuevo tablero de las mismas dimensiones que el que recibe el método
		String[][] tableroNuevo = new String[tablero.length][tablero[0].length];
		/*
		 * Estos dos bucles recorren cada una de las posiciones del tablero recibido y
		 * gracias al método comprobarVecinos() comprueba los vecinos de cada una para 
		 * saber qué ocurrirá con esa celda en la próxima generación.
		 */
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				// Así recibimos la cantidad de células vecinas de una posición
				int contador = comprobarVecinos(tablero, i, j);
				// Si la célula está viva en el tablero anterior
				if (comprobarVida(tablero[i][j])) {
					// Si alrededor tiene menos de dos células o más de 4 la célula muere
					if (contador < 2 || contador > 3) {
						// No añadimos una célula en esta posición en la siguiente generación
						tableroNuevo[i][j] = " ";
					} 
					// Si tiene alrededor dos o tres células, la célula sobrevive
					if (contador == 2 || contador == 3) {
						// Añadimos una célula en esta posición en la siguiente generación
						tableroNuevo[i][j] = "*";
					}
 				} else {
					// Si una celda vacía tiene tres células vivas alrededor nace una nueva
					if (contador == 3) {
						tableroNuevo[i][j] = "*";
					} else {
						tableroNuevo[i][j] = " ";
					}
				}
			}
		}
		return tableroNuevo;
	}
	
	/**
	 * Pre: --- 
	 * Post: Este método recibe un tablero y una posición en el mismo con 
	 * [fila] y [columna] y comprueba sus casillas adyacentes para saber el 
	 * número de vecinos (células vivas) que tiene alrededor. Antes de cada
	 * comprobación limitamos que no se estén comprobando las esquinas o los 
	 * bordes incompatibles con dicha comprobación.
	 */
	public static int comprobarVecinos (String[][] t, int fila, int columna) { 
		int contador = 0;
		// Si la celda no está en el borde superior ni en el borde izquierdo
		if (fila != 0 && columna != 0) { 
			// Comprobación de la celda superior izquierda
			if (comprobarVida(t[fila-1][columna-1])) {
				contador++;
			}
		}
		// Si la celda no está en el borde superior
		if (fila != 0) {
			// Comprobación de la celda superior
			if (comprobarVida(t[fila-1][columna])) { 
				contador++;
			}
		}
		// Si la celda no está en el borde superior ni en el borde derecho
		if (fila != 0 && columna != t[fila].length-1) {
			// Comprobación de la celda superior derecha
			if (comprobarVida(t[fila-1][columna+1])) {
				contador++;
			}
		}
		// Si la celda no está en el borde derecho
		if (columna != t[fila].length-1) {
			// Comprobación de la celda derecha
			if (comprobarVida(t[fila][columna+1])) { 
				contador++;
			}
		}
		// Si la celda no está en el borde inferior ni en el borde derecho
		if (fila != t.length-1 && columna != t[fila].length-1) {
			// Comprobación de la celda inferior derecha
			if (comprobarVida(t[fila+1][columna+1])) { 
				contador++;
			}
		}
		// Si la celda no está en el borde inferior
		if (fila != t.length-1) {
			// Comprobación de la celda inferior
			if (comprobarVida(t[fila+1][columna])) { 
				contador++;
			}
		}
		// Si la celda no está en el borde inferior ni en el borde izquierdo
		if (fila != t.length-1 && columna != 0) {
			// Comprobación de la esquina inferior izquierda
			if (comprobarVida(t[fila+1][columna-1])) { 
				contador++;
			}
		}
		// Si la celda no está en el borde izquierdo
		if (columna != 0) {
			// Comprobación de la esquina  izquierda
			if (comprobarVida(t[fila][columna-1])) { 
				contador++;
			}
		}	
		return contador;
	}
	
	/**
	 * Pre: ---
	 * Post: Este método comprueba si en una celda del tablero existe vida o no
	 * y lo devuelve en una variable de tipo boolean para poder ser utilizado
	 * en condiciones con mayor facilidad.
	 */
	public static boolean comprobarVida(String celda) {
		boolean vida = false;
		if (celda.equals("*")) {
			vida = true;
		} else if (celda.equals(" ")) {
			vida = false;
		}
		return vida;
	}
	
	/**
	 * Pre: ---
	 * Post: Este método muestra por consola el tablero que se le pasa 
	 * por parámetro.
	 */
	public static void mostrarTablero(String[][] tablero) {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Pre: ---
	 * Post: Este método muestra por consola un ArrayList que contiene
	 * objetos de tipo Tripleta.
	 */
	public static void mostrarTripleta(ArrayList<Tripleta> lista) {
		System.out.println("Generación  " + "Células vivas  " + "Células nuevas");
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).toString());
		}
		System.out.println();
	}
}
