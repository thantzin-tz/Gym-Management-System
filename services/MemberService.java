package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import config.DBConfig;
import models.Members;
import shared.mapper.MemberMapper;

public class MemberService {

	private final DBConfig dbConfig;
	private final MemberMapper memberMapper;
	
	public MemberService() {
		this.dbConfig = new DBConfig();
		this.memberMapper = new MemberMapper();
	}
	
	public void saveMember(Members member) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
				.prepareStatement("Insert into member(name,age,address,phone,date,gender,weight,height,bmi)values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, member.getName());
			ps.setInt(2, member.getAge());
			ps.setString(3, member.getAddress());
			ps.setString(4, member.getPhone());
			ps.setString(5, member.getDateOfJoin());
			ps.setString(6, member.getGender());
			ps.setDouble(7, member.getWeight());
			ps.setDouble(8, member.getHeight());
			ps.setDouble(9, member.getBmi_result());
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			 JOptionPane.showMessageDialog(null, "Already Exists");
			e.printStackTrace();
		}
	}
	
	public Members findMemberById(String id) {
		Members member = new Members();

        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM member WHERE member_id = " + id + ";";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                this.memberMapper.mapToMember(member, rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return member;
	}
	public List<Members> findAllMembers() {

        List<Members> memberList = new ArrayList<>();
        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = "SELECT * FROM member";

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Members member = new Members();
                memberList.add(this.memberMapper.mapToMember(member, rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberList;

    }
	public void updateMember(String id, Members member) {
		try {
			System.out.println("ID ---------------> "+id);
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("UPDATE member SET name=?, age=?, address=?, phone=?, date=?, "
							+ "gender=?, weight=?, height=?, bmi=? WHERE member_id =?");
			ps.setString(1, member.getName());
			ps.setInt(2, member.getAge());
			ps.setString(3, member.getAddress());
			ps.setString(4, member.getPhone());
			ps.setString(5, member.getDateOfJoin());
			ps.setString(6, member.getGender());
			ps.setDouble(7, member.getWeight());
			ps.setDouble(8, member.getHeight());
			ps.setDouble(9, member.getBmi_result());
			ps.setString(10, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Please select a Member");
			e.printStackTrace();
		}
		
	}
	public void deleteMember(String id, Members member) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("Delete from member Where member_id =?");
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
