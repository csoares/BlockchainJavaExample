/**
 * Represents a transaction in the blockchain system.
 * Each transaction records the transfer of coins from one user to another.
 * This class is immutable to ensure transaction integrity within the blockchain.
 */
public class Transaction {
    /** The address/name of the user sending the coins */
    private String sender;
    
    /** The address/name of the user receiving the coins */
    private String receiver;
    
    /** The amount of coins being transferred (must be positive) */
    private int amount;

    /**
     * Creates a new transaction with specified sender, receiver, and amount.
     *
     * @param sender   The address/name of the user sending the coins
     * @param receiver The address/name of the user receiving the coins
     * @param amount   The amount of coins to transfer
     */
    public Transaction(String sender, String receiver, int amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    /**
     * Gets the sender's address/name.
     * @return The sender's address/name
     */
    public String getSender() { return sender; }

    /**
     * Gets the receiver's address/name.
     * @return The receiver's address/name
     */
    public String getReceiver() { return receiver; }

    /**
     * Gets the amount of coins being transferred.
     * @return The amount of coins
     */
    public int getAmount() { return amount; }

    /**
     * Returns a string representation of the transaction.
     * Format: "Transaction: [sender] sends [amount] coins to [receiver]"
     *
     * @return A formatted string describing the transaction
     */
    @Override
    public String toString() {
        return String.format("Transaction: %s sends %d coins to %s", sender, amount, receiver);
    }
}