package it.polito.tdp.borders.model;

public class CountrySimulation implements Comparable<CountrySimulation>{
	private Country country;
	private int numeroPersoneStanziate;
	public CountrySimulation(Country country, int numeroPersoneStanziate) {
		this.country = country;
		this.numeroPersoneStanziate = numeroPersoneStanziate;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public int getNumeroPersoneStanziate() {
		return numeroPersoneStanziate;
	}
	public void setNumeroPersoneStanziate(int numeroPersoneStanziate) {
		this.numeroPersoneStanziate = numeroPersoneStanziate;
	}
	
	
	@Override
	public int compareTo(CountrySimulation o) {
		return o.getNumeroPersoneStanziate()-this.getNumeroPersoneStanziate();
	}
	
	
	

}
