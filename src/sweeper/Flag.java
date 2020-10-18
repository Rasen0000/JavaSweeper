package sweeper;

class Flag {
	 private Matrix flagMap; ///матрица флагов
	 private int countOfClosedBoxes;
	 
	 void start() {
		 flagMap = new Matrix (Box.CLOSED); ///иницализаци€ матрицы на закрытые €чейки
		 countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y; ///переменна€ изначальна равна площади игрового пол€
	 }
	 
	 Box get (Coord coord)
	 {
		return flagMap.get(coord); //возврат того, что там находитс€
	 }

	 void setOpenedToBox(Coord coord) {///открывает €чейки
		// TODO Auto-generated method stub
		flagMap.set(coord, Box.OPENED);
		countOfClosedBoxes --; ///при открытии €чеек уменьшает на 1 количество закрытых €чеек
	}
	 
		public void toggleFlagedToBox(Coord coord) {
			// TODO Auto-generated method stub
			switch (flagMap.get(coord)) { ////получаем координату из флагмап
			case FLAGED: setClosedToBox (coord); break; ///если флаг - то метод setClosedToBox
			case CLOSED: setFlagedToBox (coord); break; ///если флаг - то метод setFlagedToBox
			default:
				break;
			}
		}
	 
		private void setFlagedToBox(Coord coord) {  ///ставит флаги
		// TODO Auto-generated method stub
		flagMap.set(coord, Box.FLAGED);
	}



	private void setClosedToBox(Coord coord) { ///закрывает €чейки
		// TODO Auto-generated method stub
		flagMap.set(coord, Box.CLOSED);
	}

	 int getCountOfClosedBoxes() {
		// TODO Auto-generated method stub
		return countOfClosedBoxes;
	}

	 void setBombedToBox(Coord coord) { 
		// TODO Auto-generated method stub
		flagMap.set(coord, Box.BOMBED); ///устанавливаем бомбу
	}

	 void setOpenedToClosedBombBox(Coord coord) {///открыть клетку на закрытой клетке с бомбой 
		// TODO Auto-generated method stub
		if (flagMap.get(coord)== Box.CLOSED) ///если координата закрыта
			flagMap.set(coord, Box.OPENED); ///то еЄ нужно открыть
	}

	 void setNobombToFlagedSafeBox(Coord coord) {///если нет бомбы на обозначенной флагом безопасный бокс
		// TODO Auto-generated method stub
		if (flagMap.get(coord) == Box.FLAGED)///если помечено флагом
			flagMap.set(coord, Box.NOBOMB); ///а бомбы нет
	}
	 


	 int getCountOfFlagetBoxesAround(Coord coord) {
		int count = 00;
		 for (Coord around: Ranges.getCoordsAround(coord)) ///перебираем все клетки вокруг указанной 
			 if (flagMap.get(around) == Box.FLAGED) ///если на этой клетке есть флаг 
				 count ++; ///то число увеличиваетс€
		 return count;
	}
}

