package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RetiradaDAO {

    public void inserir(Retirada retirada) {
        String sql = "INSERT INTO `retirada`(`idproduto`, `quantidade`, `data`) VALUES (?, ?, ?)";
        PreparedStatement pst;

        try {
            pst = Conexao.getConexao().prepareStatement(sql);

            pst.setInt(1, retirada.getIdProduto());
            pst.setInt(2, retirada.getQuantidade());
            pst.setDate(3, retirada.getData());

            pst.execute();

            pst.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public List<Retirada> listar() {
        List<Retirada> retiradas = new ArrayList<>();
        String sql = "SELECT * FROM `retirada`";

        try {
            PreparedStatement pst = Conexao.getConexao().prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idProduto = resultSet.getInt("idproduto");
                int quantidade = resultSet.getInt("quantidade");
                java.sql.Date data = resultSet.getDate("data");

                Retirada retirada = new Retirada();
                retirada.setId(id);
                retirada.setIdProduto(idProduto);
                retirada.setData(data);
                retirada.setQuantidade(quantidade);
                retiradas.add(retirada);
            }

            resultSet.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return retiradas;
    }
}
