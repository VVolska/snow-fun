package models;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import models.Sprzet.Typ;

public class Database {
    Dao<InstruktorNaWyjezdzie, Integer> instruktorNaWyjezdzieDao;
    Dao<Instruktor, Integer> instruktorDao;
    Dao<Klient, Integer> klientDao;
    Dao<PracownikStacjonarny, Integer> pracownikStacjonarnyDao;
    Dao<Rezerwacja, Integer> rezerwacjaDao;
    Dao<Serwis, Integer> serwisDao;
    Dao<Sklep, Integer> sklepDao;
    Dao<Sprzet, Integer> sprzetDao;
    Dao<Szkolenie, Integer> szkolenieDao;
    Dao<Wypozyczenie, Integer> wypozyczenieDao;
    Dao<Wyjazd, Integer> wyjazdDao;
    String fileName;
    public static String url;
    ConnectionSource connectionSource;

    public Database() {
        // ORMLite needs a no-arg constructor
    }

    public Database(String fileName) {
        this.fileName = fileName;
        this.url = "jdbc:sqlite:" + fileName;
        try {
            this.connectionSource = new JdbcConnectionSource(url);
            this.instruktorDao = DaoManager.createDao(connectionSource, Instruktor.class);
            this.instruktorNaWyjezdzieDao = DaoManager.createDao(connectionSource, InstruktorNaWyjezdzie.class);
            this.klientDao = DaoManager.createDao(connectionSource, Klient.class);
            this.pracownikStacjonarnyDao = DaoManager.createDao(connectionSource, PracownikStacjonarny.class);
            this.rezerwacjaDao = DaoManager.createDao(connectionSource, Rezerwacja.class);
            this.serwisDao = DaoManager.createDao(connectionSource, Serwis.class);
            this.sklepDao = DaoManager.createDao(connectionSource, Sklep.class);
            this.sprzetDao = DaoManager.createDao(connectionSource, Sprzet.class);
            this.szkolenieDao = DaoManager.createDao(connectionSource, Szkolenie.class);
            this.wyjazdDao = DaoManager.createDao(connectionSource, Wyjazd.class);
            this.wypozyczenieDao = DaoManager.createDao(connectionSource, Wypozyczenie.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createNewDatabase() {

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTables() throws SQLException {
        TableUtils.createTable(connectionSource, Instruktor.class);
        TableUtils.createTable(connectionSource, InstruktorNaWyjezdzie.class);
        TableUtils.createTable(connectionSource, Klient.class);
        TableUtils.createTable(connectionSource, Osoba.class);
        TableUtils.createTable(connectionSource, PracownikStacjonarny.class);
        TableUtils.createTable(connectionSource, Pracownik.class);
        TableUtils.createTable(connectionSource, Rezerwacja.class);
        TableUtils.createTable(connectionSource, Serwis.class);
        TableUtils.createTable(connectionSource, Sklep.class);
        TableUtils.createTable(connectionSource, Sprzet.class);
        TableUtils.createTable(connectionSource, Szkolenie.class);
        TableUtils.createTable(connectionSource, Wyjazd.class);
        TableUtils.createTable(connectionSource, Wypozyczenie.class);
    }

    public void fetchData() throws SQLException {
        Sprzet sprzet = sprzetDao.queryForId(5);
        List<Sprzet> sprzety = sprzetDao.queryForAll();
        Sklep sklep = sklepDao.queryForId(1);
        ForeignCollection<Sprzet> sprzetyWSklepie = sklep.getSprzety();
    }

    public void createData() throws SQLException {
        ArrayList<Sprzet> sprzety = new ArrayList<>();
        ArrayList<Sklep> sklepy = new ArrayList<>();
        Sklep adrenalinaSklep = new Sklep("Adrenalina", "Kamieniecka 3/5");
        Sklep majesty = new Sklep("Majesty", "Modliborska 18");
        Sklep burton = new Sklep("Burton", "Kuropatwy 22");
        Sklep proShop = new Sklep("PRO SHOP", "Wilanowska 25B");

        Sprzet s1 = new Sprzet("Talent Scout", Typ.Snowboard, 1600, 80, true, true);
        Sprzet s2 = new Sprzet("Social", Typ.Snowboard, 1600, 80, true, true);
        Sprzet s3 = new Sprzet("Socialite", Typ.Snowboard, 1600, 80, true, true);
        Sprzet s4 = new Sprzet("LE-KI", Typ.Kijki, 160, 10, true, true);
        Sprzet s5 = new Sprzet("Frost Banana", Typ.Snowboard, 1600, 80, true, true);
        Sprzet s6 = new Sprzet("Daenon", Typ.Snowboard, 1600, 80, true, true);
        Sprzet s7 = new Sprzet("Free Spirit", Typ.Narty, 1600, 80, true, true);
        Sprzet s8 = new Sprzet("Mona", Typ.Kijki, 160, 10, true, true);
        Sprzet s9 = new Sprzet("Stria", Typ.Snowboard, 1600, 80, true, true);
        Sprzet s10 = new Sprzet("De-ski", Typ.Snowboard, 1600, 80, true, true);
        Sprzet s11 = new Sprzet("Lobster", Typ.Snowboard, 1600, 80, true, true);
        Sprzet s12 = new Sprzet("LE-KI", Typ.Kijki, 160, 10, true, true);
        Sprzet s13 = new Sprzet("Banana mama", Typ.Snowboard, 1600, 80, true, true);
        Sprzet s14 = new Sprzet("Dope", Typ.Snowboard, 1600, 80, true, true);
        Sprzet s15 = new Sprzet("Legend", Typ.Narty, 1600, 80, true, true);
        Sprzet s16 = new Sprzet("Monastery", Typ.Kijki, 160, 10, true, true);
        Sprzet s17 = new Sprzet("LE-KI", Typ.Kijki, 160, 10, true, true);
        Sprzet s18 = new Sprzet("Rossignol", Typ.Kijki, 160, 10, true, true);


        Sprzet s19 = new Sprzet("Rossignol", Typ.Kijki, 160, 10, true, true);
        Sprzet s20 = new Sprzet("Rossignol", Typ.Kijki, 160, 10, true, true);
        Sprzet s21 = new Sprzet("Rossignol", Typ.Kijki, 160, 10, true, true);
        Sprzet s22 = new Sprzet("Rossignol", Typ.Kijki, 160, 10, true, true);


        sklepy.add(adrenalinaSklep);
        sklepy.add(majesty);
        sklepy.add(burton);
        sklepy.add(proShop);

        for (Sklep s : sklepy) {
            this.sklepDao.create(s);
        }

        s1.setSklep(adrenalinaSklep);
        s2.setSklep(adrenalinaSklep);
        s3.setSklep(adrenalinaSklep);
        s4.setSklep(adrenalinaSklep);
        s5.setSklep(adrenalinaSklep);
        s6.setSklep(adrenalinaSklep);
        s7.setSklep(adrenalinaSklep);
        s8.setSklep(adrenalinaSklep);

        s9.setSklep(majesty);
        s10.setSklep(majesty);
        s11.setSklep(majesty);
        s12.setSklep(majesty);
        s13.setSklep(majesty);

        s14.setSklep(burton);
        s15.setSklep(burton);
        s16.setSklep(burton);
        s17.setSklep(burton);
        s18.setSklep(burton);
        s18.setSklep(burton);


        sprzety.add(s1);
        sprzety.add(s2);
        sprzety.add(s3);
        sprzety.add(s4);
        sprzety.add(s5);
        sprzety.add(s6);
        sprzety.add(s7);
        sprzety.add(s8);
        sprzety.add(s9);
        sprzety.add(s10);
        sprzety.add(s11);
        sprzety.add(s12);
        sprzety.add(s13);
        sprzety.add(s14);
        sprzety.add(s15);
        sprzety.add(s16);
        sprzety.add(s17);
        sprzety.add(s18);
        sprzety.add(s19);
        sprzety.add(s20);
        sprzety.add(s21);
        sprzety.add(s22);

        for (Sprzet s : sprzety) {
            this.sprzetDao.create(s);
        }
    }

    public ArrayList<Sklep> getData() throws SQLException {
        QueryBuilder<Sklep, Integer> queryBuilder = sklepDao.queryBuilder();
        System.out.println(queryBuilder.selectColumns());
        return (ArrayList<Sklep>) queryBuilder.query();
    }

}
