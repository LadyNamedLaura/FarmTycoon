package domain;

public enum Product {
	POTATO		( 20),
	CARROT		( 30),
	WHEAT		( 40),
	CORN		( 60),
	GRAPE		( 80),
	LETTUCE		( 90),
	TOMATO		(110),
	STRAWBERRY	(145),
	RASPBERRY	(190),
	COCOA		(220),
	SOY			(290),
	EGGS		(30),
	MILK		(70);

	final int price;
	public int getPrice()	{ return price; }
	Product(int price) 		{ this.price = price; }
}
