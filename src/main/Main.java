package main;

import java.util.Arrays;
import java.util.Map.Entry;

import sun.beans.editors.LongEditor;

public class Main {
	public static String longestString = "";
	
	public static void main(String[] args) {
//		String stringForTree = "ababbabbaabbabb";
//		String stringForTree = "peeper";
//		String stringForTree = "banana";
//		String stringForTree = "caracas";
		String stringForTree = "020408163265306122448979591836734693877551020408163265306122448979591836734693877551";
		long start = System.currentTimeMillis();
		Node head = buildTree(stringForTree);
		System.out.println("Used time is for building is: " + (System.currentTimeMillis() - start) + " ms");
		
		int depth = 0;
		printNode(head, depth);
		
		findTheLongestRepeatingString(head);
		System.out.println("\n\nAaaaaand the longest string is : " + longestString);
	}
	
	public static void findTheLongestRepeatingString(Node head) {
		String string = "";
		for(Entry<String, Node> entry : head.children.entrySet()) {
			recur(entry.getValue(), entry.getKey(), string);
		}
	}
	
	public static void recur(Node node, String nodeKey, String string) {
		string = string + nodeKey;
		if(node.children.size() >= 2) {
			if(string.length() > longestString.length()) {
				longestString = string;
			}
		}
		
		for(Entry<String, Node> entry : node.children.entrySet()) {
			recur(entry.getValue(), entry.getKey(), string);
		}
	}
	
	public static void printNode(Node node, int depth) {
		for (Entry<String, Node> entry : node.children.entrySet()) {
			for(int i = 0; depth - i >= 0; i++) {
				System.out.print('-');
			}
			System.out.println(entry.getKey());
			printNode(entry.getValue(), depth + 1);
		}
	}

	public static Node buildTree(String string) {
		Node head = new Node();
		for (int i = 0; i < string.length(); i++) {
			insertNode(head, string.substring(i));
		}
		return head;
	}

	public static void insertNode(Node parent, String newKey) {
//		System.out.println("търсим ключ за : " + newKey);
		String key = "";
		for (Entry<String, Node> entry : parent.children.entrySet()) {
			String currentKey = entry.getKey();
			if (newKey.charAt(0) == currentKey.charAt(0)) {
				key = currentKey;
				break;
			}
		}

		int matchingCharsCnt = findMatchingCharsCnt(newKey, key);
		
		Node node = parent.getNode(key);
		if(matchingCharsCnt == 0) {
//			System.out.println("няма съвпадащ ключ за : " + newKey);
			parent.addNode(newKey, new Node());
		} 
		else {
//			System.out.println("има намерен ключ за : " + newKey);
//			System.out.println("като ето и дължините : newKey(" + newKey.length() + ") = " + newKey + ", key(" + key.length() + ") = " + key + ", matchingCharsCnt = " + matchingCharsCnt);
		
			if(matchingCharsCnt == key.length() && matchingCharsCnt == newKey.length()) {
				node.addNode("#", new Node());
			} 
			else 
			if(matchingCharsCnt == key.length()) {
				insertNode(node, newKey.substring(matchingCharsCnt));
			} 
			else 
			if(matchingCharsCnt == newKey.length()) {
				explodeNodeAndAddChild(parent, key, newKey, matchingCharsCnt);
			}
			else {
				explodeNodeAndAddChild(parent, key, newKey, matchingCharsCnt);
			}
		}
//		System.out.println("\n");
	}
	
	public static void explodeNodeAndAddChild(Node parent, String toBeExplodedKey, String toBeAddedKey, int matchingCharsCnt) {
		Node toBeExploded = parent.getNode(toBeExplodedKey);
		
		String newNodeKey = toBeExplodedKey.substring(0, matchingCharsCnt);
		
		Node newNode = new Node();
		newNode.addNode(toBeExplodedKey.substring(matchingCharsCnt), toBeExploded);
		if(toBeAddedKey.length() == matchingCharsCnt) {
			newNode.addNode("#", new Node());
		} else {
			newNode.addNode(toBeAddedKey.substring(matchingCharsCnt), new Node());
		}
		
		
		parent.removeNode(toBeExplodedKey);
		parent.addNode(newNodeKey, newNode);
	}
	
	public static int findMatchingCharsCnt(String first, String second) {
		int matchingCharsCnt = 0;
		for(int i = 0; i < first.length() && i < second.length(); i++) {
			if(first.charAt(i) == second.charAt(i)) {
				matchingCharsCnt++;
			} else {
				break;
			}
		}
		return matchingCharsCnt;
	}
}
