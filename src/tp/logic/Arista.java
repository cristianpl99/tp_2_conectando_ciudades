package tp.logic;

public class Arista {

	String[] vertice1;
	String[] vertice2;
	double peso;

	public Arista(String[] vertice1, String[] vertice2, double peso) {
		this.vertice1 = vertice1;
		this.vertice2 = vertice2;
		this.peso = peso;
	}

	public String[] getVert1() {
		return this.vertice1;
	}

	public String[] getVert2() {
		return this.vertice2;
	}

	public double getPeso() {
		return this.peso;
	}

	@Override
	public String toString() {
		return "vertices: " + vertice1[0] + ", " + vertice2[0] + ", peso: " + peso;
	}
}
