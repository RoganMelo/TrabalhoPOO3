package model;

import model.VeiculoModelo;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableCarro extends AbstractTableModel {
	private List<VeiculoModelo> carros;

	public TableCarro(List<VeiculoModelo> lista) {
		this.carros = lista;
	}

	@Override
	public int getColumnCount() {

		return 4;
	}

	@Override
	public int getRowCount() {

		return carros.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		VeiculoModelo carro = carros.get(linha);

		switch (coluna) {
		case 0:
			return carro.getId();
		case 1:
			return carro.getTipo();
		case 2:
			return carro.getCor();
		case 3:
			return carro.getQuantidade();
		}

		return null;
	}

	@Override
	public String getColumnName(int i) {
		String coluna = "";

		switch (i) {
		case 0:
			return coluna = "ID";
		case 1:
			return coluna = "TIPO";
		case 2:
			return coluna = "COR";
		case 3:
			return coluna = "QUANTIDADE";
		}
		return null;
	}

	@Override
	public Class<?> getColumnClass(int coluna) {
		switch (coluna) {
		case 0:
			return Long.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return Long.class;

		}
		return null;
	}
}
