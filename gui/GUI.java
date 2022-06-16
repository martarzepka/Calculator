package gui;

import java.awt.BorderLayout;

import javax.swing.*;

public class GUI
{
	private JFrame ramka;
	private JTabbedPane panel;

	public void Twórz()
	{
		ramka=new JFrame("Kalkulator");
		panel=new JTabbedPane();
		
		panel.add("Prosty",new Prosty());
		panel.add("Naukowy",new Naukowy());
		
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ramka.getContentPane().add(BorderLayout.CENTER, panel);
		
		ramka.setSize(400,600);
		ramka.setResizable(false);
		ramka.setVisible(true);

	}
	
	
}
