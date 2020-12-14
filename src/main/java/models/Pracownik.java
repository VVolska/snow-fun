package models;

import java.util.Calendar;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "pracownicy")
public abstract class Pracownik extends Osoba {

	@DatabaseField
	String numerPracownika;
	@DatabaseField(foreign = true)
	Instruktor instruktor;
	@DatabaseField(foreign = true)
	PracownikStacjonarny pracownikStacjonarny;

	public Pracownik() {
		// ORMLite needs a no-arg constructor
	}

	public Pracownik(String imie, String nazwisko, int pesel, Calendar dataUrodzenia) {
		super(imie, nazwisko, pesel, dataUrodzenia);

	}

	public void zmienKlase(Instruktor instruktor) {
		setInstruktor(instruktor);
	}
	
	public void zmienKlase(PracownikStacjonarny pracownik) {
		setPracownikStacjonarny(pracownik);
	}

	public void setNumerPracownika(String numerPracownika) {
		this.numerPracownika = numerPracownika;
	}

	public String getNumerPracownika() {
		return numerPracownika;
	}

	public Instruktor getInstruktor() {
		return instruktor;
	}

	public PracownikStacjonarny getPracownikStacjonarny() {
		return pracownikStacjonarny;
	}

	public void setInstruktor(Instruktor instruktor) {
		if (this.pracownikStacjonarny 	!= null) {
			this.pracownikStacjonarny = null;
		}
		this.instruktor = instruktor;
	}

	public void setPracownikStacjonarny(PracownikStacjonarny pracownikStacjonarny) {
		if(this.instruktor != null) {
			this.instruktor = null;
		}
		this.pracownikStacjonarny = pracownikStacjonarny;
	}

}
