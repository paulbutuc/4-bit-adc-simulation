import java.awt.*;
import java.util.StringTokenizer;
import java.util.Random;

class Shift74LS164Elm extends ChipElm {	
	private int A, B;
	private int CLK, CLR;
	private int firstOutputPin;
	
	public Shift74LS164Elm(int xx, int yy) {
		super(xx, yy);
	}	
	public Shift74LS164Elm(int xa, int ya, int xb, int yb, int f, StringTokenizer st) {
	    super(xa, ya, xb, yb, f, st);
	}
	
	String getChipName() {
		return "74LS164 shift register";
	}
	
	int getDumpType() {
		return 191;
	}
	
	void setupPins() {
		sizeX = 9;
		sizeY = 5;
		int pos = 0;
		Random state = new Random();
		pins = new Pin[getPostCount()];
		
		A = pos;
		pins[pos++] = new Pin(1, SIDE_W, "A");
		B = pos;
		pins[pos++] = new Pin(2, SIDE_W, "B");
		
		CLK = pos;
		pins[pos] = new Pin(4, SIDE_W, "");
		pins[pos++].clock=true;
		
		firstOutputPin = pos;
		pins[pos] = new Pin(1, SIDE_N, "Q0");
		pins[pos++].output=true;	
		pins[pos] = new Pin(2, SIDE_N, "Q1");
		pins[pos++].output=true;
		pins[pos] = new Pin(3, SIDE_N, "Q2");
		pins[pos++].output=true;
		pins[pos] = new Pin(4, SIDE_N, "Q3");
		pins[pos++].output=true;
		pins[pos] = new Pin(5, SIDE_N, "Q4");
		pins[pos++].output=true;
		pins[pos] = new Pin(6, SIDE_N, "Q5");
		pins[pos++].output=true;
		pins[pos] = new Pin(7, SIDE_N, "Q6");
		pins[pos++].output=true;
		pins[pos] = new Pin(8, SIDE_N, "Q7");
		pins[pos++].output=true;
		
		CLR = pos;
		pins[pos] = new Pin(1, SIDE_S, "CLR");
		
		for (int i = firstOutputPin; i < getVoltageSourceCount(); i++)
			if (state.nextInt(100) % 2 == 0)
				pins[i].value = false;
			else
				pins[i].value = true;
				
	}
	
	int getPostCount() {
		return 12;
	}
	
	int getVoltageSourceCount() {
		return 8;
	}
	
	void clr() {
		for (int i = firstOutputPin; i < getVoltageSourceCount(); i++)
			pins[i].value = false;
	}
	
	void execute() {
		if (pins[CLR].value == true)
			clr();
	}
}
