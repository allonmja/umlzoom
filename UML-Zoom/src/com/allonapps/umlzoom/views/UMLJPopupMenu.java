package com.allonapps.umlzoom.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class UMLJPopupMenu extends JPopupMenu implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	UMLBoxJPanel box;
	JMenuItem name;
	JMenuItem addPublicMethod;
	JMenuItem addPrivateMethod;
	JMenuItem changeFile;
    public UMLJPopupMenu(UMLBoxJPanel b){
    	super();
    	box = b;
        name = new JMenuItem("Edit name");
        name.addActionListener(this);
        
        addPublicMethod = new JMenuItem("Add public method");
        addPublicMethod.addActionListener(this);

        addPrivateMethod = new JMenuItem("Add private method");
        addPrivateMethod.addActionListener(this);
        
        changeFile = new JMenuItem("Change opening file");
        changeFile.addActionListener(this);
        
        add(name);
        add(addPublicMethod);
        add(addPrivateMethod);
        add(changeFile);
    }
	@Override
	public void actionPerformed(ActionEvent action) {
		// TODO Auto-generated method stub
		if (action.getSource() == name){
			String s = showEditBox("Change Name");
			if (s!=null){
				box.setTitle(s);
			}
		}
		if (action.getSource() == addPublicMethod){
			String s = showEditBox("Add public method");
			if (s!=null){
				box.addPublicMethod(s);
			}
			
		}
		if (action.getSource() == addPrivateMethod){
			String s = showEditBox("Add private method");
			if (s!=null){
				box.addPrivateMethod(s);
			}
		}
		if (action.getSource() == changeFile){
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(box);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            box.setFileName(file.getAbsolutePath());
	        } 
		}
		box.bubbleBox();
		box.repaint();

		
	}
	
	public String showEditBox(String editCategory){
		String s = (String)JOptionPane.showInputDialog(
                box,
                "Edit "+editCategory +":",
                editCategory, JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                editCategory);

			//If a string was returned, say so.
		if ((s != null) && (s.length() > 0)) {
			return s;
		}
			return null;
		}

}
