package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	Simulator sim = new Simulator();
	TreeMap<Integer, Double> mapSim;
	
	public TreeMap<Integer, Double> getSim(River r, double k) {
		mapSim = new TreeMap<>();
		sim.setK(k);
		sim.setR(r);
		sim.init();
		sim.run();
		mapSim.put(sim.getN_giorni_ergoazione_insufficiente(), sim.getC_med());
		return mapSim;
	}
	
	RiversDAO dao = new RiversDAO();
	
	public List<River> getAllRivers(){
		return dao.getAllRivers();
	}
	
	public LocalDate getPrimaMisurazione(River r) {
		return dao.getPrimaMisurazione(r);
	}
	
	public LocalDate getUltimaMisurazione(River r) {
		return dao.getUltimaMisurazione(r);
	}

	public Integer getNumeroMisurazioni(River r) {
		return dao.getNumeroMisurazioni(r);
	}
	
	public Float getFlussoMedio(River r) {
		return dao.getFlussoMedio(r);
	}
}
