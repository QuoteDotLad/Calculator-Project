import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.Color;
public class WordRadio extends JPanel
{
	JRadioButton QWord, DWord, Word, Byte;
	WordRadio()
	{
		QWord = new JRadioButton("QWord");
		QWord.setBackground(new Color(240, 248, 255));
		DWord = new JRadioButton("DWord");
		DWord.setBackground(new Color(240, 248, 255));
		Word = new JRadioButton("Word");
		Word.setBackground(new Color(240, 248, 255));
		Byte = new JRadioButton("Byte");
		Byte.setBackground(new Color(240, 248, 255));
	}
}
