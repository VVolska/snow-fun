package models;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "rezerwacje")
public class Rezerwacja {

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(foreign = true)
	private Klient klient;
	@DatabaseField(foreign = true)
	private Szkolenie szkolenie;
	@DatabaseField(foreign = true)
	private Wyjazd wyjazd;
	private Date dataRozpoczecia;
	private Date dataZakonczenia;

	public Rezerwacja() {

	}

	public Rezerwacja(Klient klient, Wyjazd wyjazd) {
		this.dataRozpoczecia = wyjazd.getDataRozpoczecia();
		this.dataZakonczenia = wyjazd.getDataZakonczenia();
		klient.addRezerwacja(this);
		wyjazd.addRezerwacja(this);
		this.klient = klient;
		this.wyjazd = wyjazd;
		

	}

	public Rezerwacja(Klient klient, Wyjazd wyjazd, Szkolenie szkolenie) {
		//this(klient, wyjazd);
		setSzkolenie(szkolenie);

	}

	public void anuluj() {
		this.wyjazd = null;
		this.klient = null;
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

	public Wyjazd getWyjazd() {
		return wyjazd;
	}

	public Klient getKlient() {
		return klient;
	}

	public Szkolenie getSzkolenie() {
		return szkolenie;
	}

	public void setSzkolenie(Szkolenie szkolenie) {
		this.szkolenie = szkolenie;
	}

}
