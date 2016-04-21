package control;

import java.util.ArrayList;
import javax.swing.JLabel;

import javax.swing.JTextArea;

public class ThreadChassi implements Runnable {
	private ArrayList<Producao> producao;
	private int quantidade;
	private JTextArea imprimir;
        private JLabel imagem;
	private boolean controlador_producao;

	public ThreadChassi(ArrayList<Producao> p,JTextArea aux,JLabel jLabel2) {
		imagem = jLabel2;
		imprimir = aux;
		producao = p;
	}

	@Override
	public void run() {
            int controlador = 0;
            
            while(controlador < producao.size()){
                quantidade = producao.get(controlador).getVeiculoModelo().getQuantidade();
            
            
		try {
			for (int contador = 0; contador < quantidade; contador++) {
				producao.get(controlador).comecaChassi(contador);
                                imprimir.append("" + producao.get(controlador).getVeiculoModelo().getTipo() + " " + (contador + 1) 
                                        + " produzindo chassi" + "\n");
				/*
				 * Essa parte do codigo ficara verificando se houve algum pedido
				 * de interrup��o. Se houver algum pedido de interrup��o a
				 * Thread n�o vai dormi e vai ser dado um RETURN para ela
				 * terminar sua execu��o.
				 */
				
				if (!BarraProgresso.isInterromper()) {
					System.out.println("Thread vai dormir");
                               		imagem.setEnabled(true);
                                        Thread.sleep(5 * 1000);
                                        BarraProgresso.setProduzir_chassi(false);
                                       imagem.setEnabled(false);
					if (contador == 0) {
						producao.get(controlador).setFinalizarProducaoCarroceria();
					}
				} else {
                                        producao.get(controlador).interromperProducao();
                                        producao.get(controlador).setControladorHora();
                                        controlador = producao.size();
					return;
				}
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
                controlador += 1;
            }
	}

}
