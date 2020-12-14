package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "wyjazdy")
public class Wyjazd {

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String nazwa;
	@DatabaseField
	private String miejscowosc;
	@DatabaseField
	private int minimalnyWiekUczestnika;
	@DatabaseField
	private int koszt;
	@DatabaseField(dataType = DataType.DATE)
	private Date dataRozpoczecia;
	@DatabaseField(dataType = DataType.DATE)
	private Date dataZakonczenia;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private ArrayList<Szkolenie> szkolenia;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private ArrayList<InstruktorNaWyjezdzie> instruktorzyNaWyjezdzie;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private ArrayList<Rezerwacja> rezerwacje;

	public Wyjazd() {
		// ORMLite needs a no-arg constructor
	}

	public Wyjazd(String nazwa, String miejscowosc, int minimalnyWiekUczestnika, int koszt, Calendar dataRozpoczecia,
			Calendar dataZakonczenia) {
		this.nazwa = nazwa;
		this.miejscowosc = miejscowosc;
		this.minimalnyWiekUczestnika = minimalnyWiekUczestnika;
		this.koszt = koszt;
		this.dataRozpoczecia = dataRozpoczecia.getTime();
		this.dataZakonczenia = dataZakonczenia.getTime();
		this.szkolenia = new ArrayList<>();
		this.instruktorzyNaWyjezdzie = new ArrayList<>();
		//this.rezerwacje = new ArrayList<>();
	}

	public static String wyswietlInformacjeOWyjazdach() {
		return "informacje";
	}

	void addInstruktorNaWyjezdzie(InstruktorNaWyjezdzie instruktorNawWyjezdzie) {
		if (!this.instruktorzyNaWyjezdzie.contains(instruktorNawWyjezdzie)) {
			this.instruktorzyNaWyjezdzie.add(instruktorNawWyjezdzie);

		}
	}

	public void usunInstruktora(InstruktorNaWyjezdzie instruktorNaWyjezdzie) {
		instruktorNaWyjezdzie.setWyjazd(null);
		instruktorNaWyjezdzie.setInstruktor(null);
		instruktorzyNaWyjezdzie.remove(instruktorNaWyjezdzie);
	}

	public Wyjazd utworzWyjazd() {
		return new Wyjazd();
	}

	public int getId() {
		return id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public String getMiejscowosc() {
		return miejscowosc;
	}

	public int getMinimalnyWiekUczestnika() {
		return minimalnyWiekUczestnika;
	}

	public int getKoszt() {
		return koszt;
	}

	public Date getDataRozpoczecia() {
		return dataRozpoczecia;
	}

	public Date getDataZakonczenia() {
		return dataZakonczenia;
	}

	public ArrayList<Szkolenie> getSzkolenia() {
		return szkolenia;
	}

	public ArrayList<InstruktorNaWyjezdzie> getInstruktorzyNaWyjezdzie() {
		return instruktorzyNaWyjezdzie;
	}

	public ArrayList<Rezerwacja> getRezerwacje() {
		return rezerwacje;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc;
	}

	public void setMinimalnyWiekUczestnika(int minimalnyWiekUczestnika) {
		this.minimalnyWiekUczestnika = minimalnyWiekUczestnika;
	}

	public void setKoszt(int koszt) {
		this.koszt = koszt;
	}

	public void setDataRozpoczecia(Date dataRozpoczecia) {
		this.dataRozpoczecia = dataRozpoczecia;
	}

	public void setDataZakonczenia(Date dataZakonczenia) {
		this.dataZakonczenia = dataZakonczenia;
	}

	public void addSzkolenie(Szkolenie szkolenie) {
		szkolenia.add(szkolenie);
	}

	public void addRezerwacja(Rezerwacja rezerwacja) {
		if (!rezerwacje.contains(rezerwacja)) {
			rezerwacje.add(rezerwacja);
		}
	}

}
