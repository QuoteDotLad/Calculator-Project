import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;


public class OpButtons extends JPanel
{
	JButton divButton, multButton, subButton, addButton;
	OpButtons()
	{
		divButton = new JButton("/");
		divButton.setBackground(new Color(240, 248, 255));
		multButton = new JButton("*");
		multButton.setBackground(new Color(240, 248, 255));
		subButton = new JButton("-");
		subButton.setBackground(new Color(240, 248, 255));
		addButton = new JButton("+");
		addButton.setBackground(new Color(240, 248, 255));
	}
}
