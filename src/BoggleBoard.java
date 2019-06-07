import java.util.HashMap;
import java.util.Map;

public class BoggleBoard {
    private static int ALPHABET_SIZE = 26;
    private static int LINES = 4;

    private static class TrieNode{
        private Map<Character, TrieNode> children = new HashMap<>();
        private Boolean isEndOfWord = false;

        private TrieNode getChildNode(Character letter){
            return children.getOrDefault(letter, null);
        }

        private void add(String[] words){
            for(String word: words){
                add(word, 0);
            }
        }

        private void add(String word, int index){
            if(index == word.length()){
                return;
            }

            char letter = word.charAt(index);

            TrieNode child = getChildNode(letter);

            if(child == null){
                child = new TrieNode();
                children.put(letter, child);
            }

            /* Check if it is the last letter of a word */

            if(index == word.length() - 1){
                child.isEndOfWord = true;
            }

            child.add(word, index + 1);
        }
    }

    private static Boolean shouldProceed(int i, int j, Boolean[][] visited){
        return ((i < LINES) && (j < LINES) && (i >= 0) && (j >= 0) && !visited[i][j]);
    }

    private static void searchWord(char[][] boggle, TrieNode node, int i, int j, Boolean[][] visited, String word){
        if(!shouldProceed(i, j, visited)){
            return;
        }

        visited[i][j] = true;
        word = word + boggle[i][j];

        if(shouldProceed(i+1, j+1, visited)) {
            TrieNode child = node.getChildNode(boggle[i+1][j+1]);

            if (child != null) {
                searchWord(boggle, child, i+1, j+1, visited, word);
            }
        }

        if(shouldProceed(i+1, j, visited)) {
            TrieNode child = node.getChildNode(boggle[i+1][j]);

            if (child != null) {
                searchWord(boggle, child, i+1, j, visited, word);
            }
        }

        if(shouldProceed(i+1, j-1, visited)) {
            TrieNode child = node.getChildNode(boggle[i+1][j-1]);

            if (child != null) {
                searchWord(boggle, child, i+1, j-1, visited, word);
            }
        }

        if(shouldProceed(i, j-1, visited)) {
            TrieNode child = node.getChildNode(boggle[i][j-1]);

            if (child != null) {
                searchWord(boggle, child, i, j-1, visited, word);
            }
        }

        if(shouldProceed(i-1, j-1, visited)) {
            TrieNode child = node.getChildNode(boggle[i-1][j-1]);

            if (child != null) {
                searchWord(boggle, child, i-1, j-1, visited, word);
            }
        }

        if(shouldProceed(i-1, j, visited)) {
            TrieNode child = node.getChildNode(boggle[i-1][j]);

            if (child != null) {
                searchWord(boggle, child, i-1, j, visited, word);
            }
        }

        if(shouldProceed(i-1, j+1, visited)) {
            TrieNode child = node.getChildNode(boggle[i-1][j+1]);

            if (child != null) {
                searchWord(boggle, child, i-1, j+1, visited, word);
            }
        }

        if(shouldProceed(i, j+1, visited)) {
            TrieNode child = node.getChildNode(boggle[i][j+1]);

            if (child != null) {
                searchWord(boggle, child, i, j+1, visited, word);
            }
        }

        if(node.isEndOfWord){
            System.out.println("Word " + word + " was found");
        }

        visited[i][j] = false;
    }

    private static void findWords(char[][] boggle, TrieNode trie){
        Boolean[][] visited = new Boolean[LINES][LINES];

        /* Set all elements to not visited */

        for(int i = 0; i < LINES; i++){
            for(int j = 0; j < LINES; j++){
                visited[i][j] = false;
            }
        }

        /* Check if a letter is a child of the trie root node to see if it is a word it needs to search for */

        for(int i = 0; i < LINES; i++){
            for(int j = 0; j < LINES; j++){
                TrieNode child = trie.getChildNode(boggle[i][j]);
                if(child != null){
                    String word = "";
                    searchWord(boggle, child, i, j, visited, word);
                }
            }
        }
    }

    public static void main(String[] args){
        char[][] boggle = {
                {'C', 'L', 'X', 'T'},
                {'M', 'A', 'Z', 'O'},
                {'D', 'B', 'T', 'H'},
                {'L', 'E', 'R', 'E'}
        };

        String[] words = {"CAT", "THERE", "THE", "LAB", "LABEL", "ATE"};

        TrieNode trie = new TrieNode();
        trie.add(words);

        findWords(boggle, trie);
    }
}