# Triangles Exercise

A Scala 3 solution to find the minimum sum path from top to bottom of a triangle of numbers.
Solution algorithm implemented using dynamic programming.
There are 2 implementations:
 - pure Scala Vector based
 - using fs2 streams for more functional approach

## Problem

Given a triangle of numbers, find the path that minimizes the sum. You can only move to adjacent numbers on the row below.
For more assumptions check file ASSUMPTIONS.md.

Example:
```
    7
   6 3
  3 8 5
 11 2 10 9
```
Minimum path: `7 → 3 → 8 → 2` (sum = 20)

## How to Run

### Prerequisites
- Java 11+
- sbt

### Run the Application
```bash
sbt run
```

Enter triangle data row by row (each row has one more number than the previous):
```
7
6 3
3 8 5
11 2 10 9

```
(Empty line to finish)

### Run Tests
```bash
sbt test
```

## Input Rules
- Numbers separated by spaces
- Each row must have exactly one more number than the previous
- Only positive integers
- Empty line to finish input
