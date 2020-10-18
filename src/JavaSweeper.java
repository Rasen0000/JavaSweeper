import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

public class JavaSweeper extends JFrame {
	
	private Game game;
	private JPanel panel; //переменна€
	private JLabel label;
	private final int COLS =  9; ///создание константы
	private final int ROWS =  9;
	private final int BOMBS =  10;
	private final int IMAGE_SIZE = 50;
	
	public static void main(String[] args)
{
new JavaSweeper();	
}


private  JavaSweeper() {
	
	game = new Game (COLS, ROWS, BOMBS); //экземпл€р игры
	game.start();
	setImages();
	initLabel();
	initPanel();
	initFrame();//вызов инициализаций
	
}

private void initLabel() { ///добавление метки на экран
	label = new JLabel("Welcome"); ///вывод надписи
	add (label, BorderLayout.SOUTH);	///добавление на форму вниз
	
}

private String getMessage() { ///функци€ котора€ определ€ет какой текст должен быть отображен в label
	switch (game.getState()) { ///получить состо€ние игры
	case PLAYED: return "Think twice";
	case BOMBED: return "Lose!";
	case WINNER: return "CONGRATULATIONS!";
	default : return null;
	}
	
}

private void initPanel() {
	panel= new JPanel() //создали
	{
		@Override
		protected void paintComponent (Graphics g)
		{
			super.paintComponents(g);
			for (Coord coord : Ranges.getAllCords()) {
	
				g.drawImage((Image) game.getBox(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this); ///обращаемс€ к конкретной картинке
			}
		}
	};
	
	panel.addMouseListener(new MouseAdapter(){ ///слушатель мышки

		@Override
		public void mousePressed(MouseEvent e) { //метод из оверсайзных
			int x = e.getX() / IMAGE_SIZE; ///координаты куда мышка ткнулась по x
			int y = e.getY() / IMAGE_SIZE;
			Coord coord  = new Coord (x,y); ///создаем координату 
			if (e.getButton() == MouseEvent.BUTTON1) ///кака€ кнопка была нажата - лева€
				game.pressLeftButton (coord);///вызов метода pressLeftButton
			if (e.getButton() == MouseEvent.BUTTON3) 
				game.pressRightButton (coord);
			if (e.getButton() == MouseEvent.BUTTON2) 
				game.start ();
			label.setText(getMessage());
			panel.repaint(); //перерисовка формы ///!!!!! ¬ќ«ћќ∆Ќќ здесь проблема
			
		}


		
		
	});
	
	panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE)); //размер окна
	add (panel);
	
}

private void initFrame() { ///конструктор
	
	
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); ///закрытие программы после нажати€ на крестик
	setTitle ("Java Sweeper");//заголовок
	
	setResizable(false); ///не измен€емый размер окна
	setVisible(true); ///чтобы было видно
	setIconImage(getImage("icon"));
	pack(); ///метод вызывающий минимальный размер контейнера, который достаточен дл€ отображени€ всех элементов
	setLocationRelativeTo(null); ///по центру
}

private void setImages () { ///установка картинок дл€ каждого экземпл€ра box
	for (Box box : Box.values()) 
		box.image = getImage(box.name().toLowerCase());
}

private Image getImage (String name) { //функци€ получени€ картинки
	String filename = "img/" + name + ".png"; ///определени€ имени файла
	ImageIcon icon = new ImageIcon (getClass().getResource(filename)); ///доступ к ресурсам по имени файла
	return icon.getImage(); //возвращаем чтобы получить картинку
}

}
///3.16.00

