package main;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedList;

import gui.DayPanel;

public class GymDay implements Comparable<GymDay>, Serializable {

	private static final long serialVersionUID = 4788622305428987614L;
	// all immutable fields
	private LocalDate date;
	private DayType type;
	private final int NUM_COLS_TO_DISPLAY = 7; // Activity,Sets,Reps,Weight,KRP,Muscles_Dropdown_Widget,Close_Button

	// mutable field
	LinkedList<Activity> acts = new LinkedList<>();

	public GymDay(LocalDate inDate, DayType inType) {
		date = inDate;
		type = inType;
	}

	public GymDay(LocalDate inDate) {
		this(inDate, DayType.REST);
	}

	/**
	 * Takes the object's Activity List (stored as a linked list) and converts it to
	 * a 2d array
	 * 
	 * @return An object array that is used to fill the JTabl in the MainPanel
	 *         class.
	 */
	public Object[][] toObjectArray() {
		Object[][] list = new Object[this.acts.size()][NUM_COLS_TO_DISPLAY];

		for (int i = 0; i < this.acts.size(); i++) {
			Activity a = acts.get(i);
			list[i][0] = a.getExercise().getName();
			list[i][1] = a.getSets();
			list[i][2] = a.getRepsString();
			list[i][3] = a.getWeightString();
			list[i][4] = a.calculateKRP();
			list[i][5] = "Show Info";
			list[i][6] = "Delete Row";
		}

		return list;
	}

	public DayPanel createDayPanel() {
		DayPanel p = new DayPanel(this);
		return p;
	}

	public boolean isEmpty() {
		if (acts.size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * @return the LocalDate
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return date.getYear();
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return date.getMonthValue();
	}

	/**
	 * @return the day
	 */
	public int getDay() {
		return date.getDayOfMonth();
	}

	/**
	 * @return day of week
	 */
	public DayOfWeek getDayOfWeek() {
		return date.getDayOfWeek();
	}

	/**
	 * @return the type
	 */
	public DayType getType() {
		return type;
	}

	/**
	 * @param DayType the new DayType this GymDay should be
	 */
	public void setType(DayType type) {
		this.type = type;
	}

	/**
	 * 
	 * @param a : activity to be added to linked list
	 */
	public void AddActivity(Activity a) {
		acts.add(a);
	}

	/**
	 * 
	 * @param a : activity to be removed
	 * @return boolean representing successful deletion
	 */
	public boolean RemoveActivity(Activity a) {
		// might need the index of the activity to delete the correct one.
		return acts.remove(a);
	}
	/**
	 * 
	 * @param index : index/row to be removed from
	 * @return boolean representing successful deletion
	 */
	public boolean RemoveActivity(int index) {
		return acts.remove(index) != null;
	}
	
	public Activity getActivity(int index) {
		return acts.get(index);
	}
	
	public int getActivitySize() {
		return acts.size();
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof GymDay)) {
			return false;
		}
		GymDay g = (GymDay) o;
		return (this.date.equals(g.date));
	}

	public int hashCode() {
		return (super.hashCode() + (date.hashCode()) / 2);
	}

	@Override
	public int compareTo(GymDay o) {
		return date.compareTo(o.date);
	}
}
