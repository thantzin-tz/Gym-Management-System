package models;

public class Equipment {

	private int equipment_id;
	private String equip_name;
	private String equip_condition;
	private String last_maintainDate;
	private String next_maintainDate;

	private Staff staff;

	public int getEquipment_id() {
		return equipment_id;
	}

	public void setEquipment_id(int equipment_id) {
		this.equipment_id = equipment_id;
	}

	public Equipment() {

	}

	public Equipment(int equipment_id, String equip_name, String equip_condition, String last_maintainDate,
			String next_maintainDate, Staff staff)

	{
		// this.schedule_id = schedule_id;
		this.equipment_id = equipment_id;
		this.equip_name = equip_name;
		this.equip_condition = equip_condition;
		this.last_maintainDate = last_maintainDate;
		this.next_maintainDate = next_maintainDate;
		this.staff = staff;

		// this.staff=staff;
	}

	public String getEquip_name() {
		return equip_name;
	}

	public void setEquip_name(String equip_name) {
		this.equip_name = equip_name;
	}

	public String getEquip_condition() {
		return equip_condition;
	}

	public void setEquip_condition(String equip_condition) {
		this.equip_condition = equip_condition;
	}

	public String getLast_maintainDate() {
		return last_maintainDate;
	}

	public void setLast_maintainDate(String last_maintainDate) {
		this.last_maintainDate = last_maintainDate;
	}

	public String getNext_maintainDate() {
		return next_maintainDate;
	}

	public void setNext_maintainDate(String next_maintainDate) {
		this.next_maintainDate = next_maintainDate;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}
