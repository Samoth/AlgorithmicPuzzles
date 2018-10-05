package pl.florsoft.puzzles.datastructure.tree;

import java.util.HashMap;

public class Trie {

    public TrieNode root;

    public Trie(TrieNode root) {
        this.root = root;
    }

    public void insert(String word) {
        if (word == null || word.trim().isEmpty()) {
            return;
        }
        insertToNode(word, root);
    }

    private void insertToNode(String word, TrieNode node) {
        char c = word.charAt(0);
        boolean isLastLetter = word.length() == 1;
        if (node.children.containsKey(c)) {
            if (isLastLetter) {
                node.children.get(c).isWord = true;
            } else {
                insertToNode(word.substring(1), node.children.get(c));
            }
        } else {
            TrieNode newNode = new TrieNode(word, isLastLetter);
            node.children.put(c, newNode);
            if (!isLastLetter) {
                insertToNode(word.substring(1), newNode);
            }
        }
    }

    public boolean find(String word) {
        if (word == null || word.trim().isEmpty()) {
            return false;
        }
        return findInNode(word, root);
    }

    private boolean findInNode(String word, TrieNode node) {
        char c = word.charAt(0);
        if (!node.children.containsKey(c)) {
            return false;
        }
        boolean isLastLetter = word.length() == 1;
        if (isLastLetter) {
            return node.children.get(c).isWord;
        } else {
            return findInNode(word.substring(1), node.children.get(c));
        }
    }

    static class TrieNode {

        public HashMap<Character, TrieNode> children = new HashMap<>();
        public String value;
        public boolean isWord;

        public TrieNode(String value, boolean isWord) {
            this.value = value;
            this.isWord = isWord;
        }

    }

}
