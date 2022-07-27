package shared.mapper;

import java.sql.ResultSet;
import models.Members;

public class MemberMapper {
	public Members mapToMember(Members member, ResultSet rs) {
        try {
        	member.setMember_id(rs.getInt("member_id"));
        	member.setName(rs.getString("name"));
        	member.setAge(rs.getInt("age"));
        	member.setAddress(rs.getString("address"));
        	member.setPhone(rs.getString("phone"));
        	member.setDateOfJoin(rs.getString("date"));
        	member.setGender(rs.getString("gender"));
        	member.setWeight(rs.getDouble("weight"));
        	member.setHeight(rs.getDouble("height"));
        	member.setBmi_result(rs.getDouble("bmi"));
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return member;
}
}
