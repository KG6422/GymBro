package user;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserProfile {

	private String first, last;
	private WeightDay weight;
	private ArrayList<WeightDay> weightList;
	private int age;
	private gender gen;
	
	public enum gender{
		male("male"),female("female");
		
		private String name;
		
		gender(String name){
			this.name = name;
		}
	}
	
	public UserProfile(String first, String last, WeightDay weight, int age, gender gen) {
		this.first = first;
		this.last = last;
		this.weight = weight;
		this.age = age;
		this.gen = gen;
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	public WeightDay getWeightDay(LocalDate date) {
		return weightList.get(weightList.indexOf(new WeightDay(date)));
	}
	
	public float getAverageWeight(LocalDate date) {
		WeightDay wd = getWeightDay(date);
		return wd.getAverageWeight();
	}

	public int getAge() {
		return age;
	}

	public gender getGender() {
		return gen;
	}
	
	public String getGenderName() {
		return gen.name;
	}
	
}
