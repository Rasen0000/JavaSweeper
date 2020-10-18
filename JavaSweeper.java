import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;
import sweeper.Box;

public class JavaSweeper extends JFrame {
	
	private JPanel panel; //����������
	private final int COLS =  15; ///�������� ���������
	private final int ROWS =  1;
	private final int BOMBS =  10;
	private final int IMAGE_SIZE = 50;
	
	public static void main(String[] args)
{
new JavaSweeper();	
}


private  JavaSweeper() {
	game = new Game (COLS, ROWS, BOMBS);
	game.start();
	setImage();
	initPanel();
	initFrame();//����� �������������
	
}

private void initPanel() {
	panel= new JPanel() //�������
	{
		@Override
		protected void paintComponent (Graphics g)
		{
			super.paintComponents(g);
			for (Box box : Box.values())
			g.drawImage((Image)box.image,
					box.ordinal()* IMAGE_SIZE, 0, this);	///���������� ��� ��������			
		}
	};
	panel.setPreferredSize(new Dimension(COLS * IMAGE_SIZE, ROWS * IMAGE_SIZE)); //������ ����
	add (panel);
	
}

private void initFrame() { ///�����������
	pack(); ///����� ���������� ����������� ������ ����������, ������� ���������� ��� ����������� ���� ���������
	
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); ///�������� ��������� ����� ������� �� �������
	setTitle ("Java Sweeper");//���������
	setLocationRelativeTo(null); ///�� ������
	setResizable(false); ///�� ���������� ������ ����
	setVisible(true); ///����� ���� �����
}

private void setImage() { ///��������� ��������
	for (Box box : Box.values()) ///���� �� ��������� ���� ���� ��������
		box.image = getImage(box.name().toLowerCase());
}

private Image getImage (String name) { //������� ��������� ��������
	String filename = "img/" + name + ".png"; ///����������� ����� �����
	ImageIcon icon = new ImageIcon (getClass().getResource(filename)); ///������ � �������� �� ����� �����
	return icon.getImage(); //���������� ����� �������� ��������
}

}
