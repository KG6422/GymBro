package main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public enum stretches {

	neck_lateral("lateral neck stretch", new muscle[] {}, null),
	neck_frontal("frontal neck stretch", new muscle[] {}, null), 
	neck_rotation("neck rotations", new muscle[] {}, null),
	shoulder_circles("shoulder circles", new muscle[] {}, null), 
	arm_circles("arm circles", new muscle[] {}, null),
	arm_swings("arm swings", new muscle[] {}, null), 
	leg_swings("leg swings", new muscle[] {}, null),
	quadriceps_standing_stretch("standing quadriceps stretch", muscle.quads(), false, null),
	quadriceps_lying_stretch("lying quadriceps stretch", muscle.quads(), false, null),
	hamstring_standing_stretch("standing hamstring stretch", muscle.hams(), false, null),
	hamstring_seated_stretch("seated hamstring stretch", muscle.hams(), false, null),
	calves_standing_stretch("standing calves stretch", new muscle[] {}, false, null),
	lats_wall_stretch("lats wall stretch", new muscle[] {muscle.lats}, false, null),
	traps_shrug_stretch("traps shrug stretch", muscle.traps(), false, null),
	bicep_stretch("bicep arm-over-arm stretch", muscle.biceps(), false, null),
	chest_fly_stretch("chest fly stretch", muscle.pecs(), false, null),
	triceps_overhead_stretch("triceps overhead stretch", muscle.triceps(), false, null),
	deltoids_cross_chest_stretch("deltoids cross chest stretch", muscle.shoulder(), false, null),
	wrist_bend_down_stretch("wrist downwards stretch", new muscle[] {muscle.forearm}, false, null),
	wrist_bend_up_stretch("wrist upwards stretch", new muscle[] {muscle.forearm}, false, null);

	stretches() {
		this("DEFAULT", new muscle[] {}, true, null);
	}

	stretches(String name, muscle[] musc, BufferedImage image) {
		this(name, musc, true, image);
	}

	stretches(String name, muscle[] musc, boolean isDynamic, BufferedImage image) {
		this.name = name;
		this.musc = musc;
		this.isDynamic = isDynamic;
		this.image = image;
	}

	final String name;
	muscle[] musc;
	final boolean isDynamic;
	final BufferedImage image;
	// eventually add video link/integration
	
	public String getName() {
		return name;
	}

	public muscle[] getMuscle() {
		return musc;
	}
	
	public ArrayList<muscle> getMuscleArrayList(){
		ArrayList<muscle> list = new ArrayList<>();
		for (muscle m : musc) {
			list.add(m);
		}
		return list;
	}

	public boolean isDynamic() {
		return isDynamic;
	}

	public BufferedImage getImage() {
		return image;
	}
}
