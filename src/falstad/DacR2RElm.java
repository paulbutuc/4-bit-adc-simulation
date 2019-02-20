import java.awt.*;
import java.lang.Math;
import java.util.StringTokenizer;
import java.util.Random;

/* Implements a R2-R DAC. Values for resistors are hardcoded. For all
 * resistors Ri = 1kOhm. The voltage inputs are logic 5V or 0V, they
 * don't depend on the voltage input on the bits.
 * The resulting formula: R1/R * Vbit/2^n * sum(B*2^(n-1))
 */
class DacR2RElm extends ChipElm {
	private final int nBits = 4;
	private final double Vin = 5;
	
	public DacR2RElm(int xx, int yy) {
		super(xx, yy);
	}	
	public DacR2RElm(int xa, int ya, int xb, int yb, int f, StringTokenizer st) {
	    super(xa, ya, xb, yb, f, st);
	}
	
	String getChipName() {
		return "DAC_R-2R";
	}
	
	boolean needsBits() {
		return true;
	}
	
	int getDumpType() {
		return 199;
	}
	
	int getPostCount() {
		return nBits + 1;
	}
	
	int getVoltageSourceCount() {
		return 1;
	}
	
	void setupPins() {
		sizeX = 3;
		sizeY = nBits;
		// All the pins must be created with 'new'		
		pins = new Pin[getPostCount()];
		
		for (int i = 0; i < nBits; i++)
			pins[i] = new Pin(i, SIDE_W, ""+(i+1));
			
		pins[nBits] = new antPin(1, SIDE_E, "V");	
		pins[nBits].output = true;	
	}
	
	// Yet to be tested. It looks like it works.
	void doStep() {
		double Vout = 0;
		
		// is volts[i] related to pins[i] ?
		for (int i = 0; i < nBits; i++)
			if (volts[i] > 2.5)
				Vout = Vout + (Vin/Math.pow(2, nBits)) * Math.pow(2, i);
		
		sim.updateVoltageSource(0, nodes[nBits], pins[nBits].voltSource, Vout);		
	}
}
