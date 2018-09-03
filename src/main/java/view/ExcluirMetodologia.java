package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.JdbUtil;
import controller.MetodologiaJdbcDAO;

public class ExcluirMetodologia extends JFrame {
	static String nomeJanela = "Excluir Metodologia";
	static int errorDanger = 0;
	static int errorInformation = 1;
	static int errorWarning = 2;
	static int errorMissing = 3;
	
JPanel panelMetodologia = new JPanel();
	
	JLabel lblID = new JLabel("ID:");
	JLabel txtID = new JLabel();
	
	JLabel lblMetodologia = new JLabel("Metodologia:");
	
	JButton excluir = new JButton("Excluir");
	
	public ExcluirMetodologia(int id) {
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
			alturaPanel = distanciaSuperior*4-altura,
			
			janelaAltura = alturaPanel+distanciaSuperior*2-g/2,
			janelaLargura = larguraPanel+distanciaLateral*2-g/4;
			
		//   <-------->
		// 	 <-TAREFA->
		//   <-------->
		panelMetodologia.setLayout(null);
		panelMetodologia.setBorder(javax.swing.BorderFactory.createTitledBorder(nomeJanela));
		panelMetodologia.setBounds			(15, 10, larguraPanel, alturaPanel);

		lblID.setBounds						(distanciaLateral, distanciaSuperior*1, largura, altura);
		
		txtID.setBounds						(distanciaTXT, distanciaSuperior*1, largura, altura);

		excluir.setBounds					(distanciaLateral, distanciaSuperior*2, largura, altura);

		panelMetodologia.add(lblID);
		panelMetodologia.add(lblMetodologia);
		
		panelMetodologia.add(txtID);

		
		panelMetodologia.add(excluir);
		
		paine.add(panelMetodologia);
		
		txtID.setText(id + "");
		
		excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtID.getText().isEmpty()) {
					excluir( Integer.parseInt(txtID.getText()) );
				} else {
					JOptionPane.showMessageDialog(null,"Digite o ID da tarefa.", nomeJanela, errorMissing);
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				//if (JOptionPane.showConfirmDialog(null,"Deseja sair?") == JOptionPane.OK_OPTION){
					AppMetodologias a = new AppMetodologias();
				//}
			}
		});
		
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(janelaLargura, janelaAltura);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public void excluir(int idTarefa) {
		try {
			Connection connection = JdbUtil.getConnection();
			MetodologiaJdbcDAO met = new MetodologiaJdbcDAO(connection);
			
			met.deletar(idTarefa);
			
			JOptionPane.showMessageDialog(null,"Metodologia excluida com sucesso.",nomeJanela, errorInformation);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Erro ao tentar excluir",nomeJanela, errorDanger);
			ex.printStackTrace();
		}
	}
	
	public static void main(String [] args) {
		ExcluirMetodologia a = new ExcluirMetodologia(1);
	}
}
