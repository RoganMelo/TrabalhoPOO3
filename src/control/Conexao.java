package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    //Aqui tu coloca a referencia do driver do banco que vcs vao utilizar...
    private static final String Driver = "oracle.jdbc.driver.OracleDriver";
    //Aqui é a url do banco, no meu caso foi o oracle...
    private static final String Date_Url = "jdbc:oracle:thin:@//localhost:1521/xe";
    //Aqui é meu usuario(Rogan)...
    private static final String USER = "Rogan";
    //E aqui é minha senha(0000). Acho que o resto é autoexplicativo.
    private static final String PASS = "0000";

    public static Connection getConnection() {
        System.out.println("Conectando ao Banco de Dados");
        try {
            Class.forName(Driver);
            return DriverManager.getConnection(Date_Url, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void close(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (con != null) {
                con.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
