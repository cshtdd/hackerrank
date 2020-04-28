VISITED = -1

def traverse(row, col, row_count, col_count, matrix)
  queue = [{ row: row, col: col }]
  index = 0

  while index < queue.length
    current = queue[index]
    index += 1

    next if visited?(current, matrix)

    puts(read_at(current, matrix))
    mark_visited(current, matrix)

    queue += neighbors(current, row_count, col_count)
  end
end

def read_at(location, matrix)
  matrix[location[:row]][location[:col]]
end

def visited?(location, matrix)
  read_at(location, matrix) == VISITED
end

def mark_visited(location, matrix)
  matrix[location[:row]][location[:col]] = VISITED
end

def neighbors(location, row_count, col_count)
  all_neighbors = [
    { row: -1, col: 0 },
    { row: 0, col: 1 },
    { row: 1, col: 0 },
    { row: 0, col: -1 }
  ].map do |offset|
    { row: location[:row] + offset[:row],
      col: location[:col] + offset[:col] }
  end

  all_neighbors.filter { |loc| within_bounds?(loc, row_count, col_count) }
end

def within_bounds?(location, row_count, col_count)
  return false unless (0...row_count).include?(location[:row])
  return false unless (0...col_count).include?(location[:col])

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
