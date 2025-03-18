import java.util.ArrayList;

/**
 * Represents the complete blockchain system.
 * A blockchain is a continuously growing list of blocks that are linked and secured
 * using cryptography. Each block contains data and a reference to the previous block.
 */
public class Blockchain {
    // Stores all blocks in the chain
    private ArrayList<Block> chain;
    // Mining difficulty - number of leading zeros required in block hash
    private int difficulty;

    /**
     * Initializes a new blockchain with specified mining difficulty.
     * Creates the first block (genesis block) with no previous hash.
     * 
     * @param difficulty The mining difficulty level
     */
    public Blockchain(int difficulty) {
        chain = new ArrayList<Block>();
        this.difficulty = difficulty;
        // Create genesis block - the first block in the chain
        addBlock(new Block("Genesis Block", "0"));
    }

    /**
     * Adds a new block to the blockchain.
     * Process:
     * 1. Links the new block to the previous block
     * 2. Mines the block (proof of work)
     * 3. Adds it to the chain
     * 
     * @param newBlock The block to be added to the chain
     */
    public void addBlock(Block newBlock) {
        if (chain.size() > 0) {
            newBlock = new Block(newBlock.getHash(), getLatestBlock().getHash());
        }
        newBlock.mineBlock(difficulty);
        chain.add(newBlock);
    }

    /**
     * Returns the most recently added block in the chain
     */
    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    /**
     * Validates the entire blockchain.
     * Checks:
     * 1. Each block's hash is valid
     * 2. Each block correctly references the previous block's hash
     * 
     * This ensures the chain hasn't been tampered with, as changing
     * any data would invalidate all subsequent blocks.
     * 
     * @return true if the chain is valid, false if any tampering is detected
     */
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            // Verify current block's hash is valid
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }

            // Verify block links are intact
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the entire blockchain
     */
    public ArrayList<Block> getChain() {
        return chain;
    }
}