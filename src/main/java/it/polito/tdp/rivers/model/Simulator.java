package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.db.RiversDAO;

public class Simulator {
	
	private RiversDAO dao;
	
	private PriorityQueue<Flow> queue;
	
	private double k;
	private River river;
	
	private List<Flow> listaFlussi;
	private int index;
	
	private double fmed;
	private double Q;
	private double fout_min;
	private double p;
	
	private double fout;
	private double C;
	
	private int n_giorni_ergoazione_insufficiente;
	private double C_med;
	private double C_tot;
	
	public void init() {
		dao = new RiversDAO();
		
		queue = new PriorityQueue<>();
		
		listaFlussi = dao.getFlussi(river);
		index = 1;
		
		fmed = this.dao.getFlussoMedio(river)*60*60*24;
		Q = k*30*fmed;
		fout_min = 0.8*fmed;
		p = 0.05;

		fout = 0;
		C = Q/2;
		
		this.n_giorni_ergoazione_insufficiente = 0;
		this.C_med = 0;
		this.C_tot = 0;
		
		for(Flow f: listaFlussi)
			queue.add(new Flow(f.getDay(), f.getFlow(), river));
		
	}
	
	public void run() {
		
		while(!this.queue.isEmpty()) {
			Flow f = this.queue.poll();
			//System.out.println(e);
			processEvent(f);
		}
		
	}
	
	private void processEvent(Flow f) {
		// TODO Auto-generated method stub
		//LocalDate day = e.getDay();
		double fin = f.getFlow()*60*60*24;
		//EventType type = e.getType();
		double dado = Math.random();
		
		C += fin;
		
		if(dado>p)
			fout = fout_min;
		else
			fout = 10*fout_min;
		
		C = C-fout;
		
		if(C<0) {
			C = 0;
			this.n_giorni_ergoazione_insufficiente++;
		}
		
		if(C>Q)
			C=Q;
		
		C_tot = C_tot+C;
		C_med = C_tot/index;
		index++;
		
	}

	public void setR(River r) {
		this.river = r;
	}

	public int getN_giorni_ergoazione_insufficiente() {
		return n_giorni_ergoazione_insufficiente;
	}
	
	public double getC_med() {
		return C_med;
	}

	public void setK(double k) {
		this.k = k;
	}
	
}
