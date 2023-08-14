import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class App {

    BinaryTree<String> root = new BinaryTree<String>();

    // constructor
    public App() {
        // load basic binary tree
        loadBasicBinaryTree();

        // game loop
        run();

    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        String answer = new String();
        String question = new String();

        // game loop
        while (true) {
            BinaryNodeInterface<String> currentNode = root.getRootNode();

            // whilst the current node is not a leaf node
            while (!currentNode.isLeaf()) {

                // check if currentNode has two null children
                if (currentNode.getLeftChild().getData().equals("NULL")
                        && currentNode.getRightChild().getData().equals("NULL")) {
                    break;
                }

                System.out.print(currentNode.getData() + "(y/n): ");
                answer = scanner.nextLine();

                if (answer.toLowerCase().equals("y")) {
                    currentNode = currentNode.getLeftChild();
                } else if (answer.toLowerCase().equals("n")) {
                    currentNode = currentNode.getRightChild();
                } else {
                    System.out.println("Unknown input!!, try again");
                    continue;
                }

            }

            // Programs guess
            System.out.print("My guess is " + currentNode.getData() + " (y/n): ");
            answer = scanner.nextLine();

            if (answer.toLowerCase().equals("y")) {
                // we got the right answer
                System.out.println(
                        "Good stuff!\nWould you want to play again?(p)\nSave the tree?(s)\nLoad another tree?(l)\nQuit?(q)");
                answer = scanner.nextLine();
                switch (answer) {
                    case "p":
                        continue;
                    case "s":
                        saveToFile();
                        break;
                    case "l":
                        loadBinaryTree();
                        break;
                    case "q":
                        scanner.close();
                        return;
                }
            } else if (answer.toLowerCase().equals("n")) {
                // we got the answer wrong
                System.out.println("I'm afraid I do not know then.\nWhat is the right answer?");
                answer = scanner.nextLine();
                System.out.println("Ahhhh of course!, But which question should help me guess it next time?");
                question = scanner.nextLine();

                // we need to check do we have enough nodes

                if (currentNode.isLeaf())
                    addLevelToBinaryTree(root.getRootNode());
                currentNode.getLeftChild().setData(answer);
                currentNode.getRightChild().setData(currentNode.getData());
                currentNode.setData(question);

                System.out.println("\n\n<======Starting game again======>");

            }

        }
    }

    /*
     * Save binary tree to file.
     * If it is the first item we do not place \n before it
     * Output to binaryTree.txt
     */
    private void saveToFile() {
        List<BinaryNode<String>> list = new ArrayList<>();
        inorderTraverseReturn(root.getRootNode(), list);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("binaryTree.txt"))) {

            for (int i = 0; i < list.size(); i++) {
                if (i == 0)
                    writer.write(list.get(i).getData());
                else
                    writer.write("\n" + list.get(i).getData());
            }
            writer.close();
        } catch (IOException e) {
        }

    }

    /*
     * inorderTraverseReturn used for saving file
     * collects all left and right children and adds them to our list in an inorder
     * transvesal
     */
    private void inorderTraverseReturn(BinaryNodeInterface<String> node, List<BinaryNode<String>> list) {
        if (node != null) {
            inorderTraverseReturn(node.getLeftChild(), list);
            list.add(new BinaryNode<String>(node.getData()));
            inorderTraverseReturn(node.getRightChild(), list);
        }
    }

    /*
     * Adds empty leaves to current leaves in the binary tree
     */
    private void addLevelToBinaryTree(BinaryNodeInterface<String> node) {

        if (node.hasLeftChild()) {
            addLevelToBinaryTree(node.getLeftChild());
        }
        if (node.hasRightChild()) {
            addLevelToBinaryTree(node.getRightChild());
        }

        if (!node.hasLeftChild()) {
            node.setLeftChild(new BinaryNode<String>("NULL"));
        }
        if (!node.hasRightChild()) {
            node.setRightChild(new BinaryNode<String>("NULL"));
        }
    }

    /*
     * Loads binary tree from binaryTree.txt file.
     * Works in a recursive manner,
     * given that the file will always contain a full binary tree we can recursively
     * half
     * the left and right children from the root until we get to the last parent and
     * its leaves
     */
    private void loadBinaryTree() {
        List<BinaryTree<String>> binaryArray = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("binaryTree.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                binaryArray.add(new BinaryTree<>(line));
                line = reader.readLine();

            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // fileArray now contains all contents of the file
        // allowing for easier manipulation of data

        // Creating binary tree from our list
        // performs recursive tree one last time to obtain the root node

        while (binaryArray.size() > 1) {
            binaryArray = recursiveTree(binaryArray);
        }
        this.root = recursiveTree(binaryArray).get(0);

    }

    /*
     * Recursive method
     * Splits the list in half from the middle until three elements are left
     * It then assigns the two elements on each side to the middle node (parent)
     * and returns parent
     */
    private List<BinaryTree<String>> recursiveTree(List<BinaryTree<String>> al) {
        // merges the last three nodes into the one
        if (al.size() == 3) {
            al.get(1).setTree(al.get(1).getRootData(), al.get(0), al.get(2));
            al.remove(0);
            al.remove(1);
            return al;

        }

        // if there is just one node left return it
        if (al.size() <= 1) {
            return al;
        }

        // take middle node and run through the right and left nodes
        BinaryTree<String> middle = al.get((al.size()) / 2);

        recursiveTree(al.subList(0, al.size() / 2));
        recursiveTree(al.subList(al.indexOf(middle) + 1, al.size()));

        return al;

    }

    /*
     * Initial binary tree
     */
    private void loadBasicBinaryTree() {
        BinaryTree<String> node1 = new BinaryTree<>("Cat");
        BinaryTree<String> node2 = new BinaryTree<>("lion");
        BinaryTree<String> parent12 = new BinaryTree<>("Is it a pet", node1, node2);

        BinaryTree<String> node3 = new BinaryTree<>("lizard");
        BinaryTree<String> node4 = new BinaryTree<>("insect");

        BinaryTree<String> parent34 = new BinaryTree<>("Is it a reptile", node3, node4);

        BinaryTree<String> node5 = new BinaryTree<>("t-shirt");
        BinaryTree<String> node6 = new BinaryTree<>("table");
        BinaryTree<String> parent56 = new BinaryTree<>("Is it a piece of clothing?", node5, node6);

        BinaryTree<String> node7 = new BinaryTree<>("Casper the ghost");
        BinaryTree<String> node8 = new BinaryTree<>("plant");
        BinaryTree<String> parent78 = new BinaryTree<>("Is it a ghost?", node7, node8);


        BinaryTree<String> parent1234 = new BinaryTree<>("Is it a mammal", parent12, parent34);
        BinaryTree<String> parent5678 = new BinaryTree<>("Are you thinking of an inanimate object?", parent56, parent78);

        root.setTree("Are you thinking of an animal??", parent1234, parent5678);

    }

    public static void main(String[] args) {
        new App(); // runs constructor
    }

}
