import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;

public class ClearButtons extends JPanel
{
	JButton backButton, CButton, CEButton, signButton;
	ClearButtons()
	{
	backButton = new JButton("\uF0E7");
	backButton.setBackground(new Color(240, 248, 255));
	backButton.setSize(49,40);
	add(backButton);
	CButton = new JButton("C");
	CButton.setBackground(new Color(240, 248, 255));
	CButton.setSize(49,40);
	add(CButton);
	CEButton = new JButton("CE");
	CEButton.setBackground(new Color(240, 248, 255));
	CEButton.setSize(49,40);
	add(CEButton);
	signButton = new JButton("\u00B1");
	signButton.setBackground(new Color(240, 248, 255));
	signButton.setSize(49,40);
	add(signButton);
	}
}
