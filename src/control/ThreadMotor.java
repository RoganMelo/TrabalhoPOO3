package control;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ThreadMotor implements Runnable {
	private ArrayList<Producao> producao;
        private CarroDao dao;
	private int quantidade;
        private int prod_qtd;
	private boolean controlador_producao;
	private JTextArea imprimir;
	private JLabel carro_produzido;
        private JLabel imagem;
        private JLabel imagem_prod_concluida;
	private JButton botao;

	public ThreadMotor(ArrayList<Producao> p, JTextArea aux,JLabel jLabel5,JLabel jLabel7,
			JLabel produzido, JLabel hora,JButton botao_liberar) {
		producao = p;
		imprimir = aux;
		carro_produzido = produzido;
		botao = botao_liberar;
                imagem = jLabel5;
                imagem_prod_concluida = jLabel7;
	}

	@Override
	public void run() {
            int controlador = 0;
            prod_qtd = 0;
            dao = new CarroDao();
            while(controlador < producao.size()){
                quantidade = producao.get(controlador).getVeiculoModelo().getQuantidade();
            
                try {
			for (int contador = 0; contador < quantidade; contador++) {
				producao.get(controlador).comecaMotor(contador);
                                imprimir.append("" + producao.get(controlador).getVeiculoModelo().getTipo() + " " + (contador + 1)  
                                        + " produzindo motor" + "\n");
				/*
				 * Essa parte do codigo ficara verificando se houve algum pedido
				 * de interrup��o. Se houver algum pedido de interrup��o a
				 * Thread n�o vai dormi e vai ser dado um RETURN para ela
				 * terminar sua execu��o.
				 */
				
				if (!BarraProgresso.isInterromper()) {
					System.out.println("Thread vai dormir");
					imagem.setEnabled(true);
                                        BarraProgresso.setProduzir_motor(true);
                                        this.imagem_prod_concluida.setEnabled(false);
                                        Thread.sleep(5 * 1000);
                                        this.imagem_prod_concluida.setEnabled(true);
                                        BarraProgresso.setProduzir_motor(false);
                                        imagem.setEnabled(false);
					if (contador == quantidade - 1){
                                               this.imagem_prod_concluida.setEnabled(true);
                                               producao.get(controlador).setControladorHora();
                                        }
                                        prod_qtd += 1;
					carro_produzido.setText("Carros Produzidos: "
							+ prod_qtd);
                                        dao.inserir(producao.get(controlador).getVeiculoModelo());
                                        
				} else {
                                        producao.get(controlador).interromperProducao();
                                        producao.get(controlador).setControladorHora();
                                        controlador = producao.size();
					return;
				}
			}
			botao.setEnabled(true);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
                controlador += 1;
            }
            producao.clear();
	}
}
