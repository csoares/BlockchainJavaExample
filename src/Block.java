import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Represents a block in the blockchain.
 * Each block contains data and is cryptographically linked to the previous block,
 * forming a chain of immutable records.
 */
public class Block {
    private long timestamp;
    private Transaction transaction;
    private String previousHash;
    private String hash;
    private int nonce;

    public Block(Transaction transaction, String previousHash) {
        this.timestamp = System.currentTimeMillis();
        this.transaction = transaction;
        this.previousHash = previousHash;
        this.hash = calculateHash();
        this.nonce = 0;
    }

    // Update getter/setter
    public Transaction getTransaction() { return transaction; }
    public void setTransaction(Transaction transaction) { 
        this.transaction = transaction;
        this.hash = calculateHash();
    }

    // For compatibility with existing code
    // Calculates the cryptographic hash of this block.
    // The hash includes:
    // - Previous block's hash (for chain integrity)
    // - Timestamp (for uniqueness)
    // - Nonce (for proof of work)
    // - Block's data
    // Uses SHA-256 algorithm for secure hashing
        public String calculateHash() {
            String dataToHash = previousHash + 
                          Long.toString(timestamp) + 
                          Integer.toString(nonce) + 
                          transaction.toString();
            
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] bytes = digest.digest(dataToHash.getBytes());
                StringBuilder builder = new StringBuilder();
                for (byte b : bytes) {
                    builder.append(String.format("%02x", b));
                }
                return builder.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }

    // Remove duplicate getData() and setData() methods
    public String getData() {
        return transaction.toString();
    }
    
    public void setData(String data) {
        // Parse the data string to extract transaction details
        String[] parts = data.split(" ");
        String sender = parts[1];
        int amount = Integer.parseInt(parts[3]);
        String receiver = parts[6];
        this.transaction = new Transaction(sender, receiver, amount);
        this.hash = calculateHash();
    }

    /**
     * Implements the Proof of Work algorithm.
     * Repeatedly calculates the block's hash while incrementing the nonce
     * until finding a hash with the required number of leading zeros.
     * This process makes it computationally expensive to create new blocks,
     * but easy to verify them.
     * 
     * @param difficulty The number of leading zeros required in the hash
     */
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined: " + hash);
    }

    // Getters
    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public int getNonce() {
        return nonce;
    }
}