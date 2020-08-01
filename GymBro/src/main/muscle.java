package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public enum muscle implements Serializable {

	abdominals_upper("upper abdominals", "The upper section of the abs... commonly referred to as the 6 pack.",
			getImage("ABDOMINALS.png"), musclegroup.core),
	abdominals_middle("middle abdominals", "The middle section of the abs... commonly referred to as the 6 pack.",
			getImage("ABDOMINALS.png"), musclegroup.core),
	abdominals_lower("lower abdominals", "The lower section of the abs... commonly referred to as the 6 pack.",
			getImage("ABDOMINALS.png"), musclegroup.core),
	biceps_longhead("bicep long head", "Located on the front of the arm. This is the most visible bicep muscle.",
			getImage("BICEP.png"), musclegroup.biceps),
	biceps_shorthead("bicep short head",
			"Located on the inner side of the arm. Responsible for flexing and supinating the elbow joint.",
			getImage("BICEP.png"), musclegroup.biceps),
	biceps_brachialis("brachialis",
			"This is located underneath the bicep and can be difficult to target directly. This pushes out all arm muscles.",
			getImage("BICEP.png"), musclegroup.biceps),
	calves_gastrocnemius("calves gastrocnemius",
			"The U shape of the calves. Lift the body when in an upright position.", getImage("CALVES.png"),
			musclegroup.legs),
	calves_soleus("calves soleus", "Underneath the gastrocnemius. Lift the body when the knees are bent.",
			getImage("CALVES.png"), musclegroup.legs),
	deltoids_anterior("anterior deltoids",
			"The frontal section of the shoulder. Responsible for moving the arm away from the body towards the front.",
			getImage("ANTERIOR DELTS.png"), musclegroup.shoulders),
	deltoids_lateral("lateral deltoids",
			"The lateral section of the shoulder. Responsible for moving the arm away from the body laterally.",
			getImage("LATERAL DELTS.png"), musclegroup.shoulders),
	deltoids_posterior("posterior deltoids",
			"The posterior section of the shoulder. Responsible for moving the arm away from the body to the rear.",
			getImage("POSTERIOR DELTS.png"), musclegroup.shoulders),
	lowerback("erector spinae", "The muscles that make up the lower back. This is important for core strength.",
			getImage("ERECTOR SPINAE.png"), musclegroup.back),
	gluteus_maximus("gluteus maximus",
			"The largest muscle covering the butt. This is responsible for all sorts of leg and lower body movements.",
			getImage("GLUTEUS MAXIMUS.png"), musclegroup.legs),
	gluteus_medius("gluteus medius",
			"Located at the upper part of the buttocks attached to the pelvis. Abducts/rotates the thigh.",
			getImage("GLUTEUS MEDIUS.png"), musclegroup.legs),
	gluteus_minimus("gluteus minimus", "Located on the side of the butt. This extends the hip.",
			getImage("GLUTEUS MEDIUS.png"), musclegroup.legs),
	hips_adductors("hip adductors",
			"Located on the inner thigh. There are several muscles that make up this area. This brings the leg towards the body laterally.",
			getImage("HIP ADDUCTORS.png"), musclegroup.legs),
	hips_flexors("hip flexors",
			"Located on the frontal hip area. Responsible for bringing the knee up towards the body.",
			getImage("HIP FLEXORS.png"), musclegroup.legs),
	hips_outer("Tensor fasciae latae",
			"The outer area of the thigh. Responsible for moving the leg in a walking/swimming motion.",
			getImage("TENSOR FASCIAE LATAE.png"), musclegroup.legs),
	infraspinatus("infraspinatus",
			"Located on the upper back. Aids with the movement of the shoulder joint and protects the shoulder joint.",
			getImage("INFRASPINATUS.png"), musclegroup.back),
	lats("lats", "Located on the back underneath the arms. Useful for many movements with the spine and shoulder.",
			getImage("LATS.png"), musclegroup.back),
	hamstrings_semitendinosus("semitendinosus", "Posterior of the hamstring.", getImage("HAMSTRING.png"),
			musclegroup.legs),
	hamstrings_semimembranosus("semimembranosus",
			"Located on the medial side of the hamstring from the hip to the knee.", getImage("HAMSTRING.png"),
			musclegroup.legs),
	hamstrings_biceps_femoris("bicep femoris",
			"The long head of the hamstring thatflexes the knee joint and laterally rotates the knee.",
			getImage("HAMSTRING.png"), musclegroup.legs),
	sartorius("sartorius", "Extends down the entirety of the inner thigh. Helps with the flexion of the hip.",
			getImage("SARTORIUS.png"), musclegroup.legs),
	tibialis_anterior("anterior tibialis",
			"Opposite of the calves, located on the front of your lower leg. Assists with walking and balance.",
			getImage("TIBIALIS ANTERIOR.png"), musclegroup.legs),
	obliques_external("external obliques",
			"Located on the outer surface of the abdomen. They are responsible for the twisting of the body.",
			getImage("OBLIQUES.png"), musclegroup.core),
	obliques_internal("internal obliques",
			"Located on the inner edge of the abdomen. They help support the abdominal wall.", getImage("OBLIQUES.png"),
			musclegroup.core),
	pecs_upper("upper pecs",
			"The upper fibers of the chest that move from the collar bone area to where the arm meets the pelvis.",
			getImage("PECS.png"), musclegroup.chest),
	pecs_lower("lower pecs",
			"The lower fibers of the chest that move to the bottom of the chest from where the arm meets the pelvis.",
			getImage("PECS.png"), musclegroup.chest),
	pecs_middle("middle pecs",
			"The middle fibers of the chest that move horizontally from where the arm meets the pelvis.",
			getImage("PECS.png"), musclegroup.chest),
	pecs_outer("outer pecs",
			"The area of the chest that is closest to the arms. Responsible for lifting with wide grips.",
			getImage("PECS.png"), musclegroup.chest),
	quadriceps_rectus_femoris("rectus femoris",
			"Part of the quads. Located in the middle of the front of the thigh. Responsible for knee extension and hip/thigh flexion.",
			getImage("QUADS.png"), musclegroup.legs),
	quadriceps_vastus_lateralis("vastus lateralis",
			"Located on the lateral side of the thigh and is the largest of the four quad muscles.",
			getImage("QUADS.png"), musclegroup.legs),
	quadriceps_vastus_medialis("vastus medialis",
			"Part of the quads. Located on the medial/inner side of the thigh. Responsible for extending the entire thigh.",
			getImage("QUADS.png"), musclegroup.legs),
	quadriceps_vastus_intermedius("vastus intermedius",
			"Part of the quads. Sits under the rectus femoris and is on the front and lateral surfaces of the femur.",
			getImage("QUADS.png"), musclegroup.legs),
	serratus_anterior("serratus anterior",
			"Located at the edge of the top 8 ribs underneath the pelvis. This helps pull the shoulder around the thorax.",
			getImage("SERRATUS ANTERIOR.png"), musclegroup.back),
	teres_minor("teres minor", "Narrow muscle connected to the shoulder. Aids with the rotator cuff.",
			getImage("TERES.png"), musclegroup.back),
	teres_major("teres major", "Connects the shoulder to the back. Aids with shoulder movement.", getImage("TERES.png"),
			musclegroup.back),
	traps_upper("upper traps",
			"Traps connect the back of the neck to the shoulder as well as the lower back. The upper fibers elevate and rotate the scapula and extend the neck.",
			getImage("TRAPS.png"), musclegroup.back),
	traps_middle("middle traps",
			"Traps connect the back of the neck to the shoulder as well as the lower back. The middle fibers retract the scapula.",
			getImage("TRAPS.png"), musclegroup.back),
	traps_lower("lower traps",
			"Traps connect the back of the neck to the shoulder as well as the lower back. The lower fibers depress the scapula and also aid the upper fibers.",
			getImage("TRAPS.png"), musclegroup.back),
	triceps_longhead("triceps long head",
			"Located on the back of the arm. This is the largest muscle that sits on the posterior.",
			getImage("TRICEPS.png"), musclegroup.triceps),
	triceps_lateralhead("triceps lateral head",
			"Located on the outer side of the arm. This is the most visible tricep muscle.", getImage("TRICEPS.png"),
			musclegroup.triceps),
	triceps_medialhead("triceps medial head", "Located on the inner side of the arm. This is a much smaller muscle.",
			getImage("TRICEPS.png"), musclegroup.triceps),
	forearm("forearm",
			"Located between the wrist and elbow. There are many muscles that make up the forearms. This is a secondary muscle.",
			getImage("FOREARMS.png"), musclegroup.biceps);

	private static final long serialVersionUID = 231284109248L;

	private muscle(final String itsname, final String itsdescription, BufferedImage itsimage,
			musclegroup inmusclegroup) {
		this.name = itsname;
		this.description = itsdescription;
		this.image = itsimage;
		this.muscleGroup = inmusclegroup;
	}

	private static BufferedImage getImage(String pathname) {
		try {
			BufferedImage image = ImageIO.read(GymDay.class.getResourceAsStream(pathname));
			return image;
		} catch (IOException e) {
			System.out.println("IO EXCEPTION!");
			e.printStackTrace();
		}
		return new BufferedImage(373, 331, BufferedImage.TYPE_INT_RGB);

	}

	public String getName() {
		return name;
	}

	String getDescription() {
		return description;
	}

	public BufferedImage getImage() {
		return image;
	}
	
	public ArrayList<stretches> getStretches(){
		ArrayList<stretches> list = new ArrayList<>();
		for (stretches s : stretches.values()) {
			if (s.getMuscleArrayList().contains(this)) {
				list.add(s);
			}
		}
		return list;
	}
	
	public static muscle[] quads() {
		return new muscle[] {quadriceps_rectus_femoris, quadriceps_vastus_lateralis,quadriceps_vastus_medialis,quadriceps_vastus_intermedius};
	}
	
	public static muscle[] hams() {
		return new muscle[] {hamstrings_biceps_femoris,hamstrings_semitendinosus,hamstrings_semimembranosus};
	}
	
	public static muscle[] shoulder() {
		return new muscle[] {deltoids_anterior, deltoids_posterior, deltoids_lateral};
	}
	
	public static muscle[] traps() {
		return new muscle[] {traps_upper, traps_middle, traps_lower};
	}
	
	public static muscle[] pecs() {
		return new muscle[] {pecs_upper, pecs_middle, pecs_lower, pecs_outer};
	}

	public static muscle[] triceps() {
		return new muscle[] { triceps_longhead, triceps_lateralhead, triceps_medialhead };
	}

	public static muscle[] biceps() {
		return new muscle[] { biceps_longhead, biceps_shorthead, biceps_brachialis };
	}

	public musclegroup getMuscleGroup() {
		return muscleGroup;
	}

	private String name;
	private String description;
	private BufferedImage image;
	private musclegroup muscleGroup;
}
