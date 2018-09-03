package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.JdbUtil;
import controller.MetodologiaJdbcDAO;
import controller.TarefasJdbcDAO;

public class AppMetodologias extends JFrame {
	int i = 0;
	static String nomeJanela = "Metodologia";
	static int errorDanger = 0;
	static int errorInformation = 1;
	static int errorWarning = 2;
	static int errorMissing = 3;
	
	JPanel panelMetodologia = new JPanel();
	JPanel panelDados = new JPanel();
	
	JComboBox<String> cbMetodologias = new JComboBox<String>();
	JComboBox<String> cbTarefas = new JComboBox<String>();

	//   <-------->
	// 	 <-TAREFA->
	//   <-------->
	JLabel lblMetodologias = new JLabel("Metodologias: ");
	JLabel lblTarefas = new JLabel("Tarefas: ");
	
	JLabel lblTitulo = new JLabel("Título: ");
	JLabel lblPrazoEstimado = new JLabel("Prazo Estimado: ");
	JLabel lblDescricaoTarefa = new JLabel("Descrição: ");
	JLabel lblDataInicio = new JLabel("Data Inicio: ");
	JLabel lblDataTermino = new JLabel("Data Termino: ");

	JLabel txtTitulo = new JLabel();
	JLabel txtPrazoEstimado = new JLabel();
	JLabel txtDescricaoTarefa = new JLabel();
	JLabel txtDataInicio = new JLabel();
	JLabel txtDataTermino = new JLabel();
	
	JButton deletar = new JButton("Excluir");
	JButton editar = new JButton("Editar");
	
	JButton cadastrar = new JButton("Cadastrar");

	public AppMetodologias() {
		super(nomeJanela);
		Container paine = this.getContentPane();
		paine.setLayout(null);
		int g = 10,
			altura = 20,
			largura = 150,
			distanciaLateral = 10+g,
			distanciaSuperior = 20+g,
			distanciaTXT = largura+g+distanciaLateral,
			
			larguraPanel = distanciaLateral+largura+distanciaTXT,
			alturaPanel = distanciaSuperior*12,
					
			janelaAltura = alturaPanel+distanciaSuperior*2-g/2,
			janelaLargura = (larguraPanel+distanciaLateral*2-g/4)-2;
		
		//   <------------->
		// 	 <-METODOLOGIA->
		//   <------------->
		panelMetodologia.setLayout(null);
		panelMetodologia.setBorder(javax.swing.BorderFactory.createTitledBorder("Tarefas"));
		panelMetodologia.setBounds		(15, 10, larguraPanel, alturaPanel);
		
		lblMetodologias.setBounds		(distanciaLateral, distanciaSuperior*1, largura, altura);
		cbMetodologias.setBounds		(distanciaTXT, distanciaSuperior*1, largura, altura);
		
		cadastrar.setBounds 			(distanciaLateral, distanciaSuperior*10+altura, largura*2+distanciaLateral-g, altura);
		
		panelMetodologia.add(lblMetodologias);
		panelMetodologia.add(cbMetodologias);
		
		panelMetodologia.add(cadastrar);
		
		//   <------->
		// 	 <-DADOS->
		//   <------->
		panelDados.setLayout(null);
		panelDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados " + nomeJanela));
		panelDados.setBounds			(distanciaLateral, distanciaSuperior*2, largura*2+distanciaLateral-g, altura*12);

		lblTarefas.setBounds			(distanciaLateral, distanciaSuperior*1, largura-g*2, altura);
		cbTarefas.setBounds				(distanciaTXT-g*2, distanciaSuperior*1, largura-g*2, altura);
		
		lblTitulo.setBounds				(distanciaLateral, distanciaSuperior*2, largura, altura);
		lblPrazoEstimado.setBounds		(distanciaLateral, distanciaSuperior*3, largura, altura);
		lblDescricaoTarefa.setBounds	(distanciaLateral, distanciaSuperior*4, largura, altura);
		lblDataInicio.setBounds			(distanciaLateral, distanciaSuperior*5, largura, altura);
		lblDataTermino.setBounds		(distanciaLateral, distanciaSuperior*6, largura, altura);

		txtTitulo.setBounds				(distanciaTXT, distanciaSuperior*2, largura, altura);
		txtPrazoEstimado.setBounds		(distanciaTXT, distanciaSuperior*3, largura, altura);	
		txtDescricaoTarefa.setBounds	(distanciaTXT, distanciaSuperior*4, largura, altura);	
		txtDataInicio.setBounds			(distanciaTXT, distanciaSuperior*5, largura, altura);
		txtDataTermino.setBounds		(distanciaTXT, distanciaSuperior*6, largura, altura);
		
		editar.setBounds				(distanciaLateral, distanciaSuperior*7, largura-g*2, altura);
		deletar.setBounds				(distanciaTXT-g*2, distanciaSuperior*7, largura-g*2, altura);

		panelDados.add(lblTarefas);
		panelDados.add(lblTitulo);
		panelDados.add(lblPrazoEstimado);
		panelDados.add(lblDescricaoTarefa);
		panelDados.add(lblDataInicio);
		panelDados.add(lblDataTermino);
		
		panelDados.add(txtTitulo);
		panelDados.add(txtPrazoEstimado);
		panelDados.add(txtDescricaoTarefa);
		panelDados.add(txtDataInicio);
		panelDados.add(txtDataTermino);
		
		panelDados.add(editar);
		panelDados.add(deletar);
		panelDados.add(cbTarefas);

		panelMetodologia.add(panelDados);
		
		paine.add(panelMetodologia);
		
		attMetodologias();
		
		cbTarefas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attTarefa();
			}
		});
		
		cbMetodologias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attTarefas(cbMetodologias.getSelectedItem().toString());
			}
		});
		
		editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarMetodologia a = new EditarMetodologia(Integer.parseInt( cbTarefas.getSelectedItem().toString() ));
				dispose();
			}
		});
		
		deletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExcluirMetodologia a = new ExcluirMetodologia(Integer.parseInt( cbTarefas.getSelectedItem().toString() ));
				dispose();
			}
		});
		
		cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarMetodologias a = new CadastrarMetodologias();
				dispose();
			}
		});

		this.setResizable(false);
		this.setVisible(true);
		this.setSize(janelaLargura, janelaAltura);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public void attMetodologias() {
		try {
			cbMetodologias.removeAllItems();
			Connection connection = JdbUtil.getConnection();
			MetodologiaJdbcDAO met = new MetodologiaJdbcDAO(connection);
			
			i = 0;
			for ( String titulo: met.listar() ) {
				cbMetodologias.addItem(met.listar().get(i));
				i++;
			}
			
			attTarefas(cbMetodologias.getSelectedItem().toString());
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro em atualizar CB.METODOLOGIAS.",nomeJanela,errorDanger);
		}
	}
	
	public void attTarefas(String metodologia) {
		try {
			cbTarefas.removeAllItems();
			Connection connection = JdbUtil.getConnection();
			MetodologiaJdbcDAO met = new MetodologiaJdbcDAO(connection);
			
			i = 0;
			for ( String id: met.listarTarefas(metodologia) ) {
				cbTarefas.addItem(met.listarTarefas(metodologia).get(i));
				i++;
			}
			
			attTarefa();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro em atualizar CB.METODOLOGIAS.",nomeJanela, JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void attTarefa() {
		try {
			Connection connection = JdbUtil.getConnection();
			TarefasJdbcDAO tDAO = new TarefasJdbcDAO(connection);

			String[] resultado = tDAO.retornarInfTarefa( Integer.parseInt( cbTarefas.getSelectedItem().toString() ) );

			txtTitulo.setText(resultado[0]);
			txtPrazoEstimado.setText(resultado[1]);
			txtDescricaoTarefa.setText(resultado[2]);
			txtDataInicio.setText(resultado[3]);
			txtDataTermino.setText(resultado[4]);
	
		} catch (Exception ex) {
			//JOptionPane.showMessageDialog(null,"Erro ao atualizar dados da tarefas.",nomeJanela, JOptionPane.INFORMATION_MESSAGE);
			ex.printStackTrace();
		}
	}
	
	public static void main(String [] args) {
		AppMetodologias a = new AppMetodologias();
	}
}
