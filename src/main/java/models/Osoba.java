package models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "osoby")
abstract class Osoba {
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(canBeNull = false )
	private String imie;
	@DatabaseField(canBeNull = false )
	private String nazwisko;
	@DatabaseField(canBeNull = false )
	private int pesel;
	@DatabaseField(dataType=DataType.DATE)
	Date dataUrodzenia;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

	public Osoba() {
		// ORMLite needs a no-arg constructor
	}

	public Osoba(String imie, String nazwisko, int pesel, Calendar dataUrodzenia) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pesel = pesel;
		this.dataUrodzenia = dataUrodzenia.getTime();

	}

	public int getId() {
		return id;
	}

	public String getImie() {
		return imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public int getPesel() {
		return pesel;
	}

	public String getDataUrodzenia() {
		return dateFormat.format(dataUrodzenia.getTime());
	}

	public int getWiek() { // z punktu widzenia programu interesuje nas wiek rocznikowy
		return wyliczWiek();

	}

	private int wyliczWiek() {
		String[] dataUrodzeniaString = dateFormat.format(dataUrodzenia).split("/");
		int rokUrodzenia = Integer.parseInt(dataUrodzeniaString[0]);
		String[] dataTerazString = dateFormat.format(new Date()).split("/");
		int rokTeraz = Integer.parseInt(dataTerazString[0]);

		return rokTeraz - rokUrodzenia;

	}

}
