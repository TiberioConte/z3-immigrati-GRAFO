package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

import org.jgrapht.Graphs;



public class Simulatore {
	private Model model;
	
	// Parametri di simulazione(parametri che puoi cambiare 
	//prima di avviare la simulazione ma una volta avviata
	//restano costanti)
	private int migrantiIniziali;
	private Country statoPartenza;
	
	// Variabili di interesse(che sono il risultato della simulazione)
	//insieme allo
	// Stato del modello(variabili che durante la simulazione 
	//variano il loro valore)
	RisultatiSimulazione rs;
	//in rs maxIstanteSimulato è una variabile di interesse
	//mentre immigratiStanziatiInQuestoStato è lo stato del modello
	//che però a fine simulazione diventa anche esso  una variabile di interesse
	
	// Lista degli eventi
	PriorityQueue<Event> queue;

	public Simulatore( Country statoPartenza,Model model) {
		rs=new RisultatiSimulazione();
		this.migrantiIniziali = 1000;
		this.queue = new PriorityQueue<Event>();
		this.statoPartenza=statoPartenza;
		this.model=model;
	}

	public void setMigrantiIniziali(int migrantiIniziali) {
		this.migrantiIniziali = migrantiIniziali;
	}
	
	public void setStatoPartenza(Country statoPartenza) {
		this.statoPartenza = statoPartenza;
	}

	public void caricaEventi(){
		queue.add(new Event(1,statoPartenza,migrantiIniziali));
	}
	public void run() {
		while (!queue.isEmpty()) {
			Event e = queue.poll();
			System.out.println(e.toString());
			rs.setMaxIstanteSimulato(e.getPasso());
			int NONRimangonoVecchioStato=e.getNummeroImmigrati()-(int)e.getNummeroImmigrati()/2;
			ArrayList<Country> vicini= new ArrayList<Country>(Graphs.neighborListOf(model.getGrafo(),e.getStato()));
			int personePerOgniStatoVicino=(int)NONRimangonoVecchioStato/vicini.size();
			if(personePerOgniStatoVicino>0){
				for(Country c:vicini){
					queue.add(new Event(e.getPasso()+1,c,personePerOgniStatoVicino));
				}
				rs.AggiornaImmigratiStanziatiInQuestoStato(e.getStato(), e.getNummeroImmigrati()-personePerOgniStatoVicino*vicini.size());
			}else{
				rs.AggiornaImmigratiStanziatiInQuestoStato(e.getStato(), e.getNummeroImmigrati());
			}
		}
	}
	public RisultatiSimulazione getRisultati(){
		return rs;
	}
	
	

}
