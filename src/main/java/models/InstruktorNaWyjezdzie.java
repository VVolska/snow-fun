package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "instruktorzy_na_wyjezdzie")
public class InstruktorNaWyjezdzie {

	
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(foreign = true)
	private Instruktor instruktor;
	@DatabaseField(foreign = true)
	private Wyjazd wyjazd;
	@DatabaseField
	private int pensja;
	public InstruktorNaWyjezdzie() {
		// ORMLite needs a no-arg constructor
	}
	public InstruktorNaWyjezdzie(Wyjazd wyjazd, Instruktor instruktor) {
		instruktor.addInstruktoraNaWyjezdzie(this);
		//wyjazd.addInstruktorNaWyjezdzie(this);
		this.wyjazd = wyjazd;
		this.instruktor = instruktor;

	}
	public InstruktorNaWyjezdzie(Wyjazd wyjazd, Instruktor instruktor, int pensja) {
		this(wyjazd,instruktor);
		setPensja(pensja);
	}
	
	public int getId() {
		return id;
	}
	public Instruktor getInstruktor() {
		return instruktor;
	}
	public Wyjazd getWyjazd() {
		return wyjazd;
	}
	public int getPensja() {
		return pensja;
	}
	public void setInstruktor(Instruktor instruktor) {
		this.instruktor = instruktor;
	}
	public void setWyjazd(Wyjazd wyjazd) {
		this.wyjazd = wyjazd;
	}
	public void setPensja(int pensja) {
		this.pensja = pensja;
	}

}
