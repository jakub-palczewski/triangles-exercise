# Assumptions

## Path Definition

A path moves from top to bottom of the triangle, selecting one number from each row.

### Adjacent Nodes Definition
From position `i` in row `n`, you can move to positions `i` or `i+1` in row `n+1`.

### Visual Example
```
        7            Row 0: position 0 only
       / \
      6   3          Row 1: positions 0, 1
     / \ / \
    3   8   5        Row 2: positions 0, 1, 2
   / \ / \ / \
  11  2  10  9       Row 3: positions 0, 1, 2, 3
```

**Example paths:**
- From `7` (row 0, pos 0) → can go to `6` (row 1, pos 0) or `3` (row 1, pos 1)
- From `6` (row 1, pos 0) → can go to `3` (row 2, pos 0) or `8` (row 2, pos 1)
- From `3` (row 1, pos 1) → can go to `8` (row 2, pos 1) or `5` (row 2, pos 2)

**Sample complete path:** 7 → 6 → 3 → 2 (minimal path with sum = 18)

## Input Format
- Triangle is provided via standard input (System.in in JVM, typically console input)
- Each row is on a separate line
- Numbers within a row are separated by one or more spaces
- All values must be integers
- Empty lines are ignored

## Input Validation
- **Empty input**: Program exits with descriptive error message
- **Invalid triangle structure**: Program exits with error if row `n` doesn't have exactly `n+1` elements (0-indexed)
- **Non-numeric values**: Validated during parsing, program exits with error showing the invalid value
- **Negative numbers**: Allowed as valid input

## Output Format
- Output shows the complete path with all node values and sum calculation
- Format: `Minimal path is: num1 + num2 + ... + numN = sum`
- Example: `Minimal path is: 7 + 6 + 3 + 2 = 18`

## Edge Cases
- **Single row triangle**: Valid input, path contains only that single number
- **Multiple minimal paths**: If multiple paths have the same minimal sum, any one of them is acceptable
- **Large numbers**: Program should handle standard 32-bit integer range