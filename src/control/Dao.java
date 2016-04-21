package control;

import java.util.List;
import model.VeiculoModelo;

public interface Dao {

	int inserir(VeiculoModelo veiculo);

	int atualizar(VeiculoModelo veiculo);

	int remover(int id);

	List<VeiculoModelo> recuperar(String tipo, String cor);
}
