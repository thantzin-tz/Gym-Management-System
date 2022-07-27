package shared.mapper;

import java.sql.ResultSet;

import models.Schedule;
import models.ScheduleMember;
import models.Members;

public class ScheduleMemberMapper {

	public ScheduleMember mapToSchedule(ScheduleMember scheduleMember, ResultSet rs) {
		try {
			scheduleMember.setScheduleMember_id(rs.getInt("scheduledMember_id"));
			scheduleMember.setScheduleJoinDate(rs.getString("joinDate"));
			scheduleMember.setExpireDate(rs.getString("expireDate"));
			scheduleMember.setDayLeft(rs.getInt("dayLeft"));
			
			Schedule schedule = new Schedule();
			schedule.setSchedule_id(rs.getInt("schedule_id"));
			schedule.setSchedule_name(rs.getString("schedule_name"));
			schedule.setGender(rs.getString("gender"));
			schedule.setTypeOfBody(rs.getString("typeofBody"));
//			schedule.setScheduleDuration(rs.getString("schedule_duration"));
			schedule.setTime(rs.getString("time"));
			schedule.setAmount(rs.getDouble("fees"));
			schedule.setAvailabeMember(rs.getInt("availableMember"));
			/// Schedule Add to ScheduleMember
			scheduleMember.setSchedule(schedule);

			Members member = new Members();
			member.setMember_id(rs.getInt(("member_id")));
			member.setName(rs.getString("name"));
			
			/// Schedule Add to ScheduleMember
			scheduleMember.setMember(member);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleMember;
	}

}
