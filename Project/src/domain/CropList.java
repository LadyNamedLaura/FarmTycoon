package domain;

public enum CropList {
	CARROT(2,200,400),
	RASPBERRY(4,100,500);
	
	public final int growdays;
	public final int seedprice;
	public final int marketprice;
	
	private CropList(int growdays, int seedprice, int marketprice)
	{
		this.growdays=growdays;
		this.seedprice=seedprice;
		this.marketprice=marketprice;
	}
}
