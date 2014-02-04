package main;

import java.util.HashMap;

public class Node {
	public Node() {
		children = new HashMap<String, Node>();
	}
	
	public HashMap<String, Node> children;
}
