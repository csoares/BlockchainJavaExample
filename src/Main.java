/**
 * Demonstration of a simple blockchain implementation
 * This class shows the core features of blockchain technology:
 * - Mining new blocks
 * - Chain traversal
 * - Integrity verification
 * - Tamper detection
 */
public class Main {
    public static void main(String[] args) {
        // Initialize blockchain with difficulty 4
        // Higher difficulty means more computational work needed to mine blocks
        Blockchain blockchain = new Blockchain(4);
        
        // Demonstrate the mining process with sample transactions
        System.out.println("\n=== Mining Demonstration ===");
        System.out.println("Mining block 1...");
        blockchain.addBlock(new Block("Transaction: Alice sends 50 coins to Bob", "0"));
        
        System.out.println("\nMining block 2...");
        blockchain.addBlock(new Block("Transaction: Bob sends 30 coins to Charlie", "0"));
        
        System.out.println("\nMining block 3...");
        blockchain.addBlock(new Block("Transaction: Charlie sends 10 coins to David", "0"));
        
        /**
         * Displays all blocks in the chain with their properties:
         * - Block number
         * - Creation timestamp
         * - Transaction data
         * - Previous block's hash (chain link)
         * - Current block's hash
         * - Nonce value (proof of work)
         */
        System.out.println("\n=== Blockchain Contents ===");
        displayBlockchain(blockchain);
        
        // Verify the integrity of the entire chain
        System.out.println("\n=== Blockchain Verification ===");
        verifyBlockchain(blockchain);
        
        // Show how tampering is detected through hash validation
        System.out.println("\n=== Tampering Detection Demo ===");
        demonstrateTampering(blockchain);
    }
    
    /**
     * Traverses the blockchain and displays detailed information about each block
     * This demonstrates the transparency and traceability of blockchain records
     */
    private static void displayBlockchain(Blockchain blockchain) {
        for (int i = 0; i < blockchain.getChain().size(); i++) {
            Block block = blockchain.getChain().get(i);
            System.out.println("\nBlock #" + i);
            System.out.println("Timestamp: " + new java.util.Date(block.getTimestamp()));
            System.out.println("Data: " + block.getData());
            System.out.println("Previous Hash: " + block.getPreviousHash());
            System.out.println("Hash: " + block.getHash());
            System.out.println("Nonce: " + block.getNonce());
        }
    }
    
    /**
     * Demonstrates the blockchain's ability to verify its own integrity
     * by checking the hash links between blocks
     */
    private static void verifyBlockchain(Blockchain blockchain) {
        System.out.println("Verifying blockchain integrity...");
        if (blockchain.isChainValid()) {
            System.out.println("Blockchain is valid! All blocks are properly linked.");
        } else {
            System.out.println("Blockchain is invalid! Chain has been tampered with.");
        }
    }
    
    /**
     * Demonstrates the blockchain's tamper-evident property
     * by attempting to modify data in an existing block
     * This shows how the hash-chain makes the blockchain immutable
     */
    private static void demonstrateTampering(Blockchain blockchain) {
        // Try to tamper with block data
        Block secondBlock = blockchain.getChain().get(1);
        String originalData = secondBlock.getData();
        secondBlock.setData("Tampered: Bob sends 1000 coins to Charlie");
        
        System.out.println("Attempting to modify block data...");
        System.out.println("Original data: " + originalData);
        System.out.println("Tampered data: " + secondBlock.getData());
        
        // Verify chain after tampering
        if (blockchain.isChainValid()) {
            System.out.println("WARNING: Chain validation failed to detect tampering!");
        } else {
            System.out.println("SUCCESS: Chain validation detected tampering!");
        }
    }
}