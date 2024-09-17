package controller;

import java.util.concurrent.Semaphore;

public class TreinoFormula1 {
    private static final int NUM_PILOTOS = 14;
    private static final int MAX_CARROS_PISTA = 5;
    private static final int NUM_VOLTAS = 3;

    private Semaphore semaforoPista = new Semaphore(MAX_CARROS_PISTA);

    private double[][] temposPorPiloto = new double[NUM_PILOTOS][NUM_VOLTAS];
    private double[] voltaMaisRapida = new double[NUM_PILOTOS];

    public void simularTreino() {
        Thread[] pilotos = new Thread[NUM_PILOTOS];

        // Iniciar threads para cada piloto
        for (int i = 0; i < NUM_PILOTOS; i++) {
            final int pilotoId = i;
            pilotos[i] = new Thread(() -> {
                try {
                    // Simulação das voltas
                    for (int volta = 0; volta < NUM_VOLTAS; volta++) {
                        semaforoPista.acquire(); // Piloto aguarda permissão para entrar na pista
                        double tempoVolta = Math.random() * 100; // Gerando tempo de volta aleatório
                        temposPorPiloto[pilotoId][volta] = tempoVolta;
                        System.out.println("Piloto " + pilotoId + " - Volta " + (volta + 1) + " - Tempo: " + String.format("%.2f", tempoVolta));
                        Thread.sleep((long) tempoVolta); // Simulação do tempo de volta
                        semaforoPista.release(); // Piloto libera a pista após completar a volta
                    }

                    // Encontrar volta mais rápida
                    voltaMaisRapida[pilotoId] = encontrarVoltaMaisRapida(pilotoId);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            pilotos[i].start(); // Iniciar a thread do piloto
        }

        // Aguardar todas as threads dos pilotos terminarem
        for (int i = 0; i < NUM_PILOTOS; i++) {
            try {
                pilotos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Exibir grid de largada ordenado pelo tempo da volta mais rápida
        exibirGridLargada();
    }

    private double encontrarVoltaMaisRapida(int pilotoId) {
        double menorTempo = Double.MAX_VALUE;
        for (int volta = 0; volta < NUM_VOLTAS; volta++) {
            if (temposPorPiloto[pilotoId][volta] < menorTempo) {
                menorTempo = temposPorPiloto[pilotoId][volta];
            }
        }
        return menorTempo;
    }

    private void exibirGridLargada() {
        // Ordenar pilotos pelo tempo da volta mais rápida (bubble sort simples)
        for (int i = 0; i < NUM_PILOTOS - 1; i++) {
            for (int j = 0; j < NUM_PILOTOS - i - 1; j++) {
                if (voltaMaisRapida[j] > voltaMaisRapida[j + 1]) {
                    // Troca de posição
                    double temp = voltaMaisRapida[j];
                    voltaMaisRapida[j] = voltaMaisRapida[j + 1];
                    voltaMaisRapida[j + 1] = temp;
                }
            }
        }

        // Exibir grid de largada ordenado
        System.out.println("\nGrid de Largada:");

        for (int i = 0; i < NUM_PILOTOS; i++) {
            System.out.println("Piloto " + i + " - Melhor volta: " + String.format("%.2f", voltaMaisRapida[i]));
        }
    }
}
