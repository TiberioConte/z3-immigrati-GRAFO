package it.polito.tdp.borders.model;

public class Event implements Comparable<Event>{
	
	private int passo;
	private Country stato;
	private int nummeroImmigrati;
	
	public Event(int passo, Country stato, int nummeroImmigrati) {
		super();
		this.passo = passo;
		this.stato = stato;
		this.nummeroImmigrati = nummeroImmigrati;
	}
	

	public int getPasso() {
		return passo;
	}


	public void setPasso(int passo) {
		this.passo = passo;
	}


	public Country getStato() {
		return stato;
	}


	public void setStato(Country stato) {
		this.stato = stato;
	}


	public int getNummeroImmigrati() {
		return nummeroImmigrati;
	}


	public void setNummeroImmigrati(int nummeroImmigrati) {
		this.nummeroImmigrati = nummeroImmigrati;
	}


	@Override
	public int compareTo(Event o) {
		return this.passo-o.getPasso();
		
	}


	@Override
	public String toString() {
		return "Event [passo=" + passo + ", stato=" + stato + ", nummeroImmigrati=" + nummeroImmigrati + "]";
	}
	
	
	
}	