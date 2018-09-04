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

import controller.JdbUtil;
import controller.PessoasJdbcDAO;
import controller.Rel_tarefa_pessoaJdbcDAO;
import controller.TarefasJdbcDAO;

public class AppRelacoes extends JFrame {
	int i = 0;
	static String nomeJanela = "Relações";
	static int errorDanger = 0;
	static int errorInformation = 1;
	static int errorWarning = 2;
	static int errorMissing = 3;
	
	JPanel panelRelacoes = new JPanel();
	JPanel panelDados = new JPanel();
	
	JLabel lblRelacoes = new JLabel("Pessoa: ");
	
	JComboBox<String> cbPessoas = new JComboBox<String>();
	
	JButton deletar = new JButton("Excluir");
	JButton editar = new JButton("Editar");
	JButton cadastrar = new JButton("Cadastrar");

	//   <-------->
	// 	 <-TAREFA->
	//   <-------->
	JLabel lblTarefas = new JLabel("Tarefa: ");
	
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
		
		//   <------------->
		// 	 <-METODOLOGIA->
		//   <------------->
		panelRelacoes.setLayout(null);
		panelRelacoes.setBorder(javax.swing.BorderFactory.createTitledBorder(nomeJanela));
		panelRelacoes.setBounds		(15, 10, larguraPanel, alturaPanel);
		
		lblRelacoes.setBounds		(distanciaLateral, distanciaSuperior*1, largura, altura);
		cbPessoas.setBounds			(distanciaTXT, distanciaSuperior*1, largura, altura);
		
		editar.setBounds			(distanciaLateral, distanciaSuperior*9+altura, largura, altura);
		deletar.setBounds			(distanciaTXT, distanciaSuperior*9+altura, largura, altura);
		cadastrar.setBounds 		(distanciaLateral, distanciaSuperior*10+altura, largura*2+distanciaLateral-g, altura);
		
		panelRelacoes.add(lblRelacoes);
		panelRelacoes.add(cbPessoas);
		
		panelRelacoes.add(editar);
		panelRelacoes.add(deletar);
		panelRelacoes.add(cadastrar);
		
		//   <------->
		// 	 <-DADOS->
		//   <------->
		panelDados.setLayout(null);
		panelDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Tarefas"));
		panelDados.setBounds			(distanciaLateral, distanciaSuperior*2, largura*2+distanciaLateral-g, altura*11);

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

		panelDados.add(cbTarefas);

		panelRelacoes.add(panelDados);
	
		paine.add(panelRelacoes);
		
		attPessoas();
		
		cbPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attTarefas(cbPessoas.getSelectedItem().toString());
			}
		});
		
		cbTarefas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attTarefa(Integer.parseInt( cbTarefas.getSelectedItem().toString()) );
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
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro ao atualizar CB.PESSOAS.",nomeJanela,errorDanger);
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
		AppRelacoes a = new AppRelacoes();
	}
}
