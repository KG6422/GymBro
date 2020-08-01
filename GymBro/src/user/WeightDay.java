package user;

import java.time.LocalDate;

public class WeightDay implements Comparable<WeightDay>{

	float[] weights;
	LocalDate date;
	
	public WeightDay(float[] weights, LocalDate date) {
		this.weights = weights;
		this.date = date;
	}
	
	public WeightDay(LocalDate date) {
		this(new float[0], date);
	}
	
	public void addWeight(float newWeight) {
		addWeights(new float[] {newWeight}); 
	}
	
	public void addWeights(float[] newWeights) {
		float[] temp = weights.clone();
		weights = new float[temp.length + newWeights.length];
		for (int i = 0; i < temp.length; i++) {
			weights[i] = temp[i];
		}
		for (int i = temp.length; i < temp.length + newWeights.length; i++) {
			weights[i] = newWeights[i - temp.length];	// implemented correctly?
		}
	}
	
	public boolean removeWeight(float rem) {
		float[] temp = weights.clone();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] == rem) {
				for (int j = i; j < temp.length; j++) {
					temp[i] = temp[j];
				}
				weights = new float[temp.length - 1];
				for (int j = 0; j < temp.length - 1; j++) {
					weights[j] = temp[j];
				}
				return true;
			}
		}
		return false;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public float getAverageWeight() {
		float add = 0;
		for (float i : weights) {
			add+=i;
		}
		
		return add/((float)weights.length);
	}
	
	public float[] getWeights() {
		return weights;
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof WeightDay)) {
			return false;
		}
		WeightDay w = (WeightDay)o;
		return this.date.equals(w.getDate());
	}
	
	public int hashCode() {
		return date.hashCode() + 1;
	}
	
	@Override
	public int compareTo(WeightDay o) {
		return this.getDate().compareTo(o.getDate());
	}
	
	
}
