package java_Rockets;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Rocket {
	////ATRIBUTES
	private String codeName;  
	private ArrayList<Propeller> propellerList = new ArrayList<Propeller>();
	private ArrayList<Integer> propellerCurrentPower = new ArrayList<Integer>();
	
	////CONSTRUCTORS
	public Rocket(String codeName,int qPropeller) {
		this.codeName = codeName;
		
		for(int i=0; i < qPropeller; i++) {
			propellerList.add(new Propeller());			
		}
		
		
	}
	
	////METHODS
	public void setMaxPropPower(int maxPowerList[] ) {
		
		if(maxPowerList.length == this.getPropellerList().size()) {
			for(int i=0; i<maxPowerList.length; i++) {
				this.propellerList.get(i).setMaxPower(maxPowerList[i]);
			}
		}else {
			System.out.println("No conciden las cantidades de propellers");
		}
		
	}
	
	
	public String getAllMaxPropPower() {
		String allMaxPower = new String("(");
		Integer maxPoweri;
		
		for(int i=0; i<this.propellerList.size(); i++) {
			maxPoweri = this.propellerList.get(i).getMaxPower();
			allMaxPower = allMaxPower.concat(maxPoweri.toString());
			//System.out.println(maxPoweri.toString());
			//Separador o final
			if(i == this.propellerList.size()-1 ) {
				allMaxPower = allMaxPower.concat(")");
			}else {
				allMaxPower = allMaxPower.concat(",");
			}
			
		}
		
		return allMaxPower;
	}
	
	//OLD VERSION WITHOUT USER INTERFACE
	////Used for CruiseVelocity
	public void setAllTargetPropPower(int AllTargetPropPower[]) {
		for (int i = 0; i < AllTargetPropPower.length; i++) {
			if (propellerList.get(i).isSetTargetPower(AllTargetPropPower[i])) {
				new Thread(propellerList.get(i)).start();
			}
						
			
		}
		
	}
	/////Metodo nuevo con executor 
	public void setAllTargetPropPowerExecutor(ArrayList<Integer> AllTargetPropPower) {
		ExecutorService changePower = Executors.newFixedThreadPool(this.getPropellerList().size());
			
		for (int i = 0; i < AllTargetPropPower.size(); i++) {
			propellerList.get(i).setTargetPower(AllTargetPropPower.get(i));		
			changePower.execute(propellerList.get(i));
		}
		
		changePower.shutdown();//do not accept any new task
		String prevRocketPropState = this.getAllCurrentPower();
		
		do {
					
			String currentRocketPropState = this.getAllCurrentPower();
			boolean changeInAccel = false;
			
			if (!currentRocketPropState.contentEquals(prevRocketPropState)) {
				prevRocketPropState = currentRocketPropState;
				changeInAccel = true;
			}
		
			if(changeInAccel) {
				System.out.println("Rocket " + this.getCodeName() +" propellers changed to: " + this.getAllCurrentPower());
			}
			
			
		}while(!changePower.isTerminated());

		System.out.println("End whit power changes \n");
	}
	
	public String getAllCurrentPower() {
		String delimiter = ",";
		String allCurrentPower = "";
		
		for (int i = 0; i < propellerList.size(); i++) {
			int currentPowerI = propellerList.get(i).getCurrentPower();
			allCurrentPower = allCurrentPower.concat(Integer.toString(currentPowerI));		
			allCurrentPower = allCurrentPower.concat(delimiter);	
		}
		
		return allCurrentPower;
	}
	///GETTERS AND SETTERS
	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public ArrayList<Propeller> getPropellerList() {
		return propellerList;
	}

	public void setPropellerList(ArrayList<Propeller> propellerList) {
		this.propellerList = propellerList;
	}

	public ArrayList<Integer> getPropellerCurrentPower() {
			
		for (Propeller prop : this.propellerList) {
			propellerCurrentPower.add(prop.getCurrentPower());
		}
		
		return propellerCurrentPower;
	}
	
	
	

}
