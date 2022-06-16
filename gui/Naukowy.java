package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dzialania.ONP;
import dzialania.Porzadkowanie;

public class Naukowy extends JPanel implements ActionListener
{
	private JPanel panel1,panel2,panel3,panel4;
	private Przycisk[] przyciski;
	private Przycisk2[] przyciski2;
	private JTextField okienko;
	private String tekst="",tekst2="";
	private ArrayList<String> Tekst=new ArrayList<String>();
	private boolean czyWynik=false;
	
	public Naukowy()
	{
		przyciski=new Przycisk[21];
		przyciski2=new Przycisk2[12];
		
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		okienko = new JTextField();
		przyciski[0]=new Przycisk("7",this,panel2);
		przyciski[1]=new Przycisk("8",this,panel2);
		przyciski[2]=new Przycisk("9",this,panel2);
		przyciski[3]=new Przycisk("DEL",this,panel2);
		przyciski[4]=new Przycisk("AC",this,panel2);
		przyciski[5]=new Przycisk("4",this,panel2);
		przyciski[6]=new Przycisk("5",this,panel2);
		przyciski[7]=new Przycisk("6",this,panel2);
		przyciski[8]=new Przycisk("*",this,panel2);
		przyciski[9]=new Przycisk("/",this,panel2);
		przyciski[10]=new Przycisk("1",this,panel2);
		przyciski[11]=new Przycisk("2",this,panel2);
		przyciski[12]=new Przycisk("3",this,panel2);
		przyciski[13]=new Przycisk("+",this,panel2);
		przyciski[14]=new Przycisk("-",this,panel2);
		przyciski[15]=new Przycisk(".",this,panel2);
		przyciski[16]=new Przycisk("0",this,panel2);
		przyciski[17]=new Przycisk("(",this,panel2);
		przyciski[18]=new Przycisk(")",this,panel2);
		przyciski[19]=new Przycisk(",",this,panel2);
		przyciski[20]=new Przycisk("=",this,panel3);
		
		przyciski[20].setPreferredSize(new Dimension(300,50));
		
		przyciski2[0]=new Przycisk2("^",this,panel4);
		przyciski2[1]=new Przycisk2("\u221a",this,panel4);
		przyciski2[2]=new Przycisk2("\u03c0",this,panel4);
		przyciski2[3]=new Przycisk2("log",this,panel4);
		przyciski2[4]=new Przycisk2("ln",this,panel4);
		przyciski2[5]=new Przycisk2("e",this,panel4);
		przyciski2[6]=new Przycisk2("sin",this,panel4);
		przyciski2[7]=new Przycisk2("cos",this,panel4);
		przyciski2[8]=new Przycisk2("tg",this,panel4);
		
		okienko.setEditable(false);
		okienko.setPreferredSize(new Dimension(300, 30));
		okienko.setHorizontalAlignment(SwingConstants.RIGHT);
		okienko.setText("");
		panel2.setLayout(new GridLayout(4, 5));
		panel4.setLayout(new GridLayout(3, 3));
		
		panel1.add(okienko);
		
		add(BorderLayout.NORTH, panel1);
		add(BorderLayout.CENTER, panel4);
		add(BorderLayout.CENTER, panel2);
		add(BorderLayout.SOUTH, panel3);
	}
	
	public void actionPerformed(ActionEvent zdarzenie)
	{
		String z=((JButton)zdarzenie.getSource()).getText();
		
		if(okienko.getText().equals("ERROR"))
		{
			if(z=="DEL")
				okienko.setText(tekst);
			if(z=="AC")
			{
				tekst="";
				okienko.setText(tekst);
				Tekst.clear();
			}
		}
		else
		{
			switch(z)
			{
				case("DEL"):
				{
					if(tekst.length()>0)
					{
						if(!czyWynik)
						{
							char x=tekst.charAt(tekst.length()-1);
							if(x>='g' && x<='s')
							{
								String x2=tekst.substring(tekst.length()-2);
								
								if(x2.equals("ln") || x2.equals("tg"))
									tekst=tekst.substring(0, tekst.length()-2);
								else
									tekst=tekst.substring(0, tekst.length()-3);
							}
							else
								tekst=tekst.substring(0, tekst.length()-1);
							okienko.setText(tekst);
							Tekst.remove(Tekst.size()-1);
						}	
						else
						{
							czyWynik=false;
							okienko.setText(tekst);
						}
					}
					break;
				}
				case("AC"):
				{
					if(czyWynik)
						czyWynik=false;
					tekst="";
					okienko.setText(tekst);
					Tekst.clear();
					break;
				}
				case("\u221a"):
				{
					if(czyWynik)
					{
						tekst="";
						Tekst.clear();
						czyWynik=false;
					}
					tekst+="\u221a";
					Tekst.add("\u221a");
					tekst+="(";
					Tekst.add("(");
					okienko.setText(tekst);
					break;
				}
				case("\u03c0"):
				{
					if(czyWynik)
					{
						tekst="";
						Tekst.clear();
						czyWynik=false;
					}
					tekst+="\u03c0";
					okienko.setText(tekst);
					Tekst.add("\u03c0");
					break;
				}
				case("="):
				{
					ArrayList<String> Tekst2=new ArrayList<String>();
					tekst2="";
					boolean blad=false;
					if(Tekst.size()>0)
					{
						Tekst2=Porzadkowanie.Porz(Tekst);
						if(Tekst2.size()>0)
						{
							Tekst2=ONP.Przeksztalc(Tekst2);
							if(Tekst2.size()>0)
							{
								tekst2=ONP.Oblicz(Tekst2);
								if(tekst2=="")
									blad=true;
							}
							else
								blad=true;
						}
						else
							blad=true;
					}
					if(blad)
					{
						okienko.setText("ERROR");
					}
					else
					{
						czyWynik=true;
						okienko.setText(tekst2);
						
					}
					break;
				}
				default:
				{
					if(czyWynik)
					{
						czyWynik=false;
						if(!(z=="+" || z=="-" || z=="*" || z=="/" || z=="^"))
						{
							tekst="";
							Tekst.clear();
						}
						else
						{
							tekst=tekst2;
							Tekst.clear();
							Tekst.add(tekst);
						}
					}
					tekst+=z;
					Tekst.add(z);
					if(z=="ln" || z=="sin" || z=="cos" || z=="tg" || z=="log")
					{
						tekst+="(";
						Tekst.add("(");		
					}
					okienko.setText(tekst);
					
					break;
				}
			}	
		}
	}
}
