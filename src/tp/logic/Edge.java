package tp.logic;

public class Edge implements Comparable<Edge> {
	private String[] origin;
    private String[] destination;
    private double weight;

    public Edge(String[] vertex1, String[] vertex2, double weight) {
    	this.origin = vertex1;
        this.destination = vertex2;
        this.weight = weight;
    }

    public String[] getDestination() {
        return destination;
    }

    public void setDestination(String[] destination) {
        this.destination = destination;
    }
    
    public String[] getOrigin() {
		return origin;
	}

	public void setOrigin(String[] origin) {
		this.origin = origin;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(int weigth) {
		this.weight = weigth;
	}
	
	@Override
	public int compareTo(Edge otherEdge) {
		if (this.weight > otherEdge.weight) {
            return 1;
        } else if (this.weight < otherEdge.weight) {
            return -1;
        } else {
            return 0;
        }
    }
	
	

}
