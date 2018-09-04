package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AppRelacoes extends JFrame {
	int i = 0;
	static String nomeJanela = "Relações";
	static int errorDanger = 0;
	static int errorInformation = 1;
	static int errorWarning = 2;
	static int errorMissing = 3;
	
	JPanel panelRelacoes = new JPanel();
	JPanel panelDados = new JPanel();
	
	JLabel lblRelacoes = new JLabel("Relações: ");
	JComboBox<String> cbRelacoes = new JComboBox<String>();

	//   <--------->
	// 	 <-TAREFAS->
	//   <--------->
	JLabel lblTarefas = new JLabel("Tarefas: ");
	JComboBox<String> cbTarefas = new JComboBox<String>();
	
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

	public AppRelacoes() {
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
		
		//   <--------->
		// 	 <-RELAÇÃO->
		//   <--------->
		panelRelacoes.setLayout(null);
		panelRelacoes.setBorder(javax.swing.BorderFactory.createTitledBorder(nomeJanela));
		panelRelacoes.setBounds		(15, 10, larguraPanel, alturaPanel);
		
		lblRelacoes.setBounds		(distanciaLateral, distanciaSuperior*1, largura, altura);
		cbRelacoes.setBounds		(distanciaTXT, distanciaSuperior*1, largura, altura);
		
		editar.setBounds			(distanciaLateral, distanciaSuperior*9+altura, largura, altura);
		deletar.setBounds			(distanciaTXT, distanciaSuperior*9+altura, largura, altura);
		cadastrar.setBounds 		(distanciaLateral, distanciaSuperior*10+altura, largura*2+distanciaLateral-g, altura);
		
		panelRelacoes.add(lblRelacoes);
		panelRelacoes.add(cbRelacoes);
		
		panelRelacoes.add(editar);
		panelRelacoes.add(deletar);
		panelRelacoes.add(cadastrar);
		
		//   <------->
		// 	 <-DADOS->
		//   <------->
		panelDados.setLayout(null);
		panelDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Tarefas"));
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

		panelRelacoes.add(panelDados);
		
		paine.add(panelRelacoes);
		
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(janelaLargura, janelaAltura);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	

	
	public static void main(String [] args) {
		AppRelacoes a = new AppRelacoes();
	}
}
