package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	private BordersDAO dao;
	private SimpleGraph<Country,DefaultEdge> grafo;
	private CountryIdMap mappaCountry;
	private ArrayList<Integer> anni;
	
	private Simulatore simulatore;;
	
	public Model() {
		dao=new BordersDAO();
		anni=dao.getAnni();
		mappaCountry=new CountryIdMap();
	}
	
	public ArrayList<Integer> getAnni() {
		return anni;
	}

	public SimpleGraph<Country, DefaultEdge> getGrafo() {
		return grafo;
	}
	

	public void CreaGrafo(int anno){
		grafo=new SimpleGraph<Country,DefaultEdge>(DefaultEdge.class);
		//creo i vertici
		Graphs.addAllVertices(grafo, dao.tuttiCountriEsistentiGiaAnno(anno,mappaCountry));
		System.out.format("Ho aggiunto %d nodi. \n",grafo.vertexSet().size());
		//creo gli archi
		for(CountryPair cp:dao.tuttiIconfiniEsistentiGiaAnno(anno,mappaCountry)){
			grafo.addEdge(cp.getStato1(), cp.getStato2());
		}
		System.out.format("Ho aggiunto %d archi. \n",grafo.edgeSet().size());
	}
	
	public ArrayList<Country> getVerticiGrafoOridinatiPerNome(){
		//devo usare questa alternativa in quanto il compare to della classe Country mi serve per un altra cosa
		ArrayList<Country> result=new ArrayList<Country>(grafo.vertexSet());
		
		Collections.sort(result,new  Comparator<Country>() {	
		@Override
			public int compare(Country arg0, Country arg1) {
			return arg0.getStateName().compareTo(arg1.getStateName());}
		});
		
		return result;	
	}
	
	public String classifica(){
		LinkedList<Country> classifica =new LinkedList<Country>();
		for(Country c: grafo.vertexSet()){
			c.setStatiConfinanti(Graphs.neighborListOf(grafo, c).size());
			classifica.add(c);
		}
		Collections.sort(classifica);
		
		StringBuilder risultato = new StringBuilder();
		risultato.append("Classifica Stati:\n\n");
		
		for (Country c: classifica) {
				risultato.append(c.toString()+" Confina con: "+c.getStatiConfinanti()+" stati , ");
		}
		risultato.setLength(risultato.length() - 2);//elimina ultimo ", "
		risultato.append(".\n");

		return risultato.toString();
	}
	public RisultatiSimulazione simula(Country c){
		simulatore=new Simulatore(c,this);
		simulatore.caricaEventi();
		simulatore.run();
		return simulatore.getRisultati();
		
		
	}

}
