package view;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import controller.JdbUtil;
import controller.Rel_tarefa_pessoaJdbcDAO;
import controller.TarefasJdbcDAO;

public class Teste {

	
	public static void main(String [] args) {
		try {
			Connection connection = JdbUtil.getConnection();
			
			Rel_tarefa_pessoaJdbcDAO a = new Rel_tarefa_pessoaJdbcDAO(connection);
			
			
		} catch(java.sql.SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null,"Há relações com está tarefa.", "Teste", 1);
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("TESTEa");
		}
	}
}
