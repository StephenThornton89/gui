package gui;

/**
 * Created by Owner on 21/09/2015.
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MyPanel extends JPanel {

	private JButton load1;
	private JButton reverse2;
	private JButton reverse3;
	private JButton count4;
	private JTextArea textArea2;
	private JTextArea textArea1;
	private JTextArea textArea3;
	private JTextArea textArea4;
	File f;
	public MyPanel() {

		//construct components
		load1 = new JButton ("load");
		reverse2 = new JButton ("Reverse_Contents");
		reverse3 = new JButton ("Reverse_words");
		count4 = new JButton ("count");
		textArea2 = new JTextArea (5, 5);
		textArea1 = new JTextArea (5, 5);
		textArea3 = new JTextArea (5, 5);
		textArea4 = new JTextArea (5, 5);

		//adjust size and set layout
		setPreferredSize (new Dimension (667, 366));
		setLayout (null);

		//add components
		add(load1);
		add(reverse2);
		add (reverse3);
		add (count4);
		add (textArea2);
		add (textArea1);
		add (textArea3);
		add (textArea4);

		//set component bounds (only needed by Absolute Positioning)
		load1.setBounds(20, 20, 140, 40);
		reverse2.setBounds(170, 20, 140, 40);
		reverse3.setBounds (315, 20, 140, 40);
		count4.setBounds (460, 20, 140, 40);
		textArea2.setBounds (170, 75, 140, 250);
		textArea1.setBounds (20, 75, 140, 250);
		textArea3.setBounds (315, 75, 140, 250);
		textArea4.setBounds (460, 75, 140, 250);
		load1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				try {
					JFileChooser fileChooser = new JFileChooser();
					int returnValue = fileChooser.showOpenDialog(null);
					f = fileChooser.getSelectedFile();
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						f = fileChooser.getSelectedFile();
						//System.out.println(File.getName());
					}

					JTextArea text = textArea1;
					BufferedReader in = new BufferedReader(new FileReader(f));
					String line = in.readLine();
					while (line != null) {
						text.append(line + "\n");
						line = in.readLine();
					}
					in.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		reverse2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				try {
					JTextArea text2 = textArea2;
					BufferedReader inn = new BufferedReader(new FileReader(f));
					System.out.println(f.getName());
					String line2;
					line2 = inn.readLine();
					String[] str = line2.split(" ");
					//System.out.println(f2.getName());

					int i=0;
					while (str.length>0){
						str = line2.split(" ");
						for(i=str.length-1;i>=0;i--)
						{
							text2.append(str[i]+" ");
						}
						text2.append("\n");
						line2 = inn.readLine();
						if(line2 == null)
							break;
					}
					inn.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		});


		reverse3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					JTextArea text2 = textArea3;
					BufferedReader innn = new BufferedReader(new FileReader(f));
					String line2= innn.readLine();

					while (line2!=null){
						String[] str = line2.split(" ");
						String[] array = new String[str.length];

						int i;

						if(str.length % 2 ==0)
						{
							for(i=str.length-1;i>=0;i--)
							{
								if(i%2==0)
								array[i+1]=str[i];
								else
									array[i-1]=str[i];
							}
						}
						else
						{
							for(i=str.length-1;i>=0;i--)
							{
								if(i==0)
									array[i]=str[i];
								else if(i % 2 ==0)
									array[i-1]=str[i];
								else
									array[i+1]=str[i];
							}
						}
						for(i=str.length-1;i>=0;i--)
						{
							text2.append(array[i]+" ");
						}
						text2.append("\n");
						line2 = innn.readLine();

					}
					innn.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		count4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String [] stringArray;

				int arrayLength ;
				String s="";
				String stringLine="";
				JTextArea text2 = textArea4;

				try{
					BufferedReader br = new BufferedReader(new FileReader(f));
					while((s = br.readLine()) != null){
						stringLine = stringLine + s;
					}

					stringArray = stringLine.split(" ");
					arrayLength = stringArray.length;
					for (int i = 0; i < arrayLength; i++) {
						int c = 1 ;
						for (int j = i+1; j < arrayLength; j++) {
							if(stringArray[i].equalsIgnoreCase(stringArray[j])){
								c++;
								for (int j2 = j; j2 < arrayLength; j2++) {
									stringArray[j2] = stringArray[j2+1];
									arrayLength = arrayLength - 1;
								}
							}
						}

						text2.append(stringArray[i]+"  "+c+"\n");

					}

					br.close();
				}catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}


	public static void main (String[] args) {
		JFrame frame = new JFrame ("MyPanel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MyPanel());
		frame.pack();
		frame.setVisible(true);

	}
}



