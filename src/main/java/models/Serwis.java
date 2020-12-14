package models;

import java.util.ArrayList;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "serwisy")
public class Serwis {
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(foreign = true)
	private Sprzet sprzet;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private ArrayList<PracownikStacjonarny> pracownicy;

	public Serwis() {
		// ORMLite needs a no-arg constructor
	}

	public Serwis(Sprzet sprzet, PracownikStacjonarny pracownik) {
		this.sprzet = sprzet;
		this.pracownicy = new ArrayList<>();
	}

	public void wprowadzDyspozycje(Sprzet sprzet) {
		sprzet.setDostepny(false);
	}

	public Sprzet getSprzet() {
		return sprzet;
	}

	public void addPracownik(PracownikStacjonarny pracownik) {
		this.pracownicy.add(pracownik);
	}

	public ArrayList<PracownikStacjonarny> getPracownicy() {
		return this.pracownicy;
	}

}
