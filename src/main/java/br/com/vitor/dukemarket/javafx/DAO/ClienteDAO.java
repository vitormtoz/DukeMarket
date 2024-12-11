/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vitor.dukemarket.javafx.DAO;

import br.com.vitor.dukemarket.javafx.model.Cliente;
import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qualifica
 */
public class ClienteDAO {
    private static final String SQL_INSERT = "INSERT INTO clientes(nome, telefone, email, endereco, cidade, uf, cep, dataNascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM clientes";
    private static final String SQL_SELECT_ID = "SELECT * FROM clientes WHERE id = ?";
    private static final String SQL_UPDATE ="UPDATE clientes SET nome = ?, telefone = ?, email = ?, endereco = ?, cidade = ?, uf = ?, cep = ?, dataNascimento = ? WHERE id = ?";
    private static final String SQL_DELETE ="DELETE FROM clientes WHERE id = ?";
    
    /**
     * Insere um usurario na Base de Dados contatos
     * @param c
     */
    public void create(Cliente c){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getTelefone());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getEndereco());
            stmt.setString(5, c.getCidade());
            stmt.setString(6, c.getUf());
            stmt.setString(7, c.getCep());
            stmt.setString(8, c.getDataNascimentoSQL());
            
            //Executa a query
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.INFO, null, "Inclusao: " + auxRetorno);
        } catch (SQLException sQLException) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE,null,sQLException);
        } finally {
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
    
    /**
     * Retorna todos os registros da tabela produto
     * @return 
     */
    public List<Cliente> getResults(){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente c = null;
        List<Cliente> listaContatos = null;
        
        try{
            //Prepara a string de SELECT e executa a query.
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            
            //Carrega os dadosd do ResultSet rs, converte em Produto e adiciona na lista de retorno.
            listaContatos = new ArrayList<>();
            
            while(rs.next()){
                c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setEndereco(rs.getString("endereco"));
                c.setCidade(rs.getString("cidade"));
                c.setUf(rs.getString("uf"));
                c.setCep(rs.getString("cep"));
                c.setDataNascimento(rs.getString("dataNascimento"));
                listaContatos.add(c);
            }
        }catch (SQLException ex){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaContatos;
    }
    
    /**
     * Retorna um produto da tabela produto
     * @param id
     * @return 
     */
    public Cliente getResultByID(int id){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente c = null;
        
        try{
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()){
                c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setEndereco(rs.getString("endereco"));
                c.setCidade(rs.getString("cidade"));
                c.setUf(rs.getString("uf"));
                c.setCep(rs.getString("cep"));
                c.setDataNascimento(rs.getString("dataNascimento"));
            }
        }catch (SQLException sQLException){
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, sQLException);
        }finally{
        // Encerra a conexão com o banco e o statement
        MySQLConnection.closeConnection(conn,stmt,rs);
        }
        return c;
    }
    
    /**
     * Atualiza um registro na tabela produto
     * @param c
     */
    public void update(Cliente c){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getTelefone());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getEndereco());
            stmt.setString(5, c.getCidade());
            stmt.setString(6, c.getUf());
            stmt.setString(7, c.getCep());
            stmt.setString(8, c.getDataNascimento());
            stmt.setInt(6, c.getId());
            
            //Executa a query
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.INFO, null, "Update: " + auxRetorno);
        } catch (SQLException sQLException) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE,null,sQLException);
        } finally {
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt);
        }
    }
    
    /**
     * Exclui um produto com base no ID informado
     * @param id 
     */
    public void delete(int id){
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            
            //Executa a query
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.INFO, null, "Delete: " + auxRetorno);
        } catch (SQLException sQLException) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE,null,sQLException);
        } finally {
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.closeConnection(conn, stmt);
        }
        
    }
    
}
