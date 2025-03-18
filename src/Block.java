import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Represents a block in the blockchain.
 * Each block contains data and is cryptographically linked to the previous block,
 * forming a chain of immutable records.
 */
public class Block {
    // The cryptographic hash of this block's contents
    private String hash;
    // The hash of the previous block, creating the chain link
    private String previousHash;
    // The actual data/transactions stored in this block
    private String data;
    // Timestamp when this block was created
    private long timestamp;
    // Number used in proof-of-work to find a valid hash
    private int nonce;

    /**
     * Creates a new block with data and links it to the previous block
     * @param data The data to store in this block
     * @param previousHash The hash of the previous block
     */
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
    }

    /**
     * Calculates the cryptographic hash of this block.
     * The hash includes:
     * - Previous block's hash (for chain integrity)
     * - Timestamp (for uniqueness)
     * - Nonce (for proof of work)
     * - Block's data
     * Uses SHA-256 algorithm for secure hashing
     */
    public String calculateHash() {
        String dataToHash = previousHash + 
                          Long.toString(timestamp) + 
                          Integer.toString(nonce) + 
                          data;
        
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
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public int getNonce() {
        return nonce;
    }
}