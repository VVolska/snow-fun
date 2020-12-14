package models;

import java.util.ArrayList;
import java.util.Calendar;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "instruktorzy")
public class Instruktor extends Pracownik {

	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private ArrayList<InstruktorNaWyjezdzie> instruktorzyNaWyjezdzie;
	@DatabaseField
	private int numerLicencji;
	@DatabaseField
	private int numerUbezpieczenia;

	public Instruktor() {

	}

	public Instruktor(String imie, String nazwisko, int pesel, Calendar dataUrodzenia, int numerLicencji) {
		super(imie, nazwisko, pesel, dataUrodzenia);
		this.numerLicencji = numerLicencji;
		super.setInstruktor(this);
	}

	void addInstruktoraNaWyjezdzie(InstruktorNaWyjezdzie instruktorNawWyjezdzie) {
		if (!this.instruktorzyNaWyjezdzie.contains(instruktorNawWyjezdzie)) {
			this.instruktorzyNaWyjezdzie.add(instruktorNawWyjezdzie);

		}
	}

	public ArrayList<InstruktorNaWyjezdzie> getInstruktorzyNaWyjezdzie() {
		return instruktorzyNaWyjezdzie;
	}

	public int getNumerLicencji() {
		return numerLicencji;
	}

	public int getNumerUbezpieczenia() {
		return numerUbezpieczenia;
	}

	public void setNumerLicencji(int numerLicencji) {
		this.numerLicencji = numerLicencji;
	}

	public void setNumerUbezpieczenia(int numerUbezpieczenia) {
		this.numerUbezpieczenia = numerUbezpieczenia;
	}

}
