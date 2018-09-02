package view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppMetodologias extends JFrame {
	int i = 0;
	static String nomeJanela = "Metodologias";
	static int errorDanger = 0;
	static int errorInformation = 1;
	static int errorWarning = 2;
	static int errorMissing = 3;
	
	JPanel panelTarefa = new JPanel();
	JPanel panelPessoa = new JPanel();
	
	public AppMetodologias() {
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
			alturaPanel = distanciaSuperior*9-altura,
			
			//janelaAltura = (alturaPanel+distanciaSuperior)*2,
			
			janelaAltura = alturaPanel+distanciaSuperior*2-g/2,
			janelaLargura = larguraPanel+distanciaLateral*2-g/4;
		
		
		
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(janelaLargura, janelaAltura);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public static void main(String [] args) {
		AppMetodologias a = new AppMetodologias();
	}
}
