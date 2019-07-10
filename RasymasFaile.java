package skaiciaitekste;

import java.io.BufferedWriter;
//import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList; 


public class RasymasFaile {
	
	public String vardas_failo;
	
	public RasymasFaile( String vardas_failo ) {
		
		this.vardas_failo = vardas_failo;
	}

	public void iEilutes ( ArrayList<String> zodziai ) {
		
		try ( 
				BufferedWriter bw = new BufferedWriter( new FileWriter( vardas_failo ) );
			) {

				for ( String value : zodziai ) { 
							
					bw.write ( value + "\n");
				}
				bw.close();
					
		} catch ( Exception e ) {
				
				System.err.format ( "IOException: %s%n", e );
		}		
	}
}
