package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.*;

import controller.JdbUtil;
import controller.PessoasJdbcDAO;
import model.Pessoas;

public class AppPessoas extends JFrame {
	int i = 0;
	static String nomeJanela = "Pessoa";
	static int errorDanger = 0;
	static int errorInformation = 1;
	static int errorWarning = 2;
	static int errorMissing = 3;

	JPanel panelPessoa = new JPanel();
	JPanel panelDados = new JPanel();	
	
	JLabel lblEmailPessoa = new JLabel("Procurar " + nomeJanela + ": ");
	JTextField txtEmailPessoa = new JTextField();
	
	JButton procurar = new JButton("Procurar");

	JLabel lblEmail = new JLabel("E-mail: ");
	JLabel lblNome = new JLabel("Nome: ");
	JLabel lblSexo = new JLabel("Sexo: ");
	
	JRadioButton rbMasculino = new JRadioButton("Masculino");
	JRadioButton rbFeminino = new JRadioButton("Feminino");
	
	JComboBox<String> cbTarefas = new JComboBox<String>();
	
	JTextField txtEmail= new JTextField();
	JTextField txtNome= new JTextField();
	JLabel txtSexo = new JLabel();
	
	ButtonGroup grupoSexo = new ButtonGroup();

	JButton deletar = new JButton("Excluir");
	JButton listar = new JButton("Listar " + nomeJanela + "s");
	JButton editar = new JButton("Editar");
	JButton adicionar = new JButton("Cadastrar " + nomeJanela);

	public AppPessoas() {
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
			alturaPanel = distanciaSuperior*12-altura,
					
			janelaAltura = alturaPanel+distanciaSuperior*2-g/2,
			janelaLargura = (larguraPanel+distanciaLateral*2-g/4)-2;

		//   <-------->
		// 	 <-PESSOA->
		//   <-------->
		grupoSexo.add(rbMasculino);
		grupoSexo.add(rbFeminino);
		
		panelPessoa.setLayout(null);
		panelPessoa.setBorder(javax.swing.BorderFactory.createTitledBorder(nomeJanela));
		panelPessoa.setBounds			(15, 10, larguraPanel, alturaPanel);
		
		panelDados.setLayout(null);
		panelDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados " + nomeJanela));
		panelDados.setBounds			(distanciaLateral, distanciaSuperior*3, largura*2+distanciaLateral-g, altura*10);
		

		lblEmailPessoa.setBounds		(distanciaLateral, distanciaSuperior*1, largura, altura);
		txtEmailPessoa.setBounds		(distanciaTXT, distanciaSuperior*1, largura, altura);
		
		procurar.setBounds				(distanciaLateral, distanciaSuperior*2, largura*2+distanciaLateral-g, altura);

		panelPessoa.add(panelDados);
		
			lblNome.setBounds				(distanciaLateral, distanciaSuperior*1, largura-g*2, altura);
			lblEmail.setBounds				(distanciaLateral, distanciaSuperior*2, largura-g*2, altura);
			lblSexo.setBounds				(distanciaLateral, distanciaSuperior*3, largura-g*2, altura);
		
			txtNome.setBounds				(distanciaTXT-g*2, distanciaSuperior*1, largura-g*2, altura);
			txtEmail.setBounds				(distanciaTXT-g*2, distanciaSuperior*2, largura-g*2, altura);
			txtSexo.setBounds				(distanciaTXT-g*2, distanciaSuperior*3, largura-g*2, altura);
			
			rbMasculino.setBounds 			(distanciaTXT-g*2, distanciaSuperior*3, largura-g*2, altura);
			rbFeminino.setBounds 			(distanciaTXT-g*2, distanciaSuperior*4, largura-g*2, altura);
			
			editar.setBounds				(distanciaLateral, distanciaSuperior*6-g*2, largura-g*2, altura);
			deletar.setBounds				(distanciaTXT-g*2, distanciaSuperior*6-g*2, largura-g*2, altura);
		//-----
		adicionar.setBounds				(distanciaLateral, distanciaSuperior*10, largura, altura);
		listar.setBounds				(distanciaTXT, distanciaSuperior*10, largura, altura);

		panelPessoa.add(lblEmailPessoa);
		panelPessoa.add(txtEmailPessoa);
		
		panelPessoa.add(procurar);

		panelDados.add(lblNome);
		panelDados.add(lblEmail);
		panelDados.add(lblSexo);

		panelDados.add(txtNome);
		panelDados.add(txtEmail);
		panelDados.add(txtSexo);
		
		panelDados.add(rbMasculino);
		panelDados.add(rbFeminino);
		
		panelDados.add(deletar);
		panelDados.add(editar);
		
		panelPessoa.add(adicionar);
		panelPessoa.add(listar);

		visibilidade(false);
		
		paine.add(panelPessoa);
		
		procurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtEmailPessoa.getText().isEmpty()) {
					procurar(txtEmailPessoa.getText());
				} else {
					JOptionPane.showMessageDialog(null,"Digite um e-mail.", nomeJanela, errorMissing);
				}
			}
		});
		
		deletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtEmailPessoa.getText().isEmpty()) {
					int Option = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir?", nomeJanela, errorDanger);
					
		            if(Option==JOptionPane.YES_OPTION) {
		            	deletar(txtEmailPessoa.getText());
		            }
				} else {
					JOptionPane.showMessageDialog(null,"Digite um e-mail.", nomeJanela, errorMissing);
				}
			}
		});

		adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarPessoas a = new CadastrarPessoas();
			}
		});
		
		listar.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				ListarPessoas a = new ListarPessoas();
			}
		});
		
		editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtNome.getText().isEmpty() && !txtEmail.getText().isEmpty() && grupoSexo.getSelection() != null) {
					String sexo = "";
					if (rbMasculino.isSelected()) {
						sexo = "masculino";
					} else {
						sexo = "feminino";
					}
					editar(txtEmailPessoa.getText(), txtEmail.getText(), txtNome.getText(), sexo);
				} else {
					JOptionPane.showMessageDialog(null,"Preencha todos os campos.",nomeJanela, errorMissing);
				}
			}
		});

		this.setResizable(false);
		this.setVisible(true);
		this.setSize(janelaLargura, janelaAltura);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public void procurar(String email) {
		try {
			Connection connection = JdbUtil.getConnection();
			PessoasJdbcDAO pDAO = new PessoasJdbcDAO(connection);
			
			if (pDAO.verificarEmail(email) > 0) {
				String[] resultado = pDAO.retornarInfPessoa(email);

				txtNome.setText(resultado[0]);
				txtEmail.setText(resultado[1]);
				
				if (resultado[2].contentEquals("masculino")) {
					rbMasculino.setSelected(true);
				} else {
					rbFeminino.setSelected(true);
				}

				visibilidade(true);
				
			} else {
				JOptionPane.showMessageDialog(null,"E-mail não encontrado.", nomeJanela, errorMissing);
				visibilidade(false);
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Erro de conexão.", nomeJanela, errorDanger);
			ex.printStackTrace();
		}
	}
	
	public void deletar(String email) {
		try {
			Connection connection = JdbUtil.getConnection();
			PessoasJdbcDAO pes = new PessoasJdbcDAO(connection);
			
			String[] resultado = pes.retornarInfPessoa(email);
			
			pes.deletar(Integer.parseInt(resultado[3]));
			
			visibilidade(false);
			limpar();
			
            JOptionPane.showMessageDialog(null,"Cadastro excluido com sucesso.", nomeJanela, errorInformation);	
            
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erro em excluir cadastro.", nomeJanela, errorDanger);
		}
	}
	
	public void editar(String emailPessoa, String email, String nome, String sexo) {
		Pessoas pessoa1 = new Pessoas();
		
		try {
			Connection connection = JdbUtil.getConnection();
			PessoasJdbcDAO pessoasJdbcDao = new PessoasJdbcDAO(connection);
		
			try {
				String[] resultado = pessoasJdbcDao.retornarInfPessoa(emailPessoa);
				
				pessoa1.setId(Integer.parseInt(resultado[3]));
				pessoa1.setNome(nome);
				pessoa1.setEmail(email);
				pessoa1.setSexo(sexo);

				pessoasJdbcDao.alterar(pessoa1);
				
				txtEmailPessoa.setText(email);

				JOptionPane.showMessageDialog(null,"Alterações realizadas com sucesso.", nomeJanela, errorInformation);
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null,"Erro ao alterar dados.", nomeJanela, errorDanger);
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Erro de conexão.", nomeJanela, errorDanger);
			ex.printStackTrace();
		}
	}
	
	public void visibilidade(boolean visible) {
		if (visible) {
			lblNome.setVisible(true);
			lblEmail.setVisible(true);
			lblSexo.setVisible(true);
			
			txtNome.setVisible(true);
			txtEmail.setVisible(true);
			txtSexo.setVisible(true);
			
			rbMasculino.setVisible(true);
			rbFeminino.setVisible(true);
			
			deletar.setVisible(true);
			editar.setVisible(true);
		} else {
			lblNome.setVisible(false);
			lblEmail.setVisible(false);
			lblSexo.setVisible(false);
			
			txtNome.setVisible(false);
			txtEmail.setVisible(false);
			txtSexo.setVisible(false);
			
			rbMasculino.setVisible(false);
			rbFeminino.setVisible(false);
			
			deletar.setVisible(false);
			editar.setVisible(false);
		}
	}
	
	public void limpar() {
		txtEmailPessoa.setText("");
		txtEmail.setText("");
		txtNome.setText("");
		txtSexo.setText("");
	}
	
	public static void main(String [] args) {
		AppPessoas a = new AppPessoas();
	}
}
