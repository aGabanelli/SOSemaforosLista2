package view;

import java.util.concurrent.Semaphore;

import controller.Carro;

public class Principal {

	public static void main(String[] args) {
        Semaphore cruzamento = new Semaphore(1);

        Carro carro1 = new Carro("Norte", cruzamento);
        Carro carro2 = new Carro("Sul", cruzamento);
        Carro carro3 = new Carro("Leste", cruzamento);
        Carro carro4 = new Carro("Oeste", cruzamento);

        carro1.start();
        carro2.start();
        carro3.start();
        carro4.start();
    }

	
}
