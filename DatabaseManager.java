package Project;

import java.sql.*;
import java.util.*;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void saveVariety(BananaVariety variety) {
        String checkSql = "SELECT COUNT(*) FROM banana_varieties WHERE name = ?";
        String insertSql = "INSERT INTO banana_varieties (name, season, growth_duration, min_soil_ph, max_soil_ph) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            // Check if variety with the same name already exists
            checkStmt.setString(1, variety.getName());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Variety with this name already exists. Please use a different name.");
                return;
            }

            // If not duplicate, insert the new variety
            insertStmt.setString(1, variety.getName());
            insertStmt.setString(2, variety.getSeason());
            insertStmt.setInt(3, variety.getGrowthDuration());
            insertStmt.setDouble(4, variety.getMinSoilPH());
            insertStmt.setDouble(5, variety.getMaxSoilPH());

            int rowsAffected = insertStmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Saved to Database!");
            } else {
                System.out.println("No data affected!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<BananaVariety> fetchAllVariety(){
        List<BananaVariety> varieties = new ArrayList<>();
        String sql = "Select * from banana_varieties";

        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String season = rs.getString("season");
                int duration = rs.getInt("growth_duration");
                double minPH = rs.getDouble("min_soil_ph");
                double maxPH = rs.getDouble("max_soil_ph");

                BananaVariety variety = new BananaVariety(id,name,season,duration,minPH,maxPH);
                varieties.add(variety);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return varieties;
    }

    public List<BananaVariety> recommended_varieites(double inputPH){
        List<BananaVariety> matches = new ArrayList<>();
        String sql = "SELECT * FROM banana_varieties WHERE ? BETWEEN min_soil_ph AND max_soil_ph";

        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setDouble(1,inputPH);

            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String season = rs.getString("season");
                    int duration = rs.getInt("growth_duration");
                    double minPH = rs.getDouble("min_soil_ph");
                    double maxPH = rs.getDouble("max_soil_ph");

                    BananaVariety variety = new BananaVariety(id,name,season,duration,minPH,maxPH);
                    matches.add(variety);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return matches;
    }

    public void deletevarietybyid(int id){
        String sql = "DELETE FROM banana_varieties WHERE id = ?";
        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Successfully deleted ID: " + id);
            } else {
                System.out.println("No record found with ID: " + id);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deletevarietybyname(String name){
        String sql = "DELETE FROM banana_varieties WHERE name = ?";
        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, name);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Successfully deleted variety with name: " + name);
            } else {
                System.out.println("No record found with name: " + name);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void modifyname(int id, String newname){
        String sql = "UPDATE banana_varieties SET name = ? WHERE id = ?";

        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,newname);
            stmt.setInt(2,id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Successfully modified name for ID: " + id);
            } else {
                System.out.println("No record found with ID: " + id);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void modifyseason(int id, String newseason){
        String sql = "UPDATE banana_varieties SET season = ? WHERE id = ?";

        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,newseason);
            stmt.setInt(2,id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Successfully modified season for ID: " + id);
            } else {
                System.out.println("No record found with ID: " + id);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void modifyduration(int id, int newduration){
        String sql = "UPDATE banana_varieties SET growth_duration = ? WHERE id = ?";

        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,newduration);
            stmt.setInt(2,id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Successfully modified Growth Duration for ID: " + id);
            } else {
                System.out.println("No record found with ID: " + id);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void modifyminph(int id, double newminph){
        String sql = "UPDATE banana_varieties SET min_soil_ph = ? WHERE id = ?";

        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setDouble(1,newminph);
            stmt.setInt(2,id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Successfully modified Min pH Value for ID: " + id);
            } else {
                System.out.println("No record found with ID: " + id);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void modifymaxph(int id, double newmaxph){
        String sql = "UPDATE banana_varieties SET max_soil_ph = ? WHERE id = ?";

        try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setDouble(1,newmaxph);
            stmt.setInt(2,id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Successfully modified Max pH Value for ID: " + id);
            } else {
                System.out.println("No record found with ID: " + id);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
