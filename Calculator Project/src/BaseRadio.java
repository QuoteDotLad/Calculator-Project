import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Color;
public class BaseRadio extends JPanel
{
	JRadioButton Hex, Dec, Oct, Bin;
	BaseRadio()
	{
		Hex = new JRadioButton("Hex");
	Hex.setBackground(new Color(240, 248, 255));
	Dec = new JRadioButton("Dec");
	Dec.setBackground(new Color(240, 248, 255));
	Oct = new JRadioButton("Oct");
	Oct.setBackground(new Color(240, 248, 255));
	Bin = new JRadioButton("Bin");
	Bin.setBackground(new Color(240, 248, 255));
	}
	
}
