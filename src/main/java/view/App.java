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
	JPanel panelCrud = new JPanel();
	
	JButton btnCRUD = new JButton("CRUD");
	
	// ----
	
	JPanel panelSelect = new JPanel();
	
	ButtonGroup gpSelect = new ButtonGroup();
	JRadioButton rbPessoa = new JRadioButton("Pessoa");
	JRadioButton rbTarefa = new JRadioButton("Tarefa");
	JRadioButton rbMetodologia = new JRadioButton("Metodologia");
	JRadioButton rbRelacao = new JRadioButton("Relação");

	public App() {
		super("CRUD");
		Container paine = this.getContentPane();
		paine.setLayout(null);
		panelCrud.setLayout(null);
		panelSelect.setLayout(null);
		
		panelCrud.setBorder(javax.swing.BorderFactory.createTitledBorder("CRUD"));
		panelCrud.setBounds		(30, 120, 230, 60);
		
		btnCRUD.setBounds		(65, 20, 100, 30);
		panelCrud.add(btnCRUD);
		
		// ----
		
		panelSelect.setBorder(javax.swing.BorderFactory.createTitledBorder("SELECIONE "));
		panelSelect.setBounds		(30, 10, 230, 100);
		
		rbPessoa.setBounds			(10, 20, 100, 30);
		rbTarefa.setBounds			(10, 60, 100, 30);
		rbMetodologia.setBounds		(120, 20, 100, 30);
		rbRelacao.setBounds			(120, 60, 100, 30);
		
		panelSelect.add(rbPessoa);
		panelSelect.add(rbTarefa);
		panelSelect.add(rbMetodologia);
		panelSelect.add(rbRelacao);
		
		gpSelect.add(rbPessoa);
		gpSelect.add(rbTarefa);
		gpSelect.add(rbMetodologia);
		gpSelect.add(rbRelacao);
		
		rbPessoa.setActionCommand("pessoa");
		rbTarefa.setActionCommand("tarefa");
		rbMetodologia.setActionCommand("metodologia");
		rbRelacao.setActionCommand("relacao");
		
		paine.add(panelCrud);
		paine.add(panelSelect);
		
		// ----	
		
		btnCRUD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ( rbPessoa.isSelected() || rbTarefa.isSelected() || rbMetodologia.isSelected() || rbRelacao.isSelected() ) {
					
					switch (gpSelect.getSelection().getActionCommand()) {
					case "pessoa":
						AppPessoas AppPessoas = new AppPessoas();
						//dispose();
						break;
						
					case "tarefa":
						AppTarefas AppTarefas = new AppTarefas();
						//dispose();
						break;
						
					case "metodologia":
						AppTarefas AppMetodologia = new AppTarefas();
						//dispose();
						break;
						
					case "relacao":
						AppRelacoes AppRelacao = new AppRelacoes();
						//dispose();
						break;
					}
					
				} else {
					
					JOptionPane.showMessageDialog(null,"Nenhum item selecionado.","CRUD", JOptionPane.CLOSED_OPTION);
					
				}
			}
		});
		
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(296, 220);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	@SuppressWarnings("unused")
    public static void main( String[] args ) {
		App janelaApp = new App();
    }
}
