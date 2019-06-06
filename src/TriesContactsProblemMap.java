import java.util.HashMap;
import java.util.Map;

public class TriesContactsProblemMap {

    public static class Node{
        private Map<Character, Node> children = new HashMap<>();
        int size = 0;

        private Node getNode(char letter){
            return children.getOrDefault(letter, null);
        }

        private boolean exists(String word, int index){
            if(index == word.length()){
                return true;
            }

            char letter = word.charAt(index);

            Node child = children.getOrDefault(letter, null);

            if(child == null){
                return false;
            }

            return child.exists(word, index + 1);
        }

        private void add(String word, int index){
            size++;

            if(index == word.length()){
                return;
            }

            char letter = word.charAt(index);

            Node child = getNode(letter);

            if(child == null){
                child = new Node();
                children.put(letter, child);
            }

            child.add(word, index + 1);
            System.out.println(' ');
        }

        public void add(String word){
            if(exists(word, 0)){
                System.out.println("Word " + word + " already exists");
                return;
            }

            add(word, 0);
        }

        public int countWords(String word, int index){
            if(index == word.length()){
                return size;
            }

            char letter = word.charAt(index);

            Node child = getNode(letter);

            if(child == null){
                return 0;
            }

            return child.countWords(word, index + 1);
        }
    }

    public static void main(String[] args){
        Node trie = new Node();

        trie.add("alex");
        trie.add("alexander");
        trie.add("aleen");
        trie.add("alexia");
        trie.add("alex");

        int count = trie.countWords("ale", 0);

        System.out.println(count);
    }
}