package views;

import java.awt.BorderLayout;
import java.awt.Button;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import models.Database;
import models.Klient;
import models.Sklep;
import models.Sprzet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.FlowLayout;

public class Magazyn extends JFrame {

	JTable table = new JTable();
	DefaultTableModel model = new DefaultTableModel();
	JScrollPane scroll;
	String headers[] = { "Nazwa", "Typ", "Cena" };
	ListSelectionModel rowSM = table.getSelectionModel();
	int selectedRow;
	ListSelectionModel lsm;
	ArrayList<Sklep> sklepy;
	ForeignCollection<Sprzet> sprzety;
	Sklep sklep;
	private JPanel panel;
	private JButton buttonWybierz;
	private JButton buttonDodaj;
	String url = Database.url;
	ConnectionSource connectionSource;
	Dao<Sprzet, Integer> sprzetDao;
	Dao<Sklep, Integer> sklepDao;
	List<Sprzet> sprzetyNiczyje;

	public Magazyn(Sklep sklep) {
		this.sklep = sklep;
		initialize();
		try {
			addListaSprzetow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listen();
	}

	public void initialize() {

		setBackground(Color.DARK_GRAY);

		model.setColumnIdentifiers(headers);
		table.setModel(model);
		scroll = new JScrollPane(table);
		table.setBackground(Color.LIGHT_GRAY);
		scroll.setBackground(Color.DARK_GRAY);
		scroll.setForeground(Color.GREEN);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel contentPaneSklepy = new JPanel();
		contentPaneSklepy.setBackground(Color.DARK_GRAY);
		contentPaneSklepy.setForeground(Color.cyan);
		contentPaneSklepy.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneSklepy.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPaneSklepy);

		setTitle("Magazyn");

		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPaneSklepy.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		buttonWybierz = new JButton("Wybierz");
		buttonWybierz.setActionCommand("OK");
		panel.add(buttonWybierz);

		buttonDodaj = new JButton("Dodaj Nowy");
		panel.add(buttonDodaj);
		getContentPane().add(scroll, BorderLayout.CENTER);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		// setSize(400, 300);
		setVisible(true);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void listen() {
		ActionListener showAdded = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				String command = actionEvent.getActionCommand();

				System.out.println("Selected: " + command + selectedRow);

				// sprzety = sprzetDao.queryForAll();
				Sprzet sprzetWybrany = sprzetyNiczyje.get(selectedRow);
				new DodanoSprzetDoSklepu(sprzetWybrany, sklep);
				addSprzetToDatabase(sprzetWybrany);
				model.removeRow(selectedRow);
				
			}

		};

		buttonWybierz.addActionListener(showAdded);

		ActionListener addEquipment = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				new DodajSprzet();
				setVisible(false); //you can't see me!
				dispose(); //Destroy the JFrame object

			}
		};

		buttonDodaj.addActionListener(addEquipment);

		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// Ignore extra messages.
				if (e.getValueIsAdjusting())
					return;

				lsm = (ListSelectionModel) e.getSource();
				if (lsm.isSelectionEmpty()) {
					System.out.println("No rows are selected.");
				} else {
					selectedRow = lsm.getMinSelectionIndex();
					System.out.println("Row " + selectedRow + " is now selected.");
				}
			}
		});

	}

	void addSprzetToDatabase(Sprzet sprzet) {
		sprzet.setSklep(this.sklep);
		try {
			
			sklepDao.createOrUpdate(this.sklep);
			sprzetDao.createOrUpdate(sprzet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void refresh() {
		SwingUtilities.updateComponentTreeUI(this);
		invalidate();
		validate();
		repaint();
	}

	public void addListaSprzetow() throws SQLException {

		connectionSource = new JdbcConnectionSource(url);
		sprzetDao = DaoManager.createDao(connectionSource, Sprzet.class);
		sklepDao = DaoManager.createDao(connectionSource, Sklep.class);

		List<Sprzet> sprzety = sprzetDao.queryForAll();
		// sklepy.add(new Sklep("Adrenalina", "Kamieniecka"));
		// sklepy.add(new Sklep("Majesty", "Kamieniecka"));
		// sklepy.add(new Sklep("Burtion", "Kamieniecka"));
		this.sprzetyNiczyje = new ArrayList();
		int i = 0;
		for (Sprzet s : sprzety) {
			if (s.getSklep() == null) {
				model.addRow(new Object[] { s.getNazwa(), s.getTyp(), s.getCena() });
				sprzetyNiczyje.add(s);
			} else {
				System.out.println("To jest null" + i);
				i++;
			}
		}

	}
}