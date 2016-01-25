import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomTree implements Iterable {

    Node root;

    public void addNode(int key, String value) {
        Node node = new Node(key, value);

        if (root == null) {
            root = node;
            return;
        }

        Node currentNode = root;
        Node parent;

        while (true) {
            parent = currentNode;
            if (key < currentNode.key) {
                currentNode = currentNode.leftChild;
                if (currentNode == null) {
                    parent.leftChild = node;
                    return;
                }
            } else {
                currentNode = currentNode.rightChild;
                if (currentNode == null) {
                    parent.rightChild = node;
                    return;
                }
            }
        }
    }

    public Node findNode(int key) {
        if (root == null) {
            return null;
        }

        Node currentNode = root;
        while (currentNode.key != key) {
            if (key < currentNode.key)
                currentNode = currentNode.leftChild;
            else
                currentNode = currentNode.rightChild;

            if (currentNode == null) {
                return null;
            }
        }

        return currentNode;
    }

    public void traverse(Node currentNode) {
        if (currentNode != null) {
            traverse(currentNode.leftChild);
            System.out.println(currentNode.key + " - " + currentNode.value);
            traverse(currentNode.rightChild);
        }
    }

    @Override
    public Iterator iterator() {
        return new CTIterator(root);
    }

    //Three ways to search in tree - with data returning, with array of data returning and with boolean result;
    public boolean hasData(String searchable){
        for (Object searchobj:this){
            Node searchnode = (Node) searchobj;
            String strObj = searchnode.value;
            if (strObj.equals(searchable)) return true;
        }
        return false;
    }

    public Node searchData(String searchable){
        for (Object searchobj:this){
            Node searchnode = (Node) searchobj;
            String strObj = searchnode.value;
            if (strObj.equals(searchable)) return searchnode;
        }
        return new Node();
    }

    public List searchRepeatedData(String searchable){
        List<Node> resultArray = new ArrayList<Node>();
        for (Object searchobj:this){
            Node searchnode = (Node) searchobj;
            String strObj = searchnode.value;
            if (strObj.equals(searchable)) resultArray.add(searchnode);
        }
        return resultArray;
    }


    public static void main(String[] args) {
        CustomTree tree = new CustomTree();
        tree.addNode(19, "Dog");
        tree.addNode(5, "Cat");
        tree.addNode(24, "Cat");
        tree.addNode(3, "Duck");
        tree.addNode(7, "Turtle");

        tree.traverse(tree.root);

        System.out.println();
        System.out.println("Is this tree has a value \"Cat\"? - "+tree.hasData("Cat"));
        System.out.println("Node with \"Cat\" value has a key: "+tree.searchData("Cat").key);

        System.out.println("The keys of nodes with \"Cat\" value in array: ");
        List<Node> searchresult = tree.searchRepeatedData("Cat");
        for (Object obj:searchresult){
            Node objNode = (Node) obj;
            System.out.println("Key :"+objNode.key);
        }
    }


    class Node {
        int key;
        String value;

        Node leftChild;
        Node rightChild;

        public Node(){
        }

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public void setKey(int key) {
            this.key = key;
        }
    }

    private class CTIterator implements Iterator {
        private int currentNodeListPosition=0;
        private List<Node> nodeList = new ArrayList<Node>();


        public CTIterator(Node starting) {
            addToIterator(starting);
        }

        private void addToIterator(Node currentnode){
            nodeList.add(currentnode);
            if (currentnode.leftChild!=null) addToIterator(currentnode.leftChild);
            if (currentnode.rightChild!=null) addToIterator(currentnode.rightChild);
        }

        @Override
        public boolean hasNext() {
            if (currentNodeListPosition>=(nodeList.size()-1)) return false;
            return true;
        }

        @Override
        public Object next() {
            currentNodeListPosition++;
            return nodeList.get(currentNodeListPosition);
        }
    }
}