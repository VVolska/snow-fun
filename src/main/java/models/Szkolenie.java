package models;

import java.util.ArrayList;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "szkolenia")
public class Szkolenie {

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField(dataType = DataType.ENUM_STRING, canBeNull = false)
	private PoziomTrudnosci poziomTrudnosci;
	@DatabaseField
	private static int minimalnaIloscUczestnikow = 3;
	@DatabaseField(canBeNull = false, foreign = true)
	private Wyjazd wyjazd;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private ArrayList<Rezerwacja> rezerwacje;

	enum PoziomTrudnosci {
		latwy, sredni, zaawansowany, freestyle
	};

	public Szkolenie() {

	}

	public Szkolenie(Wyjazd wyjazd, PoziomTrudnosci poziomTrudnosci) {
		this.wyjazd = wyjazd;
		this.poziomTrudnosci = poziomTrudnosci;
		this.rezerwacje = new ArrayList<>();

	}

	public PoziomTrudnosci getPoziomTrudnosci() {
		return poziomTrudnosci;
	}

	public static int getMinimalnaIloscUczestnikow() {
		return minimalnaIloscUczestnikow;
	}

	public Wyjazd getWyjazd() {
		return wyjazd;
	}

	public ArrayList<Rezerwacja> getRezerwacje() {
		return rezerwacje;
	}

	public void setPoziomTrudnosci(PoziomTrudnosci poziomTrudnosci) {
		this.poziomTrudnosci = poziomTrudnosci;
	}

	public static void setMinimalnaIloscUczestnikow(int minimalnaIloscUczestnikow) {
		Szkolenie.minimalnaIloscUczestnikow = minimalnaIloscUczestnikow;
	}

	public void addRezerwacja(Rezerwacja rezerwacja) {
		if (!rezerwacje.contains(rezerwacja)) {
			this.rezerwacje.add(rezerwacja);
		}
	}

}
