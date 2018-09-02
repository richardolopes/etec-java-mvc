package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Metodologia;

public class MetodologiaJdbcDAO {
	private Connection conn;
	
	public MetodologiaJdbcDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void salvar(Metodologia c) throws SQLException {
		String sql = "insert into metodologia (id, titulo) values ('"+c.getId()+"','"+c.getTitulo()+"')";
		System.out.println(sql);
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
        prepareStatement.close();
	}
	
	public void alterar(Metodologia c) {
		String sql = "update metodologia set titulo = '"+c.getTitulo()+"' where id = "+c.getId()+";";
		System.out.println(sql);
		PreparedStatement prepareStatement;
		try {
			prepareStatement = this.conn.prepareStatement(sql);
			prepareStatement.executeUpdate();
            prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void deletar(int id) {
		String sql = "delete from metodologia where id = " + id;
		System.out.println(sql);
        try {
    		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
    		prepareStatement.executeUpdate();
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}                		
	}
	
	public String retornarInfMetodologia(Object object) throws SQLException {
		String sql = "select * from metodologia where id = " + object + ";";
        System.out.println(sql);
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		ResultSet rs = prepareStatement.executeQuery();
		rs.next();
		String nome = rs.getString("titulo");
		
		String informacoes = nome;
		
		rs.close();
		prepareStatement.close();
		
		return informacoes;
	}
	
	public List<String> listar() {
		String sql = "select count(titulo) as 'Qtd utilizada', titulo as 'Metodologia' from metodologia group by titulo order by count(titulo) DESC";
        System.out.println(sql);		
        List<String> Tarefas = new ArrayList<String>();
		try {
			PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {
				String titulo = rs.getString("Metodologia");
				Tarefas.add(titulo);
			}
			rs.close();
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Tarefas;
	}
	
	public List<String> listarTarefas(String metodologia) {
		String sql = "select * from metodologia where titulo = '" + metodologia + "';";
        System.out.println(sql);		
        List<String> Tarefas = new ArrayList<String>();
		try {
			PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				Tarefas.add(id);
			}
			rs.close();
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Tarefas;
	}
}