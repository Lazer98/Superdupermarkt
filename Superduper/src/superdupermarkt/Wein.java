package superdupermarkt;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Wein extends GenericProdukt {

	private LocalDate Stichtag;

	public Wein(String bezeichnung, int qualität, LocalDate verfallsdatum, double preis, LocalDate Stichtag) {
		super(bezeichnung, qualität, verfallsdatum, preis);
		this.Stichtag = Stichtag;
	}

	// Function die prüft ob man das Produkt in das Regal hinzufügen kann
	@Override
	public boolean okayFürRegal(LocalDate heutigesDatum) {
		return (super.okayFürRegal(heutigesDatum) && this.Qualität > 0);
	}

	// Function die die Qualitat ändert
	@Override
	public void täglicheQualitätsänderung(LocalDate heutigesDatum) {
		// wie viele Tage seit dem Stichtag
		int tageSeitStichtag = (int) ChronoUnit.DAYS.between(this.Stichtag, heutigesDatum);
		if (this.Qualität < 50) {
			
			this.Qualität = (tageSeitStichtag / 10) > 0 ? this.Qualität + 1 : this.Qualität;
		}

	}

	// Function die den Preis ändert
	@Override
	public void täglichePreisänderung() {

	}

	// Function die prüft ob das Produkt aus dem Regal entfernt werden muss
	@Override
	public boolean ausRegalAusräumen(LocalDate heutigesDatum) {
		return false;
	}

	public LocalDate getStichtag() {
		return Stichtag;
	}

	public void setStichtag(LocalDate stichtag) {
		Stichtag = stichtag;
	}

}
