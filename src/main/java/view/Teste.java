package view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Teste extends JFrame {
	
	JPanel panelPessoas = new JPanel();
	JPanel panelPessoas2 = new JPanel();

	JLabel lblNome = new JLabel("Nome: ");
	JTextField txtNome= new JTextField();
	
	
	public Teste() {
		super("teste");
		Container paine = this.getContentPane();
		paine.setLayout(null);
		
		panelPessoas.setLayout(null);
		panelPessoas.setBorder(javax.swing.BorderFactory.createTitledBorder("A"));
		panelPessoas.setBounds			(15, 10, 500, 500);
		
		lblNome.setBounds(30, 30, 100, 20);
		
		panelPessoas.add(lblNome);
		
		panelPessoas2.setLayout(null);
		panelPessoas2.setBorder(javax.swing.BorderFactory.createTitledBorder("A"));
		panelPessoas2.setBounds			(15, 10, 250, 250);
		
		txtNome.setBounds(30, 30, 100, 20);
		
		panelPessoas2.add(txtNome);
		
		panelPessoas.add(panelPessoas2);
		
		
		paine.add(panelPessoas);
		
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Teste a = new Teste();
	}

}
