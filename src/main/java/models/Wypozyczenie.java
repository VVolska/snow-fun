package models;

import java.util.Calendar;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "wypozyczenia")
public class Wypozyczenie {
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(dataType = DataType.DATE)
	private Date dataRozpoczecia;
	@DatabaseField(dataType = DataType.DATE)
	private Date dataZakonczenia;
	@DatabaseField(foreign = true)
	private Sprzet sprzet;
	@DatabaseField(foreign = true)
	private Klient klient;
	private int idKlienta;

	public Wypozyczenie(Calendar dataRozpoczecia, Calendar dataZakonczenia, Klient klient, Sprzet sprzet) {
		this.dataRozpoczecia = dataRozpoczecia.getTime();
		this.dataZakonczenia = dataZakonczenia.getTime();
		this.klient = klient;
		this.sprzet = sprzet;
		this.idKlienta = klient.getId();
		klient.addWypozyczenie(this);
		sprzet.addWypozyczenie(this);
		
	}

	public int wyliczKoszt() {
		return 0;
	}

	public void zakoncz() {
		klient = null;
		idKlienta = 0;
		sprzet = null;
	}

	public int getId() {
		return id;
	}

	public Date getDataRozpoczecia() {
		return dataRozpoczecia;
	}

	public Date getDataZakonczenia() {
		return dataZakonczenia;
	}

	public int getIdSprzetu() {
		return sprzet.getId();
	}

	public int getIdKlienta() {
		return idKlienta;
	}

	public Wypozyczenie() {
	}
}
