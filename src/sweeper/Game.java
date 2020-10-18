package sweeper;

public class Game {
	
	private Bomb bomb;
	private Flag flag; //описываем переменную
	private GameState state; 
	
	public GameState getState() { ///геттер дл€ получени€ статуса state
		return state;
	}

	
	
	public Game (int cols, int rows, int bombs) {///использование координат и количество бомб
		
		Ranges.setSize(new Coord (cols, rows));
		bomb = new Bomb(bombs);
		flag = new Flag(); //инициализируем еЄ
	}
	
	public void start () { //начало игры
		bomb.start();
		flag.start();//вызов функций
		state = GameState.PLAYED; ///когда игра начинаетс€ мы в поле state записываем состаение PLATED

	}
	
	public Box getBox (Coord coord) {
		if (flag.get(coord) == Box.OPENED) ///если во флаге находитс€ открыто 
		return bomb.get(coord); /// то возвращает бомбы 
		else 
		return flag.get(coord); // то возвращает флаги
		

	}


	public void pressLeftButton(Coord coord) {
		if (gameOver()) return;
		openBox (coord);
		checkWinner();
	
	}
	private void checkWinner() { ///проверка на победу
		if (state == GameState.PLAYED) ///если состо€ние играть
			if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs()) //если количество закрытых клеток = количеству бомб
				state = GameState.WINNER;
	}
	
	
	private void openBox(Coord coord) {
		switch (flag.get(coord)) {  ///из флага берем всЄ что там находитс€ по координатам
		case OPENED: setOpenedToClosedBoxesAroundNumber(coord); return;
		case FLAGED: return;
		case CLOSED:  ////если состо€ние закрыто, то надо смотреть что на нижнем слое
			switch (bomb.get(coord)) {  
			case ZERO: openBoxesAround (coord); return;
			case BOMB: openBombs(coord); return;
			default: flag.setOpenedToBox (coord);  return; ///дефолт это если что иное кроме вышеперечисленных состо€ний, например цифры
				
			}
		default:
			break;
		}
		
		
	}







	private void setOpenedToClosedBoxesAroundNumber(Coord coord) {///открывает закрытые €чейки вокруг числа
				if (bomb.get (coord) !=Box.BOMB) ///если в этой клетке нет бомбы
				 if (flag.getCountOfFlagetBoxesAround(coord) == bomb.get(coord).getNumber()) ///если число равно количеству флагов вокруг
					 for (Coord around : Ranges.getCoordsAround(coord)) ///перебираем всеклетки вокруг
						 if (flag.get(around) == Box.CLOSED)///если клетка закрыта
							 openBox(around);///открываем
		
	}



	private void openBombs(Coord bombed) {  ///открыть все бомбы
		// TODO Auto-generated method stub
		state = GameState.BOMBED; /// значение того что ты проиграл
		flag.setBombedToBox (bombed); //передаем координату где мы взорвались, чтобы нарисовать бомбу
		for (Coord coord: Ranges.getAllCords())
			if (bomb.get(coord)== Box.BOMB)
				flag.setOpenedToClosedBombBox (coord); ///открыть клетку на закрытой клетке с бомбой 
			else 
				flag.setNobombToFlagedSafeBox (coord);///если нет бомбы на обозначенной флагом безопасный бокс
	
	}



	private void openBoxesAround(Coord coord) {
		// TODO Auto-generated method stub
		flag.setOpenedToBox (coord); //метод открывающий клетку в координате
		for (Coord around : Ranges.getCoordsAround(coord)) ///перебираем все клетки вокруг ренджес
			openBox (coord); //вызываем метод выше и он заново рассматривает открываемую клетку на предмет состо€ни€
	}



	public void pressRightButton(Coord coord) {
		if (gameOver()) return;
		flag.toggleFlagedToBox (coord); //метод став€щий флаг клетку в координатах
		
	}



	private boolean gameOver() {
		// TODO Auto-generated method stub.
		if (state == GameState.PLAYED) ///если статус еще играем
			return false;//то продолжить
		start(); //если нет, то перезапуск
		return true;
	}
	
}
