package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dzialania.ProstyOblicz;

public class Prosty extends JPanel implements ActionListener
{
	private JPanel panel1,panel2,panel3;
	private JButton b7,b8,b9,bdziel,b4,b5,b6,brazy,b1,b2,b3,bminus,bAC,b0,bkropka,browna,bplus;
	private JTextField okienko;
	private String a="",b="0",znak="=",tekst="0";
	
	public Prosty()
	{
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		okienko = new JTextField();
		b7=new Przycisk("7",this,panel2);
		b8=new Przycisk("8",this,panel2);
		b9=new Przycisk("9",this,panel2);
		bdziel=new Przycisk("/",this,panel2);
		b4=new Przycisk("4",this,panel2);
		b5=new Przycisk("5",this,panel2);
		b6=new Przycisk("6",this,panel2);
		brazy=new Przycisk("*",this,panel2);
		b1=new Przycisk("1",this,panel2);
		b2=new Przycisk("2",this,panel2);
		b3=new Przycisk("3",this,panel2);
		bminus=new Przycisk("-",this,panel2);
		bAC=new Przycisk("AC",this,panel2);
		b0=new Przycisk("0",this,panel2);
		bkropka=new Przycisk(".",this,panel2);
		bplus=new Przycisk("+",this,panel2);
		
		browna=new Przycisk("=",this,panel3);
		browna.setPreferredSize(new Dimension(240,50));
		
		okienko.setEditable(false);
		okienko.setPreferredSize(new Dimension(240, 30));
		okienko.setHorizontalAlignment(SwingConstants.RIGHT);
		okienko.setText("0");
		panel2.setLayout(new GridLayout(4, 4));
		
		panel1.add(okienko);
		
		add(BorderLayout.NORTH, panel1);
		add(BorderLayout.CENTER, panel2);
		add(BorderLayout.SOUTH, panel3);
	}
	
	public void actionPerformed(ActionEvent zdarzenie)
	{
		char z=(zdarzenie.getActionCommand()).charAt(0);
		String z2=((JButton)zdarzenie.getSource()).getText();
		if(z>='0' && z<='9')
		{
			if(tekst=="0")
			{
				tekst=""+z;
				okienko.setText(tekst);
			}
			else
			{
				tekst+=z;
				okienko.setText(tekst);
			}
		}
		if(z=='.')
		{
			if(!tekst.contains("."))
			{
				tekst+=z;
				okienko.setText(tekst);
			}
		}
		if(z2=="AC")
		{
			tekst="0";
			a="";
			b="0";
			znak="=";
			okienko.setText(tekst);
		}
		if(z2=="/" || z2=="*" || z2=="-" || z2=="+" || z2=="=")
		{
			b=tekst;
			if(znak=="=")
			{
				if(a=="")
					a=b;
				else
					znak=z2;
			}
			else
			{
				a=ProstyOblicz.Oblicz(a,znak,b);
				okienko.setText(a);
			}
			tekst="0";
			znak=z2;
		}
		
	}
}
