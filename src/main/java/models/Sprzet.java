package models;

import java.io.Serializable;
import java.util.ArrayList;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "sprzety")
public class Sprzet implements Serializable{

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String nazwa;
	@DatabaseField(dataType = DataType.ENUM_STRING, canBeNull = false)
	private Typ typ;
	@DatabaseField
	private double cena;
	@DatabaseField(canBeNull = false)
	private int cenaZaDzienWypozyczenia;
	@DatabaseField(canBeNull = false)
	private boolean sprawny;
	@DatabaseField(canBeNull = false)
	private boolean dostepny;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private ArrayList<Wypozyczenie> wypozyczenia;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private ArrayList<Serwis> serwisy;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private static ArrayList<Sprzet> sprzety;
	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	private Sklep sklep;

	public Sprzet() {

	}

	public Sprzet(Typ typ, double cena, int cenaZaDzienWypozyczenia, boolean sprawny, boolean dostepny) {
		this.typ = typ;
		this.cena = cena;
		this.cenaZaDzienWypozyczenia = cenaZaDzienWypozyczenia;
		this.sprawny = sprawny;
		this.dostepny = dostepny;
		//sprzety.add(this);
	}

	public Sprzet(String nazwa, Typ typ, double cena, int cenaZaDzienWypozyczenia, boolean sprawny, boolean dostepny) {
		this(typ, cena, cenaZaDzienWypozyczenia, sprawny, dostepny);
		setNazwa(nazwa);
	}
	
	public Sprzet(Sklep sklep, String nazwa, Typ typ, double cena, int cenaZaDzienWypozyczenia, boolean sprawny, boolean dostepny) {
		this(nazwa,typ, cena, cenaZaDzienWypozyczenia, sprawny, dostepny);
		this.sklep = sklep;
	}

	public enum Typ {
		Narty, Kijki, Snowboard

	};

	void addWypozyczenie(Wypozyczenie wypozyczenie) {
		if (!wypozyczenia.contains(wypozyczenie)) {
			wypozyczenia.add(wypozyczenie);
		}
	}
	public static void addSprzet(Sprzet sprzet) {
		if (!sprzety.contains(sprzet)) {
			sprzety.add(sprzet);
		}
	}

	void addSerwis(Serwis serwis) {
		if (!serwisy.contains(serwis)) {
			serwisy.add(serwis);
		}
	}

	static void wyswietlInformacjeODostepnymSprzecie() {
		for (Sprzet s : sprzety) {
			if (s.dostepny) {
				System.out.println(s.getNazwa() + " " + s.getTyp() + " " + s.getCena());
			}
		}
	}

	public int getId() {
		return id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public ArrayList<Serwis> getSerwisy() {
		return serwisy;
	}

	public Sklep getSklep() {
		return sklep;
	}

	public void setSklep(Sklep sklep) {
		this.sklep = sklep;
	}

	public ArrayList<Wypozyczenie> getWypozyczenia() {
		return wypozyczenia;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Typ getTyp() {
		return typ;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getCenaZaDzienWypozyczenia() {
		return cenaZaDzienWypozyczenia;
	}

	public boolean isSprawny() {
		return sprawny;
	}

	public boolean isDostepny() {
		return dostepny;
	}

	public void setDostepny(boolean b) {
		this.dostepny = b;
	}

}
