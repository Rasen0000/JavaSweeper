package sweeper;

public class Coord {
	
	public int x;
	public int y;
	
	
	public Coord (int x, int y)
	{
		this.x = x;
		this.y = y;
	}


	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (o instanceof Coord) {  ///если передали координату
			
			Coord to = (Coord)o; //приведение типа
			return to.x == x && to.y == y;
		}
		return super.equals(o);
	}
	

}
