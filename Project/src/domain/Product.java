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
	MILK		(70),
	BUTTER		(90),
	CHEESE		(180),
	FLOUR		(300),
	JUICE		(350),
	SALAD		(2000),
	CORNOIL		(2520),
	CHOCOLATE	(1890),
	BREAD		(920),
	TRUFFLES	(12540);

	final int price;
	public int getPrice()	{ return price; }
	Product(int price) 		{ this.price = price; }
}
