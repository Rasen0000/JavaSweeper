package sweeper;

class Flag {
	 private Matrix flagMap; ///������� ������
	 private int countOfClosedBoxes;
	 
	 void start() {
		 flagMap = new Matrix (Box.CLOSED); ///������������ ������� �� �������� ������
		 countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y; ///���������� ���������� ����� ������� �������� ����
	 }
	 
	 Box get (Coord coord)
	 {
		return flagMap.get(coord); //������� ����, ��� ��� ���������
	 }

	 void setOpenedToBox(Coord coord) {///��������� ������
		// TODO Auto-generated method stub
		flagMap.set(coord, Box.OPENED);
		countOfClosedBoxes --; ///��� �������� ����� ��������� �� 1 ���������� �������� �����
	}
	 
		public void toggleFlagedToBox(Coord coord) {
			// TODO Auto-generated method stub
			switch (flagMap.get(coord)) { ////�������� ���������� �� �������
			case FLAGED: setClosedToBox (coord); break; ///���� ���� - �� ����� setClosedToBox
			case CLOSED: setFlagedToBox (coord); break; ///���� ���� - �� ����� setFlagedToBox
			default:
				break;
			}
		}
	 
		private void setFlagedToBox(Coord coord) {  ///������ �����
		// TODO Auto-generated method stub
		flagMap.set(coord, Box.FLAGED);
	}



	private void setClosedToBox(Coord coord) { ///��������� ������
		// TODO Auto-generated method stub
		flagMap.set(coord, Box.CLOSED);
	}

	 int getCountOfClosedBoxes() {
		// TODO Auto-generated method stub
		return countOfClosedBoxes;
	}

	 void setBombedToBox(Coord coord) { 
		// TODO Auto-generated method stub
		flagMap.set(coord, Box.BOMBED); ///������������� �����
	}

	 void setOpenedToClosedBombBox(Coord coord) {///������� ������ �� �������� ������ � ������ 
		// TODO Auto-generated method stub
		if (flagMap.get(coord)== Box.CLOSED) ///���� ���������� �������
			flagMap.set(coord, Box.OPENED); ///�� � ����� �������
	}

	 void setNobombToFlagedSafeBox(Coord coord) {///���� ��� ����� �� ������������ ������ ���������� ����
		// TODO Auto-generated method stub
		if (flagMap.get(coord) == Box.FLAGED)///���� �������� ������
			flagMap.set(coord, Box.NOBOMB); ///� ����� ���
	}
	 


	 int getCountOfFlagetBoxesAround(Coord coord) {
		int count = 00;
		 for (Coord around: Ranges.getCoordsAround(coord)) ///���������� ��� ������ ������ ��������� 
			 if (flagMap.get(around) == Box.FLAGED) ///���� �� ���� ������ ���� ���� 
				 count ++; ///�� ����� �������������
		 return count;
	}
}

