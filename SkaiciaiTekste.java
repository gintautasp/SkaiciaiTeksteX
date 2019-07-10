package skaiciaitekste;

public class SkaiciaiTekste {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SkaiciaiTeksteTvarkymas stt = new SkaiciaiTeksteTvarkymas();
		stt.ieskotiSkaiciu( "tekstas.txt" );
		stt.ieskotiVienetu();
		stt.parodytiSkaicius();
		stt.parodytiVienetus();
		stt.surasytiVntIrAnti();
	}

}
