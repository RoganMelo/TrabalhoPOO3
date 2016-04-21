package control;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import model.VeiculoModelo;

/*Interface da produ��o dos componentes de um carro*/
public interface Producao {
	public void comecaChassi(int index)
			throws InterruptedException;

	public void comecaCarroceria(int index)
			throws InterruptedException;

	public void comecaRoda(int index)
			throws InterruptedException;

	public void comecaMotor(int index)
			throws InterruptedException;
        
	public void setFinalizarProducaoCarroceria();

	public void setControladorHora();

	public void setFinalizarProducaoRoda();

	public void setFinalizarProducaoMotor();

	public  void interromperProducao();

	public boolean terminaHora();
        
        public void setHora(boolean condicao);

	public int getProducao();
        
        public VeiculoModelo getVeiculoModelo();
}
