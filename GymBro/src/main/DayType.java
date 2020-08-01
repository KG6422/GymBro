package main;

import java.io.Serializable;
import java.util.ArrayList;

public enum DayType implements Serializable{

	PUSH("PUSH", new musclegroup[] {musclegroup.chest, musclegroup.triceps, musclegroup.shoulders}),
	PULL("PULL", new musclegroup[] {musclegroup.back, musclegroup.biceps}),
	LEG("LEG", new musclegroup[] {musclegroup.legs}),
	REST("REST", new musclegroup[] {musclegroup.legs});
	
	private static final long serialVersionUID = -457238974912L;
	
	DayType(final String name, final musclegroup[] target){
		_name = name;
		_target = target;
	}
	
	/**
	 * @return the _name
	 */
	public String getName() {
		return _name;
	}
	
	public musclegroup[] getTarget(){
		return _target;
	}
	
	public ArrayList<muscle> getEnclosedMusclesArrayList(){
		ArrayList<muscle> list = new ArrayList<muscle>();
		for (musclegroup m : _target) {
			for (int i = 0; i < m.getMuscles().length; i++) {
				list.add(m.getMuscles()[i]);
			}
		}
		return list;
	}
	
	public muscle[] getEnclosedMuscles() {
		ArrayList<muscle> list = getEnclosedMusclesArrayList();
		muscle[] returnable = new muscle[list.size()];
		for (int i = 0; i < list.size(); i++) {
			returnable[i] = list.get(i);
		}
		return returnable;
	}

	private String _name;
	private musclegroup[] _target;
}
