package view;

import controller.SistemaBanco;

public class Principal {

	public static void main(String[] args) {
        int numTransacoes = 20;
        
        for(int i = 0; i < numTransacoes ; i++) {
        	int finalI = i;
        	SistemaBanco banco = new SistemaBanco(1000.0);
        	banco.transacaoAleatoria("Conta " + (finalI + 1));
        	banco.start();
		}
    }
}
