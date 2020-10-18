package sweeper;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {
	
	private static Coord size;
	private static ArrayList<Coord> allCoords; //список для всех координат
	private static Random random = new Random(); ///статичное поле для генератора случайных чисел
	public static void setSize (Coord _size) {
		size = _size;
		allCoords = new ArrayList<Coord>(); ///заполнение всех координат
		for (int y = 0; y <size.y; y ++)
			for (int x =0; x<size.x; x ++)
				allCoords.add(new Coord (x,y));
	}
	
	public static Coord getSize() {
		return size;
	}
	
	public static ArrayList<Coord> getAllCords(){
		return allCoords;
		
	}

	 static boolean inRange(Coord coord) {
		// проверка на пределы координат
		return coord.x>=0 && coord.x < size.x && coord.y>=0 && coord.y < size.y;
	}
	 
	 static Coord getRandomCoord(){  //метод возвращающий случайную координату
		 
		 return new Coord (random.nextInt(size.x),
				 			random.nextInt(size.y));
	 }
	 
	 static ArrayList<Coord> getCoordsAround (Coord coord) { ///перебор всех клеток вокруг заданной
		 Coord around;
		 ArrayList<Coord> list = new ArrayList<Coord>();//список всех координат которые будут вокруг
		 for (int x = coord.x-1; x <=coord.x+1; x++)	 ///перебор всех переменных начиная от координаты на одну левее и заканчивая на одну правее
			 for (int y = coord.y-1; y <=coord.y+1; y++) 
				 if (inRange(around = new Coord (x,y)))
					 if (!around.equals(coord))  ///если around не равно нашей координате
						 list.add(around); //добавляется around
		return list;
	 }
	 

}










