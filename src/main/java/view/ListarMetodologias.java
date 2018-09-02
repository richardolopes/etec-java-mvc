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

public class ListarMetodologias extends JFrame {
	int i = 0;
	static int errorDanger = 0;
	static int errorInformation = 1;
	static int errorWarning = 2;
	static int errorMissing = 3;
	static String nomeJanela = "Metodologia";
	
	JPanel panelMetodologia = new JPanel();
	
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

	public ListarMetodologias() {
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
			alturaPanel = distanciaSuperior*10-altura,
					
			janelaAltura = alturaPanel+distanciaSuperior*2-g/2,
			janelaLargura = (larguraPanel+distanciaLateral*2-g/4)-2;
		
		//   <-------->
		// 	 <-TAREFA->
		//   <-------->
		panelMetodologia.setLayout(null);
		panelMetodologia.setBorder(javax.swing.BorderFactory.createTitledBorder("Tarefas"));
		panelMetodologia.setBounds		(15, 10, larguraPanel, alturaPanel);
		
		
		
		lblMetodologias.setBounds		(distanciaLateral, distanciaSuperior*1, largura, altura);
		lblTarefas.setBounds			(distanciaLateral, distanciaSuperior*2, largura, altura);
		
		cbMetodologias.setBounds		(distanciaTXT, distanciaSuperior*1, largura, altura);
		cbTarefas.setBounds				(distanciaTXT, distanciaSuperior*2, largura, altura);
		
		lblTitulo.setBounds				(distanciaLateral, distanciaSuperior*4, largura, altura);
		lblPrazoEstimado.setBounds		(distanciaLateral, distanciaSuperior*5, largura, altura);
		lblDescricaoTarefa.setBounds	(distanciaLateral, distanciaSuperior*6, largura, altura);
		lblDataInicio.setBounds			(distanciaLateral, distanciaSuperior*7, largura, altura);
		lblDataTermino.setBounds		(distanciaLateral, distanciaSuperior*8, largura, altura);

		txtTitulo.setBounds				(distanciaTXT, distanciaSuperior*4, largura, altura);
		txtPrazoEstimado.setBounds		(distanciaTXT, distanciaSuperior*5, largura, altura);	
		txtDescricaoTarefa.setBounds	(distanciaTXT, distanciaSuperior*6, largura, altura);	
		txtDataInicio.setBounds			(distanciaTXT, distanciaSuperior*7, largura, altura);
		txtDataTermino.setBounds		(distanciaTXT, distanciaSuperior*8, largura, altura);

		panelMetodologia.add(lblMetodologias);
		panelMetodologia.add(lblTarefas);
		
		panelMetodologia.add(lblTitulo);
		panelMetodologia.add(lblPrazoEstimado);
		panelMetodologia.add(lblDescricaoTarefa);
		panelMetodologia.add(lblDataInicio);
		panelMetodologia.add(lblDataTermino);
		
		panelMetodologia.add(txtTitulo);
		panelMetodologia.add(txtPrazoEstimado);
		panelMetodologia.add(txtDescricaoTarefa);
		panelMetodologia.add(txtDataInicio);
		panelMetodologia.add(txtDataTermino);
		
		panelMetodologia.add(cbMetodologias);
		panelMetodologia.add(cbTarefas);
		
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
		ListarMetodologias a = new ListarMetodologias();
	}
}
