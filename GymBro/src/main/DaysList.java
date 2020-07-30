package main;

import java.io.Serializable;
import java.util.LinkedList;

public class DaysList extends LinkedList<GymDay> implements Serializable {

	private static final long serialVersionUID = -3928444663158345512L;
	private static DaysList SINGLETON = new DaysList();
	
	public static DaysList getInstance() {
		return SINGLETON;
	}
	
	private DaysList() {
		super();
	}
	
	public void addLast(main.GymDay obj) {
		this.add(obj);
	}
	
	public boolean contains(GymDay o) {
		for (GymDay item : getInstance()) {
			if (o.equals(item)) {
				return true;
			}
		}
		return false;
	}
	
	
}
