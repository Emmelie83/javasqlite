import java.sql.*;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:\\Users\\melie\\SQL\\biluthyrning.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private static void printActions() {
        System.out.println("\nVälj:\n");
        System.out.println("0  - Stäng av\n" +
                "1  - Visa alla kunder\n" +
                "2  - Gör en bokning för en viss kund\n" +
                "3  - Uppdatera en kund\n" +
                "4  - Visa alla bokningar\n" +
                "5  - Visa en lista över alla val.");
    }

    private static void insertBokning() {
        System.out.println("Ange kundnummer");
        int kundNr = Integer.parseInt(scanner.nextLine());
        System.out.println("Ange bokningsdatum:");
        String bokningsDatum = scanner.nextLine();
        System.out.println("Ange upphämtningsdatum:");
        String upphamtning = scanner.nextLine();
        System.out.println("Ange återlämningsdatum:");
        String aterlamning = scanner.nextLine();
        System.out.println("Ange registreringsnumret för vald hyrbil:");
        String hyrbilsregNr = scanner.nextLine();
        System.out.println("Ange pris per dag för vald hyrbil:");
        int prisPerDag = Integer.parseInt(scanner.nextLine());
        insertBokning(bokningsDatum, upphamtning, aterlamning, prisPerDag, kundNr, hyrbilsregNr);
    }

    private static void updateKund() {
        System.out.println("Ange kundnumret för den kund som du vill uppdatera:");
        System.out.println("1. Namn");
        System.out.println("2. Adress");
        System.out.println("3. Telefon");
        System.out.println("4. Epost");
        System.out.println("Ditt val:");
        int selection = Integer.parseInt(scanner.nextLine());
        switch (selection) {
            case 1:
                System.out.println("Ange förnamn:");
                System.out.println("Ange efternamn:");
                andraNamn(fornamn, efternamn);
                break;

            case 1:

                break;

            case 2:
                insertBokning();
                break;

            case 3:
                System.out.println("Ange kundnumret för den kund som du vill uppdatera:");
                int kundNr = Integer.parseInt(scanner.nextLine());
                update();
                break;

            case 4:
                //delete(1);
                deleteBokning();
                break;

            case 5:
                printActions();
                break;
        }

    }


    private static void deleteBokning(){
        System.out.println("Skriv in bokningsnumret för den bokning som ska tas bort: ");
        int bokningsNr = Integer.parseInt(scanner.nextLine());
        delete(bokningsNr);
    }

    private static void selectAll(){
        String sql = "SELECT * FROM kund";

        try {
            Connection conn = connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("kundNr") +  "\t" +
                        rs.getString("fornamn") + "\t" +
                        rs.getString("efternamn") + "\t" +
                        rs.getString("gatuadress") + "\t" +
                        rs.getString("postNr") + "\t" +
                        rs.getString("ort") + "\t" +
                        rs.getString("land") + "\t" +
                        rs.getString("telefon") + "\t" +
                        rs.getString("epost") + "\t" +
                        rs.getString("kommentar"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void insertBokning(String bokningsDatum, String upphamtning, String aterlamning, int prisPerDag, int kundNr, String hyrbilsregNr) {
        String sql = "INSERT INTO hyrbilsbokning(bokningsDatum, upphamtning, aterlamning, prisPerDag, kundNr, hyrbilsregNr) VALUES(?,?,?,?,?,?)";

        try{
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bokningsDatum);
            pstmt.setString(2, upphamtning);
            pstmt.setString(3, aterlamning);
            pstmt.setInt(4, prisPerDag);
            pstmt.setInt(5, kundNr);
            pstmt.setString(6, hyrbilsregNr);
            pstmt.executeUpdate();
            System.out.println("Du har lagt till en ny bokning");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void update(String forfattare, String titel, int pris, int id) {
        String sql = "UPDATE bok SET bokForfattare = ? , "
                + "bokTitel = ? , "
                + "bokPris = ? "
                + "WHERE bokId = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, forfattare);
            pstmt.setString(2, titel);
            pstmt.setInt(3, pris);
            pstmt.setInt(4, id);
            // update
            pstmt.executeUpdate();
            System.out.println("Du har uppdaterat vald bok");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void delete(int bokningsNr) {
        String sql = "DELETE * FROM hyrbilsbokning WHERE bokningsNr = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, bokningsNr);
            // execute the delete statement
            pstmt.executeUpdate();
            System.out.println("Du har tagit bort bokningen");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        boolean quit = false;
        printActions();
        while(!quit) {
            System.out.println("\nVälj (5 för att visa val):");
            int action = Integer.parseInt(scanner.nextLine());

            switch (action) {
                case 0:
                    System.out.println("\nStänger ner...");
                    quit = true;
                    break;

                case 1:
                    selectAll();
                    break;

                case 2:
                    insertBokning();
                    break;

                case 3:
                    System.out.println("Ange kundnumret för den kund som du vill uppdatera:");
                    int kundNr = Integer.parseInt(scanner.nextLine());
                    update();
                    break;

                case 4:
                    //delete(1);
                    deleteBokning();
                    break;

                case 5:
                    printActions();
                    break;
            }
        }

    }

}