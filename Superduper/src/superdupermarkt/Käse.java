package superdupermarkt;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Käse extends GenericProdukt {
	
	public Käse(String bezeichnung, int qualität, LocalDate verfallsdatum, double preis) {
		super( bezeichnung, qualität, verfallsdatum, preis);
	}
	
	//Function die prüft ob man das Produkt in das Regal hinzufügen kann
	@Override
	public boolean okayFürRegal(LocalDate heutigesDatum) {
		//wie viele Tage bis zum Verfallsdatum -> 50< x <100
		int verfallsCheck = (int) ChronoUnit.DAYS.between(heutigesDatum ,this.Verfallsdatum);  
		if((this.Qualität>=30) && (verfallsCheck >= 50 && verfallsCheck <= 100)) {
			return true;
		}else {
			return false;
		}
		}
	
	//Function die prüft ob das Produkt aus dem Regal entfernt werden muss
	@Override
	public boolean ausRegalAusräumen(LocalDate heutigesDatum) {
			if(this.Qualität<30 || !(this.Verfallsdatum.isAfter(heutigesDatum))) {
				return true;
			}
			else {
				return false;
			}
		}
	
}
