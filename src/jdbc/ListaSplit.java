package jdbc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Astronauta;
import modelo.Pais;
import dao.AstronautaDAO;

@SuppressWarnings("serial")
public class ListaSplit extends JFrame implements ListSelectionListener {

	private static ArrayList<Astronauta> astronautas;
	private static ArrayList<Pais> paises;
	private JList<String> listaAstro; // caixa de lista p/ escolha nome
	private JTextArea taInfo, taInfoBio; // areas de texto p/ info astronauta
	private Image icon = new ImageIcon("imagens/vetor/astronaut.ico").getImage();
	private JLabel label;

	public int 	larguraJanela = 200,
				alturaJanela = larguraJanela * 3/2;


	public ListaSplit() throws SQLException, IOException {
		super("Astronaut Database"); // ajusta titulo
		
		setIconImage(icon);
		
		// TENTA CONEXAO COM O BANCO DE DADOS
			
			try (Connection connection = AstronautaDB.getRemoteConnection()){
				AstronautaDAO dao = new AstronautaDAO(connection);
				astronautas = dao.pegaAstronautas(connection);
				paises = dao.pegaPaises(connection);
			}	catch (SQLException e){
				try (Connection connection = AstronautaDB.getLocalConnection()){
				AstronautaDAO dao = new AstronautaDAO(connection);
				astronautas = dao.pegaAstronautas(connection);
				paises = dao.pegaPaises(connection);
				}
			}
			
		// CRIA LISTA DE ASTRONAUTAS E CARREGA NO JLIST
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (Astronauta a : astronautas){
			model.addElement(a.getSobrenome());
		}
		
		listaAstro = new JList<>(model); // lista de sobrenomes de astronautas
		
		// painel de divisao
		JSplitPane split1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				new JLabel(new ImageIcon("imagens/vetor/titulo.png")), // imagem
				new JScrollPane(listaAstro)); // diretorio
		
		JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,// painel de divisao interno 
				label = new JLabel(), // janela com a Foto do astronauta
				new JScrollPane(taInfo = new JTextArea())); // area de texto

		JSplitPane split3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, 
				split2, 
				new JScrollPane(taInfoBio = new JTextArea())); // area de texto
		
		JSplitPane split4 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
				split1, 
				split3);
		
		getContentPane().add(split4, "Center"); // adiciona a janela
		
		// outros ajustes
		split1.setDividerLocation(50); 
		split2.setBorder(BorderFactory.createLoweredBevelBorder());
		split2.setDividerLocation(larguraJanela);
		split2.setOneTouchExpandable(true);
		split3.setDividerLocation(alturaJanela);
		listaAstro.addListSelectionListener(this);
		setSize(1200, 700); // dimensiona janela
		listaAstro.setSelectedIndex(0); // elemento inicial da lista
		//pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // acao fechar
	}

	// classe implementa listener
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//ImageIcon icon;
		int i = listaAstro.getSelectedIndex();
		if (i == -1)
			return;
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMM yyyy");
		StringBuilder sb = new StringBuilder("ID: \t" + astronautas.get(i).getIdAstronauta() + "\n");
		// exibe informacoes do astronauta: ID, nome, pa�s de origem, data de nascimento, sexo.
		sb.append("NOME: \t" + astronautas.get(i).getPrimeiro_Nome() + " " 
				+ astronautas.get(i).getNome_do_Meio() + " " 
				+ astronautas.get(i).getSobrenome() + "\n");
		sb.append("PAIS: \t"  + mostraPais(astronautas.get(i).getPais_Nasc()) + "\n");
		sb.append("ESTADO: \t" + astronautas.get(i).getEstado_Nasc() + "\n");
		sb.append("CIDADE: \t" + astronautas.get(i).getCidade_Nasc() + "\n");
		sb.append("NASC: \t" + (sdf.format(astronautas.get(i). getDtNasc()) + "\n"));
		/* if ((astronautas.get(i).getDtFalec().toString().charAt(0)!=' ') 
				&& (astronautas.get(i).getDtFalec().toString().charAt(0)!='0')) {
				sb.append("Falec: " + astronautas.get(i).getDtFalec() + "\n");
		} */
		sb.append("SEXO: \t" + (astronautas.get(i).getSexo().charAt(0) == 'M' ? "masculino" : "feminino") + "\n\n");
		
		StringBuilder sb2 = new StringBuilder("BIOGRAFIA: " + "\n\n" + astronautas.get(i).getInfo() + "\n\n");
		sb2.append("MISSOES: " + "\n\n");
		for (String s : astronautas.get(i).getMissao()){
			sb2.append(s + "\n");
		}
		taInfo.setText(sb.toString());
		taInfo.setLineWrap(true);
		taInfo.setWrapStyleWord(true);
		taInfo.setFont(new Font("Serif", Font.BOLD, 20));
		
		taInfoBio.setText(sb2.toString());
		taInfoBio.setLineWrap(true);
		taInfoBio.setWrapStyleWord(true);
		
		//icon = new ImageIcon("imagens/people/" + astronautas.get(i).getFoto()); 
		
		BufferedImage img = null;
		try {
				img = ImageIO.read(new File("imagens/people/" + astronautas.get(i).getFoto())); 

			} catch (IOException eio){
			
			}
		
		BufferedImage imagem = formataImagem(img, larguraJanela, alturaJanela);
		
		
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));  
		label.setSize(larguraJanela, alturaJanela);
		label.setIcon(new ImageIcon(imagem));  
	}
	
	// recebe uma imagem e retorna essa imagem no padrão LARGURA = larguraJanela, ALTURA = alturaJanela
	private BufferedImage formataImagem(BufferedImage bi, int largura, int altura) throws HeadlessException {

		BufferedImage aux = new BufferedImage(largura,altura, bi.getType());
		Graphics2D gg = aux.createGraphics();
		AffineTransform at = AffineTransform.getScaleInstance((double) largura/bi.getWidth(), (double) altura/bi.getHeight());
		gg.drawRenderedImage(bi, at);
		return aux;
	}
	   
	public String mostraPais(String paisISO){
		for (Pais p : paises){
			if (p.getId().equalsIgnoreCase(paisISO)){
				return p.getNome();
			} 
		}
		return null;
	}

	public static void main(String s[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new ListaSplit().setVisible(true);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}