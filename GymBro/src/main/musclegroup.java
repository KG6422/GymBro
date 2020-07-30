package main;

import java.io.Serializable;

public enum musclegroup implements Serializable {

	shoulders("shoulders",
			new muscle[] { muscle.deltoids_anterior, muscle.deltoids_lateral, muscle.deltoids_posterior }),
	chest("chest", new muscle[] { muscle.pecs_upper, muscle.pecs_lower, muscle.pecs_middle, muscle.pecs_outer }),
	triceps("triceps", new muscle[] { muscle.triceps_longhead, muscle.triceps_lateralhead, muscle.triceps_medialhead }),
	core("core",
			new muscle[] { muscle.abdominals_upper, muscle.abdominals_middle, muscle.abdominals_lower,
					muscle.obliques_external, muscle.obliques_internal, muscle.serratus_anterior }),
	biceps("biceps",
			new muscle[] { muscle.forearm, muscle.biceps_longhead, muscle.biceps_shorthead, muscle.biceps_brachialis }),
	back("back",
			new muscle[] { muscle.lowerback, muscle.infraspinatus, muscle.lats, muscle.traps_upper, muscle.traps_middle,
					muscle.traps_lower, muscle.teres_minor, muscle.teres_major }),
	legs("legs", new muscle[] { muscle.calves_gastrocnemius, muscle.calves_soleus, muscle.gluteus_maximus,
			muscle.gluteus_medius, muscle.gluteus_minimus, muscle.hips_adductors, muscle.hips_flexors,
			muscle.hips_outer, muscle.hamstrings_semitendinosus, muscle.hamstrings_biceps_femoris, muscle.sartorius,
			muscle.tibialis_anterior, muscle.quadriceps_vastus_medialis, muscle.quadriceps_rectus_femoris,
			muscle.quadriceps_vastus_lateralis, muscle.quadriceps_vastus_intermedius });

	private musclegroup(String inName, muscle[] inMuscle) {
		name = inName;
		muscles = inMuscle;
	}
	
	public String getName() {
		return name;
	}

	public muscle[] getMuscles() {
		return muscles;
	}

	private String name;
	private muscle[] muscles;
	private static final long serialVersionUID = 9981891113L;

	
}
