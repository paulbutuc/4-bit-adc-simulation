import java.awt.*;
import java.lang.Math;
import java.util.StringTokenizer;
import java.util.Random;

class DacR2RElm extends ChipElm {
	private final int nBits = 4;
	private int b1, b2, b3, b4;
	
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
		
		for (int i = 0; i < 4; i++)
			pins[i] = new Pin(i, SIDE_W, ""+i);
			
		pins[4] = new Pin(1, SIDE_E, "V");
		
	}
	
	void execute() {
		int i = 0;
		//System.out.println("exe dacr2r");
	}
}
