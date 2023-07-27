package superdupermarkt;

import java.time.LocalDate;

public abstract class Produkt {


		protected String Bezeichnung;
		protected int Qualität;
		protected LocalDate Verfallsdatum;
		protected double Preis;
		protected double Grundpreis;

		public Produkt(String bezeichnung, int qualität, LocalDate verfallsdatum, double preis) {
			this.Bezeichnung = bezeichnung;
			this.Qualität = qualität;
			this.Verfallsdatum = verfallsdatum;
			this.Grundpreis = preis;
			this.Preis =preis;
		}

		// Function die prüft ob man das Produkt in das Regal hinzufügen kann
		public abstract boolean okayFürRegal(LocalDate heutigesDatum);

		// Function die prüft ob das Produkt aus dem Regal entfernt werden muss
		public abstract boolean ausRegalAusräumen(LocalDate heutigesDatum) ;
		
		// Function die den Preis ändert
		public abstract void täglichePreisänderung();
		
		// Function die die Qualitat ändert
		public abstract void täglicheQualitätsänderung(LocalDate heutigesDatum);
		

		public abstract String getBezeichnung();

		public abstract void setBezeichnung(String bezeichnung);

		public abstract int getQualität();

		public abstract  void setQualität(int qualität);

		public abstract LocalDate getVerfallsdatum() ;
		
		public abstract void setVerfallsdatum(LocalDate verfallsdatum);

		public abstract double getPreis();

		public abstract void setPreis(double preis) ;

		public abstract void printProduct(LocalDate heutigesDatum);

	}
