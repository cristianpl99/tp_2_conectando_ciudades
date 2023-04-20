package tp.logic;

public class Edge {

	City city1;
	City city2 ;
	double peso;

	public Edge(City city1, City city2, double peso) {
		this.city1 = city1;
		this.city2 = city2;
		this.peso = peso;
	}

	public City getCity1() {
		return this.city1;
	}

	public City getCity2() {
		return this.city2;
	}

	public double getPeso() {
		return this.peso;
	}

	@Override
	public String toString() {
		return "vertices: " + city1.toString() + ", " + city2.toString() + ", peso: " + peso;
	}
}
