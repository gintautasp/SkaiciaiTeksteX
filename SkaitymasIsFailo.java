package skaiciaitekste;

import java.io.BufferedReader;
// import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList; 

public class SkaitymasIsFailo {
	
	private String vardas_failo;
	private BufferedReader br;
	private String file_line;
	
	public SkaitymasIsFailo ( String vardas_failo) {
		
		this.vardas_failo = vardas_failo;
	}
	
	public void pradeti() {
		
		try {
		
			this.br = new BufferedReader( new FileReader( vardas_failo ) );
			skaitytiEilute();
			
		} catch ( Exception e ) {
			
			System.err.format ( "IOException: %s%n", e );
		}		
	}
	
	public ArrayList<String> iMasyva() {
		
		ArrayList<String> zodziai = new ArrayList<String>();
		
		pradeti(); 
		
		while ( nuskaitytaEilute() ) {
			
			paimtiEilute();
			
			zodziai.add ( file_line );
	
			skaitytiEilute();
		}		
		
		return zodziai;
	}
	
	public void skaitytiEilute() {
		
		try {
			
			this.file_line = this.br.readLine();
			
			if ( ! nuskaitytaEilute() ) {
				this.br.close();
			}			
		
		} catch ( Exception e ) {
			
			System.err.format ( "IOException: %s%n", e );
		}

	}
	 
	public boolean nuskaitytaEilute() {
		
		return this.file_line != null;
	}
	
	public String paimtiEilute() {
		
		return this.file_line;
	}
}
