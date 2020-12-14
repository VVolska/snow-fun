package models;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "sklepy")
public class Sklep {

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String nazwa;
	@DatabaseField
	private String adres;
	@ForeignCollectionField(eager=true)
	private ForeignCollection<Sprzet> sprzety;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private ArrayList<PracownikStacjonarny> pracownicy;

	public Sklep() {
		// ORMLite needs a no-arg constructor
	}

	public Sklep(String nazwa, String adres) {
		this.nazwa = nazwa;
		this.adres = adres;
		//this.sprzety = new 
	}

	public int getId(){
		return id;
	}
	public String getNazwa() {
		return nazwa;
	}

	public String getAdres() {
		return adres;
	}

	public ArrayList<PracownikStacjonarny> getPracownicy() {
		return pracownicy;
	}

	public void addPracownik(PracownikStacjonarny pracownik) {
		this.pracownicy.add(pracownik);
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public ForeignCollection<Sprzet> getSprzety() {
		return this.sprzety;
	}

	public void setSprzety(ForeignCollection<Sprzet> sprzety) {
		this.sprzety =  sprzety;
		for (Sprzet s : sprzety){
			s.setSklep(this);
		}
	}
	
	public void addSprzet(Sprzet sprzet){
		this.sprzety.add(sprzet);
		sprzet.setSklep(this);
	}

}
