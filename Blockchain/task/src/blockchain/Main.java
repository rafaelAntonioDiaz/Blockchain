package blockchain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter how many zeros the hash must start with: ");
        int zerosAtStart =  Integer.valueOf(keyboard.nextLine().trim());
        BlockChain blockChain = new BlockChain(zerosAtStart);
        blockChain.getBlockById(1).printBlock();
        System.out.println();
        for (int i = 2; i < 6; i++) {
            if (blockChain.mineBlock(blockChain.getBlockById( i - 1).blockHash, i)) {
                blockChain.getBlockById(i).printBlock();
                System.out.println();
            }
        }
    }
}
