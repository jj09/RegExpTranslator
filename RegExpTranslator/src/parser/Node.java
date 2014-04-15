package parser;

import java.util.ArrayList;

public class Node {
	public ArrayList<Node> On0 = new ArrayList<Node>();
	public ArrayList<Node> On1 = new ArrayList<Node>();
	public ArrayList<Node> On2 = new ArrayList<Node>();
	public ArrayList<Node> OnE = new ArrayList<Node>();
	
	public boolean IsStart = false;
	public boolean IsFinal = false;
}
