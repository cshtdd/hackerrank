VISITED = -1

def traverse(row, col, row_count, col_count, matrix)
  return if row < 0 || col < 0 || row >= row_count || col >= col_count

  return if matrix[row][col] == VISITED

  puts(matrix[row][col])
  matrix[row][col] = VISITED

  traverse(row - 1, col, row_count, col_count, matrix)
  traverse(row, col + 1, row_count, col_count, matrix)
  traverse(row + 1, col, row_count, col_count, matrix)
  traverse(row, col - 1, row_count, col_count, matrix)
end

def main
  m = [
    [1, 2, 3, 4, 5, 6],
    [7, 8, 9, 10, 11, 12],
    [13, 14, 15, 16, 17, 18],
    [19, 20, 21, 22, 23, 24]
  ]

  traverse(0, 0, 4, 6, m)
end

main
