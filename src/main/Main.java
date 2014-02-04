package main;

import java.util.Map.Entry;

public class Main {
	public static void main(String[] args) {
		Node head = buildTree("peeper");
		for (Entry<String, Node> entry : head.children.entrySet()) {
			System.out.println(entry.getKey());
			Node node = entry.getValue();
			for (Entry<String, Node> nestedEnrty : node.children.entrySet()) {
				System.out.println(" - " + nestedEnrty.getKey());
			}

			System.out.println("new general node \n");
		}
	}

	public static Node buildTree(String string) {
		Node head = new Node();
		for (int i = 0; i < string.length(); i++) {
			insertNode(head, string.substring(i));
		}
		return head;
	}

	public static void insertNode(Node head, String subStr) {
		String containedKey = null;
		for (Entry<String, Node> entry : head.children.entrySet()) {
			String key = entry.getKey();
			if (subStr.charAt(0) == key.charAt(0)) {
				containedKey = key;
				break;
				
			}
		}

		if (containedKey == null) {
			head.children.put(subStr, new Node());
		} else {
			insertFoundNode(head, containedKey, subStr);
		}
	}
	
	public static void insertFoundNode(Node head, String key, String subStr) {
		int matchingCharsCnt = findMatchingCharsCnt(subStr, key);
		
		Node node = head.children.get(key);
		if (matchingCharsCnt == key.length()) {
			// if there are already elements that begins with this string then we just add new node
			node.children.put(subStr.substring(1), new Node());
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
