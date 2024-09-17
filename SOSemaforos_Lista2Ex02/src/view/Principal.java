package view;

import java.util.concurrent.Semaphore;

import controller.Pessoa;

public class Principal {

	public static void main(String[] args) {
		Semaphore porta = new Semaphore(1);

        Pessoa pessoa1 = new Pessoa("Pessoa 1", porta);
        Pessoa pessoa2 = new Pessoa("Pessoa 2", porta);
        Pessoa pessoa3 = new Pessoa("Pessoa 3", porta);
        Pessoa pessoa4 = new Pessoa("Pessoa 4", porta);

        pessoa1.start();
        pessoa2.start();
        pessoa3.start();
        pessoa4.start();
    }
}
