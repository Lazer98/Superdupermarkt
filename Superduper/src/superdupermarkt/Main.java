package superdupermarkt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



public class Main {

	private static void tabelleErstellen(Connection conn) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS products (" +
                "id SERIAL PRIMARY KEY," +
                "bezeichnung VARCHAR(255)," +
                "qualität INTEGER," +
                "verfallsdatum DATE," +
                "preis NUMERIC," +
                "stichtag DATE" +
                ")";

        try (PreparedStatement statement = conn.prepareStatement(createTableSQL)) {
            statement.execute();
        }
    }

    //allgemeines Produkt wird in Postgresql Tabelle eingefügt
    private static void produktEinfügen(Connection conn, String bezeichnung, int qualität, String verfallsdatum, double preis)
            throws SQLException {
        String insertSQL = "INSERT INTO products (bezeichnung, qualität, verfallsdatum, preis) VALUES (?, ?, ?, ? )";

        try (PreparedStatement statement = conn.prepareStatement(insertSQL)) {
            statement.setString(1, bezeichnung);
            statement.setInt(2, qualität);
            statement.setDate(3, java.sql.Date.valueOf(verfallsdatum));
            statement.setDouble(4, preis);
            statement.executeUpdate();
        }
    }

    // Weinprodukt wird in Postgresql Tabelle eingefügt
    private static void weinproduktEinfügen(Connection conn, String bezeichnung, int qualität, String verfallsdatum, double preis,String stichtag)
            throws SQLException {
        String insertSQL = "INSERT INTO products (bezeichnung, qualität, verfallsdatum, preis ,stichtag) VALUES (?, ?, ?, ? ,?)";

        try (PreparedStatement statement = conn.prepareStatement(insertSQL)) {
            statement.setString(1, bezeichnung);
            statement.setInt(2, qualität);
            statement.setDate(3, java.sql.Date.valueOf(verfallsdatum));
            statement.setDouble(4, preis);
            statement.setDate(5, java.sql.Date.valueOf(stichtag));
            statement.executeUpdate();
        }
    }

    //Daten aus der Postgresql Tabelle ausdrucken
    private static void datenSQLAusdrucken(Connection conn) throws SQLException {
        String selectSQL = "SELECT * FROM products";

        try (PreparedStatement statement = conn.prepareStatement(selectSQL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String bezeichnung = resultSet.getString("bezeichnung");
                int qualität = resultSet.getInt("qualität");
                String verfallsdatum = resultSet.getDate("verfallsdatum").toString();
                double preis = resultSet.getDouble("preis");
                if(resultSet.getDate("stichtag") !=null){
                    String stichtag = resultSet.getDate("stichtag").toString();
                    System.out.println("ID: " + id + ", Bezeichnung: " + bezeichnung + ", Qualität: " + qualität +
                            ", Verfallsdatum: " + verfallsdatum + ", Preis: " + preis+ ", Stichtag: " + stichtag);
                }
                else{
                    System.out.println("ID: " + id + ", Bezeichnung: " + bezeichnung + ", Qualität: " + qualität +
                            ", Verfallsdatum: " + verfallsdatum + ", Preis: " + preis);
                }

            }
        }
    }

    //Die Verbindung mit der Postgresql Tabelle wird hergestellt
    public static void mitDatabaseVerbinden() {
        String url = "jdbc:postgresql://localhost:5432/superduper";
        String user = "lazer";
        String password = "Tillmann98";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL database!");

            // Erstellen Sie eine Tabelle, wenn sie nicht vorhanden ist
            tabelleErstellen(conn);

            // Fügen Sie Daten in die Tabelle ein
            produktEinfügen(conn, "Ananas", 7, "2023-07-28", 5.99);
            produktEinfügen(conn, "Banane", 3, "2023-07-29", 1.99);
            produktEinfügen(conn, "Wasser", 10, "2024-08-08", 0.99);
            produktEinfügen(conn, "Pfirsich", 7, "2023-08-01", 2.99);
            produktEinfügen(conn, "Gurke", 6, "2023-08-03", 0.59);
            produktEinfügen(conn, "Tomate", 5, "2023-08-04", 0.89);
            produktEinfügen(conn, "Brot", 6, "2023-08-03", 1.29);
            produktEinfügen(conn, "Nektarine", 8, "2023-08-01", 1.39);
            produktEinfügen(conn, "Kaese", 50, "2023-09-28", 2.49);
            produktEinfügen(conn, "Kavier", 90, "2023-08-23", 90.0);
            weinproduktEinfügen(conn, "Wein", 20, "2023-07-28", 0.99, "2023-07-29");

            // Holen Sie sich die Daten aus der Tabelle und drucken Sie sie aus
            datenSQLAusdrucken(conn);

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

        //Alle Daten im Regal werden ausgedruckt
        public static void sortimentAusdrucken(ArrayList<GenericProdukt> sortiment, LocalDate datum) {
        for (int i = sortiment.size() - 1; i >= 0; i--) {
            sortiment.get(i).täglichePreisänderung();
            sortiment.get(i).printProduct(datum);
            sortiment.get(i).täglicheQualitätsänderung(datum);
            if (sortiment.get(i).ausRegalAusräumen(datum)) {
                sortiment.remove(i);
            }
        }

    }

    //Produkte werden in der .csv Datei gespeichert
    public static void einschreibenInDatei(String csvFile) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<GenericProdukt> products = new ArrayList<>();
        // deme inhalt
        products.add(new GenericProdukt("ProductNotSupposedToPass", 7, LocalDate.parse("2023-07-20"), 5.99));
        // sollte nicht ins Regal kommen da haltbarkeitsdatum schon vergangen ist
        products.add(new GenericProdukt("Ananas", 7, LocalDate.parse("2023-07-28"), 5.99));
        products.add(new GenericProdukt("Banane", 3, LocalDate.parse("2023-07-29"), 1.99));
        products.add(new GenericProdukt("Wasser", 10, LocalDate.parse("2024-08-08"), 0.99));
        products.add(new GenericProdukt("Pfirsich", 7, LocalDate.parse("2023-08-01"), 2.99));
        products.add(new GenericProdukt("Gurke", 6, LocalDate.parse("2023-08-03"), 0.59));
        products.add(new GenericProdukt("Tomate", 5, LocalDate.parse("2023-08-04"), 0.89));
        products.add(new GenericProdukt("Brot", 6, LocalDate.parse("2023-08-03"), 1.29));
        products.add(new GenericProdukt("Nektarine", 8, LocalDate.parse("2023-08-01"), 1.39));
        products.add(new Wein("Wein", 20, LocalDate.parse("2023-07-28"), 5.99, LocalDate.parse("2023-07-25")));
        products.add(new Wein("Wein", 50, LocalDate.parse("2023-07-28"), 5.99, LocalDate.parse("2023-07-25")));
        // Qualität sollte sich nicht ändern
        products.add(new Käse("Kaese", 50, LocalDate.parse("2023-09-28"), 2.49));
        products.add(new Käse("Kaese", 50, LocalDate.parse("2023-08-10"), 2.49)); // sollte nicht ins Regal kommen
        products.add(new Kavier("Kavier", 90, LocalDate.parse("2023-08-23"), 90.0));

        try (FileWriter writer = new FileWriter(csvFile)) {
            // Spaltenüberschriften in die CSV-Datei
            writer.write("Bezeichnung,Qualität,Verfallsdatum,Preis,Stichtag(Wein)\n");

            for (GenericProdukt produkt : products) {
                String bezeichnung = produkt.getBezeichnung();
                int qualität = produkt.getQualität();
                String verfallsdatum = produkt.getVerfallsdatum().format(formatter);
                double preis = produkt.getPreis();
                if (bezeichnung.toLowerCase().equals("wein")) {
                    Wein w = (Wein) produkt;
                    String stichtag = w.getStichtag().format(formatter);
                    writer.write(
                            bezeichnung + "," + qualität + "," + verfallsdatum + "," + preis + "," + stichtag + "\n");
                } else {
                    // Einschreiben der Daten in die CSV-Datei
                    writer.write(bezeichnung + "," + qualität + "," + verfallsdatum + "," + preis + "\n");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Die Produkte aus der .csv Datei werden gelesen
    public static void dateiLesen(String csvFile, List<GenericProdukt> products) {
        GenericProdukt produkt;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] data = line.split(",");

                String bezeichnung = data[0];
                int qualität = Integer.parseInt(data[1]);
                LocalDate verfallsdatum = LocalDate.parse(data[2]);
                double preis = Double.parseDouble(data[3]);
                if (bezeichnung.toLowerCase().equals("wein")) {
                    LocalDate stichtag = LocalDate.parse(data[4]);
                    produkt = new Wein(bezeichnung, qualität, verfallsdatum, preis, stichtag);
                } else if (bezeichnung.toLowerCase().equals("kaese")) {
                    produkt = new Käse(bezeichnung, qualität, verfallsdatum, preis);
                } else if (bezeichnung.toLowerCase().equals("kavier")) {
                    produkt = new Kavier(bezeichnung, qualität, verfallsdatum, preis);
                } else {
                    produkt = new GenericProdukt(bezeichnung, qualität, verfallsdatum, preis);
                }

                // überprüfen ob das Produkt die Anforderungen erfüllt um in das Regal zu kommen
                if (produkt.okayFürRegal(LocalDate.now())) {
                    products.add(produkt);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String csvFile = "products.csv";
        einschreibenInDatei(csvFile);
        ArrayList<GenericProdukt> sortiment = new ArrayList<>();
        dateiLesen(csvFile, sortiment);
        Regal regal = new Regal();
        regal.setRegal(sortiment);

        System.out.println("Willkommen im Superdupermarkt!");
        System.out
                .println("Im Folgenden wird das Sortiment und dessen Veränderungen in den nächsten elf Tagen gezeigt:");
        LocalDate today = LocalDate.now(); // Heutiges Datum
        LocalDate[] nextElevenDays = new LocalDate[11]; // array fuer die Daten der naechsten 11 Tage

        for (int i = 0; i < 11; i++) {
            nextElevenDays[i] = today.plusDays(i);
            System.out.println("Datum: " + nextElevenDays[i]);
            sortimentAusdrucken(sortiment, nextElevenDays[i]);
            System.out.println();
        }
        System.out.println("Produkte aus Postgresql:");
        mitDatabaseVerbinden();
    }

}
