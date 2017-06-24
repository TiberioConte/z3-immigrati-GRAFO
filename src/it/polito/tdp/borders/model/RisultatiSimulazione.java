package it.polito.tdp.borders.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class RisultatiSimulazione {
	int maxIstanteSimulato;
	private HashMap<Country,CountrySimulation> immigratiStanziatiInQuestoStato;
	
	
	public RisultatiSimulazione() {
		this.maxIstanteSimulato = 0;
		this.immigratiStanziatiInQuestoStato= new HashMap<Country,CountrySimulation>();;
	}

	public void setMaxIstanteSimulato(int n) {
		if(n>maxIstanteSimulato){
			maxIstanteSimulato=n;
		}
	}

	public void AggiornaImmigratiStanziatiInQuestoStato(Country stato, int immigratiDaAggiungere) {
		if(immigratiStanziatiInQuestoStato.get(stato)==null){
			immigratiStanziatiInQuestoStato.put(stato, new CountrySimulation(stato,immigratiDaAggiungere));
			}else{
				CountrySimulation c=immigratiStanziatiInQuestoStato.get(stato);
				c.setNumeroPersoneStanziate(immigratiDaAggiungere+c.getNumeroPersoneStanziate());	
			}
	}

	public HashMap<Country,CountrySimulation> getImmigratiStanziatiInQuestoStato() {
		return immigratiStanziatiInQuestoStato;
	}
	public String classificaOrdinata(){
		LinkedList<CountrySimulation> lista= new LinkedList<CountrySimulation>(immigratiStanziatiInQuestoStato.values());
		Collections.sort(lista);
		
		StringBuilder risultato = new StringBuilder();
		
		risultato.append("Classifica Stati:\n\n");
				
		for (CountrySimulation c: lista) {
				risultato.append(c.getCountry().toString()+" Ha stanziati : "+c.getNumeroPersoneStanziate()+" , ");
		}
		risultato.setLength(risultato.length() - 2);//elimina ultimo ", "
		risultato.append(".\n");


		return risultato.toString();
	}

	public int getMaxIstanteSimulato() {
		return maxIstanteSimulato;
	}
	
	
	
	
	

}
