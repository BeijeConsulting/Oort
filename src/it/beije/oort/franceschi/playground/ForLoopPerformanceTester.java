package it.beije.oort.franceschi.playground;

import java.util.ArrayList;
import java.util.List;

import it.beije.oort.franceschi.csvToXml.Convertitore;
import it.beije.oort.franceschi.csvToXml.InputManager;

public class ForLoopPerformanceTester {
	
	static List<Long> test1Times = new ArrayList<>();
	static List<Long> test2Times = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("Test 1 e poi Test 2.");
		for (int i = 0; i < 50; i++) {
			test1();
			test2();
		}
		
		System.out.println("Test 2 e poi Test 1.");
		for (int i = 0; i < 50; i++) {
			test2();
			test1();
		}
		
		System.out.println("Test concluso");
		System.out.println("Tempo medio Test 1: " + getTempoMedio(test1Times));
		System.out.println("Tempo medio Test 2: " + getTempoMedio(test2Times));
	}
	
	private static void test1() {
		long startTime = System.nanoTime();
		for (int i = 0; i < InputManager.getInputAmount(); i++) {
			Convertitore.csvToXml(i);
		}
		long stopTime = System.nanoTime();
		test1Times.add(stopTime - startTime);
		System.out.println("Exec time test 1: " + (stopTime - startTime)/1000000);
	}
	private static void test2() {
		long startTime = System.nanoTime();
		for (int i = 0, max = InputManager.getInputAmount(); i < max; i++) {
			Convertitore.csvToXml(i);
		}
		long stopTime = System.nanoTime();
		test2Times.add(stopTime - startTime);
		System.out.println("Exec time test 2: " + (stopTime - startTime)/1000000);
	}
	
	static double getTempoMedio(List<Long> tempi) {
		double total = 0; 
		for (Long l : tempi) {
			total += l;
		}
		return (total/tempi.size())/1_000_000;
	}
}
