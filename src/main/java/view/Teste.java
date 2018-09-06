package view;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import controller.JdbUtil;
import controller.Rel_tarefa_pessoaJdbcDAO;
import controller.TarefasJdbcDAO;
import model.Rel_tarefa_pessoa;

public class Teste {

	
	public static void main(String [] args) {
		try {
			Connection connection = JdbUtil.getConnection();
			
			Rel_tarefa_pessoaJdbcDAO a = new Rel_tarefa_pessoaJdbcDAO(connection);
			Rel_tarefa_pessoa rel = new Rel_tarefa_pessoa();
			
			rel.setId_pessoa(0);
			rel.setId_tarefa(3);
			
			a.salvar(rel);
			
		} catch(java.sql.SQLIntegrityConstraintViolationException ex) {
			ex.printStackTrace();
			System.out.println("Teste :)");
		
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Grrr");
		}
	}
}
