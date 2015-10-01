package stringworker;

/**
 * Created by Owner on 21/09/2015.
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

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

				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(null);
				f = fileChooser.getSelectedFile();
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					f = fileChooser.getSelectedFile();
				}
				new SwingWorker<String, String>() {
					protected String doInBackground() throws Exception {

						BufferedReader in = new BufferedReader(new FileReader(f)); //stores characters in memory
						String line = in.readLine();//access the lines of text and stores them in a string

						while (line != null) {
							publish(line); //update progress
							line = in.readLine();
						}
						in.close();
						return line;

					}

					//runs on EDT, allowed to update gui
					protected void process(List< String> d) {

						for(int i=0;i<d.size();i++)
							textArea1.append(d.get(i)+ " "  + "\n"); //returns the result

					}
				}.execute();// start the SwingWorker.

			}
		});

		reverse2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				new SwingWorker<String, String>() {
					protected String doInBackground() throws Exception {
						BufferedReader inn = new BufferedReader(new FileReader(f));
						String line2;
						int arrayLength ;

						line2 = inn.readLine();
						String[] str = line2.split(" ");
						arrayLength = str.length;
						while (arrayLength>0){
							str = line2.split(" ");
							for(int i=str.length-1;i>=0;i--)
							{
								publish(line2);
								textArea2.append(str[i]+" ");
							}
							textArea2.append("\n");
							line2 = inn.readLine();
						}
						inn.close();
						return line2;
					}
					//runs on EDT, allowed to update gui
					protected void process(List< String> d) {

						for(int i=d.size()-1;i>=0;i--)
						{
							//textArea2.append(d.get(i)+ " "  + "\n"); //returns the result
						}

					}
				}.execute();// start the SwingWorker.

			}
		});


		reverse3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new SwingWorker<String, String>() {
					protected String doInBackground() throws Exception {
						BufferedReader innn = new BufferedReader(new FileReader(f));
						String line2= innn.readLine();

						while (line2!=null){
							String[] str = line2.split(" ");
							String[] array = new String[str.length];

							int i;

							if(str.length % 2 ==0)		//if even number of words
							{
								for(i=str.length-1;i>=0;i--)
								{
									if(i%2==0)
										array[i+1]=str[i];		//swap pair of words
									else
										array[i-1]=str[i];
								}
							}
							else					//if odd number of words
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
								textArea3.append(array[i]+" ");
							}
							textArea3.append("\n");
							line2 = innn.readLine();

						}
						innn.close();
						return line2;
					}
					//runs on EDT, allowed to update gui
					protected void process(List< String> d) {
					}
				}.execute();// start the SwingWorker.


			}
		});
		count4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new SwingWorker<String, String>() {
					String [] Array;
					int arrayLength ;
					String str="";
					String Line="";
					protected String doInBackground() throws Exception {
						BufferedReader br = new BufferedReader(new FileReader(f));
						while((str = br.readLine()) != null){
							Line = Line + str;
						}
						Array = Line.split(" "); //split and store
						arrayLength = Array.length;
						for (int i = 0; i < arrayLength; i++)
						{
							int count = 1 ;
							for (int j = i+1; j < arrayLength; j++) 
							{
								if(Array[i].equalsIgnoreCase(Array[j]))		//if element in array equal	 
									count++;
							}
							
							textArea4.append(Array[i]+"  "+count+"\n");
						}
						br.close();
						return Line;
					}
					//runs on EDT, allowed to update gui
					protected void process(List< String> d) {
					}
				}.execute();// start the SwingWorker.

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



