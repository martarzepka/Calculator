package gui;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Przycisk extends JButton
{
	private final int wys=50;
	private final int szer=60;
	
	public Przycisk(String nazwa, ActionListener a, JPanel p)
	{
		super(nazwa);
		setPreferredSize(new Dimension(szer,wys));
		addActionListener(a);
		p.add(this);
	}
}
