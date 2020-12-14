package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Sprzet;
import java.awt.Color;
import javax.swing.JLabel;

public class Szczegoly extends JFrame {

	private JPanel contentPane;
	private Sprzet sprzet;

	public Szczegoly(Sprzet sprzet) {
		this.sprzet = sprzet;

		setBounds(100, 100, 450, 300);
		setTitle("Szczegoly");
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

		JLabel textNazwa = new JLabel(sprzet.getNazwa());
		textNazwa.setForeground(Color.WHITE);
		textNazwa.setBounds(250, 47, 190, 16);
		contentPane.add(textNazwa);

		JLabel textTyp = new JLabel(sprzet.getTyp().toString());
		textTyp.setForeground(Color.WHITE);
		textTyp.setBounds(250, 77, 190, 16);
		contentPane.add(textTyp);

		JLabel textCena = new JLabel(sprzet.getCena() + "");
		textCena.setForeground(Color.WHITE);
		textCena.setBounds(250, 107, 61, 16);
		contentPane.add(textCena);

		JLabel textCenaWypozyczenia = new JLabel(sprzet.getCenaZaDzienWypozyczenia() + "");
		textCenaWypozyczenia.setForeground(Color.WHITE);
		textCenaWypozyczenia.setBounds(250, 137, 190, 18);
		contentPane.add(textCenaWypozyczenia);

		JLabel textSprawny;
		if (sprzet.isSprawny()) {
			textSprawny = new JLabel("Tak");
		} else {
			textSprawny = new JLabel("Nie");
		}
		textSprawny.setForeground(Color.WHITE);
		textSprawny.setBounds(250, 167, 61, 16);
		contentPane.add(textSprawny);

		JLabel textDostepny;
		if (sprzet.isDostepny()) {
			textDostepny = new JLabel("Tak");
		} else {
			textDostepny = new JLabel("Nie");
		}
		textDostepny.setForeground(Color.WHITE);
		textDostepny.setBounds(250, 197, 61, 16);
		contentPane.add(textDostepny);

		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
