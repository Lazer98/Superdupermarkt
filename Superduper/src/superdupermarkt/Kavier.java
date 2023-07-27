package superdupermarkt;
import java.time.LocalDate;

public class Kavier extends GenericProdukt {
    public Kavier(String bezeichnung, int qualität, LocalDate verfallsdatum, double preis) {
        super(bezeichnung, qualität, verfallsdatum, preis);
    }
    
 // Function die die Qualitat ändert
 	@Override
 	public void täglicheQualitätsänderung(LocalDate heutigesDatum) {
 		this.Qualität = Qualität - 5;
 	}
 	
	// Function die den Preis ändert
    @Override
    public void täglichePreisänderung() {
        this.Preis = this.Preis * 0.66666666666;
		this.Preis = Math.round(this.Preis * 100.0) / 100.0;
    }
}