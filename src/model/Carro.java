package model;

import control.BarraProgresso;
import control.CarroDao;
import control.Producao;
import model.VeiculoModelo;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/*Classe que produz os componentes de um carro e informa quantos carros foram produzidos*/
public class Carro implements Producao {
	private VeiculoModelo carro = new VeiculoModelo();
	private CarroDao dao;
	private int quantidade_producao;
	private static boolean terminada = false;
	private boolean controlador_chassi = false;
	private boolean controlador_carroceria = false;
	private boolean controlador_roda = false;
	private boolean controlador_motor = false;
	private boolean controlador_hora = true;

	/* M�todo sincronizado que controla a produ��o do chassi */
	@Override
	public synchronized void comecaChassi(int index)
			throws InterruptedException {
		int contador = index;
		if (contador == 0) {
			while (controlador_chassi) {
				wait();
			}

			if (!terminada) {
				BarraProgresso.setProduzir_chassi(true);    
				controlador_chassi = true;
			}

		} else {
			controlador_carroceria = true;
			notifyAll();
			while (controlador_chassi) {
				wait();
			}

			if (!terminada) {
				BarraProgresso.setProduzir_chassi(true);
				controlador_chassi = true;
			}
		}
	}

	/* M�todo sincronizado que controla a produ��o da carroceria */
	@Override
	public synchronized void comecaCarroceria(int index)
			throws InterruptedException {
		int contador = index;
		if (contador == 0) {
			while (!controlador_carroceria) {
				wait();
			}

			if (!terminada) {
				controlador_chassi = false;
			}
		} else {

			if (!terminada) {
				controlador_chassi = false;
				controlador_roda = true;
			}
		}
		notifyAll();
	}

	/* M�todo sincronizado que controla a produ��o da roda */
	@Override
	public synchronized void comecaRoda(int index)
			throws InterruptedException {
		int contador = index;
		if (contador == 0) {
			while (!controlador_roda) {
				wait();
			}

			if (!terminada) {
				controlador_roda = false;
			}
		} else {
			controlador_motor = true;
			notifyAll();
			while (!controlador_roda) {
				wait();
			}

			if (!terminada) {
				controlador_roda = false;
			}
		}
	}

	/* M�todo sincronizado que controla a produ��o do motor */
	@Override
	public synchronized void comecaMotor(int index)
			throws InterruptedException {
		int contador = index;
		if (contador == 0) {
			while (!controlador_motor) {
				wait();
			}

			if (!terminada) {
				++quantidade_producao;
				controlador_motor = false;
			}
		} else {

			while (!controlador_motor) {
				wait();
			}

			if (!terminada) {
				++quantidade_producao;
				//this.dao = new CarroDao();
				//dao.inserir(this.carro);
				controlador_motor = false;
			}
		}
	}

	public synchronized void setFinalizarProducaoCarroceria() {
		controlador_carroceria = true;
		notifyAll();
	}

	/* M�todo sincronizado que libera a produ��o da ultima roda */
	public synchronized void setFinalizarProducaoRoda() {
		controlador_roda = true;
		notifyAll();
	}

	/* M�todo sincronizado que libera a produ��o do ultimo motor */
	public synchronized void setFinalizarProducaoMotor() {
		controlador_motor = true;
		notifyAll();
	}

	public void setControladorHora() {
		controlador_hora = false;
	}
        
        public void setHora(boolean condicao){
            controlador_hora = condicao;
        }

	
	/* M�todo para retornar o tempo da produ��o */
	public boolean terminaHora() {
		if (controlador_hora)
			return true;
		else
			return false;
	}

	/* M�todo para retornar a quantidade de carros produzidos */
	public int getProducao() {
		return quantidade_producao;
	}

	// M�todo para auxiliar na interrup��o das Threads
	public synchronized void interromperProducao() {
		terminada = true;
		this.controlador_chassi = false;
		this.controlador_carroceria = true;
		this.controlador_roda = true;
		this.controlador_motor = true;
		notifyAll();
	}

	public void setInterromperProducao(boolean continuar_proxima) {
		terminada = continuar_proxima;
	}

	// M�todo usado para controlar a interrup��o das Threads
	public boolean getInterromperProducao() {
		return terminada;
	}
        
        public VeiculoModelo getVeiculoModelo(){
            return carro;
        }
}
