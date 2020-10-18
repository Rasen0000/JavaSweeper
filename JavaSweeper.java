import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;
import sweeper.Box;

public class JavaSweeper extends JFrame {
	
	private JPanel panel; //переменная
	private final int COLS =  15; ///создание константы
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
	initFrame();//вызов инициализаций
	
}

private void initPanel() {
	panel= new JPanel() //создали
	{
		@Override
		protected void paintComponent (Graphics g)
		{
			super.paintComponents(g);
			for (Box box : Box.values())
			g.drawImage((Image)box.image,
					box.ordinal()* IMAGE_SIZE, 0, this);	///подгружаем все картинки			
		}
	};
	panel.setPreferredSize(new Dimension(COLS * IMAGE_SIZE, ROWS * IMAGE_SIZE)); //размер окна
	add (panel);
	
}

private void initFrame() { ///конструктор
	pack(); ///метод вызывающий минимальный размер контейнера, который достаточен для отображения всех элементов
	
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); ///закрытие программы после нажатия на крестик
	setTitle ("Java Sweeper");//заголовок
	setLocationRelativeTo(null); ///по центру
	setResizable(false); ///не изменяемый размер окна
	setVisible(true); ///чтобы было видно
}

private void setImage() { ///установка картинок
	for (Box box : Box.values()) ///цикл на подгрузку всех имен картинок
		box.image = getImage(box.name().toLowerCase());
}

private Image getImage (String name) { //функция получения картинки
	String filename = "img/" + name + ".png"; ///определения имени файла
	ImageIcon icon = new ImageIcon (getClass().getResource(filename)); ///доступ к ресурсам по имени файла
	return icon.getImage(); //возвращаем чтобы получить картинку
}

}
