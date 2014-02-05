package main;

import java.util.HashMap;

public class Node {
	public Node() {
		children = new HashMap<String, Node>();
	}
	
	public HashMap<String, Node> children;
	
	public Node getNode(String key) {
		return children.get(key);
	}
	
	public void addNode(String key, Node node) {
		children.put(key, node);
	}

	public void removeNode(String key) {
		children.remove(key);
	}
}
