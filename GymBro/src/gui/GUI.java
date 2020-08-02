package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.*;

import main.Activity;
import main.DayType;
import main.ex;
import main.muscle;
import main.musclegroup;
import main.stretches;
import user.UserProfile;
import main.DaysList;
import main.GymDay;
import main.Pack;
import main.Pack.PackType;

public class GUI {

	protected static JFrame frame;
	private static DaysList list;
	private final static int numDaysShown = 7;
	private static JPanel cdl;
	private static JPanel EastPanel;
	private static MainPanel mainPanel;
	public static File savePoint, prefSavePoint, userSavePoint;
	private static JScrollPane scrollPane;
	private final static int SCROLL_SPEED = 12;
	private static LocalDate cdl_storedDate;
	protected static Object[] preferencesList;
	//these 3 final ints are used when making default object[] in Pack
	public static final int preferencesSize = 1;
	public static final int DaysListSize = 6;
	public static final int UserProfileSize = 8;
	private static UserProfile profile;

	public static void main(String[] args) {
		profile = null;
		// whatever you do, don't delete this for-loop
		// for some reason the muscle enum doesn't load fully without this.
		// REASON FOUND: because images are initialized by method call - java
		// does not automatically do this
		for (muscle m : musclegroup.back.getMuscles()) {
		}
		for (stretches s : stretches.values()) {
		}

		// GUI

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("BAD LOOKANDFEEL");
		}

		frame = new JFrame("Gym Bro");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 720);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BorderLayout());

		int fw = frame.getWidth();
		int fh = frame.getHeight();

		// Menu Bar
		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu help = new JMenu("Help");
		menubar.add(file);
		menubar.add(edit);
		menubar.add(help);

		// file
		JMenuItem file_new = new JMenuItem("New");
		JMenuItem file_save = new JMenuItem("Save");
		JMenuItem file_load = new JMenuItem("Load");
		JMenuItem file_exit = new JMenuItem("Exit");
		JMenuItem file_save_exit = new JMenuItem("Save and Exit");
		file.add(file_new);
		file.add(file_save);
		file.add(file_load);
		file.add(file_exit);
		file.add(file_save_exit);

		// edit
		JMenuItem edit_preferences = new JMenuItem("Preferences");
		edit.add(edit_preferences);

		// help
		JMenuItem help_about = new JMenuItem("About");
		JMenuItem help_credit = new JMenuItem("Credit");
		help.add(help_about);
		help.add(help_credit);

		frame.setJMenuBar(menubar);

		// Calendar Days

		cdl = new JPanel();
		cdl.setLayout(new GridLayout(numDaysShown, 1));

		// this gains access to the .txt document that would hold a directory to load
		// data from
		String currentDirFile = System.getProperty("user.dir");
		savePoint = new File(currentDirFile + "\\dir_sv.txt");
		prefSavePoint = new File(currentDirFile + "\\pref_sv.txt");
		userSavePoint = new File(currentDirFile + "\\user_sv.txt");

		// preferences list
		preferencesList = (Object[])Pack.ReadAllFromFile(prefSavePoint,PackType.Preferences);
		if (preferencesList[0] != null && (boolean) preferencesList[0]) {
			Pack.ReadAllFromFile(savePoint,PackType.DaysList);
		} else {
			Object[] responses = new String[] { "Load", "New" };
			int response = JOptionPane.showOptionDialog(new JFrame(),
					"Would you like to load a save or create a new calendar?", "Gym Bro Launch",
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, responses, responses[0]);
			if (response == 0) {
				Pack.ReadAllFromFile(savePoint,PackType.DaysList);
			}
		}
		
		// load user data if it exists
		Object tempO = ( Pack.ReadAllFromFile(userSavePoint, PackType.UserProfile) );
		if (tempO instanceof UserProfile) {
			profile = (UserProfile)tempO;
		}
		

		list = DaysList.getInstance();
		SetDayRoster();

		// western side of GUI
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(250, 500));
		west.setLayout(new GridBagLayout());

		cdl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		cdl.setMinimumSize(new Dimension(200, 500));
		cdl_storedDate = LocalDate.now();

		JPanel buttonPlatform = new JPanel();
		buttonPlatform.setBackground(Color.RED);
		buttonPlatform.setMinimumSize(new Dimension(50, 50));

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = GridBagConstraints.RELATIVE;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;

		JLabel calLabel = new JLabel("Gym Bro Calendar", SwingConstants.CENTER);
		calLabel.setFont(new Font("calLabel", Font.BOLD, 24));

		west.add(calLabel, c);

		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 7;
		west.add(cdl, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.weighty = 0;
		west.add(buttonPlatform, c);

		JPanel thisWeekPanel = new JPanel();
		thisWeekPanel.setBackground(Color.DARK_GRAY);
		JButton returnWeek = new JButton("Go to this Week");
		thisWeekPanel.add(returnWeek);
		west.add(thisWeekPanel, c);

		frame.add(BorderLayout.WEST, west);

		// Adding buttons to ButtonPlatform
		buttonPlatform.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		d.gridy = 0;
		d.gridx = GridBagConstraints.RELATIVE;

		JButton rightArrow = new JButton(">");
		JButton leftArrow = new JButton("<");
		rightArrow.setFocusable(false);
		leftArrow.setFocusable(false);
		buttonPlatform.add(leftArrow, d);
		buttonPlatform.add(rightArrow, d);

		// Main Menu Area
		mainPanel = new MainPanel();
		scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLL_SPEED);
		mainPanel.setAutoscrolls(true);
		frame.add(BorderLayout.CENTER, scrollPane);

		// set colors
		menubar.setOpaque(true);
		menubar.setBackground(Color.gray);

		// User Profile on East side
		EastPanel = new JPanel();
		EastPanel.add(CreateEastPanel());
		frame.add(BorderLayout.EAST, EastPanel);

		// set Frame visible
		frame.setVisible(true);

		returnWeek.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cdl_storedDate = LocalDate.now();
				cdl_arrow(0);
			}

		});

		rightArrow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUI.cdl_arrow(7);
			}

		});

		leftArrow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUI.cdl_arrow(-7);
			}

		});

		// ActionListeners for all buttons and options
		file_new.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] choices = new String[] { "Confirm", "Cancel" };
				int result = JOptionPane.showOptionDialog(new JFrame(),
						"Are you sure you want to create a new schedule?\nThis will overwrite any existing data.",
						"Gym Bro: New", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices,
						choices[1]);
				if (result == 0) {
					DaysList.getInstance().removeAll(DaysList.getInstance());
					GUI.ClearDayRoster();
					GUI.resetMainPanel();
					GUI.SetDayRoster();
					GUI.updateCDL();
					GUI.save();
				}
			}

		});

		file_save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				save();

			}

		});

		file_load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUI.load();

			}

		});

		file_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] exitResults = new String[] { "QUIT", "CANCEL" };
				int result = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to quit without saving?",
						"Gym Bro Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, exitResults,
						exitResults[1]);
				if (result == JOptionPane.YES_OPTION) {
					frame.dispose();
					System.exit(0);
				}
			}

		});

		file_save_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] exitResults = new String[] { "SAVE AND QUIT", "CANCEL" };
				int result = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to save and quit?",
						"Gym Bro Exit", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, exitResults,
						exitResults[1]);
				if (result == 0) {
					save();
					frame.dispose();
					System.exit(0);
				}

			}

		});

		edit_preferences.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame preferences = new JFrame("Gym Bro: Preferences");
				preferences.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				preferences.setSize(600, 600);

				JPanel pan = new JPanel();
				pan.setLayout(new GridBagLayout());
				GridBagConstraints f = new GridBagConstraints();
				f.gridx = 0;
				f.gridy = GridBagConstraints.RELATIVE;
				f.fill = GridBagConstraints.HORIZONTAL;
				f.anchor = GridBagConstraints.PAGE_START;
				f.weighty = 1;

				JLabel panLabel = new JLabel("Preferences Menu");
				panLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				panLabel.setFont(new Font("panLabel", Font.BOLD, 24));

				pan.add(panLabel, f);

				JCheckBox startupLoad;

				if (preferencesList[0] != null) {
					startupLoad = new JCheckBox("Automatically Load Save", (boolean) preferencesList[0]);
				} else {
					startupLoad = new JCheckBox("Automatically Load Save");
				}

				pan.add(startupLoad, f);

				JButton saveButton = new JButton("Save Preferences");
				pan.add(saveButton, f);

				preferences.add(pan);
				preferences.setVisible(true);

				saveButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Pack.WriteObjectToFile(preferencesList, prefSavePoint);
					}

				});

				startupLoad.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						preferencesList[0] = (e.getStateChange() == 1);
					}

				});

			}

		});

		help_about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});

		help_credit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(new JFrame("Credit"),
						"Freeware produced independently by Kyle Griffin\nCopyright 2020");

			}

		});

	}

	private static void changeStoredDate(int val) {
		if (val > 0) {
			cdl_storedDate = cdl_storedDate.plusDays(val);
		} else {
			// do this because not sure how plusDays and minusDays are specifically
			// implemented
			cdl_storedDate = cdl_storedDate.minusDays(Math.abs(val));
		}
	}

	private static void cdl_arrow(int val) {
		changeStoredDate(val);
		cdl.removeAll();
		GUI.SetDayRoster(cdl_storedDate);
		GUI.updateCDL();
	}

	static void save() {

		Pack.WriteObjectToFile(list, savePoint);

	}

	static void load() {
		ClearDayRoster();
		Pack.ReadAllFromFile(savePoint, PackType.DaysList);
		SetDayRoster();
		updateCDL();
		resetMainPanel();
	}

	protected static void updateCDL() {
		cdl.updateUI();
	}

	protected static void resetMainPanel() {
		mainPanel.removeAll();
		mainPanel.updateUI();
	}

	static void SetDayRoster() {
		LocalDate startingDate = LocalDate.now();
		SetDayRoster(startingDate);
	}

	protected static void SetDayRoster(LocalDate startingDate) {
		// Find the most recent Sunday's date
		LocalDate temp = startingDate.minusDays(DaysSinceSunday(startingDate.getDayOfWeek()));
		;
		for (int i = 0; i < numDaysShown; i++) {
			LocalDate currDate = temp.plusDays(i);
			GymDay find = new GymDay(currDate);
			if (list.contains(find)) {
				find = list.get(list.indexOf(find));
				cdl.add(find.createDayPanel());
			} else {
				list.add(find);
				cdl.add(find.createDayPanel());
			}

		}
	}

	/**
	 * This Method is to be used as follows - EastPanel.add( CreateEastPanel() );
	 * @return JPanel that serves as argument to the add method for EastPanel
	 */
	protected static JPanel CreateEastPanel() {
		JPanel pan = new JPanel(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();
		d.gridy = GridBagConstraints.RELATIVE;
		d.gridx = 0;
		d.weightx = 1;
		d.fill = GridBagConstraints.HORIZONTAL;
		d.anchor = GridBagConstraints.PAGE_START;
		d.insets = new Insets(0,0,25,0);

		pan.setPreferredSize(new Dimension(200, 200));

		// if a user profile hasn't been created yet
		if (profile == null) {

			JButton createProfile = new JButton("Create Profile");
			pan.add(createProfile);
			createProfile.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					UserCreation();
				}
			});

			return pan;
		} else {
			profile.establishImageFromByteArray();
		}
		// if a user profile has been created
		JPanel tightPanel = new JPanel(new GridBagLayout());
		tightPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		tightPanel.setBackground(Color.DARK_GRAY);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = GridBagConstraints.RELATIVE;
		c.gridx = 0;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(5,0,5,0);
		
		JLabel userIcon = new JLabel(new ImageIcon(profile.scaleProfImage(75,75)), SwingConstants.CENTER);
		userIcon.setPreferredSize(new Dimension(75,75));
		JButton editImage = new JButton("edit image");
		editImage.setPreferredSize(new Dimension(25,10));
		editImage.setFont(new Font("editImage", Font.ITALIC, 8));
		JLabel age = new JLabel(String.valueOf(profile.getAge()), SwingConstants.CENTER);
		Font userFont = new Font("userFont", Font.BOLD, 18);
		age.setFont(userFont);
		JLabel wholeName = new JLabel(profile.getFirst() + " " + profile.getLast(), SwingConstants.CENTER);
		JLabel height = new JLabel(profile.getHeight()/12 + "' " +profile.getHeight() % 12 + "''", SwingConstants.CENTER);
		height.setFont(userFont);
		wholeName.setFont(userFont);
		
		tightPanel.add(userIcon,c);
		tightPanel.add(editImage, c);
		tightPanel.add(wholeName,c);
		tightPanel.add(age,c);
		tightPanel.add(height, c);
		
		pan.add(tightPanel, d);
		
		editImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal = jfc.showOpenDialog(new JFrame());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String pathname = jfc.getSelectedFile().getAbsolutePath();
					profile.setImage(pathname);
					profile.scaleProfImage(100, 100);
				}
			}
			
		});
		
		return pan;
	}

	private static void UserCreation() {
		JFrame userFrame = new JFrame("User Creation");
		userFrame.setResizable(false);
		userFrame.setLayout(new BorderLayout());
		JPanel userPanel = new JPanel(new GridBagLayout());
		userFrame.add(BorderLayout.CENTER, userPanel);
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = GridBagConstraints.RELATIVE;
		c.gridx = 1;
		c.weightx = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(10, 0, 0, 30);

		JLabel userPic = new JLabel(new ImageIcon(UserProfile.getDefaultImage()));
		JLabel userLabel = new JLabel("User Creation", SwingConstants.CENTER);
		JTextField firstField = new JTextField(20);
		JTextField lastField = new JTextField(20);
		JTextField ageField = new JTextField(20); // maybe make options
		JTextField heightField = new JTextField(20);
		JComboBox<user.UserProfile.gender> genderField = new JComboBox<>(user.UserProfile.gender.values());
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		JButton create = new JButton("Create");
		JButton cancel = new JButton("Cancel");

		buttonPanel.add(create);
		buttonPanel.add(cancel);

		userPanel.add(userPic, c);
		userPanel.add(userLabel, c);
		userPanel.add(firstField, c);
		userPanel.add(lastField, c);
		userPanel.add(ageField, c);
		userPanel.add(heightField, c);
		userPanel.add(genderField, c);
		userPanel.add(buttonPanel, c);

		c.insets = new Insets(10, 0, 0, 10);
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 2;
		JLabel firstLabel = new JLabel("First Name:", SwingConstants.RIGHT);
		userPanel.add(firstLabel, c);

		c.gridy++;
		JLabel lastLabel = new JLabel("Last Name:", SwingConstants.RIGHT);
		userPanel.add(lastLabel, c);

		c.gridy++;
		JLabel ageLabel = new JLabel("Age:", SwingConstants.RIGHT);
		userPanel.add(ageLabel, c);

		c.gridy++;
		JLabel heightLabel = new JLabel("Height (in.):", SwingConstants.RIGHT);
		userPanel.add(heightLabel, c);
		
		c.gridy++;
		JLabel genderLabel = new JLabel("Sex:", SwingConstants.RIGHT);
		userPanel.add(genderLabel, c);

		userPanel.setPreferredSize(new Dimension(400, 600));
		userFrame.setSize(400, 600);
		userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		userFrame.setVisible(true);
		userFrame.revalidate();

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userFrame.dispose();
			}

		});

		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				profile = new UserProfile(firstField.getText(), lastField.getText(),
						Integer.parseInt(ageField.getText()), Integer.parseInt(heightField.getText()) , (user.UserProfile.gender) genderField.getSelectedItem());
				EastPanel.removeAll();
				EastPanel.add(CreateEastPanel());
				EastPanel.revalidate();
				EastPanel.updateUI();
				userFrame.dispose();
				Pack.WriteObjectToFile(profile, userSavePoint);
			}

		});
	}

	static void ClearDayRoster() {
		cdl.removeAll();
	}

	static int DaysSinceSunday(DayOfWeek curr) {
		if (curr.equals(DayOfWeek.SUNDAY)) {
			return 0;
		} else if (curr.equals(DayOfWeek.MONDAY)) {
			return 1;
		} else if (curr.equals(DayOfWeek.TUESDAY)) {
			return 2;
		} else if (curr.equals(DayOfWeek.WEDNESDAY)) {
			return 3;
		} else if (curr.equals(DayOfWeek.THURSDAY)) {
			return 4;
		} else if (curr.equals(DayOfWeek.FRIDAY)) {
			return 5;
		} else {
			return 6;
		} // SATURDAY
	}

	/*
	 * Method called from DayPanel object's edit/view button. This checks if the day
	 * has already been saved in the list variable.
	 */
	protected static void SetMainPanel(GymDay day) {

		// if the selected day has type REST, show a drop down that will change the
		// selected day's type.
		DayType currType = DayType.REST;
		if (day.getType().equals(DayType.REST)) {
			JFrame dropDown = new JFrame("Type of Day Choice");
			dropDown.setAlwaysOnTop(true);
			currType = (DayType) JOptionPane.showInputDialog(frame, "Choose the Type of Day", "MENU",
					JOptionPane.PLAIN_MESSAGE, null, DayType.values(), DayType.REST);

			if (currType == null || currType == DayType.REST) {
				currType = DayType.REST;
				dropDown.dispose();
				return;
			}
			day.setType(currType);
			for (int i = 0; i < numDaysShown; i++) {
				Component c = cdl.getComponent(i);
				if (c instanceof DayPanel) {
					if (((DayPanel) c).date.equals(day.getDate())) {
						((DayPanel) c).updateType(currType);
					}

				}

			}

		}

		mainPanel.Change(day);
		mainPanel.repaint();
		mainPanel.setVisible(true);
	}

}
