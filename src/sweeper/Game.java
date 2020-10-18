package sweeper;

public class Game {
	
	private Bomb bomb;
	private Flag flag; //��������� ����������
	private GameState state; 
	
	public GameState getState() { ///������ ��� ��������� ������� state
		return state;
	}

	
	
	public Game (int cols, int rows, int bombs) {///������������� ��������� � ���������� ����
		
		Ranges.setSize(new Coord (cols, rows));
		bomb = new Bomb(bombs);
		flag = new Flag(); //�������������� �
	}
	
	public void start () { //������ ����
		bomb.start();
		flag.start();//����� �������
		state = GameState.PLAYED; ///����� ���� ���������� �� � ���� state ���������� ��������� PLATED

	}
	
	public Box getBox (Coord coord) {
		if (flag.get(coord) == Box.OPENED) ///���� �� ����� ��������� ������� 
		return bomb.get(coord); /// �� ���������� ����� 
		else 
		return flag.get(coord); // �� ���������� �����
		

	}


	public void pressLeftButton(Coord coord) {
		if (gameOver()) return;
		openBox (coord);
		checkWinner();
	
	}
	private void checkWinner() { ///�������� �� ������
		if (state == GameState.PLAYED) ///���� ��������� ������
			if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs()) //���� ���������� �������� ������ = ���������� ����
				state = GameState.WINNER;
	}
	
	
	private void openBox(Coord coord) {
		switch (flag.get(coord)) {  ///�� ����� ����� �� ��� ��� ��������� �� �����������
		case OPENED: setOpenedToClosedBoxesAroundNumber(coord); return;
		case FLAGED: return;
		case CLOSED:  ////���� ��������� �������, �� ���� �������� ��� �� ������ ����
			switch (bomb.get(coord)) {  
			case ZERO: openBoxesAround (coord); return;
			case BOMB: openBombs(coord); return;
			default: flag.setOpenedToBox (coord);  return; ///������ ��� ���� ��� ���� ����� ����������������� ���������, �������� �����
				
			}
		default:
			break;
		}
		
		
	}







	private void setOpenedToClosedBoxesAroundNumber(Coord coord) {///��������� �������� ������ ������ �����
				if (bomb.get (coord) !=Box.BOMB) ///���� � ���� ������ ��� �����
				 if (flag.getCountOfFlagetBoxesAround(coord) == bomb.get(coord).getNumber()) ///���� ����� ����� ���������� ������ ������
					 for (Coord around : Ranges.getCoordsAround(coord)) ///���������� ��������� ������
						 if (flag.get(around) == Box.CLOSED)///���� ������ �������
							 openBox(around);///���������
		
	}



	private void openBombs(Coord bombed) {  ///������� ��� �����
		// TODO Auto-generated method stub
		state = GameState.BOMBED; /// �������� ���� ��� �� ��������
		flag.setBombedToBox (bombed); //�������� ���������� ��� �� ����������, ����� ���������� �����
		for (Coord coord: Ranges.getAllCords())
			if (bomb.get(coord)== Box.BOMB)
				flag.setOpenedToClosedBombBox (coord); ///������� ������ �� �������� ������ � ������ 
			else 
				flag.setNobombToFlagedSafeBox (coord);///���� ��� ����� �� ������������ ������ ���������� ����
	
	}



	private void openBoxesAround(Coord coord) {
		// TODO Auto-generated method stub
		flag.setOpenedToBox (coord); //����� ����������� ������ � ����������
		for (Coord around : Ranges.getCoordsAround(coord)) ///���������� ��� ������ ������ �������
			openBox (coord); //�������� ����� ���� � �� ������ ������������� ����������� ������ �� ������� ���������
	}



	public void pressRightButton(Coord coord) {
		if (gameOver()) return;
		flag.toggleFlagedToBox (coord); //����� �������� ���� ������ � �����������
		
	}



	private boolean gameOver() {
		// TODO Auto-generated method stub.
		if (state == GameState.PLAYED) ///���� ������ ��� ������
			return false;//�� ����������
		start(); //���� ���, �� ����������
		return true;
	}
	
}
