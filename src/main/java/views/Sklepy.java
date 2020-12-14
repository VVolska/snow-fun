package views;

import java.awt.BorderLayout;
import java.awt.Button;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import models.Database;
import models.Klient;
import models.Sklep;
import models.Sprzet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.FlowLayout;

public class Sklepy extends JFrame {

	JTable table = new JTable();
	DefaultTableModel model = new DefaultTableModel();
	JScrollPane scroll;
	String headers[] = { "Nazwa", "Adres" };
	ListSelectionModel rowSM = table.getSelectionModel();
	int selectedRow;
	ListSelectionModel lsm;
	ArrayList<Sklep> sklepy;
	private JPanel panel;
	private JButton buttonOk;

	public Sklepy() {
		initialize();
		listen();
	}

	public void initialize() {
		setTitle("Lista sklepow");
		setBounds(100, 100, 450, 300);


		setLocationRelativeTo(null);
		setBackground(Color.DARK_GRAY);

		model.setColumnIdentifiers(headers);
		table.setModel(model);
		scroll = new JScrollPane(table);
		table.setBackground(Color.LIGHT_GRAY);
		scroll.setBackground(Color.DARK_GRAY);
		scroll.setForeground(Color.DARK_GRAY);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel contentPaneSklepy = new JPanel();
		contentPaneSklepy.setBackground(Color.DARK_GRAY);
		contentPaneSklepy.setForeground(Color.cyan);
		contentPaneSklepy.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneSklepy.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPaneSklepy);

		getContentPane().add(scroll, BorderLayout.CENTER);

		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPaneSklepy.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		buttonOk = new JButton("OK");
		buttonOk.setActionCommand("");
		panel.add(buttonOk);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void listen() {
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				String command = actionEvent.getActionCommand();

				System.out.println("Selected: " + command + selectedRow);
				Sklep sklep = sklepy.get(selectedRow);
				System.out.println(sklepy.get(selectedRow).getNazwa());
				for (Sprzet s : sklep.getSprzety()) {
					System.out.println(s.getNazwa());
				}
				new Sprzety(sklep);

			}
		};
		buttonOk.addActionListener(actionListener);

		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
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

	public void addListaSklepow(String fileName) throws SQLException {
		Database bazka = new Database(fileName);

		sklepy = bazka.getData();
		for (Sklep s : sklepy) {
			model.addRow(new Object[] { s.getNazwa(), s.getAdres() });
		}
	}
}
