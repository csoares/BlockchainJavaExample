# Blockchain Demo Project

A simple yet powerful demonstration of blockchain technology implemented in Java. This project showcases the core concepts and features of blockchain, making it perfect for learning and understanding how blockchain works.

## Features

- **Block Mining**: Demonstrates the Proof of Work (PoW) concept with adjustable difficulty
- **Chain Traversal**: View and inspect all blocks in the chain
- **Integrity Verification**: Automatic validation of the entire blockchain
- **Tamper Detection**: Demonstrates how blockchain detects and prevents data tampering

## Project Structure

```
src/
  ├── Block.java         # Block implementation with mining capability
  ├── Blockchain.java    # Main blockchain logic and chain management
  └── Main.java          # Demo application with examples
```

## Technical Details

### Block Structure
Each block in the chain contains:
- Timestamp of creation
- Transaction data
- Previous block's hash (chain link)
- Current block's hash
- Nonce value (proof of work)

### Mining Process
The mining process requires finding a hash that starts with a specific number of zeros (defined by difficulty). This demonstrates the computational work required in real blockchain networks.

### Chain Validation
The blockchain maintains its integrity through:
- Hash linking between blocks
- Continuous validation of the entire chain
- Immediate detection of any data tampering

## Running the Demo

1. Compile the Java files:
```bash
javac src/*.java
```

2. Run the main class:
```bash
java src.Main
```

## Demo Output

The demo will show:
1. Mining of new blocks with sample transactions
2. Display of the entire blockchain contents
3. Verification of chain integrity
4. Demonstration of tamper detection

## Understanding the Output

### Mining Demonstration
Watch as new blocks are mined with increasing difficulty. The mining process shows how Proof of Work ensures security.

### Blockchain Contents
See the complete chain with all block details, demonstrating transparency and traceability.

### Tampering Detection
Observe how the blockchain detects unauthorized modifications, ensuring data integrity.

## Educational Value

This demo is particularly useful for:
- Understanding blockchain fundamentals
- Learning about cryptographic hashing
- Seeing Proof of Work in action
- Studying data integrity mechanisms

## Implementation Notes

- Written in pure Java for clarity and portability
- Configurable mining difficulty (default: 4)
- Uses SHA-256 for hash generation
- Includes comprehensive comments for educational purposes

## Contributing

Feel free to fork this project and enhance it. Some possible improvements:
- Add more transaction types
- Implement different consensus mechanisms
- Add networking capabilities
- Create a web interface

## License

This project is open source and available under the MIT License.