package skaiciaitekste;

import java.io.BufferedReader;
// import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SkaiciaiTeksteTvarkymas {
	
	private String[] skaitmenys = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private String[] dalys_skaiciaus = { ".", ",", "-", "e", "E", "+", "/", "%" };
	private String[] zodzio_pabaigos = { ".", ",", " ", "\n", ";", ":" };
	private String[] ne_zodzio_dalis = { ")", ",", ";", "." , "/" };
	
	private String eilute_is_failo;
	
	String[] skaiciai = new String [ 1000 ];
	String[] vnt_po_skaiciu = new String [ 1000 ];
	int kiekis_skaiciu = 0; 	
	
	ArrayList<String> vnt; 
	ArrayList<String> anti_vnt; 
	
	public SkaiciaiTeksteTvarkymas() {
		
		SkaitymasIsFailo sf_vnt = new SkaitymasIsFailo ( "vnt.csv" );
		vnt = sf_vnt.iMasyva();
		SkaitymasIsFailo sf_anti_vnt = new SkaitymasIsFailo ( "anti_vnt.csv" );
		anti_vnt = sf_anti_vnt.iMasyva();		
	}
	
	public void ieskotiSkaiciu( String vardas_failo ) {
		
		SkaitymasIsFailo sf = new SkaitymasIsFailo ( vardas_failo );
		
		sf.pradeti();
		
		System.out.println ( "Duomenø failas: "); 
		
		while ( sf.nuskaitytaEilute() ) {
			
			eilute_is_failo = sf.paimtiEilute();
			
			System.out.println ( eilute_is_failo );
			
			ieskotiSkaiciuEiluteje();
			
			sf.skaitytiEilute();
		}
	}
	
	public void ieskotiVienetu() {
		
		BufferedReader in_kb = new BufferedReader( new InputStreamReader ( System.in ) );		

		for ( int i = 0; i < kiekis_skaiciu; i++ ) {
			
			if ( 
					( vnt.indexOf ( vnt_po_skaiciu [ i ].trim() )  == -1 ) 
				&&  
					(  ! vnt_po_skaiciu [ i ].trim().equals( "" ) ) 
				&&
					( anti_vnt.indexOf ( vnt_po_skaiciu [ i ].trim() ) == -1 )
			) {
			
				System.out.println ( "  -- ??? " + skaiciai [ i ] + " " + vnt_po_skaiciu [ i ] + ": "  );
				System.out.println ( "átraukti á mat. vnt.? " );
				
				String ats = "";
				
				try {
				
					ats = in_kb.readLine();
					
				} catch ( Exception e ) {
					
					System.err.format ( "IOException: %s%n", e );
				}
				
				if ( ats.equals( "" ) ) {
					
					vnt.add ( vnt_po_skaiciu [ i ].trim() );
					
				} else {
				
					anti_vnt.add ( vnt_po_skaiciu [ i ].trim() );
				}
			}
		}
	}
	
	public void parodytiSkaicius() {
		
		for ( int i = 0; i < kiekis_skaiciu; i++ ) {
			
			System.out.println ( " " + skaiciai [ i ] + " " + vnt_po_skaiciu [ i ] );
		}
	}
	
	public void parodytiVienetus() {
		
		System.out.println ( "\nvienetai: \n" );
		
		for ( String value : vnt ) { 
			
			System.out.print( value ); 
			System.out.print( " " ); 
		}
	}
	
	public void surasytiVntIrAnti() {
		
		RasymasFaile rf_vnt = new RasymasFaile ( "vnt.csv" );
		rf_vnt.iEilutes( vnt );
		
		RasymasFaile rf_anti_vnt = new RasymasFaile ( "anti_vnt.csv" );
		rf_anti_vnt.iEilutes( anti_vnt );
	}
	
	public void ieskotiSkaiciuEiluteje() {
		
		boolean pries_tai_buvo_skaitmuo = false;
		
		boolean yra_zodis_po_skaitmens = false;	
		
		String simb;
		
		for ( int i = 0; i < eilute_is_failo.length(); i++ ) {
			
			simb =  eilute_is_failo.substring( i, i+1 );
		
			if ( 
					priklausoAibei ( simb, skaitmenys ) 
				|| 
					( 
							pries_tai_buvo_skaitmuo  
						&& 
							priklausoAibei ( simb, dalys_skaiciaus )
					) 
				||
					yra_zodis_po_skaitmens
			) {
				
				if ( pries_tai_buvo_skaitmuo || yra_zodis_po_skaitmens ) { 				//  ------
					
					if ( yra_zodis_po_skaitmens ) {
						
						vnt_po_skaiciu [ kiekis_skaiciu - 1 ] += simb;  
						
					} else {
				
						skaiciai [ kiekis_skaiciu - 1 ] += simb;
					}
					
				} else {
					
					skaiciai [ kiekis_skaiciu ] = simb;
					vnt_po_skaiciu [ kiekis_skaiciu ] = "";
					kiekis_skaiciu++;
				}
				
				if ( ! yra_zodis_po_skaitmens ) {
					
					pries_tai_buvo_skaitmuo = true;
					
				} else {
				
					if ( priklausoAibei ( simb, zodzio_pabaigos ) ) {
						
						yra_zodis_po_skaitmens = false;
					}
				}
				
			} else {
				
				if ( pries_tai_buvo_skaitmuo ) {
					
					if ( ! priklausoAibei ( simb, ne_zodzio_dalis ) ) {
				
						yra_zodis_po_skaitmens = true;
						// skaiciai [ kiekis_skaiciu - 1 ] += simb;
						vnt_po_skaiciu [ kiekis_skaiciu - 1 ] += simb;
					}
				}
				pries_tai_buvo_skaitmuo = false;
			}
		}		
		
	}
	
	public static boolean priklausoAibei ( String simbolis, String[] aibe ) {
		
		boolean priklauso = false;
		
		for (int i = 0; i < aibe.length; i++ ) {
		
			if ( simbolis.equals ( aibe [ i ] ) ) {
			
				priklauso = true;
			}
		}
		return priklauso;
	}	

}
