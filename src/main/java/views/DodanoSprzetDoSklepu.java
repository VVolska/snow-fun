package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Database;
import models.Sklep;
import models.Sprzet;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class DodanoSprzetDoSklepu extends JDialog {
	private JTextArea textArea;
	Sprzet sprzet;
	Sklep sklep;

	public DodanoSprzetDoSklepu(Sprzet sprzet, Sklep sklep) {
		getContentPane().setBackground(Color.DARK_GRAY);
		this.sklep = sklep;
		this.sprzet = sprzet;
		initialize();
	}

	private void initialize() {
		setBounds(50, 50, 225, 150);
		getContentPane().setLayout(null);
		{
			if (this.sklep != null) {
				textArea = new JTextArea("Dodano sprzet " + sprzet.getNazwa() + "\ndo sklepu " + sklep.getNazwa());
			}else{
				textArea = new JTextArea("Dodano sprzet " + sprzet.getNazwa() + "\ndo magazynu ");
			}
			textArea.setWrapStyleWord(true);
			textArea.setBounds(6, 21, 211, 66);
			getContentPane().add(textArea);
			
			JButton btnNewButton = new JButton("OK");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Gui(Gui.fileName);
				}
			});
			btnNewButton.setBounds(102, 93, 117, 29);
			getContentPane().add(btnNewButton);
			textArea.setForeground(Color.WHITE);
			textArea.setBackground(Color.DARK_GRAY);

		}
		setVisible(true);
		setLocationRelativeTo(null);

	}
}
