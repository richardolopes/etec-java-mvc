package view;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.JdbUtil;
import controller.PessoasJdbcDAO;
import model.Pessoas;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;

public class CadastrarPessoas extends JFrame {
	static String nomeJanela = "Criar Pessoas";
	
	JPanel panelPessoas = new JPanel();

	JLabel lblNome = new JLabel("Nome: ");
	JTextField txtNome= new JTextField();
	
	JLabel lblEmail = new JLabel("E-mail: ");
	JTextField txtEmail = new JTextField();
	
	JLabel lblSexo = new JLabel("Sexo: ");
	
	ButtonGroup grupoSexo = new ButtonGroup();
	
	JRadioButton rbMasculino = new JRadioButton("Masculino");
	JRadioButton rbFeminino = new JRadioButton("Feminino");
	
	JButton novoCadastro = new JButton("Cadastrar");
	
	public CadastrarPessoas() {
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
			
			janelaAltura = alturaPanel+distanciaSuperior*2-g/2,
			janelaLargura = (larguraPanel+distanciaLateral*2-g/4)-2;

		//   <-------->
		// 	 <-TAREFA->
		//   <-------->
		panelPessoas.setLayout(null);
		panelPessoas.setBorder(javax.swing.BorderFactory.createTitledBorder(nomeJanela));
		panelPessoas.setBounds			(15, 10, larguraPanel, alturaPanel);
		
		lblNome.setBounds				(distanciaLateral, distanciaSuperior*1, largura, altura);
		lblEmail.setBounds				(distanciaLateral, distanciaSuperior*2, largura, altura);
		lblSexo.setBounds				(distanciaLateral, distanciaSuperior*3, largura, altura);

		txtNome.setBounds				(distanciaTXT, distanciaSuperior*1, largura, altura);
		txtEmail.setBounds				(distanciaTXT, distanciaSuperior*2, largura, altura);
	
		rbMasculino.setBounds			(distanciaTXT, distanciaSuperior*3, largura, altura);
		rbFeminino.setBounds			(distanciaTXT, distanciaSuperior*4, largura, altura);

		novoCadastro.setBounds			(distanciaLateral, distanciaSuperior*6, largura, altura);
		
		grupoSexo.add(rbFeminino);
		grupoSexo.add(rbMasculino);	
		
		panelPessoas.add(lblNome);
		panelPessoas.add(lblEmail);
		panelPessoas.add(lblSexo);
		
		panelPessoas.add(txtNome);
		panelPessoas.add(txtEmail);
		
		panelPessoas.add(rbMasculino);
		panelPessoas.add(rbFeminino);
		
		panelPessoas.add(novoCadastro);
		
		paine.add(panelPessoas);

		novoCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pessoas pessoa1 = new Pessoas();
				
				try {
					if (!txtNome.getText().isEmpty() && !txtEmail.getText().isEmpty() && grupoSexo.getSelection() != null) {
						Connection connection = JdbUtil.getConnection();
						PessoasJdbcDAO pessoasJdbcDao = new PessoasJdbcDAO(connection);
						
						if (pessoasJdbcDao.verificarEmail(txtEmail.getText()) > 0) {
							JOptionPane.showMessageDialog(null,"E-mail já cadastrado.","OK", JOptionPane.INFORMATION_MESSAGE);
						} else {
							try {
								pessoa1.setNome(txtNome.getText());
								pessoa1.setEmail(txtEmail.getText());
								
								if (rbMasculino.isSelected()) {
									pessoa1.setSexo("masculino");
								} else {
									pessoa1.setSexo("feminino");
								}

								pessoasJdbcDao.salvar(pessoa1);

								JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso.","Cadastro", JOptionPane.INFORMATION_MESSAGE);
								dispose();
								CadastrarPessoas a = new CadastrarPessoas();
							} catch(Exception ex) {
								JOptionPane.showMessageDialog(null,"Erro ao cadastrar.","Cadastro", JOptionPane.CLOSED_OPTION);
								ex.printStackTrace();
							}
						}
					} else {
						JOptionPane.showMessageDialog(null,"Preencha todos os campos.","Cadastro", JOptionPane.CLOSED_OPTION);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"Erro de conexão.","Cadastro", JOptionPane.CLOSED_OPTION);
					ex.printStackTrace();
				}
			}
		});
		
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(janelaLargura, janelaAltura);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	
	public static void main(String [] args) {
		CadastrarPessoas janela = new CadastrarPessoas();
	}
}
