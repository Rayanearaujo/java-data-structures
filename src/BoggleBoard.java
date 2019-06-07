import sun.text.normalizer.Trie;

import java.util.HashMap;
import java.util.Map;

public class BoggleBoard {
    private static int ALPHABET_SIZE = 26;

    public static class TrieNode{
        private Map<Character, TrieNode> children = new HashMap<>();

        private TrieNode getTrieNode(Character letter){
            return children.getOrDefault(letter, null);
        }

        public void add(String[] words){
            for(String word: words){
                add(word, 0);
            }
        }

        private void add(String word, int index){
            if(index == word.length()){
                return;
            }

            char letter = word.charAt(index);

            TrieNode child = getTrieNode(letter);

            if(child == null){
                child = new TrieNode();
                children.put(letter, child);
            }

            child.add(word, index + 1);
        }
    }

    public static Boolean findWords(char[][] boggle, TrieNode trie){

        return false;
    }

    public static void main(String[] args){
        char[][] boggle = {
                {'C', 'L', 'X', 'T'},
                {'M', 'A', 'Z', 'O'},
                {'D', 'B', 'T', 'H'},
                {'L', 'E', 'R', 'E'}
        };

        String[] words = {"CAT", "THERE", "THE", "LAB"};

        TrieNode trie = new TrieNode();
        trie.add(words);

        findWords(boggle, trie);
    }
}