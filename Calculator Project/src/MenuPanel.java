import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;

public class MenuPanel extends JPanel 
{
	JButton viewBtn, editBtn, helpBtn;
	MenuPanel()
	{
		viewBtn = new JButton("View");
		viewBtn.setBackground(new Color(240, 248, 255));
		add(viewBtn);
		editBtn = new JButton("Edit");
		editBtn.setBackground(new Color(240, 248, 255));
		add(editBtn);
		helpBtn = new JButton("Help");
		helpBtn.setBackground(new Color(240, 248, 255));
		add(helpBtn);
	}

}
