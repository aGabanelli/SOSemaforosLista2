package controller;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Pessoa extends Thread {
    private String nome;
    private Semaphore porta;
    private int distanciaCorredor = 200;
    private int velocidade;

    public Pessoa(String nome, Semaphore porta) {
        this.nome = nome;
        this.porta = porta;
        this.velocidade = ThreadLocalRandom.current().nextInt(4, 7);
    }

    @Override
    public void run() {
        try {
            int tempoParaPercorrerCorredor = distanciaCorredor / velocidade;
            System.out.println(nome + " está andando a " + velocidade + " m/s e levará " + tempoParaPercorrerCorredor + " segundos para chegar à porta.");
            Thread.sleep(tempoParaPercorrerCorredor * 1000);

            porta.acquire();
            System.out.println(nome + " chegou à porta e está abrindo...");
            
            int tempoAbrirPorta = ThreadLocalRandom.current().nextInt(1, 3);
            Thread.sleep(tempoAbrirPorta * 1000);
            System.out.println(nome + " atravessou a porta em " + tempoAbrirPorta + " segundos.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            porta.release();
        }
    }
}
