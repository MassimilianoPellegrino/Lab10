package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public List<Flow> getFlussi(River r){
		String sql = "SELECT * "
				+ "FROM flow f "
				+ "WHERE river = ? "
				+ "ORDER BY f.day ASC";
		
		List<Flow> flussi = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				flussi.add(new Flow(res.getDate("day").toLocalDate(), res.getFloat("flow"), r));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		return flussi;
		
	}
	
	public LocalDate getPrimaMisurazione(River r) {
		
		
		String sql = "SELECT MIN(f.day) as day "
				+ "FROM flow f "
				+ "WHERE river = ?";
		
		LocalDate d = null;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet res = st.executeQuery();
			if(res.next())
				d = res.getDate("day").toLocalDate();
			
			conn.close();
		}catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		return d;
		
	}
	
	public LocalDate getUltimaMisurazione(River r) {
		
		
		String sql = "SELECT MAX(f.day) as day "
				+ "FROM flow f "
				+ "WHERE river = ?";
		
		LocalDate d = null;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet res = st.executeQuery();
			if(res.next())
				d = res.getDate("day").toLocalDate();
			
			conn.close();
		}catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		return d;
		
	}
	
	public Integer getNumeroMisurazioni(River r) {
		String sql="SELECT COUNT(*) as n "
				+ "FROM flow "
				+ "WHERE river = ?";
		
		Integer n = null;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet res = st.executeQuery();
			if(res.next())
				n = res.getInt("n");
			
			conn.close();
		}catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		return n;
	}
	
	public Float getFlussoMedio(River r) {
		
		Float media = null;
		
		String sql = "SELECT AVG(flow) AS media "
				+ "FROM flow "
				+ "WHERE river = ?";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet res = st.executeQuery();
			if(res.next())
				media = res.getFloat("media");
			
			conn.close();
		}catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		return media;
		
		
		
		
	}
}
