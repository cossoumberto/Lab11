package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class River {
	private int id;
	private String name;
	private List<Flow> flows;
	private LocalDate dataMin;
	private LocalDate dataMax;
	private int flowNum;
	private double flowAvg;
	
	public River(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getDataMin() {
		return dataMin;
	}

	public void setDataMin(LocalDate dataMin) {
		this.dataMin = dataMin;
	}

	public LocalDate getDataMax() {
		return dataMax;
	}

	public void setDataMax(LocalDate dataMax) {
		this.dataMax = dataMax;
	}

	public int getFlowNum() {
		return flowNum;
	}

	public void setFlowNum(int flowNum) {
		this.flowNum = flowNum;
	}

	public double getFlowAvg() {
		return flowAvg;
	}

	public void setFlowAvg(double flowAvg) {
		this.flowAvg = flowAvg;
	}

	public void setFlows(List<Flow> flows) {
		this.flows = flows;
	}

	public List<Flow> getFlows() {
		if (flows == null)
			flows = new ArrayList<Flow>();
		return flows;
	}


	@Override
	public String toString() {
		return name;
	}
	
	public String descriviti() {
		return "River [id=" + id + ", name=" + name + ", flows=" + flows.size() + ", dataMin=" + dataMin + ", dataMax="
				+ dataMax + ", flowNum=" + flowNum + ", flowAvg=" + flowAvg + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		River other = (River) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
