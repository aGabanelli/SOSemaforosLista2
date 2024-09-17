package controller;

import java.util.concurrent.Semaphore;

public class Carro extends Thread {
    private String sentido;
    private Semaphore cruzamento;

    public Carro(String sentido, Semaphore cruzamento) {
        this.sentido = sentido;
        this.cruzamento = cruzamento;
    }

    @Override
    public void run() {
        try {
            cruzamento.acquire();
            System.out.println("Carro " + getId() + " está atravessando no sentido " + sentido);
            
            Thread.sleep(1000);

            System.out.println("Carro " + getId() + " saiu do cruzamento.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            cruzamento.release();
        }
    }
}
