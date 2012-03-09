package domain;

import java.util.Date;

public class Crop {
	private CropList crop;
	private Date planted;
	
	public Crop(String type)
	{
		this(type,Controller.getInstance().getClock().getDate());
	}
	public Crop(String type, Date planted)
	{
		crop = CropList.valueOf(type);
		planted = Controller.getInstance().getClock().getDate();
		
	}
	
	public static CropList[] getTypes()
	{
		return CropList.values();
	}
	public boolean isReady()
	{
		return (planted.getTime() + (Clock.SECONDSADAY * crop.growdays))
				< Controller.getInstance().getClock().getTime();
	}
	
	
}
