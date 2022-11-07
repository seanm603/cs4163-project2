import java.sql.*;
import java.io.*;

class jdbcMySQL {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:mysql://129.244.40.38:3306/user32?useSSL=false";
            String user = "user32";
            String password = "TU2021-blue-pen";
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            // Create a Statement
            Statement stmt = conn.createStatement();

            // Query 2 from Project 1:
            String select = "SELECT DISTINCT P.Id AS Poster, P.Gender AS P_Gender, R.Id AS Recipient, R.Gender AS R_Gender";
            String from = "FROM Users P, Users R, Comments C";
            String where = "WHERE P.Id = C.Poster AND R.Id = C.Recipient AND P.Gender != R.Gender";

            // Store records from query as a ResultSet
            ResultSet rset = stmt.executeQuery(select + " " + from + " " + where);

            ResultSetMetaData metadata = rset.getMetaData(); // get metadata from the ResultSet
            int columnCount = metadata.getColumnCount(); // get the number of columns from metadata
    
            for (int i = 1; i <= columnCount; i++) { // print out the column headers
                System.out.print(metadata.getColumnName(i) + " ");
            }
            System.out.println();

            int i = 0;
            while (rset.next()) { // Loop over each record
                String row = "";
                for (int j = 1; j <= columnCount; j++) {
                    // System.out.print(rset.getString(j) + " ");
                    row += rset.getString(j) + " ";
                }
                System.out.println(row); // print out the accumulated current record
                i++;
            }
            System.out.println("\nNumber of records fetched: " + i + "\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                // System.out.println("Success");
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
