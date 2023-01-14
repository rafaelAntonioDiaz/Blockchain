
package blockchain;

import java.time.Instant;
import java.util.Date;

public class Block {
    int id;
    long timestamp;
    String previousBlockHash;
    String blockHash;
    int magicNumber;
    int zerosAtHashStart;
    long timeToMine;


    public Block(String previousBlockHash, int id, int zerosAtHashStart) {
        this.id = id;
        this.previousBlockHash = previousBlockHash;
        this.timestamp = setTimestamp();
        this.magicNumber = setMagicNumber();
        this.zerosAtHashStart = zerosAtHashStart;
        this.blockHash = setBlockHash();


    }

    public int setMagicNumber() {
        return this.magicNumber = (int) (Math.random() * 1e8);}

    public long setTimestamp() {
        return this.timestamp = new Date().getTime();
    }

    public String setBlockHash() {
        StringBuilder startsWith = new StringBuilder();
        for (int i = 0; i < zerosAtHashStart; i++) {
            startsWith.append("0");
        }
        boolean success = false;
        Instant start = Instant.now();
        do {
            StringBuilder stringToHash = new StringBuilder(Integer.toString(id));
            stringToHash.append(setTimestamp());
            stringToHash.append(magicNumber);
            stringToHash.append(previousBlockHash);
            this.blockHash = StringUtil.applySha256(stringToHash.toString());
            success = blockHash.startsWith(startsWith.toString());
        } while (!success);
        Instant end = Instant.now();
        this.timeToMine = end.getEpochSecond() - start.getEpochSecond();
        return blockHash;
    }

    public long getTimeToMine() {
        return timeToMine;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public int getId() {
        return id;
    }

    public void printBlock() {
        System.out.println("Block:");
        System.out.println("Id: " + id);
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Magic number: " + magicNumber);
        System.out.println("Hash of the previous block: ");
        System.out.println(previousBlockHash);
        System.out.println("Hash of the block: ");
        System.out.println(blockHash);
        System.out.println("Block was generating for "
                + timeToMine + " seconds");
    }

}
