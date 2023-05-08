/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControleProduto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import model.Produto;

/**
 *
 * @author santo
 */
public class TelaListagemProdutos extends JFrame{
    private JScrollPane scrollPane;
    private JPanel mainPanel;
    private ControleProduto controleProduto;
    private ArrayList<JButtonProduto> listaButtonsDeletar;
    private ArrayList<JButtonProduto> listaButtonsEditar;
   

   public TelaListagemProdutos(ControleProduto controleProduto) {
      super("Tela Listagem Produtos"); // define o título da janela
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(1000, 800); 
      setExtendedState(JFrame.MAXIMIZED_BOTH);
      this.controleProduto = controleProduto;
      

      this.mainPanel = new JPanel();
      this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
      this.mainPanel.setBackground(new Color(72, 69, 174));
      
      
      
      
      
      this.scrollPane = new JScrollPane(mainPanel);
      this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      add(this.scrollPane);
      setLocationRelativeTo(null);
      setVisible(true); // torna a janela visível
      mostrarProdutos();
   }
   
   public class PainelProduto extends JPanel{
 
       public Produto produto;
       
       public PainelProduto(Produto produto, ControleProduto controlerProduto){
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

            
            setMaximumSize(new Dimension(600,110));
            setBackground(new Color(72, 69, 174));

            //Painel da Imagem -----------------------------------------------------------|
            JPanel painelImagemProduto = new JPanelProduto(new FlowLayout(FlowLayout.LEFT));
            painelImagemProduto.setPreferredSize(new Dimension(150, 100));
            painelImagemProduto.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
            JLabel imagemLabel =  new JLabel();
            ImageIcon icon = new ImageIcon(getClass().getResource("/imagens/jacare.jpeg"));
            Image imagem = icon.getImage().getScaledInstance(80, -1, Image.SCALE_SMOOTH);
            icon = new ImageIcon(imagem);
            imagemLabel.setIcon(icon);
            imagemLabel.setPreferredSize(new Dimension(80,80));
            painelImagemProduto.add(imagemLabel);
            
            
            
            

            //Painel das informações do produto -------------------------------------------|
           
            JPanel painelInformacoesProduto = new JPanelProduto(new GridLayout(4, 1, 0, 5));
            JLabel jLabelNome = new JLabelProduto("Nome: "+produto.getNome());
            painelInformacoesProduto.add(jLabelNome);
            JLabel jLabelQuantidade = new JLabelProduto("Quantidade: "+produto.getQuantidade());
            painelInformacoesProduto.add(jLabelQuantidade);
            JLabel jLabelPreco = new JLabelProduto("Preço: R$"+produto.getPreco());
            painelInformacoesProduto.add(jLabelPreco);
            painelInformacoesProduto.add(new JLabelProduto("Tipo: "+produto.getIdTipo()));
    
            painelInformacoesProduto.setPreferredSize(new Dimension(350, 100));
            
            
            //Painel dos botões ------------------------------------------------------------|
            JPanel painelButoes = new JPanelProduto(new GridLayout(2, 1, 0, 5));
            painelButoes.setPreferredSize(new Dimension(100, 100));
            JButtonProduto buttonEditar = new JButtonProduto(new javax.swing.ImageIcon(getClass().getResource("/imagens/Editar2.png")), produto.getId(), "editar");
            JButtonProduto buttonDeletar = new JButtonProduto(new javax.swing.ImageIcon(getClass().getResource("/imagens/Deletar2.png")), produto.getId(), "deletar");
            painelButoes.add(buttonEditar);
            painelButoes.add(buttonDeletar);
            
            controleProduto.adicionarListener(buttonEditar);
            controleProduto.adicionarListener(buttonDeletar);
            

            // adiciona os três painéis ao painel principal
            mainPanel.add(painelImagemProduto);
            mainPanel.add(painelInformacoesProduto);
            mainPanel.add(painelButoes);
            add(mainPanel);
   
       }
   }
   
   public Image getImageResized(String path){
       ImageIcon icon = new ImageIcon(path);
       Image imagem = icon.getImage();
       int larguraNova = 200; 
       int alturaNova = 200; 
       Image imagemRedimensionada = imagem.getScaledInstance(larguraNova, alturaNova, Image.SCALE_SMOOTH);
       return imagemRedimensionada;
   }
   
   public class JLabelProduto extends JLabel{
       
       public JLabelProduto(String texto){
           super(texto);
           setPreferredSize(new Dimension(340, 0));
           setBackground(new Color(86, 73, 158));
           setOpaque(true);
           setForeground(Color.WHITE);
           setFont(new Font("Tahoma", 0, 14));
           setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
           
           
       }
    
    }
   
   public class JPanelProduto extends JPanel{
       
       public JPanelProduto(LayoutManager layout){
           super(layout);
           setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
           setBackground(new Color(127, 108, 235));
       }
   }
   
   public class JButtonProduto extends JButton{
       
       private int produtoId;
       private String tipoButton;
       
        public JButtonProduto(javax.swing.ImageIcon icon, int produtoId, String tipoButton){
           this.tipoButton = tipoButton;
           this.produtoId = produtoId;
           setIcon(icon);
           //setOpaque(false);
           setBackground(new Color(86, 73, 158));
           setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
           setFocusable(false);
        }
       
        public int getProdutoId() {
            return produtoId;
        }

        public void setProdutoId(int produtoId) {
            this.produtoId = produtoId;
        }

        public String getTipoButton() {
            return tipoButton;
        }

        public void setTipoButton(String tipoButton) {
            this.tipoButton = tipoButton;
        }
       
       
   }
   
   public void mostrarProdutos(){
       this.mainPanel.removeAll();
       this.mainPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Adicionando espaçamento entre O textFieldPanel e topo da tela
      
       JPanel textFieldPanel = new JPanel();
       textFieldPanel.setPreferredSize(new Dimension(600, 40));
       textFieldPanel.setMaximumSize(new Dimension(600, 40));
       JTextField textField = new JTextField();
       textField.setBackground(new Color(127, 108, 235));
       Border border = BorderFactory.createLineBorder(Color.BLACK, 0);
       textField.setBorder(border);
       textField.setPreferredSize(new Dimension(550, 20));
       textFieldPanel.add(textField);
       textFieldPanel.setBackground(new Color(127, 108, 235));
       JLabel search = new JLabel();
       search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar.png")));
       textFieldPanel.add(search);
       this.mainPanel.add(textFieldPanel);
      
       this.mainPanel.add(Box.createRigidArea(new Dimension(0, 50))); // Adicionando espaçamento entre O textFieldPanel e os Produtos
       
       
       //Adicionando os Produtos
       
       if (!this.controleProduto.ListarProdutos().isEmpty()) {
           for (Produto produto : this.controleProduto.ListarProdutos()) {
               this.mainPanel.add(new PainelProduto(produto, this.controleProduto));
           }
       } else {
           JPanel semProdutos = new JPanel();
           semProdutos.setLayout(new FlowLayout());
           semProdutos.setMaximumSize(new Dimension(600,600));
           semProdutos.setBackground(new Color(72, 69, 150));
           semProdutos.add(new JLabel(new ImageIcon(getClass().getResource("/imagens/nenhum-produto-correspondente.png"))));
           
           this.mainPanel.add(semProdutos);
       }
       
       this.getContentPane().revalidate();
       this.getContentPane().repaint();
   }
   
}