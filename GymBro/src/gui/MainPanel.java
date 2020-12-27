package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import main.Activity;
import main.DaysList;
import main.GymDay;
import main.MuscleWork;
import main.ex;
import main.muscle;
import main.stretches;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = -8438576029794021570L;

	private final String[] headers = new String[] { "Activity", "Sets", "Reps", "Weight (lbs)", "KRP", "Muscles", "" };

	private GymDay day;

	private JFrame addFrame;

	private boolean isAdding;

	private JPanel musclePanel;
	private JPanel extraMusclePanel;

	private ArrayList<MuscleWork> mWork = new ArrayList<>(); // primary muscles list

	private ArrayList<MuscleWork> mWork2 = new ArrayList<>(); // secondary muscles list

	// calling this method does not allow for autosave
	public MainPanel() {
		day = new GymDay(LocalDate.now());
		// does nothing, done so that only one instance of MainPanel is created
		// singleton wouldn't work because data will change within while user interacts
		// w the program
	}

	// can also implement autosave when Change is called
	public void Change(GymDay inDay) {
		if (day != null) {
			// save old day
		}
		this.removeAll();
		this.setAutoscrolls(true);
		day = inDay;

		GridBagConstraints c = new GridBagConstraints();

		this.setLayout(new GridBagLayout());

		c.insets = new Insets(75, 0, 0, 0);
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		c.gridy = GridBagConstraints.RELATIVE;
		c.gridx = 5;
		c.ipady = 2;
		c.weightx = 0;
		c.weighty = 1;
		c.gridwidth = 1;

		JLabel typeText = new JLabel(day.getType().toString() + " DAY");
		typeText.setMinimumSize(new Dimension(399, 60));
		typeText.setHorizontalAlignment(JTextField.CENTER);
		typeText.setFont(new Font("TypeTitle", Font.BOLD, 72));
		add(typeText, c);

		c.insets = new Insets(0, 0, 25, 0);
		// c.gridy = 1;
		JLabel dateText = new JLabel(day.getDate().toString());
		dateText.setMinimumSize(new Dimension(300, 50));
		dateText.setHorizontalAlignment(JTextField.CENTER);
		dateText.setFont(new Font("DateTitle", Font.ITALIC, 36));
		add(dateText, c);

		c.insets = new Insets(0, 0, 0, 0);
		// c.gridy = 2;
		c.ipadx = 250;
		// c.gridwidth = 3;

		DefaultTableModel tableModel = new DefaultTableModel(day.toObjectArray(), headers) {

			private static final long serialVersionUID = -3983237186735807415L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 5 || column == 6) {
					return true;
				}
				return false;
			}

		};

		// DefaultTableModel model = new DefaultTableModel(day.toObjectArray(),
		// headers);
		JTable chart = new JTable(tableModel);

		// add buttons
		AbstractAction delete = new AbstractAction() {

			private static final long serialVersionUID = -3573260231344067336L;

			@Override
			public void actionPerformed(ActionEvent e) {
				GymDay thisDay = day;
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				((DefaultTableModel) table.getModel()).removeRow(modelRow);
				day.RemoveActivity(modelRow);
				musclePanel = dealWithMusclePanel();
				GUI.resetMainPanel();
				GUI.SetMainPanel(thisDay);
			}

		};
		AbstractAction showMuscles = new AbstractAction() {

			private static final long serialVersionUID = 8747881502983011889L;

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				// add panels for all muscles here
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setSize(700, 700);

				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(3, 0));

				muscle[] list = MainPanel.this.day.getActivity(modelRow).getMuscleList();
				for (muscle m : list) {
					panel.add(new JLabel(m.getName(), new ImageIcon(m.getImage()), JLabel.CENTER));
				}
				frame.add(panel, BorderLayout.CENTER);
				JButton close = new JButton("CLOSE");
				frame.add(close, BorderLayout.SOUTH);

				frame.revalidate();
				frame.setVisible(true);

				close.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}

				});
			}
		};

		// these are the delete buttons, don't remove
		@SuppressWarnings("unused")
		ButtonColumn bcDelete = new ButtonColumn(chart, delete, 6);
		@SuppressWarnings("unused")
		ButtonColumn bcInfo = new ButtonColumn(chart, showMuscles, 5);

		JScrollPane chartScroll = new JScrollPane(chart);
		chartScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		chartScroll.setMinimumSize(new Dimension(250, 500));
		chartScroll.setEnabled(false);
		add(chartScroll, c);

		c.insets = new Insets(5, 0, 10, 0);
		JButton addActivity = new JButton("Add Activity");
		addActivity.setMinimumSize(new Dimension(150, 50));
		add(addActivity, c);

		c.ipadx = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		JLabel muscleLabel = new JLabel("Muscular Analysis", SwingConstants.CENTER);
		muscleLabel.setFont(new Font("MuscleTitle", Font.ITALIC, 36));
		muscleLabel.setMinimumSize(new Dimension(250, 40));
		add(muscleLabel, c);

		c.weightx = 0;
		c.insets = new Insets(0, 0, 0, 0);

		// musclepanel
		add(musclePanel = dealWithMusclePanel(), c);
		musclePanel.updateUI();
		
		// extraMusclePanel initialized in the dealWithMusclePanel() method
		JLabel extraLabel1 = new JLabel("Extra Muscles Worked", SwingConstants.CENTER);
		extraLabel1.setFont(new Font("Extra Label", Font.BOLD, 36));
		c.insets = new Insets(10, 0, 10, 0);
		add(extraLabel1, c);
		c.insets = new Insets(0, 0, 0, 0);
		
		add(extraMusclePanel, c);

		JLabel stretchesLabel = new JLabel("Applicable Stretches", SwingConstants.CENTER);
		stretchesLabel.setFont(new Font("stretches Label", Font.BOLD, 36));
		add(stretchesLabel, c);
		
		JPanel stretchesPanel = returnableStretchesPanel();
		add(stretchesPanel, c);
		
		c.insets = new Insets(10, 0, 0, 0);
		JButton changeDayTypeButton = new JButton("Change the Type of Day");
		changeDayTypeButton.setBackground(Color.PINK);
		add(changeDayTypeButton, c);

		this.setBackground(Color.DARK_GRAY);
		this.updateUI();

		isAdding = false;
		addActivity.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (isAdding) {
					return;
				}
				isAdding = true;
				AddPanel panel = new AddPanel(MainPanel.this);

				MainPanel.this.addFrame = new JFrame("ADD NEW ACTIVITY");
				MainPanel.this.addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				MainPanel.this.addFrame.setSize(700, 700);
				MainPanel.this.addFrame.setResizable(false);

				MainPanel.this.addFrame.add(panel);

				musclePanel.updateUI();

				MainPanel.this.addFrame.setVisible(true);
				MainPanel.this.addFrame.setAlwaysOnTop(true);

			}

		});

		changeDayTypeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] responses = new String[] { "Reset Day and Save", "Cancel" };
				int response = JOptionPane.showOptionDialog(new JFrame(),
						"Changing the Type of Day will delete all activities for the day and reset it.\nGym Bro will also save all unsaved data.",
						"Gym Bro Override", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, responses,
						responses[1]);
				if (response == 0) {
					// reset the gymday and mainPanel in GUI
					DaysList.getInstance().remove(day);
					GUI.save();
					GUI.load(); // inefficient
				}
			}

		});
	}

	protected void disposeFrame() {
		addFrame.dispose();
		isAdding = false;
	}

	/**
	 * Index 0 : String name of exercise Index 1 : Sets in String form Index 2 :
	 * Reps in String form Index 3 : Weight in String form Index 4 : null - KRP
	 * calculation Index 5 : muscles in muscle[] form Index 6 : null - Eventual
	 * Delete Button
	 * 
	 * This method reads all of the data from the AddPanel choicemenu and translates
	 * it into the correct data types. Afterwards it is sent to the associated
	 * GymDay method as a new activity.
	 * 
	 * @param info : The messily sorted information sent from the user's interaction
	 *             to AddPanel
	 */
	@SuppressWarnings("unused")
	protected void sendAddedInfo(Object[] info) {
		// sort index 0 - String name
		String i0 = (String) info[0];
		// sort index 1 - sets in String form
		int i1 = Integer.parseInt((String) info[1]);
		// sort index 2 - Reps in int[] form
		int[] i2;
		String temp2 = (String) info[2];
		temp2 += ",";
		if (temp2.contains(",")) {
			int numCommas = 0;
			for (int i = 0; i < temp2.length(); i++) {
				// find number of commas to initialize the array
				if (temp2.charAt(i) == ',') {
					numCommas++;
				}
			}
			i2 = new int[numCommas];
			int index = 0;
			String curr = "";
			for (int i = 0; i < temp2.length(); i++) {
				if (temp2.charAt(i) != ',' && temp2.charAt(i) != ' ') {
					curr += temp2.charAt(i);
				} else {
					i2[index++] = Integer.parseInt(curr);
					curr = "";
				}
			}

		} else {
			i2 = new int[] { Integer.parseInt(temp2) };
		}

		// sort index 3 - Weight in int[] form
		int[] i3;
		String temp3 = (String) info[3];
		temp3 += ",";
		if (temp3.contains(",")) {
			int numCommas = 0;
			for (int i = 0; i < temp3.length(); i++) {
				// find num of commas same as before
				if (temp3.charAt(i) == ',') {
					numCommas++;
				}
			}
			i3 = new int[numCommas];
			int index = 0;
			String curr = "";
			for (int i = 0; i < temp3.length(); i++) {
				if (temp3.charAt(i) != ',' && temp3.charAt(i) != ' ') {
					curr += temp3.charAt(i);
				} else {
					i3[index++] = Integer.parseInt(curr);
					curr = "";
				}
			}
		} else {
			i3 = new int[] { Integer.parseInt(temp3) };
		}

		// sort index 5 - muscle[] list
		muscle[] i5 = (muscle[]) info[5];
		// fill in the rest
		int i4 = 0;
		JButton i6 = new JButton("X");

		// Send packed data to GymDay into a new Activity
		Activity a = new Activity(i2, i3, i1, ex.findFromName(i0));
		a.addMuscleList(i5);
		day.AddActivity(a);
		this.revalidate();
		this.updateUI();
		this.Change(day);
	}

	/**
	 * Method that creates a Panel with all of the muscles that should be hit based
	 * on the type of day. Red Muscles are not hit at all, yellow are only hit as
	 * secondary muscles, and green means that it is being hit as a primary muscle
	 * at least once.
	 * 
	 * @return JPanel holding the muscle info
	 */
	private JPanel dealWithMusclePanel() {
		JPanel pan = new JPanel();

		muscle[] list = day.getType().getEnclosedMuscles();

		// inefficient, may want to consider saving the arrayList within the Day Object.

		// leftovers arraylist holds all the muscles
		// while the mWork arrays are being built, each muscle that is included in the
		// activities will be removed from the leftovers arraylist
		ArrayList<MuscleWork> leftovers = new ArrayList<>();
		ArrayList<MuscleWork> leftovers2 = new ArrayList<>();

		mWork = new ArrayList<>();
		mWork2 = new ArrayList<>();
		for (int i = 0; i < day.getActivitySize(); i++) {
			Activity a = day.getActivity(i);
			for (muscle m : a.getMuscleList()) {
				MuscleWork mw = new MuscleWork(m, 1, a.calculateKRP());
				if (mWork.contains(mw)) {
					MuscleWork newmw = mWork.get(mWork.indexOf(mw));
					newmw.incrementCount(mw.getCount());
					newmw.incrementKRP(mw.getKRP());
				} else {
					mWork.add(mw);
				}
			}
			for (muscle m : a.getSecondaryMuscleList()) {
				MuscleWork mw = new MuscleWork(m, 1, a.calculateKRP());
				if (mWork2.contains(mw)) {
					MuscleWork newMW = mWork2.get(mWork2.indexOf(mw));
					newMW.incrementCount(mw.getCount());
					newMW.incrementKRP(mw.getKRP());
				} else {
					mWork2.add(mw);
				}
			}
		}

		// initialize leftovers
		for (MuscleWork mw : mWork) {
			leftovers.add(mw);
		}
		for (MuscleWork mw : mWork2) {
			leftovers2.add(mw);
		}

		pan.setLayout(new GridLayout((list.length + 1) / 2, 2));

		int panelHeight = list.length * 200;

		for (muscle m : list) {
			JPanel iconPanel = new JPanel(new BorderLayout());
			iconPanel.add(BorderLayout.CENTER, new JLabel(new ImageIcon(m.getImage())));

			JLabel jTitle = new JLabel(m.getName(), JLabel.CENTER);
			jTitle.setFont(new Font("LABEL", Font.BOLD, 18));
			jTitle.setText(jTitle.getText().toUpperCase());

			iconPanel.add(BorderLayout.NORTH, jTitle);

			// check mWork to see how much the muscle has been worked.
			int currKRP = 0;
			int currCount = 0;
			Color currColor = Color.RED;
			for (MuscleWork mw : mWork2) {
				if (mw.getMuscle().equals(m)) {
					currKRP += mw.getKRP();
					currCount += mw.getCount();
					currColor = Color.YELLOW;
					leftovers2.remove(mw);
					leftovers.remove(mw);
				}
			}
			for (MuscleWork mw : mWork) {
				if (mw.getMuscle().equals(m)) {
					currKRP += mw.getKRP();
					currCount += mw.getCount();
					currColor = Color.GREEN;
					leftovers.remove(mw);
					leftovers2.remove(mw);
				}
			}

			iconPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			iconPanel.setBackground(currColor);
			iconPanel.add(BorderLayout.SOUTH,
					new JLabel("Worked " + currCount + " times : KRP " + currKRP, SwingConstants.CENTER));

			pan.add(iconPanel);
		}

		pan.setBackground(Color.GRAY);
		pan.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		pan.setPreferredSize(new Dimension(150, panelHeight));

		pan.setVisible(true);
		pan.revalidate();

		extraMusclePanel = DealWithExtraMusclePanel(leftovers, leftovers2);
		extraMusclePanel.revalidate();
		extraMusclePanel.updateUI();
		extraMusclePanel.setVisible(true);

		return pan;
	}

	private JPanel DealWithExtraMusclePanel(ArrayList<MuscleWork> leftovers, ArrayList<MuscleWork> leftovers2) {
		JPanel pan = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.weighty = 1;
		c.insets = new Insets(0, 0, 0, 0);
		c.gridy = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.BOTH;

		JLabel panPrimLabel = new JLabel("Primary Muscles", SwingConstants.CENTER);
		panPrimLabel.setFont(new Font("panPrim", Font.BOLD, 24));

		JPanel panPrim = new JPanel(new GridLayout(0, 2));
		panPrim.setPreferredSize(new Dimension(150, leftovers.size() * 200));
		for (int i = 0; i < leftovers.size(); i++) {
			panPrim.add(returnableMuscleIcon(leftovers.get(i)));
		}

		JLabel panSecLabel = new JLabel("Secondary Muscles", SwingConstants.CENTER);
		panSecLabel.setFont(new Font("panSec", Font.BOLD, 24));

		JPanel panSec = new JPanel(new GridLayout(0, 2));
		panSec.setPreferredSize(new Dimension(150, leftovers2.size() * 200));

		for (int i = 0; i < leftovers2.size(); i++) {
			panSec.add(returnableMuscleIcon(leftovers2.get(i)));
		}

		pan.add(panPrimLabel, c);
		if (leftovers.size() == 0) {
			pan.add(new JLabel("NO DATA", SwingConstants.CENTER), c);
		}
		pan.add(panPrim, c);
		pan.add(panSecLabel, c);
		if (leftovers2.size() == 0) {
			pan.add(new JLabel("NO DATA", SwingConstants.CENTER), c);
		}
		pan.add(panSec, c);

		int panelHeight = (leftovers.size() + leftovers2.size()) * 200;

		if (panelHeight < 200) {
			System.out.println("LESS THAN 200");
			panelHeight = 200;
		}
		pan.setPreferredSize(new Dimension(150, panelHeight));
		return pan;
	}

	/**
	 * Returns a Muscle Icon JPanel - fits the requirement for the Primary/Secondary
	 * Extra Muscles
	 * 
	 * @param mw the current MW - usually held within a list
	 * @return JPanel that formats the MW into a viewable form
	 */
	private JPanel returnableMuscleIcon(MuscleWork mw) {
		JPanel pan = new JPanel(new BorderLayout());

		muscle m = mw.getMuscle();
		JPanel iconPanel = new JPanel(new BorderLayout());
		JLabel iconLabel = new JLabel(m.getName().toUpperCase(), SwingConstants.CENTER);
		iconLabel.setFont(new Font("icon Label", Font.BOLD, 16));
		JLabel iconMuscle = new JLabel(new ImageIcon(m.getImage()));
		JLabel iconInfo = new JLabel("Worked " + mw.getCount() + " times : KRP " + mw.getKRP(), SwingConstants.CENTER);

		iconPanel.add(BorderLayout.NORTH, iconLabel);
		iconPanel.add(BorderLayout.CENTER, iconMuscle);
		iconPanel.add(BorderLayout.SOUTH, iconInfo);

		iconPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		pan.add(iconPanel);

		return pan;
	}
	
	private JPanel returnableStretchesPanel() {
		JPanel pan = new JPanel(new GridLayout(0,2));
		ArrayList<stretches> list = new ArrayList<>();

		for (muscle m : day.getType().getEnclosedMuscles()) {
			ArrayList<stretches> stretchList = m.getStretches();
			for (int i = 0 ; i < stretchList.size(); i++) {
				if (!list.contains(stretchList.get(i))) {
					list.add(stretchList.get(i));
				}
			}
		}
		
		for (stretches s : list) {
			System.out.println(s.getName());
			JPanel stretchPanel = new JPanel(new BorderLayout());
			JLabel stretchName = new JLabel(s.getName().toUpperCase(), SwingConstants.CENTER);
			JLabel stretchIcon;
			if (s.getImage() != null) {
				stretchIcon = new JLabel(new ImageIcon(s.getImage()));
			} else {
				stretchIcon = new JLabel(new ImageIcon(new BufferedImage(50,50, BufferedImage.TYPE_INT_ARGB)));
			}
			stretchPanel.add(BorderLayout.CENTER, stretchIcon);
			stretchPanel.add(BorderLayout.NORTH, stretchName);
			stretchPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			pan.add(stretchPanel);
		}
		pan.setPreferredSize(new Dimension(150, list.size() * 200));
		pan.revalidate();
		pan.updateUI();
		pan.setVisible(true);
		return pan;
	}

}

class CustomTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -6193917638491409302L;

	public CustomTableModel() {
		super();
	}

	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}

class RoundButton extends JButton {

	private static final long serialVersionUID = -4024225782733927375L;

	RoundButton(String label) {
		super(label);
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}

}