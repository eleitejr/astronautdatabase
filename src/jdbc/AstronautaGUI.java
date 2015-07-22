package jdbc;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Astronauta;
import modelo.ListaDeAstronautas;
import modelo.Pais;
import dao.AstronautaDAO;

@SuppressWarnings("serial")
public class AstronautaGUI extends JFrame implements ListSelectionListener {

	private static ArrayList<Astronauta> astronautas;
	private static ArrayList<Pais> paises;
	private static DefaultListModel<Astronauta>		modelAstro = new DefaultListModel<>();
	private static DefaultListModel<Pais> 			modelPais =  new DefaultListModel<>();
	private static ListaDeAstronautas listaAstro; 			// caixa de lista p/ escolha nome
	private static JList<Pais> listaPais; 					// caixa de lista p/ escolha pais
	private static JTextArea taInfo; 					// areas de texto p/ info astronauta
	private static JTextArea taInfoBio;
	private static JLabel label; 						// label que contem a foto do astronauta
	
	private File arqFonte = new File ("./fontes/Spaceport.ttf"); 
	
	public static int 	larguraJanela = 140;			// o tamanho da janela da foto
	public static int 	alturaJanela = larguraJanela * 3/2;

	private static String sArquivo[] = {
	      "Novo","new16.gif","N", 
	      "Abrir ...","open16.gif","A",
	      "Salvar","save16.gif","S", 
	      "Salvar como ...","saveas16.gif","c",
	      null, null, null, 
	      "Imprimir ...","print16.gif","I", 
	      null, null, null, 
	      "Sair","blank16.gif","r"};
	
	private static String sEditar[] = {
	      "Recortar","cut16.gif","R", 
	      "Copiar","copy16.gif","C",
	      "Colar","paste16.gif","o", 
	      null, null, null,
	      "Excluir","delete16.gif","x", 
	      "Selecionar tudo","blank16.gif","t",
	      null, null, null};
	
	private static String sBanco[] = {
	      "Exportar","database-export.png","E", 
	      "Importar","database-import.png","m",
	      "Nuvem","database-cloud.png","u", 
	      "Rede","database-network.png","R",
	      null, null, null, 
	      "Estatísticas","database-property.png","s", 
	      null, null, null, 
	      "Editar registro", "database--pencil.png", "d",
	      "Inserir registro","database-insert.png","I", 
	      "Remover registro", "database-delete.png","e"};
	
	private static String sPais[] = {
										"Todos","ALL.png","T",
		   								"Estados Unidos","USA.png","E",
		   								"Russia","RUS.png","R",
		   								"China","CHN.png","C",
		   								"Japão","JPN.png","J",
		   								"Alemanha","DEU.png","A",
		   								"França","FRA.png","F",
		   								"Canadá","CAN.png","d",
		   								"Itália", "ITA.png","I",
		   								"Brasil","BRA.png","B",
		   								"Bélgica", "BEL.png", "L",
		   								"Áustria", "AUT.png", "u",
		   								"Índia", "IND.png", "n",
		   								"Rep. Checa","CZE.png","h"
		   								}; 
	   
	private static String sAjuda[] = {
	      "Ajuda","help16.gif","A", null, null, null,
	      "Sobre ...","about16.gif","S"};
	
	static String 	strSexo = "ALL", 
					strMissao = "", 
					strPais = "ALL",
					strDataNasc = "";

	/*
	 *  Construtor da Interface Gráfica
	 */
	public AstronautaGUI() throws SQLException, IOException {
		super("Astronaut Database"); // ajusta titulo
	    setIconImage(new ImageIcon("./imagens/vetor/astronaut-icon.png").getImage());  
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // acao fechar
	    
		inicializaLista();
	}

	/**
	 * CRIAÇÃO DOS MENUS
	 */
	private void criaMenu() {
		
	      JMenuBar mb = new JMenuBar();
	      MenuHandler mh = new MenuHandler();
	      MenuRadioHandler mrh = new MenuRadioHandler();
	      MenuPaisCheckBoxHandler mcbh = new MenuPaisCheckBoxHandler();
	      
	      MenuBuilder.imagePrefix = "./imagens/vetor/";
	      mb.add(MenuBuilder.newMenu("Arquivo", 'A', sArquivo, mh));
	      mb.add(MenuBuilder.newMenu("Editar", 'E', sEditar, mh));
	      mb.add(MenuBuilder.newMenu("Banco de Dados", 'B', sBanco, mh));
	      
	      JMenu menuConsulta = new JMenu("Consulta"); 
			      JMenuItem miMissao = new JMenuItem("Missão");
			      miMissao.setIcon(new ImageIcon("./imagens/vetor/Space-Shuttle-icon.png"));
			      JMenuItem miDataNasc = new JMenuItem("Data de Nascimento");
			      JMenuItem miOrdena = new JMenuItem("Ordena seleção por...");
			      miOrdena.setIcon(new ImageIcon("./imagens/vetor/sort-alphabet.png"));
			      JMenuItem miAtualiza = new JMenuItem("Atualiza");
			      miAtualiza.addActionListener(mh);
			      miAtualiza.setIcon(new ImageIcon("./imagens/vetor/database-sql.png"));
			      
				   JMenu menuSexo = new JMenu("Sexo");
					      menuSexo.setIcon(new ImageIcon("./imagens/vetor/gender.png"));
						      JRadioButtonMenuItem miAmbos = new JRadioButtonMenuItem("Ambos", true);
						      miAmbos.setIcon(new ImageIcon("./imagens/vetor/toilet.png"));
						      JRadioButtonMenuItem miMale = new JRadioButtonMenuItem("Masculino", false);
						      miMale.setIcon(new ImageIcon("./imagens/vetor/toilet-male.png"));
						      JRadioButtonMenuItem miFemale = new JRadioButtonMenuItem("Feminino", false);
						      miFemale.setIcon(new ImageIcon("./imagens/vetor/toilet-female.png"));
						      
						      miAmbos.addItemListener(mrh);
						      miMale.addItemListener(mrh);
						      miFemale.addItemListener(mrh);
						      ButtonGroup bg = new ButtonGroup();
						      bg.add(miMale);bg.add(miFemale);bg.add(miAmbos);
						      
						      menuSexo.add(miMale);menuSexo.add(miFemale);menuSexo.add(miAmbos);
						      
						  JMenu menuPais = MenuBuilder.newMenuCheckBox("Pais", 'P', sPais, mcbh);
						  menuPais.setIcon(new ImageIcon("./imagens/flags/_united-nations.png"));
						      
				      menuConsulta.add(menuPais);
				      menuConsulta.add(menuSexo);
				      menuConsulta.add(miMissao);
				      menuConsulta.add(miDataNasc);
				      menuConsulta.addSeparator();
				      menuConsulta.add(miOrdena);
				      menuConsulta.addSeparator();
				      menuConsulta.add(miAtualiza);
				      menuConsulta.setMnemonic('C');
			      mb.add(menuConsulta);
	      
	      mb.add(MenuBuilder.newMenu("Ajuda", 'u', sAjuda, mh));
	      setJMenuBar(mb); //JMenu menu = mb.getMenu(1);
	
	}

	/**
	 * Cria painéis de divisão
	 */
	private void criaPainel() {
		// painel de divisao
		JSplitPane split1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				new JLabel(new ImageIcon("imagens/vetor/logoAstroDB.png")), // imagem
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
		Font fonte;
		try {
			fonte = Font.createFont(Font.TRUETYPE_FONT, arqFonte);
			fonte = fonte.deriveFont(Font.PLAIN, 14);
			
		} catch (FontFormatException | IOException e1) {
			fonte = new Font("Verdana", Font.BOLD, 14);
			//e1.printStackTrace();
		}
		split1.setDividerLocation(50); 
		split2.setBorder(BorderFactory.createLoweredBevelBorder());
		split2.setDividerLocation(larguraJanela);
		split2.setOneTouchExpandable(true);
		split3.setDividerLocation(alturaJanela);
		taInfo.setEditable(false);
		taInfo.setAutoscrolls(false);
		taInfoBio.setEditable(false);
		taInfo.setSelectionStart(0);
		taInfoBio.setSelectionStart(0);
		taInfo.setLineWrap(true);
		taInfo.setWrapStyleWord(true);
		taInfo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		taInfoBio.setLineWrap(true);
		taInfoBio.setWrapStyleWord(true);
		setSize(1200, 700); // dimensiona janela
		listaAstro.addListSelectionListener(this);
		listaAstro.setSelectedIndex(0); // elemento inicial da lista
		//pack();
	}
	/**
	 * @throws HeadlessException
	 * @throws SQLException
	 */
	
	// TENTA CONEXAO COM O BANCO DE DADOS
	private void consultaSQL() throws HeadlessException, SQLException {
		try (Connection con = AstronautaDB.getRemoteConnection()){	
			atualizaDados(con);
		}	catch (SQLException e){
			JOptionPane.showMessageDialog(null, 
					"Não foi possível estabelecer conexão remota. Tentando conexão local...", 
					"Erro", JOptionPane.ERROR_MESSAGE);
			try (Connection con = AstronautaDB.getLocalConnection()){
				atualizaDados(con);
			}
		}
		
	}

	// atualiza dados
	public void atualizaDados(Connection connection)
			throws SQLException {
		AstronautaDAO dao = new AstronautaDAO(connection);
		astronautas = dao.pegaAstronautas(connection);
		paises = dao.pegaPaises(connection);
	}
	
	
	/**
	 * Cria listas de astronautas e países
	 */
	public void criaListas() {
		// CRIA LISTA DE ASTRONAUTAS E CARREGA NO JLIST
		 
		for (Astronauta a : astronautas){
			modelAstro.addElement(a);
		}
			
		System.out.println("Registros: " + modelAstro.getSize());
				
		// CRIA LISTA DE PAISES E CARREGA NO JLIST
		
		for (Pais p : paises){
			modelPais.addElement(p);
		}
		/*
		 * @FIX listaPais = new JList<String>(modelPais); // de paises
		 */
		
		listaAstro = new ListaDeAstronautas(); 
		listaAstro.setModel(modelAstro);
		listaPais = new JList<Pais>(modelPais);
		listaAstro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaPais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}

	/**
	 * @method mostraStatusListaAstro
	 * Mostra o estado atual da lista de astronautas
	 */
	public void mostraStatusListaAstro() {
		System.out.println("LISTA ASTRONAUTAS");
		System.out.println("Pais = " + strPais);
		System.out.println("Sexo = " + strSexo);
		for (Astronauta astronauta : astronautas) {
			System.out.println(astronauta);
		}
	}

	// classe implementa listener
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		int i = listaAstro.getSelectedIndex();
		if (i == -1)
			return;
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMM yyyy");
		
		// CRIA A ÁREA DE TEXTO COM INFO DO ASTRONAUTA
		// exibe informacoes do astronauta: ID, nome, pa�s de origem, data de nascimento, sexo.
		Astronauta selecionado = listaAstro.getSelectedValue();
		StringBuilder sb = new StringBuilder("ID: \t" + selecionado.getIdAstronauta() + "\n");
		String nome = selecionado.getPrimeiro_Nome() + " " 
					+ selecionado.getNome_do_Meio() + " " 
					+ selecionado.getSobrenome() + "\n";
		String pais = mostraPais(selecionado.getPais_Nasc()) + "\n";
		String estado = selecionado.getEstado_Nasc() + "\n";
		String cidade = selecionado.getCidade_Nasc() + "\n";
		String dataNasc = (sdf.format(selecionado.getDtNasc()) + "\n");
		Date dataFalec = selecionado.getDtFalec();
		String strDataFalec = dataFalec.toString();
		
		sb.append("NOME: \t" + nome	);
		sb.append("PAIS: \t"  + pais );
		sb.append("ESTADO: \t" + estado );
		sb.append("CIDADE: \t" + cidade );
		sb.append("NASC: \t" + dataNasc);

		if (!(strDataFalec.equals("2100-01-01")) && !(strDataFalec.isEmpty())) 
			{
				sb.append("FALEC: \t" + (sdf.format(dataFalec) + "\n"));
		}
		
		sb.append("SEXO: \t" + (selecionado.getSexo().charAt(0) == 'M' ? "masculino" : "feminino") + "\n\n");
		
		StringBuilder sb2 = new StringBuilder("BIOGRAFIA: " + "\n\n" + selecionado.getInfo() + "\n\n");
		sb2.append("MISSOES: " + "\n\n");
		for (String s : selecionado.getMissao()){
			sb2.append(s + "\n");
		}
		
		taInfo.setText(sb.toString());
		taInfoBio.setText(sb2.toString());
		
		BufferedImage img = null;
		try {
				img = ImageIO.read(new File("./imagens/people/" + selecionado.getFoto())); 

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
	
	
	   private class MenuHandler implements ActionListener {
		   
		   	@Override	
		    public void actionPerformed(ActionEvent e) {
		        
		   		String acao = ((JMenuItem)e.getSource()).getText();
		        
		         if (acao.equals(sArquivo[1*3])) {
		            JFileChooser dialogo = new JFileChooser();
		            int resultado = dialogo.showOpenDialog(AstronautaGUI.this);
		            if (resultado == JFileChooser.APPROVE_OPTION) {
		            	// TODO
		               // File arqEscolhido = dialogo.getSelectedFile();
		               // processamento do arquivo escolhido
		               // taEditor.append("Arquivo: "+ arqEscolhido.getName() + "\n");
		            }
		         }
		         
		         if (acao.equals(sArquivo[7*3])) {System.exit(0);}
		         
		         if (acao.equals("Estatísticas")){
		        	 // TODO
		         }
		         
		         if (acao.equals("Sobre ...")){
		        	 
		        	 JOptionPane.showMessageDialog(
		        			 AstronautaGUI.this, 
		        			 "(C) Erasmo Leite Jr 2015 - eleitejr@gmail.com", 
		        			 "Astronaut Database", 
		        			 JOptionPane.INFORMATION_MESSAGE);
		         }
		         
		         if (acao.equals("Atualiza")){
		         		System.out.println("atualizando ----------------------------------------->");
		         		listaAstro.atualizaLista(astronautas, strSexo, strPais);
		         		System.out.println("Consulta atualizada com sucesso-------> " + listaAstro.getModel().getSize() + " registros encontrados.");
		         	}
		      }

	   }
     		 
	   private class MenuPaisCheckBoxHandler implements ItemListener {

			@Override
			public void itemStateChanged(ItemEvent eventoSelecionaPais) {
				
				String paisSel = ((JMenuItem)eventoSelecionaPais.getSource()).getIcon().toString();
				
				// A expressão abaixo retorna o código ISO-3 do país, a partir do ícone armazenado no JMenuItem
				strPais = paisSel.substring(16,19);
		
				}
				
			}

	   
	   private class MenuRadioHandler implements ItemListener {

			@Override
			public void itemStateChanged(ItemEvent eventoSelecionaSexo) {
				String sexoSel = ((JMenuItem)eventoSelecionaSexo.getSource()).getText();
				
				switch(sexoSel) {
				
					case "Ambos" : default	:	{strSexo = "ALL"; 	break;}
					case "Masculino" 		:	{strSexo = "M"; 	break;}
					case "Feminino" 		:	{strSexo = "F"; 	break;}
				}	
				
				System.out.println("atualizando ----------------------------------------->");
         		listaAstro.atualizaLista(astronautas, strSexo, strPais);
         		System.out.println("Consulta atualizada com sucesso-------> " + listaAstro.getModel().getSize() + " registros encontrados.");
			}
	   }
	       
	
	   private void inicializaLista() throws HeadlessException, SQLException {
			    /*
			     * INICIALIZA DADOS DAS LISTAS E CRIA O PAINEL PRINCIPAL  
			     */
			criaMenu();
			consultaSQL(); 
			criaListas();
			criaPainel();
		}

	public String mostraPais(String paisISO){
		for (Pais p : paises){
			if (p.getId().equalsIgnoreCase(paisISO)){
				return p.getNome();
			} 
		}
		return null;
	}
	
	public String mostraISO(String pais){
		for (Pais p : paises){
			if (p.getNome().equalsIgnoreCase(pais)){
				return p.getId();
			} 
		}
		return null;
	}

	/*
	 * Método MAIN()
	 */
	public static void main(String s[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new AstronautaGUI().setVisible(true);
				} catch (SQLException | IOException e) {
					JOptionPane.showMessageDialog(
							null, 
							"Erro de Conexão!", 
							"Alerta", 
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
	}
}
