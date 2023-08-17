package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DevolucaoDAO {

    public void inserir(Devolucao devolucao) {
        String sql = "INSERT INTO `devolucao`(`idproduto`, `quantidade`, `data`) VALUES (?, ?, ?)";
        PreparedStatement pst;

        try {
            pst = Conexao.getConexao().prepareStatement(sql);

            pst.setInt(1, devolucao.getIdProduto());
            pst.setInt(2, devolucao.getQuantidade());
            pst.setDate(3, devolucao.getData());

            pst.execute();

            pst.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Devolucao> listar() {
        List<Devolucao> devolucoes = new ArrayList<>();
        String sql = "SELECT * FROM `devolucao`";

        try {
            PreparedStatement pst = Conexao.getConexao().prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idProduto = resultSet.getInt("idproduto");
                int quantidade = resultSet.getInt("quantidade");
                java.sql.Date data = resultSet.getDate("data");

                Devolucao devolucao = new Devolucao();
                devolucao.setId(id);
                devolucao.setIdProduto(idProduto);
                devolucao.setQuantidade(quantidade);
                devolucao.setData(data);
                devolucoes.add(devolucao);
            }

            resultSet.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return devolucoes;
    }
}
