package main;

import java.util.Map.Entry;

public class Main {
	public static void main(String[] args) {
		String stringForTree = "ababbabbaabbabb#";
		Node head = buildTree(stringForTree);

		int depth = 0;
		printNode(head, depth);
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
		for (int i = 0; i < string.length() - 1; i++) {
			insertNode(head, string.substring(i));
		}
		return head;
	}

	public static void insertNode(Node head, String subStr) {
		String key = null;
		for (Entry<String, Node> entry : head.children.entrySet()) {
			String currentKey = entry.getKey();
			if (subStr.charAt(0) == currentKey.charAt(0)) {
				key = currentKey;
				break;
			}
		}

		if (key == null) {
			head.children.put(subStr, new Node());
		} else {
			int matchingCharsCnt = findMatchingCharsCnt(subStr, key);
			
			if(matchingCharsCnt == key.length() && matchingCharsCnt == subStr.length()) { // daljinata na savpada6tiq string e kolkoto daljinata na string, koito trqbva da se dobavi
				
			} else if(matchingCharsCnt == key.length()) { // daljinata na savda6tiq string e kolkoto daljinata na kliu4a na node-a
				
			} else if(matchingCharsCnt == subStr.length()) { // daljinata na savda6tiq string e kolkoto daljinata na string koito procesvame
				
			}
			
			insertFoundNode(head, key, subStr);
		}
	}
	
	public static void insertFoundNode(Node head, String key, String subStr) {
		int matchingCharsCnt = findMatchingCharsCnt(subStr, key);
		
		Node node = head.children.get(key);
		if (matchingCharsCnt == key.length()) {
			// if there are already elements that begins with this string then we just add new node
			node.children.put(subStr.substring(matchingCharsCnt), new Node());
		} else {
			// delete the old node
			head.children.remove(key);

			// remove one char from the key
			String reducedKey = key.substring(matchingCharsCnt);

			Node newNode = new Node();
			// insert the old element in the new one
			newNode.children.put(reducedKey, node);

			// insert the new element in the node
			newNode.children.put(subStr.substring(matchingCharsCnt), new Node());

			// insert the new node in the tree
			head.children.put(subStr.substring(0, matchingCharsCnt), newNode);
		}
	}
	
	public static int findMatchingCharsCnt(String first, String second) {
		int matchingCharsCnt = 0;
		for(int i = 0; i < first.length() && i < second.length(); i++) {
			if(first.charAt(i) == second.charAt(i)) {
				matchingCharsCnt++;
			}
		}
		return matchingCharsCnt;
	}
}
