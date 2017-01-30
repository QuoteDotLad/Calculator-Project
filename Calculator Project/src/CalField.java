import java.awt.*;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CalField extends JTextField
{
	private JTextField field;
	public CalField()
	{
		setLayout(new GridLayout(1, 10, 1, 1));
		setBackground(Color.WHITE);
		setSize(478, 49);
		setLocation(10, 23);
		setEditable(false);
		setHorizontalAlignment(SwingConstants.RIGHT);
		field = new JTextField("0",16);
	}
	
	public void setText()
	{
		field.setText(field.getText());
	}

}
