package connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *Gerencia a conexão com o banco de dados.
 * Possui as instruções necessárias para conectar ao banco.
 * @author vitormtoz
 */
public class MySQLConnection {
    
    // Define strings de conexão com o banco de dados.
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://172.16.0.30/vmt_dukemarket";
    
    private static final String USER = "tozzato";
    private static final String PASS = "senai";

    /**
    *Cria conexão com o banco de dados MySql.
    * @return
    */
    public static Connection getConnection(){
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL,USER,PASS);
        }catch(SQLException ex){
            throw new RuntimeException("Erro na conexão com o banco de dados. Verifique!", ex);
        }catch (ClassNotFoundException ex){
            throw new RuntimeException("Falha na carga do Driver" , ex);
        }
    }
    /**
     * Fecha a conexão com o Banco de Dados.
     * @param conn Connection a ser fechada.
     */
    public static void closeConnection(Connection conn){
        try{
            if(conn != null){
                conn.close();
            }
        } catch (SQLException ex){
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    /**
     * Fecha a conexão com o Banco de Dados.
     * @param conn Conexão
     * @param stmt Statement
     */
    public static void closeConnection(Connection conn, PreparedStatement stmt){
        closeConnection(conn);
        
        try{
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException ex){
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Fecha a conexão com o Banco de Dados
     * @param conn Conexão
     * @param stmt Statement
     * @param rs Result
     */
    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs){
        closeConnection(conn,stmt);
        
        try{
            if(rs != null){
                rs.close();
            }
        } catch (SQLException ex){
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}