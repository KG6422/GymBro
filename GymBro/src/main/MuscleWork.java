package main;

/**
 * 
 * Essentially a pair class that holds a reference to a muscle. It also holds
 * data for the total amount of KRP and the number of different exercises that
 * have targeted it.
 * 
 * @author kggri
 *
 */
public class MuscleWork implements Comparable<MuscleWork> {

	private muscle musc;
	private int count, KRP;
	private boolean isPrimary;

	/**
	 * Constructor that automatically sets this muscleWork object as a primary muscle.
	 * 
	 * @param musc
	 * @param count
	 * @param KRP
	 */
	public MuscleWork(muscle musc, int count, int KRP) {
		this(musc, count, KRP, true);
	}
	
	/**
	 * Constructor that sets whether is a primary muscle or not.
	 * 
	 * @param musc
	 * @param count
	 * @param KRP
	 * @param isPrimary
	 */
	public MuscleWork(muscle musc, int count, int KRP, boolean isPrimary) {
		this.musc = musc;
		this.count = count;
		this.KRP = KRP;
		this.isPrimary = isPrimary;
	}

	public muscle getMuscle() {
		return musc;
	}

	public int getKRP() {
		return KRP;
	}

	public int getCount() {
		return count;
	}

	public void setKRP(int newKRP) {
		KRP = newKRP;
	}

	public void setCount(int newCount) {
		count = newCount;
	}

	public void incrementKRP(int value) {
		KRP += value;
	}

	public void incrementCount(int value) {
		count += value;
	}
	
	public boolean getPrim() {
		return isPrimary;
	}
	
	public String toString() {
		return "MuscleWork:"+hashCode() + ", " + getMuscle() + ", " + getKRP() + ", worked " + getCount() + " times;";
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof MuscleWork)) {
			return false;
		}
		MuscleWork m = (MuscleWork) o;
		return (m.musc.equals(this.musc));
	}

	public int hashCode() {
		return (super.hashCode() + musc.hashCode()) / 2;
	}

	@Override
	public int compareTo(MuscleWork o) {
		return this.getMuscle().compareTo(o.getMuscle());
	}

}
