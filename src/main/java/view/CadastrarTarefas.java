package view;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.JdbUtil;
import controller.MetodologiaJdbcDAO;
import controller.TarefasJdbcDAO;
import model.Metodologia;
import model.Tarefas;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionEvent;

public class CadastrarTarefas extends JFrame {
	static String nomeJanela = "Criar Tarefas";
	
	JPanel panelTarefa = new JPanel();
	
	JLabel lblNomeTarefa = new JLabel("Nome da tarefa: ");
	JTextField txtNomeTarefa = new JTextField();
	
	JLabel lblMetodologia = new JLabel("Metodologia: ");
	JTextField txtMetodologia = new JTextField();

	JLabel lblDescricaoTarefa = new JLabel("Descrição: ");
	JTextField txtDescricaoTarefa = new JTextField();
	
	JLabel lblPrazoEstimado = new JLabel("Prazo estimado (AAAA-MM-DD): ");
	JFormattedTextField txtPrazoEstimado = new JFormattedTextField();
	
	JLabel lblDataInicio = new JLabel("Data de início (AAAA-MM-DD): ");
	JFormattedTextField txtDataInicio = new JFormattedTextField();
	
	JLabel lblDataTermino = new JLabel("Data de término (AAAA-MM-DD): ");
	JFormattedTextField txtDataTermino = new JFormattedTextField();

	JButton novoCadastro = new JButton("Cadastrar");
	
	public CadastrarTarefas() {
		super("Cadastro de Tarefas");
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
		panelTarefa.setLayout(null);
		panelTarefa.setBorder(javax.swing.BorderFactory.createTitledBorder(nomeJanela));
		panelTarefa.setBounds				(15, 10, larguraPanel, alturaPanel);

		
		lblNomeTarefa.setBounds			(distanciaLateral, distanciaSuperior*1, largura, altura);
		lblDescricaoTarefa.setBounds	(distanciaLateral, distanciaSuperior*2, largura, altura);
		lblMetodologia.setBounds		(distanciaLateral, distanciaSuperior*3, largura, altura);
		lblPrazoEstimado.setBounds		(distanciaLateral, distanciaSuperior*4, largura*2, altura);
		lblDataInicio.setBounds			(distanciaLateral, distanciaSuperior*5, largura*2, altura);
		lblDataTermino.setBounds		(distanciaLateral, distanciaSuperior*6, largura*2, altura);

		txtNomeTarefa.setBounds			(distanciaTXT, distanciaSuperior*1, largura, altura);
		txtDescricaoTarefa.setBounds	(distanciaTXT, distanciaSuperior*2, largura, altura);
		txtMetodologia.setBounds		(distanciaTXT, distanciaSuperior*3, largura, altura);
		txtPrazoEstimado.setBounds		(distanciaTXT+distanciaLateral*4+g/4, distanciaSuperior*4, 67, altura);
		txtDataInicio.setBounds			(distanciaTXT+distanciaLateral*4+g/4, distanciaSuperior*5, 67, altura);
		txtDataTermino.setBounds		(distanciaTXT+distanciaLateral*4+g/4, distanciaSuperior*6, 67, altura);

		novoCadastro.setBounds			(distanciaLateral, distanciaSuperior*8, largura, altura);
		
		panelTarefa.add(lblNomeTarefa);
		panelTarefa.add(lblPrazoEstimado);
		panelTarefa.add(lblDescricaoTarefa);
		panelTarefa.add(lblMetodologia);
		panelTarefa.add(lblDataInicio);
		panelTarefa.add(lblDataTermino);
		
		panelTarefa.add(txtNomeTarefa);
		panelTarefa.add(txtPrazoEstimado);
		panelTarefa.add(txtDescricaoTarefa);
		panelTarefa.add(txtMetodologia);
		panelTarefa.add(txtDataInicio);
		panelTarefa.add(txtDataTermino);
		
		panelTarefa.add(novoCadastro);
		
		paine.add(panelTarefa);
		
		try {
			MaskFormatter mskPrazo = new MaskFormatter("####/##/##");
			mskPrazo.setPlaceholderCharacter('_');
			mskPrazo.install(txtPrazoEstimado);
			
			MaskFormatter mskDtInicio = new MaskFormatter("####/##/##");
			mskDtInicio.setPlaceholderCharacter('_');
			mskDtInicio.install(txtDataInicio);
			
			MaskFormatter mskDtTermino = new MaskFormatter("####/##/##");
			mskDtTermino.setPlaceholderCharacter('_');
			mskDtTermino.install(txtDataTermino);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		
		
		
		novoCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tarefas tarefa1 = new Tarefas();
				Metodologia metodologia1 = new Metodologia();
				
				if (!txtNomeTarefa.getText().isEmpty() && !txtPrazoEstimado.getText().isEmpty() && !txtDataInicio.getText().isEmpty()) {
					try {
						Connection connection = JdbUtil.getConnection();
						TarefasJdbcDAO tarefasJdbcDAO = new TarefasJdbcDAO(connection);
						MetodologiaJdbcDAO metodologiaJdbcDAO = new MetodologiaJdbcDAO(connection);

						tarefa1.setTitulo(txtNomeTarefa.getText());
						tarefa1.setPrazo_estimado(txtPrazoEstimado.getText());
						tarefa1.setDescricao(txtDescricaoTarefa.getText());
						tarefa1.setData_inicio(txtDataInicio.getText());
						tarefa1.setData_termino(txtDataTermino.getText());
						
						tarefasJdbcDAO.salvar(tarefa1);
					
						metodologia1.setId(tarefasJdbcDAO.ultimaTarefa());
						metodologia1.setTitulo(txtMetodologia.getText());

						metodologiaJdbcDAO.salvar(metodologia1);
						
						JOptionPane.showMessageDialog(null,"Cadastro da tarefa " + tarefasJdbcDAO.ultimaTarefa() + " realizado com sucesso.",nomeJanela, JOptionPane.INFORMATION_MESSAGE);
						CadastrarRelTarefaPessoa reltarefapessoa = new CadastrarRelTarefaPessoa(tarefasJdbcDAO.ultimaTarefa());
					} catch(Exception ex) {
						JOptionPane.showMessageDialog(null,"Erro",nomeJanela, JOptionPane.CLOSED_OPTION);
						ex.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null,"Preencha todos os campos",nomeJanela, JOptionPane.CLOSED_OPTION);
				}
			}
		});
		
		
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(janelaLargura, janelaAltura);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	
	public static void main(String[] args) {
		CadastrarTarefas janela = new CadastrarTarefas();
	}
}
