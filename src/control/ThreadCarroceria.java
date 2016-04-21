package control;

import java.util.ArrayList;
import javax.swing.JLabel;

import javax.swing.JTextArea;

public class ThreadCarroceria implements Runnable {
	private ArrayList<Producao> producao;
	private int quantidade;
	private JTextArea imprimir;
        private JLabel imagem;
	private boolean controlador_producao;

	public ThreadCarroceria(ArrayList<Producao> p,JTextArea aux,JLabel jLabel3) {
		producao = p;
		imprimir = aux;
                imagem = jLabel3;
	}

	@Override
	public void run() {
            int controlador = 0;
            
            while(controlador < producao.size()){
                quantidade = producao.get(controlador).getVeiculoModelo().getQuantidade();
            
		try {
			for (int contador = 0; contador < quantidade; contador++) {
				producao.get(controlador).comecaCarroceria(contador);
                                imprimir.append("" + producao.get(controlador).getVeiculoModelo().getTipo() + " " + (contador + 1) 
                                        + " produzindo carroceria" + "\n");
				/*
				 * Essa parte do codigo ficara verificando se houve algum pedido
				 * de interrup��o. Se houver algum pedido de interrup��o a
				 * Thread n�o vai dormi e vai ser dado um RETURN para ela
				 * terminar sua execu��o.
				 */
				
				if (!BarraProgresso.isInterromper()) {
					System.out.println("Thread vai dormir");
					imagem.setEnabled(true);
                                        BarraProgresso.setProduzir_carroceria(true);
                                        Thread.sleep(12 * 1000);
                                        BarraProgresso.setProduzir_carroceria(false);
                                        imagem.setEnabled(false);
					if (contador == quantidade - 1) {
						producao.get(controlador).setFinalizarProducaoRoda();
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
