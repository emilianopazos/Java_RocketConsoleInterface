package java_Rockets;

import java.util.ArrayList;

public class Rocket {
	////ATRIBUTES
	private String codeName;  
	private ArrayList<Propeller> propellerList = new ArrayList<Propeller>();
	
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
	

}