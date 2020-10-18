package sweeper;

public enum Box {   ///enum это перечисления
	
	ZERO,
	NUM1,
	NUM2,
	NUM3,
	NUM4,
	NUM5,
	NUM6,
	NUM7,
	NUM8,
	BOMB,
	OPENED,
	CLOSED,
	FLAGED,
	BOMBED,
	NOBOMB;
	
	public Object image; ///хранение картинки
	
	Box getNextNumberBox() {
		return Box.values()[this.ordinal()+1]; //весь массив перечисления и следующий номер
	}
	
	int getNumber () {
		return this.ordinal(); ///получить текущий номер этого объекта
	}
}
