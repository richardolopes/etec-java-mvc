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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.JdbUtil;
import controller.PessoasJdbcDAO;
import controller.Rel_tarefa_pessoaJdbcDAO;
import controller.TarefasJdbcDAO;

public class ListarPessoas extends JFrame {
	int i = 0;
	String email = "";
	static String nomeJanela = "Listar Pessoas";
	static int errorDanger = 0;
	static int errorInformation = 1;
	static int errorWarning = 2;
	static int errorMissing = 3;
	
	JPanel panelPessoa = new JPanel();
	JPanel panelTarefa = new JPanel();

	JLabel lblEmailPessoa = new JLabel("Pessoas: ");
	
	JLabel lblEmail = new JLabel("E-mail: ");
	JLabel lblNome = new JLabel("Nome: ");
	JLabel lblSexo = new JLabel("Sexo: ");
	
	JLabel txtEmail = new JLabel("");
	JLabel txtNome = new JLabel("");
	JLabel txtSexo = new JLabel("");
	
	// Tarefa
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
	
	JComboBox<String> cbTarefas = new JComboBox<String>();
	JComboBox<String> cbPessoas = new JComboBox<String>();

	public ListarPessoas() {
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
			//janelaAltura = (alturaPanel+distanciaSuperior)*2,
			
			//janelaLargura = (larguraPanel+distanciaLateral*2-g-g/3)*2;
			janelaLargura = ((larguraPanel+distanciaLateral)*2+distanciaLateral-g/2)-2;

		//   <-------->
		//   <-PESSOA->
		//	 <-------->
		panelPessoa.setLayout(null);
		panelPessoa.setBorder(javax.swing.BorderFactory.createTitledBorder(nomeJanela));
		panelPessoa.setBounds			(15, 10, larguraPanel, alturaPanel);
		
		lblEmailPessoa.setBounds		(distanciaLateral, distanciaSuperior*1, largura, altura);
		cbPessoas.setBounds				(distanciaTXT, distanciaSuperior*1, largura, altura);
		
		lblEmail.setBounds				(distanciaLateral, distanciaSuperior*3, largura, altura);
		lblNome.setBounds				(distanciaLateral, distanciaSuperior*4, largura, altura);
		lblSexo.setBounds				(distanciaLateral, distanciaSuperior*5, largura, altura);
		
		txtEmail.setBounds				(distanciaTXT, distanciaSuperior*3, largura, altura);
		txtNome.setBounds				(distanciaTXT, distanciaSuperior*4, largura, altura);
		txtSexo.setBounds				(distanciaTXT, distanciaSuperior*5, largura, altura);
		
		panelPessoa.add(lblEmailPessoa);
		panelPessoa.add(cbPessoas);
		
		panelPessoa.add(lblEmail);
		panelPessoa.add(lblNome);
		panelPessoa.add(lblSexo);
		
		panelPessoa.add(txtEmail);
		panelPessoa.add(txtNome);
		panelPessoa.add(txtSexo);
		
		//   <-------->
		// 	 <-TAREFA->
		//   <-------->
		panelTarefa.setLayout(null);
		panelTarefa.setBorder(javax.swing.BorderFactory.createTitledBorder("Tarefas"));
		panelTarefa.setBounds			(larguraPanel+15*2, 10, larguraPanel, alturaPanel);
	
		lblTarefas.setBounds			(distanciaLateral, distanciaSuperior*1, largura, altura);
		lblTitulo.setBounds				(distanciaLateral, distanciaSuperior*3, largura, altura);
		lblPrazoEstimado.setBounds		(distanciaLateral, distanciaSuperior*4, largura, altura);
		lblDescricaoTarefa.setBounds	(distanciaLateral, distanciaSuperior*5, largura, altura);
		lblDataInicio.setBounds			(distanciaLateral, distanciaSuperior*6, largura, altura);
		lblDataTermino.setBounds		(distanciaLateral, distanciaSuperior*7, largura, altura);
		
		cbTarefas.setBounds				(distanciaTXT, distanciaSuperior*1, largura, altura);
		
		txtTitulo.setBounds				(distanciaTXT, distanciaSuperior*3, largura, altura);
		txtPrazoEstimado.setBounds		(distanciaTXT, distanciaSuperior*4, largura, altura);	
		txtDescricaoTarefa.setBounds	(distanciaTXT, distanciaSuperior*5, largura, altura);	
		txtDataInicio.setBounds			(distanciaTXT, distanciaSuperior*6, largura, altura);
		txtDataTermino.setBounds		(distanciaTXT, distanciaSuperior*7, largura, altura);
	
		panelTarefa.add(lblTarefas);
		panelTarefa.add(lblTitulo);
		panelTarefa.add(lblPrazoEstimado);
		panelTarefa.add(lblDescricaoTarefa);
		panelTarefa.add(lblDataInicio);
		panelTarefa.add(lblDataTermino);
		
		panelTarefa.add(txtTitulo);
		panelTarefa.add(txtPrazoEstimado);
		panelTarefa.add(txtDescricaoTarefa);
		panelTarefa.add(txtDataInicio);
		panelTarefa.add(txtDataTermino);
		
		panelTarefa.add(cbTarefas);

		paine.add(panelTarefa);
		paine.add(panelPessoa);
		
		attPessoas();
		attTarefas(cbPessoas.getSelectedItem().toString());
		
		// -----
		cbTarefas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attTarefa(Integer.parseInt( cbTarefas.getSelectedItem().toString() ));
			}
		});
		
		cbPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				email = cbPessoas.getSelectedItem().toString();
				attPessoa(email);
			}
		});
		

		this.setResizable(false);
		this.setVisible(true);
		this.setSize(janelaLargura, janelaAltura);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public void attPessoas() {
		try {
			cbPessoas.removeAllItems();
			Connection connection = JdbUtil.getConnection();
			PessoasJdbcDAO pes = new PessoasJdbcDAO(connection);
			
			i = 0;
			for ( String titulo: pes.listar() ) {
				cbPessoas.addItem(pes.listar().get(i));
				i++;
			}
			
			attTarefas(cbPessoas.getSelectedItem().toString());
			attPessoa(cbPessoas.getSelectedItem().toString());
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro ao atualizar CB.PESSOAS.",nomeJanela,errorDanger);
		}
	}
	
	public void attPessoa(String email) {
		try {
			Connection connection = JdbUtil.getConnection();
			PessoasJdbcDAO pDAO = new PessoasJdbcDAO(connection);
			
			String[] resultado = pDAO.retornarInfPessoa(email);

			txtNome.setText(resultado[0]);
			txtEmail.setText(resultado[1]);
			txtSexo.setText(resultado[2]);
			
			attTarefas(email);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro ao atualizar dados da pessoa.",nomeJanela,errorDanger);
		}
	}
	
	public void attTarefas(String email) {
		try {
			cbTarefas.removeAllItems();
			Connection connection = JdbUtil.getConnection();
			TarefasJdbcDAO tar = new TarefasJdbcDAO(connection);
			
			i = 0;
			for ( String id: tar.listarTarPessoa(email) ) {
				cbTarefas.addItem(tar.listarTarPessoa(email).get(i));
				i++;
			}
			
			attTarefa(Integer.parseInt( cbTarefas.getSelectedItem().toString() ));
		} catch (java.lang.NullPointerException ex) {
			try {
				Connection connection = JdbUtil.getConnection();
				PessoasJdbcDAO pes = new PessoasJdbcDAO(connection);
				Rel_tarefa_pessoaJdbcDAO rel = new Rel_tarefa_pessoaJdbcDAO(connection);
				
				String[] inf = pes.retornarInfPessoa((String) email);
				int resultado = rel.verificarPessoa(Integer.parseInt(inf[3]));
				
				if (resultado > 0) {
					attTarefas(email);
				} else {
					txtTitulo.setText("");
					txtPrazoEstimado.setText("");
					txtDescricaoTarefa.setText("");
					txtDataInicio.setText("");
					txtDataTermino.setText("");
					JOptionPane.showMessageDialog(null,"Não há tarefas com essa pessoa.", nomeJanela, errorMissing);
					throw new Exception("Não há tarefas com essa pessoa.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro ao atualizar CB.TAREFAS.", nomeJanela, errorDanger);
		}
	}
	
	public void attTarefa(int id) {
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
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro ao atualizar dados da tarefa.",nomeJanela,errorDanger);
		}
	}
	
	public static void main(String [] args) {
		ListarPessoas a = new ListarPessoas();
	}
}