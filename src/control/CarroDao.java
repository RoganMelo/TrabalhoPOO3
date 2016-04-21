package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.VeiculoModelo;

public class CarroDao implements Dao {

    private static final String SQL_INSERIR = "INSERT INTO CARRO (ID,TIPO,COR,QUANTIDADE) VALUES (?,?,?,?)";
    private static final String SQL_ATUALIZAR = "UPDATE CARRO SET TIPO = ?, COR = ?, QUANTIDADE = ? WHERE ID = ?";
    private static final String SQL_REMOVER = "DELETE FROM CARRO WHERE ID = ?";
    private static final String SQL_RECUPERAR_1 = "SELECT*FROM CARRO ORDER BY ID";
    private static final String SQL_RECUPERAR_2 = "SELECT*FROM CARRO WHERE COR = ? ORDER BY ID";
    private static final String SQL_RECUPERAR_3 = "SELECT*FROM CARRO WHERE TIPO = ? ORDER BY ID";
    private static final String SQL_RECUPERAR_4 = "SELECT*FROM CARRO WHERE TIPO = ? AND COR = ? ORDER BY ID";

    //Método para inserir registro no banco...
    @Override
    public int inserir(VeiculoModelo veiculo) {
        Connection con = Conexao.getConnection();
        PreparedStatement st = null;
        PreparedStatement st1;
        int resultado = 0;
        try {
            st = con.prepareStatement(SQL_RECUPERAR_1);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
            }
            i++;
            st1 = con.prepareStatement(SQL_INSERIR);
            st1.setInt(1, i);
            st1.setString(2, veiculo.getTipo());
            st1.setString(3, veiculo.getCor());
            st1.setInt(4, veiculo.getQuantidade());
            /* Se a inser��o der certo ele retorna 1 caso contr�rio retorna 2 */
            resultado = st1.executeUpdate();
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } finally {
                Conexao.close(con, st, null);
            }
            e.printStackTrace();
        }

        return resultado;
    }

    //Método para atualizar registro no banco...
    @Override
    public int atualizar(VeiculoModelo veiculo) {
        Connection con = Conexao.getConnection();
        PreparedStatement st = null;
        int resultado = 0;
        try {
            st = con.prepareStatement(SQL_ATUALIZAR);
            st.setString(1, veiculo.getTipo());
            st.setString(2, veiculo.getCor());
            st.setInt(3, veiculo.getQuantidade());
            st.setInt(4, veiculo.getId());
            /* Se a inser��o der certo ele retorna 1 caso contr�rio retorna 2 */
            resultado = st.executeUpdate();
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } finally {
                Conexao.close(con, st, null);
            }
            e.printStackTrace();
        }
        return resultado;
    }

    //Método para remover registro do banco...
    @Override
    public int remover(int id) {
        Connection con = Conexao.getConnection();
        PreparedStatement st = null;
        int resultado = 0;
        try {
            st = con.prepareStatement(SQL_REMOVER);
            st.setInt(1, id);
            /* Se a inser��o der certo ele retorna 1 caso contr�rio retorna 2 */
            resultado = st.executeUpdate();
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } finally {
                Conexao.close(con, st, null);
            }
            e.printStackTrace();
        }

        return resultado;
    }

    //Método para consulta no banco.
    @Override
    public List<VeiculoModelo> recuperar(String tipo, String cor) {
        Connection con = Conexao.getConnection();
        PreparedStatement st = null;
        List<VeiculoModelo> carros;
        carros = new ArrayList<>();
        ResultSet rs = null;
        try {
            if (tipo.equals("Nenhum") && cor.equals("Nenhum")) {
                st = con.prepareStatement(SQL_RECUPERAR_1);
            } else if (tipo.equals("Nenhum")) {
                st = con.prepareStatement(SQL_RECUPERAR_2);
                st.setString(1, cor);
            } else if (cor.equals("Nenhum")) {
                st = con.prepareStatement(SQL_RECUPERAR_3);
                st.setString(1, tipo);
            } else {
                st = con.prepareStatement(SQL_RECUPERAR_4);
                st.setString(1, tipo);
                st.setString(2, cor);
            }
            rs = st.executeQuery();
            while (rs.next()) {
                VeiculoModelo carro = new VeiculoModelo();
                carro.setId(rs.getInt("ID"));
                carro.setTipo(rs.getString("TIPO"));
                carro.setCor(rs.getString("COR"));
                carro.setQuantidade(rs.getInt("QUANTIDADE"));
                carros.add(carro);
            }
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } finally {
                Conexao.close(con, st, rs);
            }
            e.printStackTrace();
        }

        return carros;
    }

}
