import java.sql.SQLException;
import models.Database;
import views.Gui;

public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		/// Sprzet s1 = new Sprzet("Talent Scout", Typ.Snowboard, 1600, 80,
		/// true, true);
		// Sprzet.addSprzet(s1);

		String fileName = "testbaza5";
//		Database bazka = new Database(fileName);
//
//		bazka.createNewDatabase();
//		try {
//			bazka.createTables();
//			bazka.createData();
//			//bazka.fetchData();
//			// bazka.getData();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		 new Gui(fileName);
		// new Sklepy();
		// Klient k = new Klient("Jan", "Kowalski", 920217892, new
		// GregorianCalendar(2010,0,31));

	}

}