package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gui.GUI;

public class Pack implements Serializable {

	private static final long serialVersionUID = -637880063550441284L;

	static File saveFile;

	/**
	 * 
	 * @return boolean val if save was complete
	 * @deprecated use WriteObjectToFile() instead.
	 */
	@Deprecated
	public static boolean Save() {
		// if there is no savepoint then create one
		String currentDirFile = System.getProperty("user.dir");
		saveFile = new File(currentDirFile + "\\dir_sv");

		return true;
	}
	
	public static void WriteObjectToFile(Object o, File inFile) {
		WriteObjectToFile(o, inFile, true);
	}

	private static void WriteObjectToFile(Object o, File inFile, boolean showSaving) {

		try {
			FileOutputStream fileOut = new FileOutputStream(inFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(o);
			objectOut.close();
			if (showSaving) {
				JOptionPane.showMessageDialog(new JFrame(), "Saving Complete", "Gym Bro: Save Message",
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Object[] ReadPreferencesFromFile(File inFile) {
		if (inFile == null) {
			JOptionPane.showMessageDialog(new JFrame(), "Unable to Load");
			return new Object[GUI.preferencesSize];
		}
		try {
			FileInputStream fileIn = new FileInputStream(inFile);
			if (fileIn.available() == 0) {
				fileIn.close();
				WriteObjectToFile(new Object[] {false}, inFile, false);
				return new Object[GUI.preferencesSize];
			}
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			Object[] returnable = (Object[])objectIn.readObject();
			objectIn.close();
			return returnable;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Object[GUI.preferencesSize];
	}

	public static void ReadObjectFromFile(File inFile) {
		if (inFile == null) {
			JOptionPane.showMessageDialog(new JFrame(), "Unable to Load");
			return;
		}
		try {

			FileInputStream fileIn = new FileInputStream(inFile);
			if (fileIn.available() == 0) {
				JOptionPane.showMessageDialog(new JFrame(), "No data to load.");
				fileIn.close();
				return;
			}
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			LinkedList<GymDay> obj = (DaysList) objectIn.readObject();
			objectIn.close();
			DaysList.getInstance().removeAll(DaysList.getInstance());
			for (GymDay o : obj) {
				DaysList.getInstance().addLast(o);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
