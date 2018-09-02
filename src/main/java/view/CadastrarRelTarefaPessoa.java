package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.JdbUtil;
import controller.PessoasJdbcDAO;
import controller.Rel_tarefa_pessoaJdbcDAO;
import controller.TarefasJdbcDAO;
import model.Rel_tarefa_pessoa;
import model.Tarefas;

public class CadastrarRelTarefaPessoa extends JFrame {	
	static String nomeJanela = "Cadastrar Relações";
	static int errorDanger = 0;
	static int errorInformation = 1;
	static int errorWarning = 2;
	static int errorMissing = 3;
	
	JPanel panelRel = new JPanel();
	
	JLabel lblID = new JLabel("ID Tarefa:");
	JLabel txtID = new JLabel();
	
	JLabel lblPessoa = new JLabel("E-mail:");
	JTextField txtPessoa = new JTextField();
	
	JButton novoCadastro = new JButton("Cadastrar");
	
	public CadastrarRelTarefaPessoa(int id) {
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
			alturaPanel = distanciaSuperior*6-altura,
			
			janelaAltura = alturaPanel+distanciaSuperior*2-g/2,
			janelaLargura = larguraPanel+distanciaLateral*2-g/4;
			
		//   <-------->
		// 	 <-TAREFA->
		//   <-------->
		panelRel.setLayout(null);
		panelRel.setBorder(javax.swing.BorderFactory.createTitledBorder(nomeJanela));
		panelRel.setBounds			(15, 10, larguraPanel, alturaPanel);
		
		lblID.setBounds				(distanciaLateral, distanciaSuperior*1, largura, altura);
		lblPessoa.setBounds			(distanciaLateral, distanciaSuperior*2, largura, altura);
		
		txtID.setBounds				(distanciaTXT, distanciaSuperior*1, largura, altura);
		txtPessoa.setBounds			(distanciaTXT, distanciaSuperior*2, largura, altura);

		novoCadastro.setBounds		(distanciaLateral, distanciaSuperior*4, largura, altura);

		panelRel.add(lblID);
		panelRel.add(lblPessoa);
		
		panelRel.add(txtID);
		panelRel.add(txtPessoa);
		
		panelRel.add(novoCadastro);
		
		paine.add(panelRel);
		
		txtID.setText(id + "");
		
		novoCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtPessoa.getText().isEmpty()) {
					Cadastrar(Integer.parseInt(txtID.getText()), txtPessoa.getText());
				} else {
					JOptionPane.showMessageDialog(null,"Digite um e-mail", nomeJanela, errorMissing);
				}
			}
		});

		this.setResizable(false);
		this.setVisible(true);
		this.setSize(janelaLargura, janelaAltura);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public void Cadastrar(int idTarefa, String email) {
		try {
			Connection connection = JdbUtil.getConnection();
			Rel_tarefa_pessoaJdbcDAO rel_tarefa_pessoa = new Rel_tarefa_pessoaJdbcDAO(connection);
			PessoasJdbcDAO pessoas = new PessoasJdbcDAO(connection);
			Rel_tarefa_pessoa relTarefaPessoa1 = new Rel_tarefa_pessoa();

			if (pessoas.verificarEmail(email) > 0) {					
				String[] idPessoa = pessoas.retornarInfPessoa(email);
				
				if (rel_tarefa_pessoa.verificarTarefaPessoa(idTarefa, Integer.parseInt(idPessoa[3]) ) > 0) {
					JOptionPane.showMessageDialog(null,"Relação já feita", nomeJanela, errorWarning);
				} else {
					try {
						relTarefaPessoa1.setId_pessoa( Integer.parseInt(idPessoa[3]) );
						relTarefaPessoa1.setId_tarefa( idTarefa );

						rel_tarefa_pessoa.salvar(relTarefaPessoa1);
						
						JOptionPane.showMessageDialog(null,"Relação realizada com sucesso.", nomeJanela, errorInformation);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,"Erro ao cadastrar.", nomeJanela, errorDanger);
						ex.printStackTrace();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null,"E-mail não encontrado", nomeJanela, errorMissing);
			}
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Erro de conexão", nomeJanela, errorDanger);
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		CadastrarRelTarefaPessoa a = new CadastrarRelTarefaPessoa(1);
	}
}
