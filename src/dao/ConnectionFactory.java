package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class ConnectionFactory {

    public static Connection getConnection() {

        try {
            // TENTA CONEXÃO COM BANCO DE DADOS REMOTO
            Connection RemoteConnection;
            RemoteConnection = DriverManager.getConnection("jdbc:mysql://xmysql.astronautdatabase.com:3306/astronautdatabase", "astronautdataba", "Juliana11@");
            return RemoteConnection;

        } catch (Exception e) {
            try {
                // TENTA CONEXÃO COM BANCO DE DADOS MYSQL LOCAL
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/astronautdatabase", "eleitejr", "Juliana11@");

                // TENTA CONEXÃO COM BANCO DE DADOS SQLITE

                // return DriverManager.getConnection("jdbc:sqlite:F:/Documents/astronauta.db");

            } catch (SQLException e1) {
                //  MOSTRA MENSAGEM DE ERRO EM CASO DE FALHA DE CONEXÃO COM BANCOS DE DADOS
                JOptionPane.showMessageDialog(null,
                        "Não foi possível estabelecer conexão...",
                        "Erro", JOptionPane.ERROR_MESSAGE, new ImageIcon("./imagens/vetor/scary.png") );
                throw new RuntimeException(e);
            }
        }
    }

}
