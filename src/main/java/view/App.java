package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class App extends JFrame {
	static String nomeJanela = "Página inicial";
	static int errorDanger = 0;
	static int errorInformation = 1;
	static int errorWarning = 2;
	static int errorMissing = 3;

	JPanel panelSelect = new JPanel();
	JPanel panelCrud = new JPanel();
	
	JButton btnCRUD = new JButton("CRUD");
	
	ButtonGroup gpSelect = new ButtonGroup();
	JRadioButton rbPessoa = new JRadioButton("Pessoa");
	JRadioButton rbTarefa = new JRadioButton("Tarefa");
	JRadioButton rbMetodologia = new JRadioButton("Metodologia");
	JRadioButton rbRelacao = new JRadioButton("Relação");

	public App() {
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
				alturaPanel = distanciaSuperior*7-altura,
						
				janelaAltura = alturaPanel+distanciaSuperior*2-g/2,
				janelaLargura = (larguraPanel+distanciaLateral*2-g/4)-2;

		//	 <-------->
		// 	 <-SELECT->
		//	 <-------->
		panelSelect.setBorder(javax.swing.BorderFactory.createTitledBorder("SELECIONE "));
		panelSelect.setBounds			(15, 10, larguraPanel, alturaPanel);
		panelSelect.setLayout(null);
		
		rbPessoa.setBounds				(distanciaLateral, distanciaSuperior*1, largura, altura);
		rbTarefa.setBounds				(distanciaLateral, distanciaSuperior*2, largura, altura);
		rbMetodologia.setBounds			(distanciaTXT, distanciaSuperior*1, largura, altura);
		rbRelacao.setBounds				(distanciaTXT, distanciaSuperior*2, largura, altura);
		
		panelSelect.add(rbPessoa);
		panelSelect.add(rbTarefa);
		panelSelect.add(rbMetodologia);
		panelSelect.add(rbRelacao);
		
		
		//	 <-------->
		// 	 <--CRUD-->
		//	 <-------->
		panelCrud.setBorder(javax.swing.BorderFactory.createTitledBorder("CRUD"));
		panelCrud.setBounds				(distanciaLateral, distanciaSuperior*3, largura*2+distanciaLateral-g, altura*4);
		panelCrud.setLayout(null);
		
		btnCRUD.setBounds				(distanciaLateral, distanciaSuperior*1, largura*2-distanciaLateral-g, altura*2-g-g/2);
		
		panelCrud.add(btnCRUD);
		
		
		gpSelect.add(rbPessoa);
		gpSelect.add(rbTarefa);
		gpSelect.add(rbMetodologia);
		gpSelect.add(rbRelacao);

		panelSelect.add(panelCrud);
		paine.add(panelSelect);
		
		rbPessoa.setActionCommand("P");
		rbTarefa.setActionCommand("R");
		rbMetodologia.setActionCommand("M");
		rbRelacao.setActionCommand("R");
		
		// ----	
		
		btnCRUD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ( rbPessoa.isSelected() || rbTarefa.isSelected() || rbMetodologia.isSelected() || rbRelacao.isSelected() ) {
					
					switch (gpSelect.getSelection().getActionCommand()) {
					case "P":
						AppPessoas AppPessoas = new AppPessoas();
						break;
						
					case "T":
						AppTarefas AppTarefas = new AppTarefas();
						break;
						
					case "M":
						AppTarefas AppMetodologia = new AppTarefas();
						break;
						
					case "R":
						AppRelacoes AppRelacao = new AppRelacoes();
						break;
					}
					
				} else {
					
					JOptionPane.showMessageDialog(null,"Nenhum item selecionado.","CRUD", JOptionPane.CLOSED_OPTION);
					
				}
			}
		});
		
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(janelaLargura, janelaAltura);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
    public static void main( String[] args ) {
		App a = new App();
    }
}
