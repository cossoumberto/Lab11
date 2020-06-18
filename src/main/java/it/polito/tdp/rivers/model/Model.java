package it.polito.tdp.rivers.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO dao;
	private Map<Integer, River> idMapRivers;
	private Simulator s;
	
	public Model() {
		dao = new RiversDAO();
		idMapRivers = new HashMap<>();
		s = new Simulator();
	}
	
	public Collection<River> getAllRivers(){
		dao.getAllRivers(idMapRivers);
		return idMapRivers.values();
	}

	public void setRiverInfo(River river) {
		dao.setRiverInfo(river, idMapRivers);
	}
	
	public void simula(double k, River river) {
		dao.setFlowRiver(river, idMapRivers);
		s.init(k, river);
		s.run();
	}
	
	public double getAvgSimulator() {
		return s.getAvg();
	}
	
	public int getGiorniNoSimulator() {
		return s.getGiorniNo();
	}

}
