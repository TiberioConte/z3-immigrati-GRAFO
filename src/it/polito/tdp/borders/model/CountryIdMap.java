package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.Map;

public class CountryIdMap {
	private Map<Integer,Country> map;

	public CountryIdMap() {
		map = new HashMap<Integer,Country>();
	}
	
	public Country get(int  id){
		return map.get(id);
	}
	
	public void put(Country t){
		 map.put(t.getcCode(),t);
		 
	}
}
