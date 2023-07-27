package superdupermarkt;

import java.time.LocalDate;

public class GenericProdukt extends Produkt {


	public GenericProdukt(String bezeichnung, int qualität, LocalDate verfallsdatum, double preis) {
		super(bezeichnung,qualität,verfallsdatum,preis);
	}

	// Function die prüft ob man das Produkt in das Regal hinzufügen kann
	@Override
	public boolean okayFürRegal(LocalDate heutigesDatum) {
		return (this.Verfallsdatum.isAfter(heutigesDatum));
	}

	// Function die prüft ob das Produkt aus dem Regal entfernt werden muss
	@Override
	public boolean ausRegalAusräumen(LocalDate heutigesDatum) {
		return !(this.Verfallsdatum.isAfter(heutigesDatum));
	}

	// Function die den Preis ändert
	@Override
	public void täglichePreisänderung() {
		this.Preis = this.Grundpreis + (0.1 * this.Qualität);
		this.Preis = Math.round(this.Preis * 100.0) / 100.0;
	}

	// Function die die Qualitat ändert
	@Override
	public void täglicheQualitätsänderung(LocalDate heutigesDatum) {
		this.Qualität = Qualität - 1;
	}

	@Override
	public String getBezeichnung() {
		return Bezeichnung;
	}

	@Override
	public void setBezeichnung(String bezeichnung) {
		Bezeichnung = bezeichnung;
	}

	@Override
	public int getQualität() {
		return Qualität;
	}

	@Override
	public void setQualität(int qualität) {
		Qualität = qualität;
	}

	@Override
	public LocalDate getVerfallsdatum() {
		return Verfallsdatum;
	}

	@Override
	public void setVerfallsdatum(LocalDate verfallsdatum) {
		Verfallsdatum = verfallsdatum;
	}

	@Override
	public double getPreis() {
		return Preis;
	}

	@Override
	public void setPreis(double preis) {
		Preis = preis;
	}

	@Override
	public void printProduct(LocalDate heutigesDatum) {
		boolean mussEntsorgtWerden = ausRegalAusräumen(heutigesDatum);
		String fazit = mussEntsorgtWerden ? "Ja" : "Nein";
		System.out.println("Produkt [Bezeichnung=" + Bezeichnung + ", Qualität=" + Qualität + ", Preis=" + Preis
				+ " ,muss Entsorgt werden: " + fazit + " ]");
	}

}
