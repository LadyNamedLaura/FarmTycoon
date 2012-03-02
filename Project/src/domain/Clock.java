package domain;

import java.util.Date;

public class Clock {
	private final long STARTTIME = 1325397600; // 1 jan. 2012 6:00
	private double multiplier;
	private long offset;
	
	public Clock(double multiplier){
		this.multiplier = multiplier;
		this.offset=new Date().getTime();
	}
	
	public long getTime(){
		long delta = new Date().getTime()-offset;
		return (long) (delta*multiplier) + STARTTIME;
	}
	public Date getDate(){
		return new Date(this.getTime());
	}
	public void setMultiplier(double multiplier){
		//recalculate offset
		long newoffset=this.getTime()-STARTTIME;
	}
}
