package main;

import java.io.Serializable;

public class Activity implements Serializable{

	
	private static final long serialVersionUID = 4947826518378318735L;
	//all fields are immutable
	private final int[] reps, weight;
	private final int sets;
	private ex exercise;
	private muscle[] muscleList;
	
	public Activity(int[] _reps, int[] _weight, int _sets, ex _exercise) throws BadActivityException {	//should also throw exception if sets is non-positive.
		
		if (_reps.length == 0 || _weight.length == 0 || _sets == 0 || _exercise == null) {
			throw new BadActivityException();
		}
		sets =  _sets;
		exercise = _exercise;
		if (_weight.length != sets || sets != _reps.length) {
			//weight and reps are not same length
			// first check to see if the weight array is less than the integer value sets.
			if (_weight.length == sets) {
				weight = _weight;
			} else {
				weight = new int[sets];
				for (int i = 0; i < sets; i++) {
					if (i < _weight.length) {
						weight[i] = _weight[i];
					} else {
						weight[i] = _weight[_weight.length - 1];
					}
				}
				
			}
			if (_reps.length == sets) {
				reps = _reps;
			} else {
				reps = new int[sets];
				for (int i = 0; i < sets; i++) {
					if (i < _reps.length) {
						reps[i] = _reps[i];
					} else {
						reps[i] = _reps[_reps.length - 1];
					}
				}
			}
			
		} else {
			reps = _reps;
			weight = _weight;
		}
	}
	
	public muscle[] getMuscleList() {
		return muscleList;
	}
	
	public void addMuscleList(muscle[] in) {
		muscleList = in;
	}

	/**
	 * @return the reps
	 */
	public int[] getReps() {
		return reps;
	}
	public String getRepsString() {
		String s = "";
		for (int i = 0; i < reps.length - 1; i++) {
			s += (reps[i] + ",");
		}
		s+= reps[reps.length - 1];
		return s;
	}

	/**
	 * @return the sets
	 */
	public int getSets() {
		return sets;
	}

	/**
	 * @return the exercise
	 */
	public ex getExercise() {
		return exercise;
	}

	/**
	 * @return the weight
	 */
	public int[] getWeight() {
		return weight;
	}
	public String getWeightString() {
		String s = "";
		for (int i = 0; i < weight.length - 1; i++) {
			s += (weight[i] + ",");
		}
		s+= weight[weight.length - 1];
		return s;
	}
	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Activity)) {
			return false;
		}
		Activity a = (Activity)o;
		return (a.reps.equals(this.reps) && a.sets == this.sets && a.exercise.equals(this.exercise));
	}
	
	public int hashCode() {
		return (super.hashCode() + (sets * 83) + reps.hashCode() + exercise.hashCode());
	}


	public int calculateKRP() {
		int krp = 0;
		for (int i = 0; i < reps.length; i++) {
			krp += (reps[i] * weight[i]);
		}
		return krp;
	}

	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append(exercise.getName() + " performed in " + sets + " sets. ");
		for (int i = 0; i < sets; i++) {
			s.append("| Set " + i + ": " + reps[i] + " reps of " + weight[i] + " pounds ");
		}
		s.append("|");
		return s.toString();
	}

}
