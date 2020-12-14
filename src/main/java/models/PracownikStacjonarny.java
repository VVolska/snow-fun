package models;

import java.util.Calendar;
import java.util.Date;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "pracownicyStacjonarni")
public class PracownikStacjonarny extends Pracownik {

	@DatabaseField
	private Date dataZatrudnienia;
	@DatabaseField(foreign = true)
	private Sklep sklep;

	public PracownikStacjonarny() {

	}

	public PracownikStacjonarny(String imie, String nazwisko, int pesel, Calendar dataUrodzenia,
			Calendar dataZatrudnienia) {
		super(imie, nazwisko, pesel, dataUrodzenia);
		this.dataZatrudnienia = dataZatrudnienia.getTime();
		super.setPracownikStacjonarny(this);

	}

	public Date getDataZatrudnienia() {
		return dataZatrudnienia;
	}

	public Sklep getSklep() {
		return sklep;
	}

	public void setSklep(Sklep sklep) {
		this.sklep = sklep;
	}

	public void setDataZatrudnienia(Date dataZatrudnienia) {
		this.dataZatrudnienia = dataZatrudnienia;
	}

}
