package gui;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import main.ex;
import main.muscle;

public class KTree extends JTree{

	private static final long serialVersionUID = 1976917853459548436L;
	
	public KTree(TreeNode root) {
		super(root);
	}
	
	public void createNodes(DefaultMutableTreeNode top, String restriction) {
		DefaultMutableTreeNode musc = null;
		DefaultMutableTreeNode exer = null;

		for (muscle m : muscle.values()) {
			musc = new DefaultMutableTreeNode(m.getName());
			
			for (ex e : ex.values()) {
				if (e.name().contains(restriction)) {
					for (int i = 0; i < e.getPrimaryMuscles().length; i++) {
						if (e.getPrimaryMuscles()[i].equals(m)) {
							exer = new DefaultMutableTreeNode(e.getName());
							musc.add(exer);
							break;
						}
					}
				}
				
			}
			
			if (musc.getChildCount() > 0) {
				top.add(musc);
			}

		}
	}
	
	public void createNodes(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode musc = null;
		DefaultMutableTreeNode exer = null;

		for (muscle m : muscle.values()) {
			musc = new DefaultMutableTreeNode(m.getName());
			top.add(musc);
			for (ex e : ex.values()) {
				for (int i = 0; i < e.getPrimaryMuscles().length; i++) {
					if (e.getPrimaryMuscles()[i].equals(m)) {
						exer = new DefaultMutableTreeNode(e.getName());
						musc.add(exer);
						break;
					}
				}
			}

		}
	}
}
