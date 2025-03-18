import java.util.ArrayList;

public class Blockchain {
    private ArrayList<Block> chain;
    private int difficulty;

    public Blockchain(int difficulty) {
        chain = new ArrayList<Block>();
        this.difficulty = difficulty;
        // Create genesis block with system initialization message
        Transaction genesisTransaction = new Transaction("System", "Network", 0);
        Block genesisBlock = new Block(genesisTransaction, "0");
        genesisBlock.mineBlock(difficulty);  // Mine the genesis block
        chain.add(genesisBlock);
    }

    public void addBlock(Block newBlock) {
        // Get the previous block's hash
        String previousHash = chain.get(chain.size()-1).getHash();
        // Create new block with correct previous hash
        Block block = new Block(newBlock.getTransaction(), previousHash);
        block.mineBlock(difficulty);
        chain.add(block);
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i-1);

            // Verify current block's hash
            String calculatedHash = currentBlock.calculateHash();
            if (!currentBlock.getHash().equals(calculatedHash)) {
                return false;
            }

            // Compare previous hash reference
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Block> getChain() {
        return chain;
    }
}