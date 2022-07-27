package shared.mapper;

import java.sql.ResultSet;
import models.Staff;
import models.UserRole;

public class StaffMapper {
	public Staff mapToStaff(Staff staff, ResultSet rs) {
        try {
        	staff.setStaff_id(rs.getInt("staff_id"));
        	staff.setName(rs.getString("name"));
        	staff.setAge(rs.getInt("age"));
        	staff.setPhone(rs.getString("phone"));
        	staff.setAddress(rs.getString("address"));
        	staff.setEmail(rs.getString("email"));
        	staff.setGender(rs.getString("gender"));
        	staff.setUsername(rs.getString("username"));
        	staff.setSalary(rs.getDouble("salary"));
        	staff.setRole(UserRole.valueOf(rs.getString("role")));
        	staff.setActive(rs.getBoolean("active"));
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
}
}
