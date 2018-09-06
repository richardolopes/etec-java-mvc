package view;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.JdbUtil;
import controller.MetodologiaJdbcDAO;
import controller.Rel_tarefa_pessoaJdbcDAO;
import controller.TarefasJdbcDAO;
import model.Metodologia;
import model.Tarefas;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionEvent;

public class EditarTarefa extends JFrame {
	static String nomeJanela = "Editar Tarefa";
	static int errorDanger = 0;
	static int errorInformation = 1;
	static int errorWarning = 2;
	static int errorMissing = 3;
	
	JPanel panelTarefa = new JPanel();
	
	JLabel lblNomeTarefa = new JLabel("Nome da tarefa: ");
	JTextField txtNomeTarefa = new JTextField();

	JLabel lblDescricaoTarefa = new JLabel("Descrição: ");
	JTextField txtDescricaoTarefa = new JTextField();
	
	JLabel lblPrazoEstimado = new JLabel("Prazo estimado (AAAA-MM-DD): ");
	JFormattedTextField txtPrazoEstimado = new JFormattedTextField();
	
	JLabel lblDataInicio = new JLabel("Data de início (AAAA-MM-DD): ");
	JFormattedTextField txtDataInicio = new JFormattedTextField();
	
	JLabel lblDataTermino = new JLabel("Data de término (AAAA-MM-DD): ");
	JFormattedTextField txtDataTermino = new JFormattedTextField();

	JButton editarTarefa = new JButton("Editar Tarefa");
	JButton editarMetodologia = new JButton("Editar Metodologia");
	
	
	public EditarTarefa(final int id) {
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
			alturaPanel = distanciaSuperior*10-altura,
			
			janelaAltura = alturaPanel+distanciaSuperior*2-g/2,
			janelaLargura = (larguraPanel+distanciaLateral*2-g/4)-2;
				
		//   <-------->
		// 	 <-TAREFA->
		//   <-------->
		panelTarefa.setLayout(null);
		panelTarefa.setBorder(javax.swing.BorderFactory.createTitledBorder(nomeJanela));
		panelTarefa.setBounds				(15, 10, larguraPanel, alturaPanel);

		
		lblNomeTarefa.setBounds			(distanciaLateral, distanciaSuperior*1, largura, altura);
		lblDescricaoTarefa.setBounds	(distanciaLateral, distanciaSuperior*2, largura, altura);
		lblPrazoEstimado.setBounds		(distanciaLateral, distanciaSuperior*3, largura*2, altura);
		lblDataInicio.setBounds			(distanciaLateral, distanciaSuperior*4, largura*2, altura);
		lblDataTermino.setBounds		(distanciaLateral, distanciaSuperior*5, largura*2, altura);

		txtNomeTarefa.setBounds			(distanciaTXT, distanciaSuperior*1, largura, altura);
		txtDescricaoTarefa.setBounds	(distanciaTXT, distanciaSuperior*2, largura, altura);
		txtPrazoEstimado.setBounds		(distanciaTXT+distanciaLateral*4+g/4, distanciaSuperior*3, 67, altura);
		txtDataInicio.setBounds			(distanciaTXT+distanciaLateral*4+g/4, distanciaSuperior*4, 67, altura);
		txtDataTermino.setBounds		(distanciaTXT+distanciaLateral*4+g/4, distanciaSuperior*5, 67, altura);

		editarTarefa.setBounds			(distanciaLateral, distanciaSuperior*8, largura, altura);
		editarMetodologia.setBounds		(distanciaTXT, distanciaSuperior*8, largura, altura);
		
		panelTarefa.add(lblNomeTarefa);
		panelTarefa.add(lblPrazoEstimado);
		panelTarefa.add(lblDescricaoTarefa);
		panelTarefa.add(lblDataInicio);
		panelTarefa.add(lblDataTermino);
		
		panelTarefa.add(txtNomeTarefa);
		panelTarefa.add(txtPrazoEstimado);
		panelTarefa.add(txtDescricaoTarefa);
		panelTarefa.add(txtDataInicio);
		panelTarefa.add(txtDataTermino);
		
		panelTarefa.add(editarTarefa);
		panelTarefa.add(editarMetodologia);
		
		paine.add(panelTarefa);
		
		try {
			MaskFormatter mskPrazo = new MaskFormatter("####/##/##");
			mskPrazo.setPlaceholderCharacter('_');
			mskPrazo.install(txtPrazoEstimado);
			
			MaskFormatter mskDtInicio = new MaskFormatter("####/##/##");
			mskDtInicio.setPlaceholderCharacter('_');
			mskDtInicio.install(txtDataInicio);
			
			MaskFormatter mskDtTermino = new MaskFormatter("####/##/##");
			mskDtTermino.setPlaceholderCharacter('_');
			mskDtTermino.install(txtDataTermino);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		tarefa(id);
		
		editarTarefa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNomeTarefa.getText().isEmpty() || txtDescricaoTarefa.getText().isEmpty() || txtPrazoEstimado.getText().isEmpty() || txtDataInicio.getText().isEmpty() || txtDataTermino.getText().isEmpty()) {
					System.out.println("Preencha todos os campos.");
				} else {
					editarTarefa(id, txtNomeTarefa.getText(), txtDescricaoTarefa.getText(), txtPrazoEstimado.getText(), txtDataInicio.getText(), txtDataTermino.getText());
				}
			}
		});
		
		editarMetodologia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarMetodologia a = new EditarMetodologia(id, false);
				dispose();
			}
		});
		
		
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(janelaLargura, janelaAltura);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public void tarefa(int idTarefa) {
		try {
			Connection connection = JdbUtil.getConnection();
			TarefasJdbcDAO tar = new TarefasJdbcDAO(connection);
			
			String[] tarefa = tar.retornarInfTarefa(idTarefa);
			
			txtNomeTarefa.setText(tarefa[0]);
			txtDescricaoTarefa.setText(tarefa[2]);
			txtPrazoEstimado.setText(replace(tarefa[1]));
			txtDataInicio.setText(replace(tarefa[3]));
			txtDataTermino.setText(replace(tarefa[4]));
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
	}
	
	public void editarTarefa(int id, String titulo, String descricao, String prazo, String datainicio, String datatermino) {
		try {
			Connection connection = JdbUtil.getConnection();
			TarefasJdbcDAO tar = new TarefasJdbcDAO(connection);
			Tarefas tarefa = new Tarefas();
			
			tarefa.setId(id);
			tarefa.setTitulo(titulo);
			tarefa.setDescricao(descricao);
			tarefa.setPrazo_estimado(prazo);
			tarefa.setData_inicio(datainicio);
			tarefa.setData_termino(datatermino);
			
			tar.alterar(tarefa);
			JOptionPane.showMessageDialog(null,"Alteração realizada com sucesso.", nomeJanela, errorInformation);
			AppTarefas a = new AppTarefas();
			dispose();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Erro.", nomeJanela, errorDanger);
			ex.printStackTrace();
		}
	}
	
	public void visibilidade(boolean a) {
		lblNomeTarefa.setVisible(a);
		lblDescricaoTarefa.setVisible(a);
		lblPrazoEstimado.setVisible(a);
		lblDataInicio.setVisible(a);
		lblDataTermino.setVisible(a);
		
		txtNomeTarefa.setVisible(a);
		txtDescricaoTarefa.setVisible(a);
		txtPrazoEstimado.setVisible(a);
		txtDataInicio.setVisible(a);
		txtDataTermino.setVisible(a);
		
		editarTarefa.setVisible(a);
		editarMetodologia.setVisible(a);
	}
	
	public String replace(String data) {
		return data.replace("-", "/");
	}
	
	public static void main(String[] args) {
		EditarTarefa janela = new EditarTarefa(2);
	}
}
