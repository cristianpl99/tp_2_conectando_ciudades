package tp.logic;

import java.util.Objects;

public class Edge {

	City city1;
	City city2;
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
		return "Ciudades: " + city1.getName() + ", " + city2.getName() + ", Costo de la Conexion: " + peso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city1, city2, peso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		return Objects.equals(city1, other.city1) && Objects.equals(city2, other.city2)
				&& Double.doubleToLongBits(peso) == Double.doubleToLongBits(other.peso);
	}
}
