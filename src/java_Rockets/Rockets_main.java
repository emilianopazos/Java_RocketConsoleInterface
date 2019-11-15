package java_Rockets;

import java.util.ArrayList;
import java.util.Scanner;

public class Rockets_main {
	Scanner sc = new Scanner(System.in);
	boolean executeRockets_main = true;
	ArrayList<Rocket> myRockets = new ArrayList<Rocket>();
	
	public void main() {
		
		///Initialize 1st Rocket, and add to myRockets 
		Rocket my1stRocket = new Rocket("32WESSDS", 3);//Utiliza el constructor Propeller() que los inicializa en currentPower = 0
		my1stRocket.setMaxPropPower(new int[] { 10,30,80 });		
		myRockets.add(my1stRocket);	
		
		///Initialize 2nd Rocket, and add to myRockets
		Rocket my2ndRocket = new Rocket("LDSFJA32", 6);
		my2ndRocket.setMaxPropPower(new int[] { 30,40,50,50,30,10 });		
		myRockets.add(my2ndRocket);	
				
		System.out.println("Wellcome to the rockets control panel:");
		
		do {
			System.out.println("\nWhat would you like to do?:");
			System.out.println("\t 1-SHOW ROCKETS WITH ITS MAXPOWER");
			System.out.println("\t 2-CHANGE POWER");
			System.out.println("\t 3-ROCKET TO CRUISE VELOCITY");
			System.out.println("\t 4-CHOOSE FINAL VELOCITY WITH ROCKET 1");
			System.out.println("\t 5-EXIT APP");
			System.out.print("Your choice: ");
			//int userChoice = sc.nextInt();
			///FIX NextInt Exceptions FOR SC.NEXT -> ToChar
			char userChoice = sc.next().charAt(0);
					
			switch (userChoice) {
			case '1':////SHOW ROCKETS DATA
				showAllRockets();
				break;

			case '2':	//SELECT ROCKECT -> CHANGE POWER -> EXECUTOR_SERVICE
				int idRocket = idRocketSelect(sc);
				System.out.println("ID CHOOSEN : "+idRocket + "\n");
				///Ask for target power(idRocket);
				ArrayList<Integer> targetPowers = askTargetPowers(sc, idRocket);
				System.out.println("Target: "+ targetPowers);
				this.myRockets.get(idRocket).setAllTargetPropPowerExecutor(targetPowers);
				//ExecutorService executor = Executors.newFixedThreadPool(10);
				//ThreadPoolExecutor executor = new Thread
								
				
				break;
		
			case '3':///GO TO CRUISE VELOCITY
				
				///HARDCODING THE EXECUTOR_SERVICE
				/////1-Set initial State and Final Target State
				/////2-Launch Threads 
				/////3-DO(WHILE) Final State is not reached
				
				//Set Initial State
				boolean velocityCruise = false;
				String prevRocket0PropState = myRockets.get(0).getAllCurrentPower();
				String prevRocket1PropState = myRockets.get(1).getAllMaxPropPower();
				//Build Cruise Velocity
				int[] velCruiseRocket0 = { 5, 10, 20};
				int[] velCruiseRocket1 = { 5, 0, 10, 5, 0, 10};
				///START RUN THREADS
				myRockets.get(0).setAllTargetPropPower(velCruiseRocket0);
				myRockets.get(1).setAllTargetPropPower(velCruiseRocket1);
				
				///Emulate Executor Service watchdog				
				do {
					
					String currentRocket1PropState = myRockets.get(0).getAllCurrentPower();
					String currentRocket2PropState = myRockets.get(1).getAllCurrentPower();
					boolean changeInAccel = false;
					
					if (!currentRocket1PropState.contentEquals(prevRocket0PropState)) {
						//System.out.print(" rocket1 :" + currentRocket1PropState);
						prevRocket0PropState = currentRocket1PropState;
						changeInAccel = true;
					}
					if (!currentRocket2PropState.contentEquals(prevRocket1PropState)) {
						//System.out.print("rocket2 :" + currentRocket2PropState);
						prevRocket1PropState = currentRocket2PropState;
						changeInAccel = true;				
					}
								
					if(changeInAccel) {
						System.out.print("Propeller State on each Rocket:");
						System.out.print(" \t Rocket1 :" + currentRocket1PropState);
						System.out.print(" \t Rocket2 :" + currentRocket2PropState);
						System.out.println();
					}else { 
						if(myRockets.get(0).getAllCurrentPower().contentEquals("5,10,20,") &&
							myRockets.get(1).getAllCurrentPower().contentEquals("5,0,10,5,0,10,")) {
							velocityCruise = true;
						}
					}
					
					
				}while(!velocityCruise);
				///Break whan both velocities where reached
				break;

			case '4':///CHOOSE FINAL VELOCITY IN ROCKET 1
				////Implements the Executor Service in setAllTargetPropPowerExecutor()
				int maxProp0 = myRockets.get(0).getPropellerList().get(0).getMaxPower();
				int maxProp1 = myRockets.get(0).getPropellerList().get(1).getMaxPower();
				int maxProp2 = myRockets.get(0).getPropellerList().get(2).getMaxPower();
				double maxTotalPower = maxProp0 + maxProp1+ maxProp2;
										
				System.out.println("introduce vel deseada para rocket 1");
				int velTarget = sc.nextInt();
				///Using Velocity Formula provided 
				double potTotalTarget = Math.pow((velTarget / 100), 2);
				
				if(potTotalTarget > 120) {
					System.out.println("Unnreachable!! Sorry you´ll be caught!! (Max 120)");
				}else {
					ArrayList<Integer> targetPowersFromVelocity = new ArrayList<Integer>(); 
					double powerTargetProp0 = maxProp0 / maxTotalPower * potTotalTarget;
					double powerTargetProp1 = maxProp1 / maxTotalPower * potTotalTarget;
					double powerTargetProp2 = maxProp2 / maxTotalPower * potTotalTarget;
					targetPowersFromVelocity.add((int)powerTargetProp0);
					targetPowersFromVelocity.add((int)powerTargetProp1);
					targetPowersFromVelocity.add((int)powerTargetProp2);
					System.out.println(targetPowersFromVelocity);
					//SEND POWER TARGET DATA TO EXECUTOR
					this.myRockets.get(0).setAllTargetPropPowerExecutor(targetPowersFromVelocity);
					//WHEN EXECUTOR FINISH					
				}
						
				break;				

			case '5':////EXIT APLICATION
				this.executeRockets_main = false;
				break;
				
			default:
				System.out.println("WRONG ANSWER. Try again");
				break;
			}
			
			
		}while(this.executeRockets_main);
		
		System.out.println("Como Rockets_main te digo adios");
	}
	
//	NOTES
//		////AFTER EXIT DE USER INTARFACE APP, COMES PREVIUS APP WITH CORRECT THREAD IMPLEMENTATION.
//		//Change the .start() from the Propeller itself to the rocket. Was a wrong aproach to thread management.
//		//That is the correct way because the rocket is the one who ask for changing the power of each Propeller
//		//Was in one PUBLIC VOID METHOD (setCurrentPower()) of the "thread object"
//		//Move to Rocket set_target_power() who do the .start on each propeller of the Arraylist
//		
//		//NEXT STEP - COMPLETED. ExecutorService
//		//Create a ExecutorService with n thread allowed in pool 
//		//When new power is asked, create the ExecutorService, change the target power on each propeller and submit the propellers as runnables in the executor;
//		//This approach allows to change rocket powers, (implicit multiple threads running) and wait until all tasks are finished
		


	///METHODS
	public void showAllRockets() {
		int idRocket = 0;
		//String rocketName;
		System.out.println("\nWe have this Rockets available: ");
		for (Rocket rocket : myRockets) {
			System.out.println("*****ROCKET ID = " + idRocket);
			System.out.println("\t-CodeName: " + rocket.getCodeName() + "; Q of Prop: " + rocket.getPropellerList().size());
			System.out.println("\t-MaxPower: " + rocket.getAllMaxPropPower());
			System.out.println("\t-CurrentPower: " + rocket.getAllCurrentPower());
			idRocket += 1;
		}
	}
		
	///USER INTERFACE VOIDs
	public int idRocketSelect(Scanner sc) {
		int idRocketSelected = 0;
		showAllRockets();
		boolean idRocketOK;
		
		do {
			System.out.print("Which Rocket do you want to modify? ID = ");
			idRocketSelected = sc.nextInt();
			idRocketOK = (idRocketSelected < myRockets.size());
			if(!idRocketOK) {
				System.out.println("**Id not found. Try again***");
			}
			
		}while(!idRocketOK);
						
		return idRocketSelected;
	}
	
	public ArrayList<Integer> askTargetPowers(Scanner sc, int idRocket){
		int qProp = myRockets.get(idRocket).getPropellerList().size();
		ArrayList<Integer> targetPowers = new ArrayList<Integer>();
		
		System.out.println("Rocket "+idRocket+" has " + qProp +" propellers");
		System.out.println("Enter targert power for each propeller:");
		
		for (int i = 0; i < qProp; i++) {
			int userTargetPower;
			int maxPower = myRockets.get(idRocket).getPropellerList().get(i).getMaxPower();
			System.out.print("Target Power Propeller " + i + "(Max. "+ maxPower +")" +" = ");
			boolean targetOK;
			do {
				userTargetPower = sc.nextInt();
				targetOK = (userTargetPower <= maxPower);
				if(targetOK) {
					targetPowers.add(userTargetPower);
				}else {
					System.out.print("To much power for that propeller. Enter new target power");
				}
				
			}while(!targetOK);
			
		}
		
		return targetPowers;
	}
}
	


