/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Mensagem;
import modelo.Usuario;

/**
 *
 * @author desenvolverdor
 */
public class MensagemDAO {
    
    public MensagemDAO(){}
     public boolean inserir(Mensagem mensagem)
    {
        String sql = "INSERT INTO mensgem(nome,mensagem,tempo,tempo_chegada) VALUES(?,?,?,?)";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            pst.setString(1,mensagem.getNome());
            pst.setString(2, mensagem.getMsg());
            pst.setString(3, mensagem.getTempo());
            pst.setString(4, mensagem.getTempochegada());
            
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    }
}
