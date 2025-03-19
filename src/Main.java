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
        Blockchain blockchain = new Blockchain(4);
        
        // Demonstrate the mining process with sample transactions
        System.out.println("\n=== Mining Demonstration ===");
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Henry", "Ivy", "Jack"};
        
        for (int i = 0; i < 20; i++) {
            System.out.println("\nMining block " + (i + 1) + "...");
            // Generate random transactions between different users
            int sender = (int) (Math.random() * names.length);
            int receiver = (int) (Math.random() * names.length);
            while (receiver == sender) {
                receiver = (int) (Math.random() * names.length);
            }
            int amount = (int) (Math.random() * 100) + 1;
            
            Transaction transaction = new Transaction(names[sender], names[receiver], amount);
            blockchain.addBlock(new Block(transaction, ""));
        }

        /*
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
        String originalData = secondBlock.getData();  // Store original data
        
        System.out.println("Attempting to modify block data...");
        System.out.println("Original transaction: " + originalData);
        
        // Perform tampering
        String tamperedData = "Tampered: Bob sends 1000 coins to Charlie";
        secondBlock.setData(tamperedData);
        System.out.println("Tampered transaction: " + secondBlock.getData());
        
        // Verify chain after tampering
        if (blockchain.isChainValid()) {
            System.out.println("WARNING: Chain validation failed to detect tampering!");
        } else {
            System.out.println("SUCCESS: Chain validation detected tampering!");
        }
        
        // Restore original data (optional)
        secondBlock.setData(originalData);
    }
}