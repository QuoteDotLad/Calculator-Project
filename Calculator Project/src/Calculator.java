import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class Calculator extends JFrame implements ActionListener
{
	//******I have a lot of helper methods written at the bottom, which are typically used in situations where I am just repeating code multiple times over*************
	int firstNum, secondNum, result;
	String operations = "";
	boolean reset = false;
	private static JFrame CalcFrame;
	
	//Panel of radio buttons that would've controlled the size of the binary field
	private WordRadio WordPanel;
	ButtonGroup group2;
	
	//The panel holding radio buttons to change base
	private BaseRadio BasePanel;
	ButtonGroup group;
	int currentBase = 10;
	
	//The arithmetic operation panels
	private OpButtons OpPanel;
	
	//The panel holding the clear operations and also the sign button
	private ClearButtons ClearPanel;
	
	//64 buttons that will be our binary representation
	private JTextField BitField;
	
	//Hexadecimal Panel filled with A-F
	private HexButtons HexPanel;
	
	//Numeric keypad
	private CalButton NumPanel;
	
	private CalField ResultField;
	
	//Menu bar with View, Edit, and File
	private MenuPanel menuBar;
	boolean isVis = true;
	
	//The non-panel buttons
	private JButton equalsButton;
	private JButton zeroButton;
	private JButton modButton;
	
	public static void main(String[] args)
		{
			EventQueue.invokeLater(new Runnable() //Thanks Google
					{
						public void run()
						{
							Calculator window = new Calculator();
							CalcFrame.setVisible(true);	
							CalcFrame.getContentPane();
							CalcFrame.setFocusable(true);
						}
					});
		}
	public Calculator() 
	{
							
	CalcFrame = new JFrame();
	CalcFrame.getContentPane().setBackground(new Color(240, 248, 255));
	CalcFrame.setBackground(new Color(240, 248, 255));
	CalcFrame.setTitle("Calculator");
	CalcFrame.setBounds(100, 100, 564, 457);
	CalcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	CalcFrame.getContentPane().setLayout(null);
	
/*----------------------------------------------------------------------------------------------------------------------------------------------------*/	
//Start WordPanel
		WordPanel = new WordRadio();
		WordPanel.setBackground(new Color(240, 248, 255));
		WordPanel.setBounds(10, 266, 83, 111);
		WordPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		group2 = new ButtonGroup();
		group2.add(WordPanel.QWord);
		group2.add(WordPanel.DWord);
		group2.add(WordPanel.QWord);
		group2.add(WordPanel.Byte);
		WordPanel.QWord.setSelected(true);
		CalcFrame.getContentPane().add(WordPanel);
		GroupLayout gl_WordPanel = new GroupLayout(WordPanel);
		gl_WordPanel.setHorizontalGroup(
			gl_WordPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_WordPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_WordPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(WordPanel.QWord)
						.addComponent(WordPanel.Word)
						.addComponent(WordPanel.Byte)
						.addComponent(WordPanel.DWord, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
						.addContainerGap())
		);
		gl_WordPanel.setVerticalGroup(
			gl_WordPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_WordPanel.createSequentialGroup()
					.addGap(10)
					.addComponent(WordPanel.QWord)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(WordPanel.DWord)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(WordPanel.Word)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(WordPanel.Byte)
					.addGap(9))
		);
		WordPanel.setLayout(gl_WordPanel);

//End WordPanel
/*----------------------------------------------------------------------------------------------------------------------------------------------------*/
//Start BasePanel
		BasePanel = new BaseRadio();
		BasePanel.setBackground(new Color(240, 248, 255));
		BasePanel.setBounds(10, 147, 83, 108);
		BasePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //Creating a border around the panel that holds the Base radio buttons	
		BasePanel.Hex.addActionListener(this);
		BasePanel.Dec.addActionListener(this);
		BasePanel.Oct.addActionListener(this);
		BasePanel.Bin.addActionListener(this);
		group = new ButtonGroup();
		group.add(BasePanel.Hex);
		group.add(BasePanel.Dec);
		group.add(BasePanel.Oct);
		group.add(BasePanel.Bin);
		BasePanel.Dec.setSelected(true); //Start with Dec selected
		
		CalcFrame.getContentPane().add(BasePanel);
		GroupLayout gl_BasePanel = new GroupLayout(BasePanel);
		gl_BasePanel.setHorizontalGroup(
			gl_BasePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_BasePanel.createSequentialGroup()
					.addGroup(gl_BasePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_BasePanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(BasePanel.Hex))
						.addGroup(gl_BasePanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(BasePanel.Dec))
						.addGroup(gl_BasePanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(BasePanel.Oct))
						.addGroup(gl_BasePanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(BasePanel.Bin)))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_BasePanel.setVerticalGroup(
			gl_BasePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_BasePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(BasePanel.Hex)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(BasePanel.Dec)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(BasePanel.Oct)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(BasePanel.Bin)
					.addGap(9))
		);
		BasePanel.setLayout(gl_BasePanel);
//End BasePanel
/*----------------------------------------------------------------------------------------------------------------------------------------------------*/		
//Start BitPanel
		BitField = new JTextField();
		BitField.setBackground(Color.WHITE);
		BitField.setBounds(10, 78, 528, 57);
		BitField.setEditable(false);
		BitField.setHorizontalAlignment(SwingConstants.RIGHT);
		BitField.setText(Integer.toString(0));
		CalcFrame.getContentPane().add(BitField);
		BitField.setLayout(new GridLayout(1, 0, 0, 0));
//End BitPanel
/*----------------------------------------------------------------------------------------------------------------------------------------------------*/
//Start HexPanel		
		HexPanel = new HexButtons();
		HexPanel.setBackground(new Color(240, 248, 255));
		HexPanel.btnA.addActionListener(this);
		HexPanel.btnB.addActionListener(this);
		HexPanel.btnC.addActionListener(this);
		HexPanel.btnD.addActionListener(this);
		HexPanel.btnE.addActionListener(this);
		HexPanel.btnF.addActionListener(this);
		HexPanel.setBounds(103, 144, 49, 274);
		
		CalcFrame.getContentPane().add(HexPanel);
		GroupLayout gl_HexPanel = new GroupLayout(HexPanel);
		gl_HexPanel.setHorizontalGroup(
			gl_HexPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(HexPanel.btnA, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
				.addComponent(HexPanel.btnB, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
				.addComponent(HexPanel.btnC, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
				.addComponent(HexPanel.btnD, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
				.addComponent(HexPanel.btnE, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
				.addComponent(HexPanel.btnF, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
		);
		gl_HexPanel.setVerticalGroup(
			gl_HexPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_HexPanel.createSequentialGroup()
					.addGap(2)
					.addComponent(HexPanel.btnA, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(6, 6)
					.addComponent(HexPanel.btnB, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(6, 6)
					.addComponent(HexPanel.btnC, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(6, 6)
					.addComponent(HexPanel.btnD, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(6, 6)
					.addComponent(HexPanel.btnE, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(6, 6)
					.addComponent(HexPanel.btnF, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					)
		);
		HexPanel.setLayout(gl_HexPanel);
//End HexPanel
/*----------------------------------------------------------------------------------------------------------------------------------------------------*/
//Start NumPanel
		NumPanel = new CalButton();
		NumPanel.setBackground(new Color(240, 248, 255));
		NumPanel.btn7.addActionListener(this);
		NumPanel.btn8.addActionListener(this);
		NumPanel.btn9.addActionListener(this);
		NumPanel.btn4.addActionListener(this);
		NumPanel.btn5.addActionListener(this);
		NumPanel.btn6.addActionListener(this);
		NumPanel.btn1.addActionListener(this);
		NumPanel.btn2.addActionListener(this);
		NumPanel.btn3.addActionListener(this);
		NumPanel.setBounds(155, 187, 161, 141);
		CalcFrame.getContentPane().add(NumPanel);
		GroupLayout gl_NumPanel = new GroupLayout(NumPanel);
		gl_NumPanel.setAutoCreateGaps(true);
		gl_NumPanel.setHorizontalGroup(
			gl_NumPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_NumPanel.createSequentialGroup()
					.addGroup(gl_NumPanel.createParallelGroup()
							
						.addGroup(gl_NumPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(NumPanel.btn7, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addComponent(NumPanel.btn8, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addComponent(NumPanel.btn9, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_NumPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(NumPanel.btn4, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addComponent(NumPanel.btn5, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addComponent(NumPanel.btn6, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_NumPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(NumPanel.btn1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addComponent(NumPanel.btn2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addComponent(NumPanel.btn3, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							))));
		gl_NumPanel.setVerticalGroup(
			gl_NumPanel.createParallelGroup()
				.addGroup(gl_NumPanel.createSequentialGroup().addGap(4)
					.addGroup(gl_NumPanel.createParallelGroup()
						.addComponent(NumPanel.btn7, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(NumPanel.btn8, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(NumPanel.btn9, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_NumPanel.createParallelGroup()
						.addComponent(NumPanel.btn4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(NumPanel.btn5, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(NumPanel.btn6, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_NumPanel.createParallelGroup()
						.addComponent(NumPanel.btn1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(NumPanel.btn2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(NumPanel.btn3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))));
		NumPanel.setLayout(gl_NumPanel);
//End NumPanel
/*----------------------------------------------------------------------------------------------------------------------------------------------------*/
//Start ResultPanel
				ResultField = new CalField();
				ResultField.setBounds(10, 26, 529, 46);
				ResultField.setColumns(10);
				ResultField.setText("0");
				CalcFrame.getContentPane().add(ResultField);
				
//End ResultPanel
/*----------------------------------------------------------------------------------------------------------------------------------------------------*/
//Start Clear panel
		ClearPanel = new ClearButtons();
		ClearPanel.setBackground(new Color(240, 248, 255));
		ClearPanel.backButton.addActionListener(this);
		ClearPanel.CButton.addActionListener(this);
		ClearPanel.CEButton.addActionListener(this);
		ClearPanel.signButton.addActionListener(this);
		
		ClearPanel.setBounds(155, 147, 223, 37);
		CalcFrame.getContentPane().add(ClearPanel);
		ClearPanel.setLayout(new GridLayout(0, 4, 6, 0));
//End MemoryPanel
/*----------------------------------------------------------------------------------------------------------------------------------------------------*/
//Start Operations Panel
		OpPanel = new OpButtons();
		OpPanel.divButton.addActionListener(this);
		OpPanel.multButton.addActionListener(this);
		OpPanel.subButton.addActionListener(this);
		OpPanel.addButton.addActionListener(this);
		OpPanel.setBackground(new Color(240, 248, 255));
		OpPanel.setBounds(327, 187, 52, 183);
		CalcFrame.getContentPane().add(OpPanel);
		GroupLayout gl_OpPanel = new GroupLayout(OpPanel);
		gl_OpPanel.setAutoCreateGaps(true);
		gl_OpPanel.setHorizontalGroup(
			gl_OpPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_OpPanel.createSequentialGroup()
					.addGroup(gl_OpPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(OpPanel.addButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(OpPanel.divButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(OpPanel.multButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(OpPanel.subButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addGap(1))
		);
		gl_OpPanel.setVerticalGroup(
			gl_OpPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_OpPanel.createSequentialGroup()
					.addGap(4)
					.addComponent(OpPanel.divButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addComponent(OpPanel.multButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addComponent(OpPanel.subButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(OpPanel.addButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		OpPanel.setLayout(gl_OpPanel);
		
/*----------------------------------------------------------------------------------------------------------------------------------------------------*/		
//Start menuBar
		menuBar = new MenuPanel();
		menuBar.setBackground(new Color(240, 248, 255));
		menuBar.setBounds(10, 0, 252, 21);
		menuBar.setLayout(new GridLayout(0,3,4,0));
		menuBar.viewBtn.addActionListener(this);
		menuBar.editBtn.addActionListener(this);
		menuBar.helpBtn.addActionListener(this);
		CalcFrame.getContentPane().add(menuBar);
//End menuBar
/*----------------------------------------------------------------------------------------------------------------------------------------------------*/
//Remnants
		modButton = new JButton("%");
		modButton.addActionListener(this);
		modButton.setBackground(new Color(240, 248, 255));
		modButton.setBounds(268, 328, 49, 40);
		CalcFrame.getContentPane().add(modButton);
		
		equalsButton = new JButton("=");
		equalsButton.addActionListener(this);
		equalsButton.setBackground(new Color(240, 248, 255));
		equalsButton.setSize(104, 40);
		equalsButton.setLocation(158, 374);
		CalcFrame.getContentPane().add(equalsButton);
		
		zeroButton = new JButton("0");
		zeroButton.addActionListener(this);
		zeroButton.setBackground(new Color(240, 248, 255));
		zeroButton.setBounds(157, 328, 104, 40);
		CalcFrame.getContentPane().add(zeroButton);
		
		//Have Dec selected by default at startup
		if(BasePanel.Dec.isSelected())
		{
			HexPanel.btnA.setEnabled(false);
			HexPanel.btnB.setEnabled(false);
			HexPanel.btnC.setEnabled(false);
			HexPanel.btnD.setEnabled(false);
			HexPanel.btnE.setEnabled(false);
			HexPanel.btnF.setEnabled(false);
		}	
		}
/*----------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	//When a button is pressed this is called
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == BasePanel.Hex) //Checks if Hexadecimal radio button is picked, and enables all buttons that should be enabled for that base
		{
			HexPanel.btnA.setEnabled(true);
			HexPanel.btnB.setEnabled(true);
			HexPanel.btnC.setEnabled(true);
			HexPanel.btnD.setEnabled(true);
			HexPanel.btnE.setEnabled(true);
			HexPanel.btnF.setEnabled(true);
			zeroButton.setEnabled(true);
			NumPanel.btn1.setEnabled(true);
			NumPanel.btn2.setEnabled(true);
			NumPanel.btn3.setEnabled(true);
			NumPanel.btn4.setEnabled(true);
			NumPanel.btn5.setEnabled(true);
			NumPanel.btn6.setEnabled(true);
			NumPanel.btn7.setEnabled(true);
			NumPanel.btn8.setEnabled(true);
			NumPanel.btn9.setEnabled(true);
			ResultField.setText(convertBase(ResultField.getText(),currentBase, 16)); //Converts ResultField number to hexadecimal
		}
		if(e.getSource() == BasePanel.Dec) //Checks if Decimal radio button is picked, and enables all buttons that should be enabled for that base
		{
			HexPanel.btnA.setEnabled(false);
			HexPanel.btnB.setEnabled(false);
			HexPanel.btnC.setEnabled(false);
			HexPanel.btnD.setEnabled(false);
			HexPanel.btnE.setEnabled(false);
			HexPanel.btnF.setEnabled(false);
			zeroButton.setEnabled(true);
			NumPanel.btn1.setEnabled(true);
			NumPanel.btn2.setEnabled(true);
			NumPanel.btn3.setEnabled(true);
			NumPanel.btn4.setEnabled(true);
			NumPanel.btn5.setEnabled(true);
			NumPanel.btn6.setEnabled(true);
			NumPanel.btn7.setEnabled(true);
			NumPanel.btn8.setEnabled(true);
			NumPanel.btn9.setEnabled(true);
			ResultField.setText(convertBase(ResultField.getText(),currentBase, 10)); //Converts ResultField number to decimal
		}
		if(e.getSource() == BasePanel.Oct) //Checks if Octal radio button is picked, and enables all buttons that should be enabled for that base
		{
			HexPanel.btnA.setEnabled(false);
			HexPanel.btnB.setEnabled(false);
			HexPanel.btnC.setEnabled(false);
			HexPanel.btnD.setEnabled(false);
			HexPanel.btnE.setEnabled(false);
			HexPanel.btnF.setEnabled(false);
			zeroButton.setEnabled(true);
			NumPanel.btn1.setEnabled(true);
			NumPanel.btn2.setEnabled(true);
			NumPanel.btn3.setEnabled(true);
			NumPanel.btn4.setEnabled(true);
			NumPanel.btn5.setEnabled(true);
			NumPanel.btn6.setEnabled(true);
			NumPanel.btn7.setEnabled(true);
			NumPanel.btn8.setEnabled(false);
			NumPanel.btn9.setEnabled(false);
			ResultField.setText(convertBase(ResultField.getText(),currentBase, 8)); //Converts ResultField number to octal
		}
		if(e.getSource() == BasePanel.Bin) //Checks if Binary radio button is picked, and enables all buttons that should be enabled for that base
		{
			HexPanel.btnA.setEnabled(false);
			HexPanel.btnB.setEnabled(false);
			HexPanel.btnC.setEnabled(false);
			HexPanel.btnD.setEnabled(false);
			HexPanel.btnE.setEnabled(false);
			HexPanel.btnF.setEnabled(false);
			zeroButton.setEnabled(true);
			NumPanel.btn1.setEnabled(true);
			NumPanel.btn2.setEnabled(false);
			NumPanel.btn3.setEnabled(false);
			NumPanel.btn4.setEnabled(false);
			NumPanel.btn5.setEnabled(false);
			NumPanel.btn6.setEnabled(false);
			NumPanel.btn7.setEnabled(false);
			NumPanel.btn8.setEnabled(false);
			NumPanel.btn9.setEnabled(false);
			if(result != 0)
			{
				ResultField.setText(Integer.toString(result, 2));
			}
			ResultField.setText(convertBase(ResultField.getText(),currentBase, 2)); //Converts ResultField number to binary
		}
		
		String s = "";
		if(ResultField.getText().equals("0") && ResultField.getText().equals(String.valueOf(firstNum)) //Replaces default value of ResultField (0) with whatever is input, and the button checks are to make sure it does not cause any erasing just from
											 && e.getSource() != OpPanel.addButton						//Pressing a button
											 && e.getSource() != OpPanel.subButton
											 && e.getSource() != OpPanel.multButton
											 && e.getSource() != OpPanel.divButton
											 && e.getSource() != ClearPanel.signButton
											 && e.getSource() != BasePanel.Hex
											 && e.getSource() != BasePanel.Dec
											 && e.getSource() != BasePanel.Oct
											 && e.getSource() != BasePanel.Bin)
		{
		ResultField.setText(s);
		}
		if(e.getSource() == zeroButton) //ZERO
		{
			if(reset) //Checks if this is the beginning of a new calculation
			{
				clear();
			}
		String numEntered = ResultField.getText() + zeroButton.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(ResultField.getText())); //Put zero into ResultField
		}
		if(e.getSource() == NumPanel.btn1) //ONE
		{
			if(ResultField.getText().equals(String.valueOf(firstNum)))
			{
				ResultField.setText(s);
			}
		String numEntered = ResultField.getText() + NumPanel.btn1.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(ResultField.getText()));
		}
		if(e.getSource() == NumPanel.btn2) //TWO
		{
			if(ResultField.getText().equals(String.valueOf(firstNum)))
			{
				ResultField.setText(s);
			}
		String numEntered = ResultField.getText() + NumPanel.btn2.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(ResultField.getText()));
		}
		if(e.getSource() == NumPanel.btn3) //THREE
		{
			if(ResultField.getText().equals(String.valueOf(firstNum)))
			{
				ResultField.setText(s);
			}
		String numEntered = ResultField.getText() + NumPanel.btn3.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(ResultField.getText()));
		}
		if(e.getSource() == NumPanel.btn4) //FOUR
		{
			if(ResultField.getText().equals(String.valueOf(firstNum)))
			{
				ResultField.setText(s);
			}
		String numEntered = ResultField.getText() + NumPanel.btn4.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(ResultField.getText()));
		}
		if(e.getSource() == NumPanel.btn5) //FIVE
		{
			if(ResultField.getText().equals(String.valueOf(firstNum)))
			{
				ResultField.setText(s);
			}
		String numEntered = ResultField.getText() + NumPanel.btn5.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(ResultField.getText()));
		}
		if(e.getSource() == NumPanel.btn6) //SIX
		{
			if(ResultField.getText().equals(String.valueOf(firstNum)))
			{
				ResultField.setText(s);
			}
		String numEntered = ResultField.getText() + NumPanel.btn6.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(ResultField.getText()));
		}
		if(e.getSource() == NumPanel.btn7) //SEVEN
		{
			if(ResultField.getText().equals(String.valueOf(firstNum)))
			{
				ResultField.setText(s);
			}
		String numEntered = ResultField.getText() + NumPanel.btn7.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(ResultField.getText()));
		}
		if(e.getSource() == NumPanel.btn8) //EIGHT
		{
			if(ResultField.getText().equals(String.valueOf(firstNum)))
			{
				ResultField.setText(s);
			}
		String numEntered = ResultField.getText() + NumPanel.btn8.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(ResultField.getText()));
		}
		if(e.getSource() == NumPanel.btn9) //NINE
		{
			if(ResultField.getText().equals(String.valueOf(firstNum)))
			{
				ResultField.setText(s);
			}
		String numEntered = ResultField.getText() + NumPanel.btn9.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(ResultField.getText()));
		}
		if(e.getSource() == HexPanel.btnA) //A BUTTON
		{
			if(ResultField.getText().equals(String.valueOf(Integer.toString(firstNum,16))))
			{
				ResultField.setText(s);
			}
		String numEntered = ResultField.getText() + HexPanel.btnA.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(ResultField.getText(),16))));
		}
		if(e.getSource() == HexPanel.btnB) //B BUTTON
		{
			if(ResultField.getText().equals(String.valueOf(Integer.toString(firstNum,16))))
			{
				ResultField.setText(s);
			}
		String numEntered = ResultField.getText() + HexPanel.btnB.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(ResultField.getText(),16))));
		}
		if(e.getSource() == HexPanel.btnC) //C BUTTON
		{
			if(ResultField.getText().equals(String.valueOf(Integer.toString(firstNum,16))))
			{
				ResultField.setText(s);
			}
		String numEntered = ResultField.getText() + HexPanel.btnC.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(ResultField.getText(),16))));
		}
		if(e.getSource() == HexPanel.btnD) //D BUTTON
		{
			if(ResultField.getText().equals(String.valueOf(Integer.toString(firstNum,16))))
			{
				ResultField.setText(s);
			}
		String numEntered = ResultField.getText() + HexPanel.btnD.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(ResultField.getText(),16))));
		}
		if(e.getSource() == HexPanel.btnE) //E BUTTON
		{
			if(ResultField.getText().equals(String.valueOf(Integer.toString(firstNum,16))))
			{
				ResultField.setText(s);
			}
		String numEntered = ResultField.getText() + HexPanel.btnE.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(ResultField.getText(),16))));
		}
		if(e.getSource() == HexPanel.btnF) //F BUTTON
		{
			if(ResultField.getText().equals(String.valueOf(Integer.toString(firstNum,16))))
			{
				ResultField.setText(s);
			}
		String numEntered = ResultField.getText() + HexPanel.btnF.getText();
		ResultField.setText(numEntered);
		getBitField(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(ResultField.getText(),16))));
		}
		if(e.getSource() == ClearPanel.CButton) //CLEAR ALL
		{
			ResultField.setText("0");
			firstNum = 0;
			secondNum = 0;
			result = 0;
			BitField.setText("0");
		}
		if(e.getSource() == ClearPanel.CEButton) //CLEAR
		{
			ResultField.setText("0");
			
		}
		if(e.getSource() == OpPanel.addButton) //ADD
		{
			setFirstNum();
			ResultField.setText("");
			operations = "+";
		}
		if(e.getSource() == OpPanel.subButton) //SUBTRACT
		{
			setFirstNum();
			operations = "-";
		}
		if(e.getSource() == OpPanel.multButton) //MULTIPLY
		{
			setFirstNum();
			ResultField.setText("");
			operations = "*";
		}
		if(e.getSource() == OpPanel.divButton) //DIVIDE
		{
			setFirstNum();
			ResultField.setText("");
			operations = "/";
		}
		if(e.getSource() == modButton) //MODULUS
		{
			setFirstNum();
			ResultField.setText("");
			operations = "%";
		}
		if(e.getSource() == ClearPanel.signButton) // +/-
		{
			if(!ResultField.getText().equals(""))
			{
				int op = 0;
				if(BasePanel.Hex.isSelected()){
					op = Integer.parseInt(ResultField.getText(),16);
					op = op*(-1);
					ResultField.setText(Integer.toHexString(op));
				}
				if(BasePanel.Dec.isSelected())
					op = Integer.parseInt(ResultField.getText());
				if(BasePanel.Oct.isSelected())
					op = Integer.parseInt(ResultField.getText(),8);
				if(BasePanel.Bin.isSelected())
					op = Integer.parseInt(ResultField.getText(),2);
			
			// ResultField.setText(String.valueOf(op));
			getBitField(Integer.parseInt(ResultField.getText()));
			}
			else
			{
			ResultField.setText("0");
			}
		}
		if(e.getSource() == ClearPanel.backButton) //BACK
		{
			String back = null;
			if(ResultField.getText().length() > 0) 
			{
				StringBuilder str = new StringBuilder(ResultField.getText()); //Making a technically mutable String
				str.deleteCharAt(ResultField.getText().length()-1);
				back = str.toString();
				ResultField.setText(back);
			}
		}
		if(e.getSource() == menuBar.viewBtn) //MENU BUTTON, VIEW
		{
			if(isVis == true) //If the Calculator is currently visible, upon clicking "View" it will hide all panels
			{
				isVis = false;
				ResultField.setVisible(false);
				BitField.setVisible(false);
				BasePanel.setVisible(false);
				WordPanel.setVisible(false);
				HexPanel.setVisible(false);
				NumPanel.setVisible(false);
				ClearPanel.setVisible(false);
				zeroButton.setVisible(false);
				OpPanel.setVisible(false);
				equalsButton.setVisible(false);
				modButton.setVisible(false);
			}
			else if(isVis == false) //If the Calculator is currently not visible, upon clicking "View" it will bring back all panels
			{
				isVis = true;
				if(ResultField.getText().equals("0"));
					ResultField.setText("0");
				ResultField.setVisible(true);
				BitField.setVisible(true);
				BasePanel.setVisible(true);
				WordPanel.setVisible(true);
				HexPanel.setVisible(true);
				NumPanel.setVisible(true);
				ClearPanel.setVisible(true);
				zeroButton.setVisible(true);
				OpPanel.setVisible(true);
				equalsButton.setVisible(true);
				modButton.setVisible(true);
			}
		}
		if(e.getSource() == menuBar.editBtn) //EDIT BUTTON (Saves result to clip board, thanks again Google)
		{
			StringSelection stringSelection = new StringSelection(ResultField.getText()); //Gets text from ResultField
			Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard(); //Gets clip board
			clip.setContents(stringSelection, null); //Sets clip board to contents of ResultField
			System.out.println("Result field copied to clipboard "); //notifies user that it is has copied the text to the clipboard
		}
		if(e.getSource() == menuBar.helpBtn) //HELP BUTTON (takes you to Windows support site if you press button;
		{ 																				//thanks Google x3)
			String url = "https://support.microsoft.com/en-us/help/14089/windows-8-calculator-app-faq"; //URL of Microsoft help website for calculator
			if(Desktop.isDesktopSupported())
			{
				try 
				{
					Desktop.getDesktop().browse(new URI(url)); //Tries to open help site
				} 
				catch (IOException | URISyntaxException e1) 
				{
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource() == equalsButton) //EQUALS                                     
		{
			if(BasePanel.Hex.isSelected()) //Checks base and sets second number to equivalent value
				secondNum = Integer.parseInt(ResultField.getText(),16);
			if(BasePanel.Dec.isSelected())
				secondNum = Integer.parseInt(ResultField.getText());
			if(BasePanel.Oct.isSelected())
				secondNum = Integer.parseInt(ResultField.getText(),8);
			if(BasePanel.Bin.isSelected())
				secondNum = Integer.parseInt(ResultField.getText(),2);
			if(operations == "+")
			{
				result = firstNum + secondNum;
				checkBase(result);
				operations = ""; //Clears operations String
				reset = true; //Sets flag to tell clear() function that it will be the beginning of a new calculation
			}
			else if(operations == "-")
			{
				result = firstNum - secondNum;
				checkBase(result);
				operations = "";
				reset = true;
			}
			else if(operations == "*")
			{
				result = firstNum * secondNum;
				checkBase(result);
				operations = "";
				reset = true;
			}
			else if(operations == "%")
			{
				result = firstNum % secondNum;
				checkBase(result);
				operations = "";
				reset = true;
			}
			else if(operations == "/")
			{
				if(secondNum == 0)
				{
					ResultField.setText("0");
					reset = true;
				}
				else
				{
					result = firstNum / secondNum;
					checkBase(result);
				}
				operations = "";
				reset = true;
			}
			else if(operations == "")
			{
				
				firstNum = secondNum;
				result = secondNum;
				checkBase(result);
				reset = true;
			}
			if(ResultField.getText().equals(""))
			{
				BitField.setText(Integer.toString(0));
			}
			else
			{
				getBitField(result);
			}
		}
	}
//END ACTIONPERFORMED------------------------------------------------------------------------------------------------------------
	public String convertBase(String number, int base1, int base2) //Converts the base of the number in the ResultField, and gets called everytime a new radio button is pressed.
	{
		currentBase = base2;
		if(number.equals("a")) //If statements for dealing with conversions of A-F
		{
			if(currentBase == 10)
				return "10";
			else if(currentBase == 8)
				return "12";
			else if(currentBase == 2)
				return "1010";
		}
		if(number.equals("b"))
		{
			if(currentBase == 10)
				return "11";
			else if(currentBase == 8)
				return "13";
			else if(currentBase == 2)
				return "1011";
		}
		if(number.equals("c"))
		{
			if(currentBase == 10)
				return "12";
			else if(currentBase == 8)
				return "14";
			else if(currentBase == 2)
				return "1100";
		}
		if(number.equals("d"))
		{
			if(currentBase == 10)
				return "13";
			else if(currentBase == 8)
				return "15";
			else if(currentBase == 2)
				return "1101";
		}
		if(number.equals("e"))
		{
			if(currentBase == 10)
				return "14";
			else if(currentBase == 8)
				return "16";
			else if(currentBase == 2)
				return "1110";
		}
		if(number.equals("f"))
		{
			if(currentBase == 10)
				return "15";
			else if(currentBase == 8)
				return "17";
			else if(currentBase == 2)
				return "1111";
		}
			return Integer.toString(Integer.parseInt(number,base1),base2);
	}
	public void getBitField(int num) //Method to make binary representation of whatever is in calculator's text field
	{
		int count = 0;
		String binNum = Integer.toString(num,2);
		String temp = "";
		while(binNum.length() < 64) //makes a 32 number String
			binNum = "0" + binNum;
		for(int i = 0; i < binNum.length(); ++i) //Breaks String every 4 digits for easier representation of numbers
		{
			if(i % 4 == 0)
				temp += " ";
			temp += binNum.charAt(i);
			count++;
		}
		BitField.setText(temp);
		if(num < 0) //Creates the bit field if the number is negative
		{
			String binary = Integer.toBinaryString(num);
			long lNeg = Long.parseLong(binary, 2);
			int i = (int)lNeg;
			int countNeg = 0;
			String tempNeg = "";
			while(binNum.length() < 64)
			{
				binNum = "0" + binNum;
			}
				
			for(int x = 0; x < binary.length(); ++x)
			{
				if(x % 4 == 0)
					tempNeg += " ";
				tempNeg += binary.charAt(x);
				countNeg++;
			}
			BitField.setText(tempNeg);
			
		}
	}
	public void clear() //Clears the ResultField after every calculation as to prevent separate calculations from concatenating onto each other
	{
		if(reset)
		{
			ResultField.setText("");
			reset = false;
		}
	}
	public void checkBase(int number) //Checks base of the result of the arithmetic operation, and sets firstNum to the result
	{
		if(BasePanel.Hex.isSelected())
		{
		ResultField.setText(Integer.toString(number,16));
		firstNum = number;
		}
		if(BasePanel.Dec.isSelected())
		{
		ResultField.setText(Integer.toString(number));
		firstNum = number;
		}
		if(BasePanel.Oct.isSelected())
		{
		ResultField.setText(Integer.toString(number,8));
		firstNum = number;
		}
		if(BasePanel.Bin.isSelected())
		{
		ResultField.setText(Integer.toString(number,2));
		firstNum = number;
		}
	}
	public void setFirstNum()
	{
		if(BasePanel.Hex.isSelected())
			firstNum = Integer.parseInt(Integer.toString(Integer.parseInt(ResultField.getText(),16)));
		if(BasePanel.Dec.isSelected())
			firstNum = Integer.parseInt(ResultField.getText());
		if(BasePanel.Oct.isSelected())
			firstNum = Integer.parseInt(Integer.toString(Integer.parseInt(ResultField.getText(),8)));
		if(BasePanel.Bin.isSelected())
			firstNum = Integer.parseInt(Integer.toString(Integer.parseInt(ResultField.getText(),2)));
	}
}
