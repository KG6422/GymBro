package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import main.ex;
import main.muscle;

public class AddPanel extends JPanel {
	private static final long serialVersionUID = -2558938699671322713L;
	private KTree tree;
	private DefaultMutableTreeNode top;
	private JScrollPane treeView;
	private JLabel selectedEx;
	private Object[] returnList; // 0 Activity, 1 Sets, 2 Reps, 3 Weight, 4 KRP *can leave empty*, 5 Muscle List,
									// 6 *can leave empty*

	private MainPanel parent;

	protected AddPanel(MainPanel parent) {
		super();
		this.parent = parent;
		returnList = new Object[7];
		this.setLayout(new GridBagLayout());
		GridBagConstraints f = new GridBagConstraints();
		f.anchor = GridBagConstraints.ABOVE_BASELINE;
		f.fill = GridBagConstraints.HORIZONTAL;
		f.gridy = GridBagConstraints.RELATIVE;
		f.gridx = 1;
		f.ipady = 1;

		selectedEx = new JLabel("Selected: none", SwingConstants.CENTER);
		selectedEx.setFont(new Font("selected ex" , Font.ITALIC, 18));
		JLabel searchLabel = new JLabel("Search: ", JLabel.TRAILING);
		this.add(searchLabel, f);

		f.gridx = 2;
		JTextField searchField = new JTextField(20);
		searchLabel.setLabelFor(searchField);
		this.add(searchField, f);

		//f.gridy = 1;
		f.gridwidth = 1;
		f.gridheight = 1;

		f.insets = new Insets(5, 0, 0, 0);
		top = new DefaultMutableTreeNode("Search By Muscle");
		tree = new KTree(top);

		// selectability
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (node == null || node.getChildCount() > 0 || node.getLevel() != 2) {
					return;
				} else {
					returnList[0] = node.getUserObject(); // name
					returnList[5] = ex.findFromName((String) node.getUserObject()).getPrimaryMuscles(); // primary
					selectedEx.setText("Selected: " + returnList[0]);																					// muscles just
																										// for now
					// ** need to implement adding secondary muscles to this list as well!!!!!
				}

			}

		});

		tree.createNodes(top);
		treeView = new JScrollPane(tree);
		this.add(treeView, f);

		f.ipady = 0;
		//f.gridy = 2;
		f.insets = new Insets(10, 0, 0, 0);
		JButton openAllToggle = new JButton("Toggle View");
		this.add(openAllToggle, f);

		f.gridheight = 1;
		f.insets = new Insets(5, 0, 0, 0);

		

		JTextField setsField = new JTextField(20);
		this.add(setsField, f);


		JTextField repsField = new JTextField(20);
		
		this.add(repsField, f);

		

		JTextField weightField = new JTextField(20);
		this.add(weightField, f);

		f.fill = GridBagConstraints.CENTER;
		//f.gridy = 6;
		f.ipadx = 10;
		JLabel hint = new JLabel("Use commas (,) to separate reps and weights if needed");
		hint.setFont(new Font("Hint", Font.ITALIC, 12));
		this.add(hint, f);
		
		f.fill = GridBagConstraints.HORIZONTAL;
		f.ipadx = 35;
		f.ipady = 15;
		f.insets = new Insets(10, 0, 0, 0);
		//f.gridy = 7;
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,3));
		JButton doneButton = new JButton("Done");
		doneButton.setBackground(new Color(123, 168, 119));
		

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(new Color(191, 133, 115));
		buttonPanel.add(doneButton);
		buttonPanel.add(new JPanel()); // blank space between the two buttons
		buttonPanel.add(cancelButton);
		this.add(buttonPanel, f);
		
		f.weightx = 0;
		//f.gridy = 8;
		this.add(selectedEx, f);
		
		
		f.gridy = 3;
		f.gridx = 1;
		JLabel setsLabel = new JLabel("Sets: ", JLabel.TRAILING);
		this.add(setsLabel, f);
		
		f.gridy = 4;
		JLabel repsLabel = new JLabel("Reps: ", JLabel.TRAILING);
		this.add(repsLabel, f);
		
	    f.gridy = 5;
		JLabel weightLabel = new JLabel("Weight: ", JLabel.TRAILING);
		this.add(weightLabel, f);
		
		repsLabel.setLabelFor(repsField);
		weightLabel.setLabelFor(weightField);
		setsLabel.setLabelFor(setsField);
		
		this.revalidate();
		this.repaint();

		doneButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (returnList[0] != null && returnList[1] != null && returnList[2] != null && returnList[3] != null
						&& returnList[5] != null) {
					parent.sendAddedInfo(returnList);
					parent.disposeFrame();
				} else {
					String s0 = returnList[0] != null ? "" : "\nEXERCISE NOT SELECTED";
					String s1 = returnList[1] != null ? "" : "\nSETS FIELD EMPTY";
					String s2 = returnList[2] != null ? "" : "\nREPS FIELD EMPTY";
					String s3 = returnList[3] != null ? "" : "\nWEIGHT FIELD EMPTY";
					String s5 = returnList[5] != null ? "" : "\nDEV ERROR";
					JOptionPane.showMessageDialog(new JFrame("Insufficient Information"),
							"Error: All fields must be filled out." +s0+s1+s2+s3+s5);
				}

			}

		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				parent.disposeFrame();
			}

		});

		openAllToggle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// toggles whether the muscle groups show their children or not
				if (tree.isExpanded(0)) {
					for (int i = 0; i < tree.getRowCount(); i++) {
						tree.collapseRow(i);
					}
				} else {
					for (int i = 0; i < tree.getRowCount(); i++) {
						tree.expandRow(i);
					}
				}
			}

		});

		setsField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateSetsField();

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateSetsField();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateSetsField();

			}

			private void updateSetsField() {
				try {
					returnList[1] = setsField.getDocument().getText(0, setsField.getDocument().getLength());
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		repsField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateRepsField();

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateRepsField();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateRepsField();

			}

			private void updateRepsField() {
				try {
					returnList[2] = repsField.getDocument().getText(0, repsField.getDocument().getLength());
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		weightField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateWeightField();

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateWeightField();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateWeightField();

			}

			private void updateWeightField() {
				try {
					returnList[3] = weightField.getDocument().getText(0, weightField.getDocument().getLength());
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		searchField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				search();

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				search();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				search();

			}

			private void search() {
				top.removeAllChildren();

				if (searchField.getDocument().getLength() > 0) {
					try {
						tree.createNodes(top,
								searchField.getDocument().getText(0, searchField.getDocument().getLength()));
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("WOAH THERE");
					}
				} else {
					tree.createNodes(top);
				}
				treeView.updateUI();
				tree.updateUI();
				AddPanel.this.revalidate();
				AddPanel.this.repaint();
			}
		});
	}

}
