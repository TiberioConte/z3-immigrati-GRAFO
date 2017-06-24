package it.polito.tdp.borders.db;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.CountryIdMap;
import it.polito.tdp.borders.model.CountryPair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BordersDAO {
	
	public ArrayList<Integer> getAnni() {
		String sql = "select distinct year from contiguity ORDER BY  year ASC;" ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			ArrayList<Integer> list = new ArrayList<Integer>() ;
			
			while( rs.next() ) {
				
				list.add(rs.getInt("year")) ;
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}

	
	
	public List<Country> loadAllCountries() {
		
		String sql = 
				"SELECT ccode,StateAbb,StateNme " +
				"FROM country " +
				"ORDER BY StateAbb " ;

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			List<Country> list = new LinkedList<Country>() ;
			
			while( rs.next() ) {
				
				Country c = new Country(
						rs.getInt("ccode"),
						rs.getString("StateAbb"), 
						rs.getString("StateNme")) ;
				
				list.add(c) ;
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
	}
	
	public ArrayList<Country> tuttiCountriEsistentiGiaAnno(int anno, CountryIdMap mappaCountry) {
		String sql = 
				"select distinct (country.CCode),country.StateAbb,country.StateNme from contiguity,country where CCode=state1no and year<=?";

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			
			ResultSet rs = st.executeQuery() ;
			
			ArrayList<Country> list = new ArrayList<Country>() ;
			
			while( rs.next() ) {
				Country c = mappaCountry.get(rs.getInt("ccode"));
				if(c==null){
					c=new Country(rs.getInt("ccode"),rs.getString("StateAbb"),rs.getString("StateNme")) ;
					mappaCountry.put(c);
				}
						
				list.add(c) ;
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
		
	}
	
	public ArrayList<CountryPair> tuttiIconfiniEsistentiGiaAnno(int anno, CountryIdMap mappaCountry) {
		String sql = 
				"select state1no,state2no from contiguity where year<=? and conttype='1'";

		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno);
			
			ResultSet rs = st.executeQuery() ;
			
			ArrayList<CountryPair> list = new ArrayList<CountryPair>() ;
			
			while( rs.next() ) {
				
				Country c1 = mappaCountry.get(rs.getInt("state1no"));
				Country c2 = mappaCountry.get(rs.getInt("state2no"));
				if(c1==null||c2==null){
					throw new IllegalArgumentException("ERRORE! Stato non presente nella mappa");
				}else{
					list.add(new CountryPair(c1,c2));
					
				}
			}
			
			conn.close() ;
			
			return list ;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null ;
		
	}
	

	

	
	
	
}
