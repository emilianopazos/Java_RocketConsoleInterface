package java_Rockets;

public class Propeller implements Runnable {
	////ATRIBUTES
	private int maxPower;
	private int currentPower;
	private int targetPower;
	
	public boolean reachTargetPower() {
		if(currentPower == targetPower) {
			return true;
		}else {
			return false;
		}
		
	}
	
	////CONSTRUCTORS
	public Propeller() {
		this.currentPower = 0;		
	}

	
	public Propeller(int maxPower) {
		this.maxPower = maxPower;
		this.currentPower = 0;
		this.targetPower = 0;
	}
	
	
	////METHODS
	public void acelerate() {
		//this.currentPower += 1;
		//System.out.println(this.currentPower);
		this.setCurrentPower(this.currentPower + 1);
	}
	
	public void decelerate() {
		this.currentPower = this.currentPower - 1;
	}
	
	@Override
	public void run() {
		
		do {
			
			if(this.currentPower < this.targetPower) {
				this.acelerate();
				
			}else if(this.currentPower > this.targetPower){
				this.decelerate();
			}
			
			
			try {
				Thread.sleep((long) 500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}while(!reachTargetPower());
				
		
	}

	///GETTERS AND SETTERS
	
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



	public int getTargetPower() {
		return targetPower;
	}



	public boolean setTargetPower(int targetPower) {
		if (targetPower <= this.maxPower) {
			this.targetPower = targetPower;
			//this.run();
			//this.start();
			return true;
		}else {
			System.out.println("La potencia elegida no se puede alcanzar en este propulsor");
			return false;
		}
		
	}

	
	

}
