package it.polito.tdp.rivers.db;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public void getAllRivers(Map<Integer, River> idMapRivers) {
		
		final String sql = "SELECT id, name FROM river";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				if(!idMapRivers.containsKey(res.getInt("id")))
					idMapRivers.put(res.getInt("id"), new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
	}
	
	public void setRiverInfo(River river, Map<Integer, River> idMapRivers){
		
		final String sql = "SELECT MIN(DAY) AS min, MAX(DAY) AS max, COUNT(id) AS c, AVG(flow) AS m FROM flow WHERE river = ?";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();
			
			if(idMapRivers.containsKey(river.getId()))
				while(res.next()) {
					river.setDataMin(res.getDate("min").toLocalDate());
					river.setDataMax(res.getDate("max").toLocalDate());
					river.setFlowNum(res.getInt("c"));
					river.setFlowAvg(res.getDouble("m"));
				}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
	}
	
	public void setFlowRiver(River river, Map<Integer, River> idMapRivers) {
		
		final String sql = "SELECT * FROM flow WHERE river = ? ORDER BY day";
		
		List<Flow> list = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();
			
			if(idMapRivers.containsKey(river.getId())) {
				while(res.next()) {
					Flow f = new Flow(res.getDate("day").toLocalDate(), res.getDouble("flow"), river);
					list.add(f);
				}
				river.setFlows(list);
			}
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
	}
}
