package views;

import java.sql.SQLException;

public class Gui {
	
static String fileName;
	public Gui(String fileName){
		this.fileName = fileName;
		Sklepy sklepy = new Sklepy();
		try {
			sklepy.addListaSklepow(fileName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
