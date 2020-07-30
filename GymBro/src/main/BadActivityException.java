package main;

public class BadActivityException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String desc;
	
	BadActivityException(){
		desc = "REPS AND WEIGHT MUST HAVE LENGTH OF AT LEAST 1. SETS MUST BE POSITIVE. EXERCISE MUST NOT BE NULL.";
		System.out.println(desc);
	}
}
