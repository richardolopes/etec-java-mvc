package view;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import controller.JdbUtil;
import controller.TarefasJdbcDAO;

public class Teste {

	
	public static void main(String [] args) {
		try {
			Connection connection = JdbUtil.getConnection();
			
			TarefasJdbcDAO a = new TarefasJdbcDAO(connection);
			a.deletar(2);
			
		} catch(java.sql.SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null,"Há relações com está tarefa.", "Teste", 1);
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
