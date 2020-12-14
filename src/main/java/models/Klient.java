package models;

import java.util.ArrayList;
import java.util.Calendar;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "klienci")
public class Klient extends Osoba {

	@DatabaseField
	private String numerKlienta;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private ArrayList<Rezerwacja> rezerwacje;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private ArrayList<Wypozyczenie> wypozyczenia;
	@DatabaseField(foreign = true)
	private Pracownik pracownik;
	private int idPracownika;

	public Klient() {
		// ORMLite needs a no-arg constructor
	}

	public Klient(String imie, String nazwisko, int pesel, Calendar dataUrodzenia) {
		super(imie, nazwisko, pesel, dataUrodzenia);
		wypozyczenia = new ArrayList<>();
		rezerwacje = new ArrayList<>();

	}

	public Klient(String imie, String nazwisko, int pesel, Calendar dataUrodzenia, Pracownik pracownik) {
		this(imie, nazwisko, pesel, dataUrodzenia);
		setIdPracownika(pracownik);
	}

	public int getIdPracownika() {
		return idPracownika;
	}

	public String getNumerKlienta() {
		return numerKlienta;
	}

	public ArrayList<Rezerwacja> getRezerwacje() {
		return rezerwacje;
	}

	public ArrayList<Wypozyczenie> getWypozyczenia() {
		return wypozyczenia;
	}

	public void setIdPracownika(Pracownik pracownik) {
		this.pracownik = pracownik;
		this.idPracownika = pracownik.getId();
	}

	public void setNumerKlienta(String numerKlienta) {
		this.numerKlienta = numerKlienta;
	}

	public void addWypozyczenie(Wypozyczenie wypozyczenie) {
		wypozyczenia.add(wypozyczenie);
	}

	public void addRezerwacja(Rezerwacja rezerwacja) {
		rezerwacje.add(rezerwacja);
	}

}
