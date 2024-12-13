package controlSoftware;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;

public class ElectronicScaleListenerStub implements ElectronicScaleListener {

	// Class variables
	public double currentWeight;
	public int numberOfItems;
	public int maximumWeightInGrams = 10000;
	public boolean isOverload;
	
	// Constructor
	public ElectronicScaleListenerStub() {
		this.currentWeight = 0;
		this.numberOfItems = 0;
		this.isOverload = false;
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		// TODO Auto-generated method stub
		
	}

	// Updating the number of items and getting the weight change
	@Override
	public void weightChanged(ElectronicScale scale, double weightInGrams) {
		// TODO Auto-generated method stub
		this.currentWeight = weightInGrams;
		System.out.println("Weight: " + this.currentWeight);
		this.numberOfItems += 1;
	}

	@Override
	public void overload(ElectronicScale scale) {
		// TODO Auto-generated method stub
		//System.out.println();
		
	}

	@Override
	public void outOfOverload(ElectronicScale scale) {
		// TODO Auto-generated method stub
		
	}
	
	// Method to get the current weight
	public double getCurrentWeight() {
		return this.currentWeight;
	}
	// Method to check if the scale is overloaded
	public boolean isOverload() {
		return this.isOverload;
		
	}
	// Method to set overload checker
	public void setOverload() {
		this.isOverload = true;
		
	}
}