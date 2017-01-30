import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;

public class HexButtons extends JPanel 
{
	JButton btnA, btnB, btnC, btnD, btnE, btnF;
	HexButtons()
	{
	btnA = new JButton("A");
	btnA.setBackground(new Color(240, 248, 255));
	add(btnA);
	btnB = new JButton("B");
	btnB.setBackground(new Color(240, 248, 255));
	add(btnB);
	btnC = new JButton("C");
	btnC.setBackground(new Color(240, 248, 255));
	add(btnC);
	btnD = new JButton("D");
	btnD.setBackground(new Color(240, 248, 255));
	add(btnD);
	btnE = new JButton("E");
	btnE.setBackground(new Color(240, 248, 255));
	add(btnE);
	btnF = new JButton("F");
	btnF.setBackground(new Color(240, 248, 255));
	add(btnF);
	}
	
}
