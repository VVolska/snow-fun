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

public class Sprzety extends JFrame {

    JTable table = new JTable();
    DefaultTableModel model = new DefaultTableModel();
    JScrollPane scroll;
    String headers[] = {"Nazwa", "Typ", "Cena"};
    ListSelectionModel rowSM = table.getSelectionModel();
    int selectedRow;
    ListSelectionModel lsm;
    ArrayList<Sklep> sklepy;
    ForeignCollection<Sprzet> sprzety;
    Sklep sklep;
    private JPanel panel;
    private JButton buttonWybierz;
    private JButton buttonDodaj;
    Sprzety widok;

    public Sprzety(Sklep sklep) {
        widok = this;
        this.sklep = sklep;
        initialize();
        listen();
        try {
            addListaSprzetow(sklep);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

        setTitle("Asortyment sklepu " + sklep.getNazwa());

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
        setVisible(true);

    }

    public void listen() {
        ActionListener showDetails = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                String command = actionEvent.getActionCommand();
                Object[] arr = sklep.getSprzety().toArray();
                Sprzet sprzet = (Sprzet) arr[selectedRow];
                new Szczegoly(sprzet);
                refresh();

            }
        };

        buttonWybierz.addActionListener(showDetails);

        ActionListener showWarehouse = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String command = actionEvent.getActionCommand();
                new Magazyn(sklep);
            }
        };

        buttonDodaj.addActionListener(showWarehouse);

        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                // Ignore extra messages.
                if (e.getValueIsAdjusting())
                    return;

                lsm = (ListSelectionModel) e.getSource();
                if (!lsm.isSelectionEmpty()) {
                    selectedRow = lsm.getMinSelectionIndex();
                }
            }
        });

    }

    void refresh() {
        SwingUtilities.updateComponentTreeUI(this);
        invalidate();
        validate();
        repaint();
    }

    void addListaSprzetow(Sklep sklep) throws SQLException {

        sprzety = sklep.getSprzety();
        for (Sprzet s : sprzety) {
            model.addRow(new Object[]{s.getNazwa(), s.getTyp(), s.getCena()});
        }
    }
}