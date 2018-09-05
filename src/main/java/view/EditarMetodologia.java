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
import model.Metodologia;

public class EditarMetodologia extends JFrame {
	static String nomeJanela = "Editar Metodologia";
	static int errorDanger = 0;
	static int errorInformation = 1;
	static int errorWarning = 2;
	static int errorMissing = 3;
	
	JPanel panelMetodologia = new JPanel();
	
	JLabel lblID = new JLabel("ID:");
	JLabel txtID = new JLabel();
	
	JLabel lblMetodologia = new JLabel("Metodologia:");
	JTextField txtMetodologia = new JTextField();
	
	JButton editar = new JButton("Editar");
	
	public EditarMetodologia(int id, final boolean appMet) {
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
		panelMetodologia.setLayout(null);
		panelMetodologia.setBorder(javax.swing.BorderFactory.createTitledBorder(nomeJanela));
		panelMetodologia.setBounds			(15, 10, larguraPanel, alturaPanel);

		lblID.setBounds				(distanciaLateral, distanciaSuperior*1, largura, altura);
		lblMetodologia.setBounds			(distanciaLateral, distanciaSuperior*2, largura, altura);
		
		txtID.setBounds				(distanciaTXT, distanciaSuperior*1, largura, altura);
		txtMetodologia.setBounds			(distanciaTXT, distanciaSuperior*2, largura, altura);

		editar.setBounds		(distanciaLateral, distanciaSuperior*4, largura, altura);

		panelMetodologia.add(lblID);
		panelMetodologia.add(lblMetodologia);
		
		panelMetodologia.add(txtID);
		panelMetodologia.add(txtMetodologia);
		
		panelMetodologia.add(editar);
		
		paine.add(panelMetodologia);
		
		txtID.setText(id + "");
		
		editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtMetodologia.getText().isEmpty()) {
					editarMetodologia(Integer.parseInt(txtID.getText()), txtMetodologia.getText());
				} else {
					JOptionPane.showMessageDialog(null,"Digite o nome da metodologia.", nomeJanela, errorMissing);
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				//if (JOptionPane.showConfirmDialog(null,"Deseja sair?") == JOptionPane.OK_OPTION){
				if(appMet) {
					AppMetodologias a = new AppMetodologias();
				} else {
					AppTarefas a = new AppTarefas();
				}
				//}
			}
		});
		
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(janelaLargura, janelaAltura);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public void editarMetodologia(int id, String titulo) {
		try {
			Connection connection = JdbUtil.getConnection();
			MetodologiaJdbcDAO tDAO = new MetodologiaJdbcDAO(connection);
			Metodologia met = new Metodologia();
			
			met.setId(id);
			met.setTitulo(titulo);
			
			tDAO.alterar(met);
			System.out.println(tDAO.retornarInfMetodologia(id));
			
			JOptionPane.showMessageDialog(null,"Alteração realizada com sucesso.",nomeJanela, errorInformation);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Erro ao atualizar dados da metodologia.",nomeJanela, errorDanger);
			ex.printStackTrace();
		}
	}
	
	public static void main(String [] args) {
		EditarMetodologia a = new EditarMetodologia(1, true);
	}
}
