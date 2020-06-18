package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Event implements Comparable<Event>{
	
	public enum EventType {
		F_IN, F_OUT;
	}
	
	private LocalDate date;
	private EventType type;
	private double flow;
	
	public Event(LocalDate date, EventType type, double flow) {
		this.date = date;
		this.type = type;
		this.flow = flow;
	}

	public LocalDate getDate() {
		return date;
	}

	public EventType getType() {
		return type;
	}
	
	public double getFlow() {
		return flow;
	}

	@Override
	public int compareTo(Event o) {
		return this.date.compareTo(o.date);
	}

	@Override
	public String toString() {
		return "Event [date=" + date + ", type=" + type + ", flow=" + flow + "]";
	}
		
}
