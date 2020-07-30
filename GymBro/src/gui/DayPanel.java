package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import main.DayType;
import main.GymDay;

public class DayPanel extends JPanel{

	private static final long serialVersionUID = -7454359579624638916L;
	private boolean hasBeenSet;
	private static final int DATEW = 12;
	
	protected DayType type;
	protected LocalDate date;
	private JTextField typeText;
	
	public DayPanel(GymDay parent){
		super();
		this.setLayout(new BorderLayout());
		//this.setBackground(Color.gray);
		hasBeenSet = false;
		
		//set vars from parent
		LocalDate storedDate = parent.getDate();
		date = storedDate;
		DayType dayType = parent.getType();
		
		int frameWidth = GUI.frame.getWidth();
		int frameHeight = GUI.frame.getHeight();
		
		this.setPreferredSize(new Dimension(frameWidth/4, frameHeight /10));
		this.setMaximumSize(new Dimension(frameWidth/4, frameHeight / 9));
		this.setMinimumSize(new Dimension(frameWidth/4, frameHeight / 11));
		
		JTextField ddate = (LocalDate.now().equals(storedDate)) ? new JTextField(storedDate.toString() + "  -  TODAY") : new JTextField(storedDate.toString());
		
		
		ddate.setEditable(false);
		ddate.setHorizontalAlignment(JTextField.LEFT);
		ddate.setFont(new Font("Custom", Font.HANGING_BASELINE, 24));
		ddate.setBackground(new Color(200,200,200));
		
		add(ddate, BorderLayout.NORTH);
		
		
		
		typeText = new JTextField(dayType.toString());
		
		typeText.setEditable(false);
		typeText.setHorizontalAlignment(JTextField.LEFT);
		typeText.setFont(new Font("Custom", Font.BOLD, 16));
		typeText.setBackground(new Color(120,120,120));
		
		
		add(typeText, BorderLayout.CENTER);
		
		JButton edit = new JButton("EDIT"); // be sure to change this to "view" after edits are finalized.
		
		
		add(edit, BorderLayout.EAST);
		
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		ddate.setVisible(true);
		typeText.setVisible(true);
		edit.setVisible(true);
		this.setVisible(true);
		
		
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				GUI.SetMainPanel(parent);
				//Open Panel Method in GUI and send in date information from here as params, leave link to this DayPanel object for saving purposes.
			}
			
		});
	}
	
	public void updateType(DayType inType) {
		type = inType;
		typeText.setText(type.toString());
	}
	
	public void confirmPanel() {
		if (hasBeenSet) {
			return;
		}
		hasBeenSet = true;
		
		//create new GymDay and store it
	}
	

}
