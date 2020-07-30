package main;

import java.io.Serializable;

public enum ex implements Serializable{
	//PUSH
	incline_bench_press("incline bench press", muscle.pecs_upper, new muscle[]{muscle.triceps_longhead, muscle.triceps_lateralhead, muscle.triceps_medialhead, muscle.deltoids_anterior}),
	incline_dumbbell_flies("incline dumbbell flies", muscle.pecs_middle, new muscle[] {muscle.triceps_longhead, muscle.triceps_lateralhead, muscle.triceps_medialhead, muscle.deltoids_anterior}),
	decline_bench_press("decline bench press", muscle.pecs_lower, new muscle[] {muscle.triceps_longhead, muscle.triceps_lateralhead, muscle.triceps_medialhead, muscle.deltoids_anterior}),
	decline_dumbbell_flies("decline dumbbell flies", muscle.pecs_lower, new muscle[] {muscle.triceps_longhead, muscle.triceps_lateralhead, muscle.triceps_medialhead, muscle.deltoids_anterior}),
	flat_bench_press("flat bench press", muscle.pecs_middle, new muscle[] {muscle.triceps_longhead, muscle.triceps_lateralhead, muscle.triceps_medialhead, muscle.deltoids_anterior}),
	one_sided_resistance_cable_flies("one-sided resistance cable flies", muscle.pecs_middle, new muscle[] {muscle.triceps_longhead, muscle.triceps_lateralhead, muscle.triceps_medialhead, muscle.deltoids_anterior}),
	deltoid_lateral_raises("deltoid lateral raises", muscle.deltoids_lateral, new muscle[] {muscle.traps_upper, muscle.traps_lower}),
	barbell_shoulder_press("barbell shoulder press", muscle.deltoids_anterior, new muscle[] {muscle.deltoids_lateral, muscle.triceps_longhead, muscle.triceps_lateralhead, muscle.triceps_medialhead}),
	barbell_tricep_extension("barbell tricep extension", muscle.triceps_longhead, new muscle[] {muscle.forearm}),
	diamond_cutter_pushup("diamond cutter pushup", muscle.triceps_lateralhead, new muscle[] {muscle.triceps_longhead, muscle.deltoids_anterior}),
	close_grip_dumbbell_press("close-grip dumbbell press", muscle.triceps_medialhead, new muscle[] {}),
	tricep_push_away("tricep push away",muscle.triceps_longhead, new muscle[] {}),
	tricep_pull_down("tricep pull down", muscle.triceps_lateralhead, new muscle[] {}),
	reverse_grip_pulldown("reverse grip pulldown", muscle.triceps_medialhead, new muscle[] {}),
	//PULL
	farmers_carry("farmers carry", muscle.forearm, new muscle[] {muscle.traps_lower}),
	deadlift("deadlift", new muscle[] {muscle.hamstrings_semitendinosus, muscle.hamstrings_semimembranosus, muscle.hamstrings_biceps_femoris,muscle.gluteus_maximus, muscle.gluteus_medius, muscle.lowerback}, new muscle[] {muscle.quadriceps_rectus_femoris, muscle.quadriceps_vastus_lateralis, muscle.quadriceps_vastus_medialis, muscle.quadriceps_vastus_intermedius, muscle.hips_adductors, muscle.hips_outer}),
	bent_over_barbell_row("bent-over barbell row", muscle.lats, new muscle[] {muscle.traps_upper, muscle.traps_middle, muscle.traps_lower, muscle.teres_minor, muscle.teres_major}),
	bent_over_dumbbell_rows("bent-over dumbbell rows", muscle.lats, new muscle[] {muscle.traps_upper, muscle.traps_middle, muscle.traps_lower, muscle.teres_minor, muscle.teres_major}),
	kneeling_dumbbell_reverse_flies("kneeling dumbbell reverse flies", muscle.deltoids_posterior, new muscle[] {}),
	shoulder_shrugs("shoulder shrug", new muscle[] {muscle.traps_lower, muscle.traps_upper}, new muscle[] {}),
	seated_dumbbell_bicep_curls("seated dumbbell bicep curls", muscle.biceps_longhead, new muscle[] {muscle.deltoids_anterior}),
	zottman_curls("zottman curls", new muscle[] {muscle.biceps_longhead, muscle.biceps_shorthead, muscle.biceps_brachialis}, new muscle[] {muscle.deltoids_anterior}),
	hammer_curls("hammer curls", new muscle[] {muscle.biceps_longhead, muscle.biceps_brachialis}, new muscle[] {muscle.biceps_shorthead, muscle.deltoids_anterior}),
	spider_curls("spider curls", muscle.biceps_shorthead, new muscle[] {muscle.biceps_longhead, muscle.biceps_brachialis, muscle.deltoids_anterior}),
	concentration_curls("concentration curls", muscle.biceps_shorthead, new muscle[] {muscle.biceps_longhead}),
	//LEG
	side_lunge("side lunge", new muscle[] {muscle.quadriceps_rectus_femoris, muscle.quadriceps_vastus_lateralis, muscle.quadriceps_vastus_medialis, muscle.quadriceps_vastus_intermedius, muscle.gluteus_maximus, muscle.gluteus_medius, muscle.hips_adductors}, new muscle[] {muscle.hamstrings_semitendinosus, muscle.hamstrings_semimembranosus, muscle.hamstrings_biceps_femoris, muscle.hips_outer}),
	reverse_lunge("reverse lunge", new muscle[] {muscle.quadriceps_rectus_femoris, muscle.quadriceps_vastus_lateralis, muscle.quadriceps_vastus_medialis, muscle.quadriceps_vastus_intermedius, muscle.gluteus_maximus, muscle.gluteus_medius}, new muscle[] {muscle.hamstrings_semitendinosus, muscle.hamstrings_semimembranosus, muscle.hamstrings_biceps_femoris, muscle.hips_outer, muscle.hips_adductors, muscle.calves_gastrocnemius, muscle.calves_soleus}),
	lunge("lunge", new muscle[] {muscle.quadriceps_rectus_femoris, muscle.quadriceps_vastus_lateralis, muscle.quadriceps_vastus_medialis, muscle.quadriceps_vastus_intermedius, muscle.gluteus_maximus, muscle.gluteus_medius}, new muscle[] {muscle.hamstrings_semitendinosus, muscle.hamstrings_semimembranosus, muscle.hamstrings_biceps_femoris, muscle.calves_gastrocnemius, muscle.calves_soleus}),
	barbell_squat("barbell squat", new muscle[] {muscle.quadriceps_rectus_femoris, muscle.quadriceps_vastus_lateralis, muscle.quadriceps_vastus_medialis, muscle.quadriceps_vastus_intermedius, muscle.gluteus_maximus, muscle.gluteus_medius}, new muscle[] {muscle.hips_adductors, muscle.hamstrings_semimembranosus, muscle.hamstrings_biceps_femoris, muscle.hamstrings_semitendinosus, muscle.lowerback, muscle.abdominals_lower, muscle.abdominals_middle, muscle.abdominals_upper, muscle.lats, muscle.calves_gastrocnemius}),
	lying_lateral_leg_raises("lying lateral leg raises", new muscle[] {muscle.hips_outer, muscle.hips_flexors}, new muscle[] {muscle.gluteus_maximus, muscle.gluteus_medius, muscle.obliques_external, muscle.obliques_internal}),
	lateral_foot_drags("lateral foot drags", new muscle[] {muscle.gluteus_maximus, muscle.gluteus_medius, muscle.hips_adductors, muscle.hips_flexors}, new muscle[] {muscle.hips_outer, muscle.obliques_external, muscle.obliques_internal}),
	leg_curls("leg curls", new muscle[] {muscle.calves_gastrocnemius, muscle.calves_soleus, muscle.hamstrings_semitendinosus, muscle.hamstrings_semimembranosus, muscle.hamstrings_biceps_femoris}, new muscle[] {}),
	leg_extensions("leg extensions", new muscle[] {muscle.quadriceps_rectus_femoris, muscle.quadriceps_vastus_lateralis, muscle.quadriceps_vastus_medialis, muscle.quadriceps_vastus_intermedius}, new muscle[] {}),
	jump_squats("jump squats", new muscle[] {muscle.quadriceps_rectus_femoris, muscle.quadriceps_vastus_lateralis, muscle.quadriceps_vastus_medialis, muscle.quadriceps_vastus_intermedius, muscle.gluteus_maximus, muscle.gluteus_medius, muscle.calves_gastrocnemius, muscle.calves_soleus, muscle.hamstrings_semitendinosus, muscle.hamstrings_semimembranosus, muscle.hamstrings_biceps_femoris}, new muscle[] {muscle.lowerback, muscle.abdominals_lower, muscle.obliques_external, muscle.obliques_internal, muscle.tibialis_anterior}),
	calf_raises("calf raises", new muscle[] {muscle.calves_gastrocnemius, muscle.calves_soleus}, new muscle[] {});
	
	private static final long serialVersionUID = -12133421431L;
	
	ex(String name, muscle primary_muscle, muscle[] secondary_muscles) {
		this._name = name;
		this._muscle = primary_muscle;
		this._secondary_muscles = secondary_muscles;
		_hasMultiplePrimary = false;
	}
	
	ex(String name, muscle[] primary_muscle, muscle[] secondary_muscles){
		this._name = name;
		this._primary_muscles = primary_muscle;
		this._secondary_muscles = secondary_muscles;
		_hasMultiplePrimary = true;
	}
	private String _name;
	private muscle _muscle;
	private muscle[] _primary_muscles;
	private muscle[] _secondary_muscles;
	private boolean _hasMultiplePrimary;
	
	public String getName() {
		return _name;
	}
	
	public muscle[] getPrimaryMuscles() {
		if (_hasMultiplePrimary) {
			return _primary_muscles;
		} else {
			return new muscle[] {_muscle};
		}
	}
	
	public muscle[] getSecondaryMuscles() {
		return _secondary_muscles;
	}
	
	public static ex findFromName(String s) {
		for (ex e : ex.values()) {
			if (e.getName().equals(s)) {
				return e;
			}
		}
		return null;
	}

}
