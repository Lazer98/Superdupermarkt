package superdupermarkt;

import java.util.ArrayList;

public class Regal {

	private ArrayList <GenericProdukt> regal;
	
	public Regal() {
		this.regal= new ArrayList<>();
	}

	public ArrayList<GenericProdukt> getRegal() {
		return regal;
	}

	public void setRegal(ArrayList<GenericProdukt> regal) {
		this.regal = regal;
	}
	
	
	
}
