import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class TriesContactsProblem {
    public static class Node{
        private static int NUMBER_OF_CHARACTERS = 26;
        Node[] children = new Node[NUMBER_OF_CHARACTERS];
        int size = 0;

        private static int getCharIndex(char c){
            return c - 'a';
        }

        private Node getNode(char c){
            return children[getCharIndex(c)];
        }

        private void setNode(char c, Node node){
            children[getCharIndex(c)] = node;
        }

        public void add(String s){
            add(s, 0);
        }

        private void add(String s, int index){
            size++;

            if(index == s.length()){
                return;
            }

            char value = s.charAt(index);

            Node child = getNode(value);

            if(child == null){
                child = new Node();
                setNode(value, child);
            }

            child.add(s, index + 1);
        }

        public int findCount(String s, int index){
            if(index == s.length()){
                return size;
            }

            Node child = getNode(s.charAt(index));

            if(child == null){
                return 0;
            }

            return child.findCount(s, index + 1);
        }

    }

    public static void main(String[] args){
        Node tries = new Node();

        tries.add("alex");
        tries.add("alexander");
        tries.add("aleen");
        tries.add("alexia");

        int count = tries.findCount("alex", 0);

        System.out.println(count);
    }
}