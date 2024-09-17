package controller;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class SistemaBanco extends Thread{
    private double saldo;
    
    private final Semaphore saquePermitido = new Semaphore(1);
    private final Semaphore depositoPermitido = new Semaphore(1);

    public SistemaBanco(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void saque(String codigoConta, double valor) {
        try {
            saquePermitido.acquire();
            synchronized (this) {
                if (saldo >= valor) {
                    System.out.println(codigoConta + " - Saque de R$" + String.format("%.2f", valor) + " realizado.");
                    saldo -= valor;
                } else {
                    System.out.println(codigoConta + " - Saldo insuficiente para o saque de R$" + String.format("%.2f", valor));
                }
                System.out.println("Saldo atual: R$" + String.format("%.2f", saldo));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            saquePermitido.release();
        }
    }

    public void deposito(String codigoConta, double valor) {
        try {
            depositoPermitido.acquire();
            synchronized (this) {
                System.out.println(codigoConta + " - Depósito de R$" + String.format("%.2f", valor) + " realizado.");
                saldo += valor;
                System.out.println("Saldo atual: R$" + String.format("%.2f", saldo));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            depositoPermitido.release();
        }
    }

    public void transacaoAleatoria(String codigoConta) {
        boolean isSaque = ThreadLocalRandom.current().nextBoolean();
        double valor = ThreadLocalRandom.current().nextDouble(10, 200);

        if (isSaque) {
            saque(codigoConta, valor);
        } else {
            deposito(codigoConta, valor);
        }
    }
}