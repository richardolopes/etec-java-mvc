package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.*;

import controller.JdbUtil;
import controller.PessoasJdbcDAO;
import controller.Rel_tarefa_pessoaJdbcDAO;
import model.Pessoas;

public class EditarPessoas extends JFrame {
	
	JLabel lblDescricao = new JLabel("Digite o E-mail e clique em procurar.");
	
	JLabel lblEmailPessoa = new JLabel("Procurar pessoa: ");
	JTextField txtEmailPessoa = new JTextField();
	
	JButton procurar = new JButton("Procurar");
	
	JLabel lblNome = new JLabel("Nome: ");
	JTextField txtNome= new JTextField();
	
	JLabel lblEmail = new JLabel("E-mail: ");
	JTextField txtEmail = new JTextField();
	
	JLabel lblSexo = new JLabel("Sexo: ");
	
	ButtonGroup grupoSexo = new ButtonGroup();
	
	JRadioButton rbMasculino = new JRadioButton("Masculino");
	JRadioButton rbFeminino = new JRadioButton("Feminino");
	
	JButton editarPessoa = new JButton("Salvar alterações");
	
	JButton deletarPessoa = new JButton("");
	
	public EditarPessoas() {
		super("Editar Cadastro");
		
		grupoSexo.add(rbFeminino);
		grupoSexo.add(rbMasculino);	
		
		Container paine = this.getContentPane();
		paine.setLayout(null);
		
		lblDescricao.setBounds(10, 10, 250, 20);
		paine.add(lblDescricao);
		
		lblEmailPessoa.setBounds(10, 35, 110, 20);
		paine.add(lblEmailPessoa);
		txtEmailPessoa.setBounds(120, 35, 180, 20);
		paine.add(txtEmailPessoa);
		
		procurar.setBounds(10, 70, 100, 20);
		paine.add(procurar);
		
		procurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtEmailPessoa.getText().isEmpty()) {
					try {
						Connection connection = JdbUtil.getConnection();
						PessoasJdbcDAO pDAO = new PessoasJdbcDAO(connection);
						
						if (pDAO.verificarEmail(txtEmailPessoa.getText()) > 0) {
							String[] resultado = pDAO.retornarInfPessoa(txtEmailPessoa.getText());
	
							txtNome.setText(resultado[0]);
							txtEmail.setText(resultado[1]);
							
							if (resultado[2].contentEquals("masculino")) {
								rbMasculino.setSelected(true);
							} else {
								rbFeminino.setSelected(true);
							}
							
						} else {
							JOptionPane.showMessageDialog(null,"E-mail não encontrado.","Editar Cadastro", JOptionPane.INFORMATION_MESSAGE);
						}
						
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,"Erro de conexão.","Editar Cadastro", JOptionPane.CLOSED_OPTION);
						ex.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null,"Digite um e-mail.","Editar Cadastro", JOptionPane.CLOSED_OPTION);
				}
			}
		});
		
		// -------
		
		lblNome.setBounds(20, 130, 100, 20);
		paine.add(lblNome);
		txtNome.setBounds(65, 130, 180, 20);
		paine.add(txtNome);
		
		lblEmail.setBounds(20, 160, 100, 20);
		paine.add(lblEmail);
		txtEmail.setBounds(65, 160, 180, 20);
		paine.add(txtEmail);
		
		lblSexo.setBounds(20, 190, 100, 20);
		paine.add(lblSexo);
		
		rbMasculino.setBounds(60, 190, 100, 20);
		paine.add(rbMasculino);
		
		rbFeminino.setBounds(60, 210, 100, 20);
		paine.add(rbFeminino);
		
		editarPessoa.setBounds(8, 240, 150, 40);
		paine.add(editarPessoa);
		
		deletarPessoa.setBounds(168, 240, 40, 40);
		paine.add(deletarPessoa);
		
		deletarPessoa.setIcon(new ImageIcon(getClass().getResource("/controller/img/lixo.png")));

		editarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pessoas pessoa1 = new Pessoas();
				
				try {
					if (!txtNome.getText().isEmpty() && !txtEmail.getText().isEmpty() && grupoSexo.getSelection() != null) {
						Connection connection = JdbUtil.getConnection();
						PessoasJdbcDAO pessoasJdbcDao = new PessoasJdbcDAO(connection);
					
						try {
							String[] resultado = pessoasJdbcDao.retornarInfPessoa(txtEmailPessoa.getText());
							
							pessoa1.setId(Integer.parseInt(resultado[3]));
							pessoa1.setNome(txtNome.getText());
							pessoa1.setEmail(txtEmail.getText());
							
							if (rbMasculino.isSelected()) {
								pessoa1.setSexo("masculino");
							} else {
								pessoa1.setSexo("feminino");
							}

							pessoasJdbcDao.alterar(pessoa1);
		
							JOptionPane.showMessageDialog(null,"Alterações realizadas com sucesso.","Editar Cadastro", JOptionPane.INFORMATION_MESSAGE);
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(null,"Erro ao alterar dados.","Editar Cadastro", JOptionPane.CLOSED_OPTION);
							ex.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null,"Preencha todos os campos.","Editar Cadastro", JOptionPane.CLOSED_OPTION);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"Erro de conexão.","Editar Cadastro", JOptionPane.CLOSED_OPTION);
					ex.printStackTrace();
				}
			}
		});
		
		deletarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtEmailPessoa.getText().isEmpty()) {
					try {
						Connection connection = JdbUtil.getConnection();
						PessoasJdbcDAO pes = new PessoasJdbcDAO(connection);
						
						String[] resultado = pes.retornarInfPessoa(txtEmailPessoa.getText());
						
						pes.deletar(Integer.parseInt(resultado[3]));
						
						if (pes.verificarEmail(resultado[1]) > 0) {
							System.out.println("AAAA");
						} else {
							JOptionPane.showMessageDialog(null,"Cadastro excluido com sucesso.","Editar Cadastro", JOptionPane.INFORMATION_MESSAGE);	
						}
	
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null,"Erro em excluir cadastro.","Editar Cadastro", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,"Digite um e-mail.","Editar Cadastro", JOptionPane.CLOSED_OPTION);
				}
			}
		});
		
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(330, 330);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		EditarPessoas janela = new EditarPessoas();
	}
}
