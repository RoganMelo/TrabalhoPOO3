package control;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class ThreadTempo implements Runnable {
	private ArrayList<Producao> producao;
	private JLabel impressao_hora;
        private JProgressBar barra1;
        private JProgressBar barra2;
        private JProgressBar barra3;
        private JProgressBar barra4;
	private boolean para_contagem;
	private boolean controlador_producao;
        private int controlador_barras = 0;
        private int tempo_producao;
        

	public ThreadTempo(ArrayList<Producao> p,JLabel hora,JProgressBar jProgressBar1,JProgressBar jProgressBar2,
                JProgressBar jProgressBar3,JProgressBar jProgressBar4) {
		producao = p;
		impressao_hora = hora;
                barra1 = jProgressBar1;
                barra2 = jProgressBar2;
                barra3 = jProgressBar3;
                barra4 = jProgressBar4;
                
	}

	@Override
	public void run() {
                int controlador = 0;
            
                while(controlador < producao.size()){
                  if(!BarraProgresso.isInterromper()){  
                    controlador_barras = producao.get(controlador).getVeiculoModelo().getQuantidade();
                    producao.get(controlador).setHora(true);
                    para_contagem = producao.get(controlador).terminaHora();
			while (para_contagem) {
                              	comecaHora(impressao_hora);
                                if(BarraProgresso.isProduzir_chassi())
                                    BarraProgresso.barraChassi(barra1, controlador_barras);
                                if(BarraProgresso.isProduzir_carroceria())
                                    BarraProgresso.barraCarroceria(barra2, controlador_barras);
                                if(BarraProgresso.isProduzir_roda())
                                    BarraProgresso.barraRoda(barra3, controlador_barras);
                                if(BarraProgresso.isProduzir_motor())
                                    BarraProgresso.barraMotor(barra4, controlador_barras);
                                try {
					Thread.sleep(1 * 1000);
                                      
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
                                 para_contagem = producao.get(controlador).terminaHora();
			}
                        controlador += 1;
                        controlador_barras = 0;
                  }else{
                      producao.get(controlador).interromperProducao();
                      controlador = producao.size();
                      return;
                  }   
                }
	}
        
        public void comecaHora(JLabel imprimir_hora) {
		tempo_producao++;
		imprimir_hora.setText("Tempo: " + tempo_producao + "H");
	}
}
