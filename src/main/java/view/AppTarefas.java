package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.ButtonGroup;
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

public class AppTarefas extends JFrame {
	int i = 0;
	static String nomeJanela = "Tarefas";
	static int errorDanger = 0;
	static int errorInformation = 1;
	static int errorWarning = 2;
	static int errorMissing = 3;
	
	JPanel panelTarefa = new JPanel();
	JPanel panelPessoa = new JPanel();
	JPanel panelAcoes = new JPanel();
	
	JLabel lblPessoas = new JLabel("Pessoas: ");
	JLabel lblEmail = new JLabel("E-mail: ");
	JLabel lblNome = new JLabel("Nome: ");
	JLabel lblSexo = new JLabel("Sexo: ");
	
	JComboBox<String> cbTarefas = new JComboBox<String>();
	JComboBox<String> cbPessoas = new JComboBox<String>();
	
	JLabel txtEmail= new JLabel();
	JLabel txtNome= new JLabel();
	JLabel txtSexo = new JLabel();
	
	ButtonGroup grupoSexo = new ButtonGroup();
	
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
	
	JLabel tSemRelacao = new JLabel();

	JButton deletarTarefa = new JButton("Excluir Tarefa");
	JButton cadastrarTarefa = new JButton("Cadastrar Tarefa");
	JButton editarTarefa = new JButton("Editar Tarefa");
	
	public AppTarefas() {
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
			alturaPanel = distanciaSuperior*8-altura,
					
			janelaAltura = ((alturaPanel+distanciaSuperior)*2)+(alturaPanel)/2,
			janelaLargura = larguraPanel+distanciaLateral*2-g/4;
		
		//   <-------->
		// 	 <-TAREFA->
		//   <-------->
		panelTarefa.setLayout(null);
		panelTarefa.setBorder(javax.swing.BorderFactory.createTitledBorder(nomeJanela));
		panelTarefa.setBounds			(15, 10, larguraPanel, alturaPanel);

		lblTarefas.setBounds			(distanciaLateral, distanciaSuperior*1, largura, altura);
		lblTitulo.setBounds				(distanciaLateral, distanciaSuperior*2, largura, altura);
		lblPrazoEstimado.setBounds		(distanciaLateral, distanciaSuperior*3, largura, altura);
		lblDescricaoTarefa.setBounds	(distanciaLateral, distanciaSuperior*4, largura, altura);
		lblDataInicio.setBounds			(distanciaLateral, distanciaSuperior*5, largura, altura);
		lblDataTermino.setBounds		(distanciaLateral, distanciaSuperior*6, largura, altura);
		
		cbTarefas.setBounds				(distanciaTXT, distanciaSuperior*1, largura, altura);
		
		txtTitulo.setBounds				(distanciaTXT, distanciaSuperior*2, largura, altura);
		txtPrazoEstimado.setBounds		(distanciaTXT, distanciaSuperior*3, largura, altura);	
		txtDescricaoTarefa.setBounds	(distanciaTXT, distanciaSuperior*4, largura, altura);	
		txtDataInicio.setBounds			(distanciaTXT, distanciaSuperior*5, largura, altura);
		txtDataTermino.setBounds		(distanciaTXT, distanciaSuperior*6, largura, altura);

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

		//   <-------->
		// 	 <-PESSOA->
		//   <-------->
		panelPessoa.setLayout(null);
		panelPessoa.setBorder(javax.swing.BorderFactory.createTitledBorder("Pessoa"));
		panelPessoa.setBounds			(15, alturaPanel+15, larguraPanel, alturaPanel);

		lblPessoas.setBounds			(distanciaLateral, distanciaSuperior*1, largura, altura);
		lblNome.setBounds				(distanciaLateral, distanciaSuperior*2, largura, altura);
		lblEmail.setBounds				(distanciaLateral, distanciaSuperior*3, largura, altura);
		lblSexo.setBounds				(distanciaLateral, distanciaSuperior*4, largura, altura);
		
		tSemRelacao.setBounds			(distanciaLateral, distanciaSuperior*3, largura*2, altura);
		
		cbPessoas.setBounds				(distanciaTXT, distanciaSuperior*1, largura, altura);
		
		txtNome.setBounds				(distanciaTXT, distanciaSuperior*2, largura, altura);
		txtEmail.setBounds				(distanciaTXT, distanciaSuperior*3, largura, altura);	
		txtSexo.setBounds				(distanciaTXT, distanciaSuperior*4, largura, altura);

		panelPessoa.add(lblPessoas);
		panelPessoa.add(lblNome);
		panelPessoa.add(lblEmail);
		panelPessoa.add(lblSexo);
		
		panelPessoa.add(tSemRelacao);
		
		panelPessoa.add(txtNome);
		panelPessoa.add(txtEmail);
		panelPessoa.add(txtSexo);
		
		panelPessoa.add(cbPessoas);

		//   <------->
		// 	 <-AÇÕES->
		//   <------->
		panelAcoes.setLayout(null);
		panelAcoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Ações"));
		panelAcoes.setBounds			(15, alturaPanel*2+15, larguraPanel, alturaPanel/2);
		
		cadastrarTarefa.setBounds			(distanciaLateral, distanciaSuperior*1, largura, altura);
		deletarTarefa.setBounds			(distanciaTXT, distanciaSuperior*1, largura, altura);
		editarTarefa.setBounds				(distanciaLateral, distanciaSuperior*2, largura*2+distanciaLateral-g, altura);
		
		panelAcoes.add(deletarTarefa);
		panelAcoes.add(cadastrarTarefa);
		panelAcoes.add(editarTarefa);
		
		paine.add(panelAcoes);
		paine.add(panelTarefa);
		paine.add(panelPessoa);

		attTarefas();
		attPessoas();

		cbPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbPessoas.getSelectedItem() != null) {
					attPessoa(cbPessoas.getSelectedItem().toString());
				}
			}
		});
		
		cbTarefas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attPessoas();
				attTarefa();
			}
		});
		
		cadastrarTarefa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarTarefas adicionarTarefa = new CadastrarTarefas();
				dispose();
			}
		});
		
		deletarTarefa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Option = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir relação entre "+cbPessoas.getSelectedItem().toString()+" e a tarefa "+cbTarefas.getSelectedItem().toString()+"?",nomeJanela,JOptionPane.YES_NO_OPTION);
				
                if(Option==JOptionPane.YES_OPTION) {
					
                } 
			}
		});
		
		editarTarefa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarPessoas a = new ListarPessoas();
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
			Rel_tarefa_pessoaJdbcDAO rel = new Rel_tarefa_pessoaJdbcDAO(connection);
			
			i = 0;
			for ( String pessoa: rel.listarPessoas(Integer.parseInt((String) cbTarefas.getSelectedItem())) ) {
				cbPessoas.addItem(rel.listarPessoas(Integer.parseInt((String) cbTarefas.getSelectedItem())).get(i));
				i++;
			}	
			
			attPessoa(cbPessoas.getSelectedItem().toString());
			
		} catch (Exception ex) {
			tSemRelacao.setText("A Tarefa " + cbTarefas.getSelectedItem().toString() + " não possui relações.");
			visibilidade(false);
			ex.printStackTrace();
		}
	}
	
	public void attTarefas() {
		try {
			cbTarefas.removeAllItems();
			Connection connection = JdbUtil.getConnection();
			TarefasJdbcDAO tar = new TarefasJdbcDAO(connection);
			
			i = 0;
			for ( String tarefa: tar.listar() ) {
				cbTarefas.addItem(tar.listar().get(i));
				i++;
			}
			
			attTarefa();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro em atualizar CB.TAREFAS.", nomeJanela, errorDanger);
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
			
			visibilidade(true);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Erro ao atualizar dados da pessoa.","Editar relação - tarefa", JOptionPane.INFORMATION_MESSAGE);
			ex.printStackTrace();
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
			JOptionPane.showMessageDialog(null,"Erro ao atualizar dados da tarefas.",nomeJanela, JOptionPane.INFORMATION_MESSAGE);
			ex.printStackTrace();
		}
	}
	
	public void visibilidade(boolean a) {
		lblPessoas.setVisible(a);
		cbPessoas.setVisible(a);
		
		lblNome.setVisible(a);
		lblEmail.setVisible(a);
		lblSexo.setVisible(a);
		
		txtNome.setVisible(a);
		txtEmail.setVisible(a);
		txtSexo.setVisible(a);
		
		tSemRelacao.setVisible(!a);
	}	
	
	public static void main(String[] args) {
		AppTarefas a = new AppTarefas();
	}
}
