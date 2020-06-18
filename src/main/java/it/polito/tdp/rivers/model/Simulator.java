package it.polito.tdp.rivers.model;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.model.Event.EventType;

public class Simulator {
	
	//PARAMETRI DI SIMULAZIONE
	private River river;
	private double Q;
	private LocalDate dataMin;
	private LocalDate dataMax;
	private long giorniTot;
	private double foutMin;
	private double foutTop;
	
	// OUTPUT DA CALCOLARE
	private int giorniNo;
	private double sommaC;
	
	// STATO DEL SISTEMA
	private double C;
	
	// CODA DEGLI EVENTI
	private PriorityQueue<Event> queue;
	
	public void init(double k, River river) {
		queue = new PriorityQueue<>();
		this.river = river;
		Q = k * this.river.getFlowAvg() * 3600 * 24 * 30;
		dataMin = this.river.getDataMin();
		dataMax = this.river.getDataMax();
		giorniTot = dataMin.until(dataMax, ChronoUnit.DAYS)+1;
		foutMin = 0.8*this.river.getFlowAvg()*3600*24;
		foutTop = 10 * foutMin;
		C = Q/2;
		giorniNo = 0;
		sommaC = 0;
		//System.out.println(Q);
		//System.out.println(C);
		LocalDate giorno = dataMin;
		while(giorno.isBefore(dataMax) || giorno.isEqual(dataMax)) {
			Event e = new Event(giorno, EventType.F_IN, this.river.getFlows().get(giorno.getDayOfMonth()-1).getFlow()*3600*24);
			queue.add(e);
			giorno = giorno.plusDays(1);
		}
	}
	
	public void run () {
		while(!queue.isEmpty()) {
			Event e = queue.poll();
			processEvent(e);
			//System.out.println(e);
		}
	}

	private void processEvent(Event e) {
		switch(e.getType()) {
			case F_IN:
				double fout = 0;
				C += e.getFlow();
				if(Math.random()>=0.95) {
					if(C>=foutTop && C<=Q+foutTop)
						fout = foutTop;
					else if(C<=foutTop) {
						fout = C;
						giorniNo++;
					}
					else if(C>=Q+0.8*foutTop)
						fout = C - Q; 
				}
				else {
					if(C>=foutMin && C<=Q+foutMin)
						fout = foutMin;
					else if(C<=foutMin) {
						fout = C;
						giorniNo++;
					}
					else if(C>=Q+0.8*foutMin)
						fout = C - Q;
				}
				queue.add(new Event(e.getDate(), EventType.F_OUT, fout)); 
				break;
			case F_OUT:
				C -= e.getFlow();
				sommaC += C;
				//System.out.println(C);
				break;
		}
	}
	
	public double getAvg() {
		return (sommaC/giorniTot);
	}
	
	public int getGiorniNo() {
		return giorniNo;
	}
}
