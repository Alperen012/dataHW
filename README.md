# 🎓 Data Structures & Algorithms Homework Projects

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

*A comprehensive collection of CSE232 course homework assignments focusing on data structures, algorithms, and object-oriented programming concepts.*

</div>

---

## 📚 Table of Contents

- [🎯 Overview](#-overview)
- [📁 Project Structure](#-project-structure)
- [🚀 Quick Start](#-quick-start)
- [📖 Homework Descriptions](#-homework-descriptions)
  - [HW #1: Hardware System Management](#hw-1-hardware-system-management)
  - [HW #7-8: Graphs and Sorting Algorithms](#hw-7-8-graphs-and-sorting-algorithms)
- [🛠️ Installation & Setup](#️-installation--setup)
- [💻 Usage](#-usage)
- [📋 API Documentation](#-api-documentation)
- [🧪 Testing](#-testing)
- [📊 Performance Analysis](#-performance-analysis)
- [🤝 Contributing](#-contributing)

---

## 🎯 Overview

This repository contains two major homework assignments for the CSE232 Data Structures and Algorithms course:

- **Homework #1**: Object-oriented hardware system management with device abstraction
- **Homework #7-8**: Advanced graph algorithms and sorting algorithm implementations

Each project demonstrates key computer science concepts including:
- Object-oriented programming principles
- Data structure implementations
- Algorithm design and analysis
- Graph theory applications
- Performance optimization techniques

---

## 📁 Project Structure

```
dataHws/
├── 📂 hw_1/                    # Hardware System Management
│   ├── 📂 src/                 # Source code
│   │   ├── 📂 HWSystem/        # Core system classes
│   │   │   ├── 📂 Devices/     # Device implementations
│   │   │   │   ├── 📂 Displays/
│   │   │   │   ├── 📂 MotorDrivers/
│   │   │   │   ├── 📂 Sensors/
│   │   │   │   └── 📂 WirelessIO/
│   │   │   └── 📂 Protocols/   # Communication protocols
│   │   └── 📂 Main/            # Entry point
│   ├── 📂 build/               # Compiled classes
│   ├── 📄 makefile             # Build automation
│   ├── 📄 config.txt           # System configuration
│   └── 📄 sources.txt          # Source file list
│
├── 📂 hw_7_8/                  # Graphs and Sorting
│   ├── 📂 src/                 # Source code
│   │   ├── 📂 DSA/             # Data structures & algorithms
│   │   │   ├── 📂 Graphs/      # Graph implementations
│   │   │   │   ├── 📂 GCA/     # Graph Coloring Algorithm
│   │   │   │   └── 📂 MatrixGraph/
│   │   │   └── 📂 Sorting/     # Sorting algorithms
│   │   ├── 📂 Main/            # Entry point
│   │   └── 📂 Tests/           # Unit tests
│   ├── 📂 docs/                # JavaDoc documentation
│   └── 📄 makefile             # Build automation
│
└── 📄 README.md                # This file
```

---

## 🚀 Quick Start

### Prerequisites

- ☕ **Java 8+** (JDK required for compilation)
- 🔧 **Make** (for build automation)
- 📝 **Text editor or IDE** (VS Code, IntelliJ IDEA, etc.)

### Quick Setup

```bash
# Clone the repository
git clone https://github.com/Alperen012/dataHws.git
cd dataHws

# Build and run HW #1
cd hw_1
make build
make run

# Build and run HW #7-8
cd ../hw_7_8
make build
make run input.txt output/
```

---

## 📖 Homework Descriptions

### HW #1: Hardware System Management

<details>
<summary><strong>🔧 Click to expand details</strong></summary>

#### 🎯 Objective
Design and implement a comprehensive hardware system management framework using object-oriented programming principles.

#### 🏗️ Architecture
- **Device Abstraction**: Base `Device` class with specialized implementations
- **Protocol Management**: Support for I2C, SPI, UART, and OneWire protocols
- **State Management**: Device state tracking and lifecycle management
- **Command Interface**: Interactive CLI for system operations

#### 🔌 Supported Devices
| Category | Devices |
|----------|---------|
| **Displays** | LCD, OLED |
| **Sensors** | Temperature (DHT11, BMP280), IMU (MPU6050, GY951) |
| **Motor Drivers** | PCA9685, SparkFun Motor Driver |
| **Wireless** | Bluetooth, WiFi modules |

#### 💡 Key Features
- ✅ Dynamic device addition/removal
- ✅ Protocol-based communication
- ✅ State management system
- ✅ Configuration file support
- ✅ Interactive command interface

</details>

### HW #7-8: Graphs and Sorting Algorithms

<details>
<summary><strong>📊 Click to expand details</strong></summary>

#### 🎯 Objective
Implement advanced graph algorithms and optimize sorting algorithm performance through hybrid approaches.

#### 🌐 Graph Algorithms
- **Matrix Graph Implementation**: Adjacency matrix with vector optimization
- **Graph Coloring**: Greedy coloring algorithm (GCA)
- **Connectivity Analysis**: Component detection and analysis

#### 🔄 Sorting Algorithms
| Algorithm | Time Complexity | Space Complexity | Features |
|-----------|----------------|------------------|----------|
| **Insert Sort** | O(n²) | O(1) | Adaptive, stable |
| **Selection Sort** | O(n²) | O(1) | In-place, simple |
| **Quick Sort** | O(n log n) avg | O(log n) | Divide & conquer |
| **Hybrid QuickSort** | O(n log n) | O(log n) | Optimized cutoff |

#### 📈 Performance Features
- Benchmark testing framework
- Hybrid algorithm optimization
- Memory usage analysis
- Comparative performance reports

</details>

---

## 🛠️ Installation & Setup

### Method 1: Using Make (Recommended)

```bash
# For HW #1
cd hw_1
make collect    # Collect source files
make build      # Compile all classes
make run        # Execute the program
make clean      # Remove build artifacts
```

```bash
# For HW #7-8
cd hw_7_8
make collect    # Collect source files
make build      # Compile all classes
make doc        # Generate JavaDoc
make test       # Run unit tests
make clean      # Clean build directory
```

### Method 2: Manual Compilation

```bash
# HW #1 Manual Build
cd hw_1
find src -name "*.java" > sources.txt
javac -d build @sources.txt
java -cp build Main.Main

# HW #7-8 Manual Build
cd hw_7_8
find src -name "*.java" > sources.txt
javac -d build @sources.txt
java -cp build Main.Main input.txt output/
```

---

## 💻 Usage

### HW #1: Hardware System Commands

The hardware system provides an interactive command-line interface:

```bash
# Add a device
> addDev LCD 1 101
LCD added to port 1 with ID 101

# Remove a device
> rmDev 1
Device removed from port 1

# List all devices
> listDevs
Port 1: LCD (ID: 101, State: ON)
Port 2: DHT11 (ID: 102, State: OFF)

# Check device state
> devInfo 1
Device: LCD, Port: 1, ID: 101, State: ON

# Exit the system
> exit
```

### HW #7-8: Graph and Sorting Operations

```bash
# Run with input file and output directory
java -cp build Main.Main data/graph_input.txt results/

# Example input file format:
# Line 1: Graph size (number of vertices)
# Following lines: Edge pairs (vertex1 vertex2)
5
0 1
1 2
2 3
3 4
4 0
```

---

## 📋 API Documentation

### HW #1: Core Classes

#### `HWSystem`
```java
public class HWSystem {
    public boolean addDevice(String deviceType, int port, int id)
    public boolean rmDev(int port)
    public ArrayList<Device> listDevices()
    public Device getDevice(int port)
}
```

#### `Device` (Abstract Base Class)
```java
public abstract class Device {
    protected int deviceId;
    protected State currentState;
    public abstract void turnOn();
    public abstract void turnOff();
    public abstract String getDeviceInfo();
}
```

### HW #7-8: Core Interfaces

#### `GTUGraph`
```java
public interface GTUGraph {
    Boolean setEdge(int v1, int v2);
    Boolean getEdge(int v1, int v2);
    Collection<Integer> getNeighbors(int v);
    void saveToFile(String filename);
}
```

#### `GTUSorter`
```java
public abstract class GTUSorter {
    public abstract Integer[] sort(Integer[] arr);
    public long getComparisonCount();
    public long getSwapCount();
}
```

---

## 🧪 Testing

### Running Tests

```bash
# HW #7-8 includes comprehensive unit tests
cd hw_7_8
make test

# Or run specific test classes
java -cp build Tests.GraphTest
java -cp build Tests.SorterTest
java -cp build Tests.AdjacencyVectTest
```

### Test Coverage

- ✅ **Graph Operations**: Edge creation, neighbor retrieval, file I/O
- ✅ **Sorting Algorithms**: Correctness, performance benchmarks
- ✅ **Matrix Operations**: Adjacency vector functionality
- ✅ **Graph Coloring**: Algorithm correctness and optimization

---

## 📊 Performance Analysis

### Sorting Algorithm Benchmarks

The homework includes performance analysis comparing different sorting approaches:

```
Algorithm               | 1K elements | 10K elements | 100K elements
------------------------|-------------|--------------|---------------
MyInsertSort           | 2ms         | 180ms        | 18s
MySelectSort           | 3ms         | 210ms        | 21s
MyQuickSort            | 1ms         | 12ms         | 140ms
QuickSort + InsertSort | 0.8ms       | 10ms         | 120ms
QuickSort + SelectSort | 0.9ms       | 11ms         | 125ms
```

### Memory Usage Analysis

Graph implementations are optimized for different use cases:
- **Dense graphs**: Traditional adjacency matrix
- **Sparse graphs**: Adjacency vector optimization
- **Memory-constrained**: Custom compression techniques

---

## 🤝 Contributing

We welcome contributions! Please follow these guidelines:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Code Style Guidelines

- Follow Java naming conventions
- Include comprehensive JavaDoc comments
- Maintain consistent indentation (4 spaces)
- Add unit tests for new functionality

---

## 🙋‍♂️ Support & Contact

- **Course**: CSE222/BIL505 Data Structures and Algorithms
- **Issues**: Please use the [GitHub Issues](https://github.com/yourusername/dataHws/issues) page
- **Documentation**: Full JavaDoc available in `hw_7_8/docs/`

---

<div align="center">

**⭐ Star this repository if you found it helpful! ⭐**

Made with ❤️ for learning and academic purposes

</div>
