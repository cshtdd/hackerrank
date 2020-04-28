VISITED = -1

def traverse(row, col, row_count, col_count, matrix)
  queue = []
  queue << {row: row, col: col}
  index = 0

  while index < queue.length
    current = queue[index]
    index += 1
    current_row = current[:row]
    current_col = current[:col]
    current_value = matrix[current_row][current_col]

    next if current_value == VISITED

    puts(current_value)
    matrix[current_row][current_col] = VISITED

    queue += neighbors(current_row, current_col, row_count, col_count)
  end
end

def neighbors(row, col, row_count, col_count)
  result = []

  offsets = [
    { row: -1, col: 0 },
    { row: 0, col: 1 },
    { row: 1, col: 0 },
    { row: 0, col: -1 }
  ]

  offsets.each do |offset|
    updated_row = row + offset[:row]
    updated_col = col + offset[:col]

    if within_bounds?(updated_row, updated_col, row_count, col_count)
      result << { row: updated_row, col: updated_col }
    end
  end

  result
end

def within_bounds?(row, col, row_count, col_count)
  return false unless (0...row_count).include?(row)
  return false unless (0...col_count).include?(col)

  true
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
