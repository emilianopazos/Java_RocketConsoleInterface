package java_Rockets;

public class Propeller {
	
	private int maxPower;
	private int currentPower;
	
//	private int targetPower;
//	
//	public boolean reachTargetPower() {
//		if(currentPower == targetPower) {
//			return true;
//		}else {
//			return false;
//		}
//		
//	}
	
	public Propeller() {
		this.currentPower = 0;		
	}

	
	public Propeller(int maxPower) {
		this.maxPower = maxPower;
		this.currentPower = 0;		
	}

	
	public int getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
	}

	public int getCurrentPower() {
		return currentPower;
	}

	public void setCurrentPower(int targetPower) {
		if(targetPower > this.maxPower) {
			this.currentPower = maxPower;
		}else {
			this.currentPower = targetPower;
		}
		
	}

	
	

}
