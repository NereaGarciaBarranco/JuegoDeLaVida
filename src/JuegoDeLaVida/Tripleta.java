package JuegoDeLaVida;

public class Tripleta {
	private int numeroGeneracion;
	private int numeroVivas;
	private int numeroNuevas;
	
	// Método constructor
	public Tripleta (int numeroGeneracion, int numeroVivas, int numeroNuevas) {
		this.numeroGeneracion = numeroGeneracion;
		this.numeroVivas = numeroVivas;
		this.numeroNuevas = numeroNuevas;
	}
	
	// Métodos get y set de la clase Tripleta
	public int getNumeroGeneracion() {
		return numeroGeneracion;
	}
	
	public void setNumeroGeneracion(int numeroGeneracion) {
		this.numeroGeneracion = numeroGeneracion;
	}
	
	public int getNumeroVivas() {
		return numeroVivas;
	}
	
	public void setNumeroVivas(int numeroVivas) {
		this.numeroVivas = numeroVivas;
	}
	
	public int getNumeroNuevas() {
		return numeroNuevas;
	}
	
	public void setNumeroNuevas(int numeroNuevas) {
		this.numeroNuevas = numeroNuevas;
	}
	
	@Override
	public String toString() {
		return "    " + numeroGeneracion + "            " +  numeroVivas 
				+ "             " + numeroNuevas;
	}
}
