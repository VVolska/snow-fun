package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import models.Database;
import models.Sprzet;
import models.Sprzet.Typ;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JList;

public class DodajSprzet extends JFrame {

	private JPanel contentPane;
	private JTextField textNazwa;
	private JTextField textTyp;
	private JTextField textCena;
	private JTextField textCenaWypozyczenia;

	JButton buttonDodaj;
	JRadioButton radioButtonSprawny;
	JRadioButton radioButtonDostepny;

	String url = Database.url;
	ConnectionSource connectionSource;
	Dao<Sprzet, Integer> sprzetDao;

	public DodajSprzet() {
		initialize();
		listen();

	}

	private void initialize() {

		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Dodaj nowy sprzet");
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelNazwa = new JLabel("Nazwa");
		labelNazwa.setForeground(Color.WHITE);
		labelNazwa.setBounds(21, 47, 61, 16);
		contentPane.add(labelNazwa);

		JLabel labelTyp = new JLabel("Typ");
		labelTyp.setForeground(Color.WHITE);
		labelTyp.setBounds(21, 77, 61, 16);
		contentPane.add(labelTyp);

		JLabel labelCena = new JLabel("Cena");
		labelCena.setForeground(Color.WHITE);
		labelCena.setBounds(21, 107, 61, 16);
		contentPane.add(labelCena);

		JLabel labelCenaWypozyczenia = new JLabel("Cena za dzien wypozyczenia");
		labelCenaWypozyczenia.setForeground(Color.WHITE);
		labelCenaWypozyczenia.setBounds(21, 137, 190, 18);
		contentPane.add(labelCenaWypozyczenia);

		JLabel labelSprawny = new JLabel("Sprawny");
		labelSprawny.setForeground(Color.WHITE);
		labelSprawny.setBounds(21, 167, 61, 16);
		contentPane.add(labelSprawny);

		JLabel labelDostepny = new JLabel("Dostepny");
		labelDostepny.setForeground(Color.WHITE);
		labelDostepny.setBounds(21, 197, 61, 16);
		contentPane.add(labelDostepny);

		textNazwa = new JTextField();
		textNazwa.setBounds(226, 42, 130, 26);
		contentPane.add(textNazwa);
		textNazwa.setColumns(10);

		textTyp = new JTextField();
		textTyp.setBounds(226, 72, 130, 26);
		contentPane.add(textTyp);
		textTyp.setColumns(10);

		textCena = new JTextField();
		textCena.setBounds(226, 102, 130, 26);
		contentPane.add(textCena);
		textCena.setColumns(10);
		textCena.setText("0");

		textCenaWypozyczenia = new JTextField();
		textCenaWypozyczenia.setBounds(226, 133, 130, 26);
		contentPane.add(textCenaWypozyczenia);
		textCenaWypozyczenia.setColumns(10);
		textCenaWypozyczenia.setText("0");


		radioButtonSprawny = new JRadioButton("");
		radioButtonSprawny.setBounds(226, 167, 141, 23);
		contentPane.add(radioButtonSprawny);

		radioButtonDostepny = new JRadioButton("");
		radioButtonDostepny.setBounds(226, 197, 141, 23);
		contentPane.add(radioButtonDostepny);

		buttonDodaj = new JButton("Dodaj");
		buttonDodaj.setBounds(308, 232, 117, 29);
		contentPane.add(buttonDodaj);
	}

	private void listen() {

		ActionListener addEquipment = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				addSprzet();
			}
		};

		buttonDodaj.addActionListener(addEquipment);

	}

	void addSprzet() {
		System.out.println("Klikniety");
		Typ typEnum = null;
		String typ = textTyp.getText().toLowerCase();
		if (typ.equals("narty")) {
			System.out.println("NARTY!!!!");
			typEnum = Typ.Narty;
		} else if (typ.equals("kijki")) {
			typEnum = Typ.Kijki;
		} else if (typ.equals("snowboard")) {
			typEnum = Typ.Snowboard;
		}

		boolean sprawny = false;
		boolean dostepny = false;
		if (radioButtonSprawny.isSelected()) {
			System.out.println("JEST SPRAWNY");
			sprawny = true;
		}
		if (radioButtonDostepny.isSelected()) {
			System.out.println("JEST DOSTEPNY");
			dostepny = true;
		}

		try {
			connectionSource = new JdbcConnectionSource(url);
			sprzetDao = DaoManager.createDao(connectionSource, Sprzet.class);

			Sprzet sprzet = new Sprzet(textNazwa.getText(), typEnum, Double.parseDouble(textCena.getText()),
					Integer.parseInt(textCenaWypozyczenia.getText()), sprawny, dostepny);
			
			sprzetDao.create(sprzet);
			setVisible(false);
			dispose();

			new DodanoSprzetDoMagazynu(sprzet, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
