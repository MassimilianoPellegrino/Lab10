package it.polito.tdp.rivers.model;

import it.polito.tdp.rivers.db.RiversDAO;

public class testSimulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Simulator sim = new Simulator();
		RiversDAO dao = new RiversDAO();
		
		sim.setK(0.5);
		sim.setR(dao.getAllRivers().get(1));
		sim.init();
		sim.run();
		System.out.println("Giorni erogazione insufficiente: "+sim.getN_giorni_ergoazione_insufficiente()+"\nC media: "+sim.getC_med());
	}

}
