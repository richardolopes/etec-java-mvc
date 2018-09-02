package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ListarRelacoes extends JFrame {
	int i = 0;
	static String nomeJanela = "Relações";
	
	JPanel panelTarefa = new JPanel();
	JPanel panelPessoa = new JPanel();
	
	JLabel lblPessoas = new JLabel("Pessoas: ");
	JLabel lblEmail = new JLabel("E-mail: ");
	JLabel lblNome = new JLabel("Nome: ");
	JLabel lblSexo = new JLabel("Sexo: ");
	
	JRadioButton rbMasculino = new JRadioButton("Masculino");
	JRadioButton rbFeminino = new JRadioButton("Feminino");
	
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

	JButton deletarRelacao = new JButton("Excluir relação");
	JButton adicionarRelacao = new JButton("Adicionar relação");
	
	public ListarRelacoes() {
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
					
			janelaAltura = (alturaPanel+distanciaSuperior)*2,
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
		
		panelTarefa.add(adicionarRelacao);
		
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
		
		cbPessoas.setBounds				(distanciaTXT, distanciaSuperior*1, largura, altura);
		
		txtNome.setBounds				(distanciaTXT, distanciaSuperior*2, largura, altura);
		txtEmail.setBounds				(distanciaTXT, distanciaSuperior*3, largura, altura);	
		txtSexo.setBounds				(distanciaTXT, distanciaSuperior*4, largura, altura);

		panelPessoa.add(lblPessoas);
		panelPessoa.add(lblNome);
		panelPessoa.add(lblEmail);
		panelPessoa.add(lblSexo);
		
		panelPessoa.add(txtNome);
		panelPessoa.add(txtEmail);
		panelPessoa.add(txtSexo);
		
		panelPessoa.add(cbPessoas);
		
		panelPessoa.add(deletarRelacao);
		
		paine.add(panelTarefa);
		paine.add(panelPessoa);

		attTarefas();
		attPessoas();
		
		adicionarRelacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarRelTarefaPessoa adicionarRelTarefa = new CadastrarRelTarefaPessoa(Integer.parseInt( cbTarefas.getSelectedItem().toString() ));
			}
		});

		cbPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attPessoa();
			}
		});
		
		cbTarefas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attPessoas();
				attTarefa();
			}
		});
		
		deletarRelacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Option = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir relação entre "+cbPessoas.getSelectedItem().toString()+" e a tarefa "+cbTarefas.getSelectedItem().toString()+"?",nomeJanela,JOptionPane.YES_NO_OPTION);
				
                if(Option==JOptionPane.YES_OPTION) {
					try {
						Connection connection = JdbUtil.getConnection();
						Rel_tarefa_pessoaJdbcDAO rel = new Rel_tarefa_pessoaJdbcDAO(connection);
						PessoasJdbcDAO pes = new PessoasJdbcDAO(connection);
						
						String[] infPessoa = pes.retornarInfPessoa((String) cbPessoas.getSelectedItem());
						rel.deletar(Integer.parseInt((String) cbTarefas.getSelectedItem()), Integer.parseInt((String)infPessoa[3]));
						
						JOptionPane.showMessageDialog(null,"Relação excluida com sucesso.",nomeJanela, JOptionPane.INFORMATION_MESSAGE);
						attPessoas();
						
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null,"Erro em excluir relação.",nomeJanela, JOptionPane.INFORMATION_MESSAGE);
					}
                } else {
                	
                }
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
			
			attPessoa();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro em atualizar CB.PESSOAS.",nomeJanela, JOptionPane.INFORMATION_MESSAGE);
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
			JOptionPane.showMessageDialog(null,"Erro em atualizar CB.TAREFAS.",nomeJanela, JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void attPessoa() {
		try {
			Connection connection = JdbUtil.getConnection();
			PessoasJdbcDAO pDAO = new PessoasJdbcDAO(connection);
			
			String[] resultado = pDAO.retornarInfPessoa(cbPessoas.getSelectedItem());			
			
			txtNome.setText(resultado[0]);
			txtEmail.setText(resultado[1]);
			txtSexo.setText(resultado[2]);
			
		} catch (Exception ex) {
			//JOptionPane.showMessageDialog(null,"Erro ao atualizar dados da pessoa.","Editar relação - tarefa", JOptionPane.INFORMATION_MESSAGE);
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
	
	public static void main(String[] args) {
		ListarRelacoes a = new ListarRelacoes();
	}
}
