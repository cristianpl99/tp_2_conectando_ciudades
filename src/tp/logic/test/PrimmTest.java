package tp.logic.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tp.logic.GrafoConPeso;
import tp.logic.Primm;

public class PrimmTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void grafoNoConexoTest() {
		GrafoConPeso g = new GrafoConPeso(4);
		g.agregarArista(0, 1, 234);
		g.agregarArista(0, 2, 34);
		Primm.recorridoPrimm(g);
	}

	private static GrafoConPeso crearGrafoCompleto1() {
		GrafoConPeso grafo = new GrafoConPeso(6);
		grafo.agregarArista(0, 1, 1);
		grafo.agregarArista(0, 2, 100);
		grafo.agregarArista(0, 3, 100);
		grafo.agregarArista(0, 4, 100);
		grafo.agregarArista(0, 5, 100);
		grafo.agregarArista(1, 2, 100);
		grafo.agregarArista(1, 3, 4);
		grafo.agregarArista(1, 4, 100);
		grafo.agregarArista(1, 5, 100);
		grafo.agregarArista(2, 3, 5);
		grafo.agregarArista(2, 4, 100);
		grafo.agregarArista(2, 5, 100);
		grafo.agregarArista(3, 4, 27);
		grafo.agregarArista(3, 5, 100);
		grafo.agregarArista(4, 5, 15);
		return grafo;
	}

	@Test
	public void happy1Test() {
		GrafoConPeso g = crearGrafoCompleto1();
		GrafoConPeso agm = Primm.recorridoPrimm(g);
		System.out.println(agm);
		boolean acum = true;
		acum = acum && agm.existeArista(0, 1);
		acum = acum && agm.existeArista(1, 3);
		acum = acum && agm.existeArista(2, 3);
		acum = acum && agm.existeArista(3, 4);
		acum = acum && agm.existeArista(4, 5);
		assertEquals(true, acum);
	}
	
	private static GrafoConPeso crearGrafoCompleto2() {
		GrafoConPeso grafo = new GrafoConPeso(4);
		grafo.agregarArista(0, 1, 100);
		grafo.agregarArista(0, 2, 100);
		grafo.agregarArista(0, 3, 12);
		grafo.agregarArista(1, 2, 100);
		grafo.agregarArista(1, 3, 9);
		grafo.agregarArista(2, 3, 9);
		return grafo;
	}
	
	@Test
	public void happy2Test() {
		GrafoConPeso g = crearGrafoCompleto2();
		GrafoConPeso agm = Primm.recorridoPrimm(g);
		System.out.println(agm);
		boolean acum = true;
		acum = acum && agm.existeArista(0, 3);
		acum = acum && agm.existeArista(1, 3);
		acum = acum && agm.existeArista(2, 3);
		assertEquals(true, acum);
	}
	
	@Test
	public void noAGMTest() {
		GrafoConPeso g = crearGrafoCompleto2();
		GrafoConPeso agm = Primm.recorridoPrimm(g);
		System.out.println(agm);
		boolean acum = true;
		acum = acum && agm.existeArista(0, 3);
		acum = acum && agm.existeArista(1, 2);
		acum = acum && agm.existeArista(2, 3);
		assertEquals(false, acum);
	}
	
	private static GrafoConPeso crearGrafoCompleto3() {
		GrafoConPeso grafo = new GrafoConPeso(5);
		grafo.agregarArista(0, 1, 100);
		grafo.agregarArista(0, 2, 100);
		grafo.agregarArista(0, 3, 100);
		grafo.agregarArista(0, 4, 100);
		grafo.agregarArista(1, 2, 100);
		grafo.agregarArista(1, 3, 100);
		grafo.agregarArista(1, 4, 100);
		grafo.agregarArista(2, 3, 100);
		grafo.agregarArista(2, 4, 100);
		grafo.agregarArista(3, 4, 100);
		return grafo;
	}
	
	@Test
	public void happy3Test() {
		GrafoConPeso g = crearGrafoCompleto3();
		GrafoConPeso agm = Primm.recorridoPrimm(g);
		System.out.println(agm);
		boolean acum = true;
		acum = acum && agm.existeArista(0, 1);
		acum = acum && agm.existeArista(0, 2);
		acum = acum && agm.existeArista(0, 3);
		acum = acum && agm.existeArista(0, 4);
		assertEquals(true, acum);
	}

}
