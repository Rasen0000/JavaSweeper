package sweeper;

class Matrix {

	private Box [] [] matrix;	
	
	Matrix (Box defaultBox){
		matrix = new Box [Ranges.getSize().x][Ranges.getSize().y]; ///матрица нужного размера
		for (Coord coord:Ranges.getAllCords()) ///цикл для перебора всех координат
			matrix [coord.x][coord.y] = defaultBox; 
	}
	
	Box get (Coord coord) {
		
		if (Ranges.inRange (coord))   ///проверка координат на пределы экрана
		return matrix [coord.x][coord.y];
		return null;
		
	}
	
	void set (Coord coord, Box box) {   ///установка значения
		if (Ranges.inRange (coord))
		matrix [coord.x][coord.y] = box;
	}


	
}


