
package blockchain;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {
    private List<Block> blockChain = new ArrayList<>(5);
    private int zerosAtStart;


    public BlockChain(int zerosAtStart) {
        this.zerosAtStart = zerosAtStart;
        Block fistBlock = new Block("0", 1, zerosAtStart);
        blockChain.add(fistBlock);
    }

    public boolean validateBlock(Block block) {
        boolean validation = true;

        StringBuilder startsWith = new StringBuilder("");
        for (int i = 1; i <= zerosAtStart; i++) {
            startsWith.append("0");
        }
        for (int i = 0; i < block.getId() - 1; i++) {
            Block temporal = blockChain.get(i);
            if (i == 0) {
                if (!"0".equals(temporal.previousBlockHash)) {
                    validation = false;
                } else {
                    validation = true;
                }
            } else {
                if (!temporal.getPreviousBlockHash().equals(blockChain.get(i - 1).blockHash) ||
                    !blockChain.get(i - 1).blockHash.startsWith(String.valueOf(startsWith))) {
                    validation = false;
                    break;
                }
            }
        }
        return validation;
    }

    public boolean mineBlock(String previousHash, int id) {
        Block minedBlock = new Block(previousHash, id, zerosAtStart);
        if (validateBlock(minedBlock)) {
            blockChain.add(minedBlock);
            return true;
        } else {
            return false;
        }
    }

    public Block getBlockById(int id) {
        Block block = blockChain.get(id - 1);
        return block;
    }

}
