package br.com.vitor.dukemarket.javafx.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vitormtoz
 */
public class Produto {
    Calendar dataCadastro;
    int id;
    String codBarras;
    String descricao;
    double qtd;
    double valorCompra;
    double valorVenda;

    public String getDataCadastro() {
        if (this.dataCadastro != null){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
            return sdf.format(this.dataCadastro.getTime());
        }else{
            return "";
        }
    }

    public void setDataCadastro(String strDataCadastro) {
        try{
            this.dataCadastro = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.dataCadastro.setTime(sdf.parse(strDataCadastro));
        } catch (ParseException ex){
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    @Override
    public String toString() {
        return String.format("%3d | %15s | %-30s | %10.2f | %10.2f | %10.2f | %10s", this.id, this.codBarras, this.descricao, this.qtd, this.valorCompra, this.valorVenda, this.getDataCadastro());
    }
    
    
    
}
