package java_Rockets;


public class Rockets_main {
	
	boolean executeRockets_main = true;
	
	public void main() {
		
		System.out.println("Soy el main de rockets");
		Rocket my1stRocket = new Rocket("32WESSDS", 3);//Utiliza el constructor Propeller() que los inicializa en currentPower = 0
		my1stRocket.setMaxPropPower(new int[] { 10,30,80 });		
		System.out.println(my1stRocket.getCodeName()+" "+my1stRocket.getAllMaxPropPower());
		
		Rocket my2ndRocket = new Rocket("LDSFJA32", 6);
		my2ndRocket.setMaxPropPower(new int[] { 30,40,50,50,30,10 });		
		System.out.println(my2ndRocket.getCodeName()+" "+my2ndRocket.getAllMaxPropPower());
		
		
		String prevRocket1PropState = my1stRocket.getAllCurrentPower();
		String prevRocket2PropState = my2ndRocket.getAllMaxPropPower();
		
		///TO DO. CREATE SET TARGET POWER
		my1stRocket.setAllTargetPropPower(new int[] { 5, 10, 20});
		my2ndRocket.setAllTargetPropPower(new int[] { 5, 0, 10, 5, 0, 10});
		
		
				
		do {
			String currentRocket1PropState = my1stRocket.getAllCurrentPower();
			String currentRocket2PropState = my2ndRocket.getAllCurrentPower();
			boolean changeInAccel = false;
			
			if (!currentRocket1PropState.contentEquals(prevRocket1PropState)) {
				//System.out.print(" rocket1 :" + currentRocket1PropState);
				prevRocket1PropState = currentRocket1PropState;
				changeInAccel = true;
			}
			if (!currentRocket2PropState.contentEquals(prevRocket2PropState)) {
				//System.out.print("rocket2 :" + currentRocket2PropState);
				prevRocket2PropState = currentRocket2PropState;
				changeInAccel = true;				
			}
			
			
			if(changeInAccel) {
				System.out.print("Propeller State on each Rocket:");
				System.out.print(" \t Rocket1 :" + currentRocket1PropState);
				System.out.print(" \t Rocket2 :" + currentRocket2PropState);
				System.out.println();
			}
			
			
		}while(true);
		
				
	}


}
