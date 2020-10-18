package sweeper;

class Bomb {
	
	private Matrix bombMap;
	private int totalBombs;
	
	Bomb (int totalBombs){ ///конструктор принимающий количество бомб и сохран€ющий его
		
		this.totalBombs = totalBombs;
		fixBombsCount ();
	}
	
	void start () { //метод создающий экземпл€р bobmMap
		
		bombMap = new Matrix (Box.ZERO);
		for (int j = 0; j < totalBombs; j++) ///цикл который перебирает общее количество бомб и размещает все эти бомбы
		placeBomb();
	}
	
	Box get (Coord coord) { ////геттер дл€ узнавани€ что находитс€ в той или иной клетке
		return bombMap.get(coord); ///принимает координату и возвращает что в этой координате есть
	}
	
	private void fixBombsCount () { ///фиксируем количество бомб
		int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2; //максимальное количество бомб это площадь деленна€ на 2
		if (totalBombs > maxBombs) //если общее количество бомб больше максимального
			totalBombs = maxBombs;///то общее количество равно максимальному
	}
	
	private void placeBomb() { ///функци€ дл€ размещений одной бомбы
		while (true) ///цикл выбирающий координату в которой бомбы еще нет
		{
		Coord coord = Ranges.getRandomCoord(); ///найти случайную координату
		if (Box.BOMB == bombMap.get(coord)) //если в найденном месте уже находитс€ бомба 
			continue; //то дальше
		bombMap.set (coord, Box.BOMB); //размещение бомбы в координате выше
		incNumbersAroundBomb (coord);
		break; ///выход из вечного цикла
		}
			
	}
	
	private void incNumbersAroundBomb (Coord coord) { //функци€ увеличивающа€ цифры вокруг бомб
		for (Coord around : Ranges.getCoordsAround(coord))///цикл перебор все клетки вокруг текущей
			if (Box.BOMB != bombMap.get(around)) //проверка что в этих координатах находитс€ не бомба
			bombMap.set(around, bombMap.get(around).getNextNumberBox()); ///и установить в координате каждой клетки
		
	}

	int getTotalBombs() {
		// TODO Auto-generated method stub
		return totalBombs;
	}
	

}
//2.16.23
