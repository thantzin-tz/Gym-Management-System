package services;

import models.BodyPart;
import config.DBConfig;

//import shared.exception.AppException;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BodyPartService  {

    private final DBConfig dbConfig = new DBConfig();

    public void saveBodyPart(BodyPart body) {
        try {

            PreparedStatement ps = this.dbConfig.getConnection()
                    .prepareStatement("INSERT INTO bodyPart(bodyPart_name) VALUES (?);");

            ps.setString(1, body.getName());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            
                JOptionPane.showMessageDialog(null, "Already Exists");
            
        }

    }

    public void updateBodyPart(String id, BodyPart body) {
        try {

            PreparedStatement ps = this.dbConfig.getConnection()
                    .prepareStatement("UPDATE bodyPart SET bodyPart_name = ? WHERE bodyPart_id = ?");

            ps.setString(1, body.getName());
            ps.setString(2, id);
            ps.execute();

            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<BodyPart> findAllBodyPart() {
        List<BodyPart> bodyPartList = new ArrayList<>();

        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM bodyPart";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                BodyPart body = new BodyPart();
                body.setId(rs.getInt("bodyPart_id"));
                body.setName(rs.getString("bodyPart_name"));
                bodyPartList.add(body);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bodyPartList;
    }

    public BodyPart findById(String id) {
    	BodyPart brand = new BodyPart();

        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM bodyPart WHERE bodyPart_id = " + id + ";";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                brand.setId(rs.getInt("bodyPart_id"));
                brand.setName(rs.getString("bodyPart_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return brand;
    }
   /* public void deleteWorkout(String id, Workout workout) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("Delete from workout Where workout_id =?");
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
  public void delete(String id,BodyPart body) {
        try {

            List<Workout> productsByCategoryId = body.findProductsByBrandId(id);

            if (productsByCategoryId.size() > 0) {
                throw new AppException("This brand cannot be deleted");
            }

            String query = "DELETE FROM brand WHERE brand_id = ?";

            PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(query);
            ps.setString(1, id);

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            if (e instanceof AppException)
                JOptionPane.showMessageDialog(null, e.getMessage());
            else e.printStackTrace();
        }
    }*/
}
