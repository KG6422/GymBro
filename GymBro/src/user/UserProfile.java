package user;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GymDay;

public class UserProfile implements Serializable {
	
	private static final long serialVersionUID = -3537489308419878470L;
	private String first, last;
	private ArrayList<WeightDay> weightList;
	private int age, height;
	private gender gen;
	private transient BufferedImage image;
	private transient static BufferedImage defaultImage;
	private byte[] imageData;
	private LocalDate profileCreation;

	public enum gender {
		male("male"), female("female");

		private String name;

		gender(String name) {
			this.name = name;
		}
	}

	public static BufferedImage getDefaultImage() {
		return createImage("defprof.png");
	}

	public UserProfile(String first, String last, int age, int height, gender gen) {
		defaultImage = getDefaultImage();
		this.first = first;
		this.last = last;
		this.age = age;
		this.height = height;
		this.gen = gen;
		setImage(defaultImage);
		
		profileCreation = LocalDate.now();
	}

	private static BufferedImage createImage(String pathname) {
		try {
			BufferedImage image = ImageIO.read(UserProfile.class.getResourceAsStream(pathname));
			return image;
		} catch (IOException e) {
			System.out.println("IO EXCEPTION!");
			e.printStackTrace();
		}
		return new BufferedImage(200, 185, BufferedImage.OPAQUE);

	}

	public BufferedImage scaleProfImage(int width, int height) {
		BufferedImage res = new BufferedImage(width, height, BufferedImage.OPAQUE);
		Graphics2D g2 = res.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(image, 0, 0, width, height, null);
		g2.dispose();
		return res;
	}
	
	/**
	 * Method that converts the serializable byte array into a bufferedimage
	 */
	public void establishImageFromByteArray() {
		byte[] array = imageData;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(array);
			image = ImageIO.read(bais);
			bais.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setImage(String pathname) {
		setImage(createImage(pathname));
	}

	public void setImage(BufferedImage file) {
		image = file;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			baos.flush();
			imageData = baos.toByteArray();

			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getHeight() {
		return height;
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

	public BufferedImage getImage() {
		return image;
	}
}
