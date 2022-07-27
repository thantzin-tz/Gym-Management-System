package shared.mapper;

import java.sql.ResultSet;

import models.Schedule;
import models.Staff;

public class ScheduleMapper {
	public Schedule mapToSchedule(Schedule schedule, ResultSet rs) {
        try {
        	
        	schedule.setSchedule_id(rs.getInt("schedule_id"));
        	schedule.setSchedule_name(rs.getString("schedule_name"));
        	schedule.setSchedule_type(rs.getString("schedule_type"));
        	schedule.setTime(rs.getString("time"));
        	schedule.setTypeOfBody(rs.getString("typeOfBody"));
        	schedule.setGender(rs.getString("gender"));
        	schedule.setAmount(rs.getDouble("fees"));
        	schedule.setAvailabeMember(rs.getInt("availableMember"));
        
        	Staff staff=new Staff();
        	staff.setStaff_id(rs.getInt("staff_id"));
        	staff.setName(rs.getString("name"));
        	
        	schedule.setStaff(staff);
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedule;
}
	
	public Schedule mapToAvailableMember(Schedule schedule, ResultSet rs) {
        try {
        	schedule.setAvailabeMember(rs.getInt("availableMember"));
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedule;
}

}
