package java_Rockets;

public class Rockets_main {
	
	public void main() {
		
		System.out.println("Soy el main de rockets");
		Rocket my1stRocket = new Rocket("32WESSDS", 3);
		my1stRocket.setMaxPropPower(new int[] { 10,30,80 });		
		System.out.println(my1stRocket.getCodeName()+" "+my1stRocket.getAllMaxPropPower());
		
		Rocket my2ndRocket = new Rocket("LDSFJA32", 6);
		my2ndRocket.setMaxPropPower(new int[] { 30,40,50,50,30,10 });		
		System.out.println(my2ndRocket.getCodeName()+" "+my2ndRocket.getAllMaxPropPower());
		
		
		String rocket1 = my1stRocket.getAllMaxPropPower();
		String rocket2 = my2ndRocket.getAllMaxPropPower();
		
		///TO DO. CREATE SET TARGET POWER
		//my1stRocket.setTargetPropPower(new int[] { 5, 10, 20});
		
		do {
			String currentRocket1 = my1stRocket.getAllMaxPropPower();			
			if (currentRocket1 != rocket1) {
				rocket1 = currentRocket1;
				System.out.println("rocket1" + rocket1);
				
			}

			String currentRocket2 = my2ndRocket.getAllMaxPropPower();			
			if (currentRocket2 != rocket2) {
				rocket2 = currentRocket2;
				System.out.println("rocket2" +rocket2);
				
			}
			
			
		}while(true);
		
	}


}
