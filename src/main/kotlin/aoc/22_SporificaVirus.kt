package aoc

data class Point(val x: Int, val y: Int) {
    fun step(step: Point) = Point(x + step.x, y + step.y)
}

class SportificaVirus(input: String) {
    private val directions = listOf(Point(0, -1), Point(1, 0), Point(0, 1), Point(-1, 0))
    private var carrier = Point(0, 0)
    private var dir = directions[0]
    private val map: MutableMap<Point, Char>
    
    init {
        this.map = input.split("\n")
                .mapIndexed { y, row ->
                    row.mapIndexed { x, char ->
                        Point(x - row.length / 2, y - row.length / 2) to char
                    }
                }
                .flatten().toMap().toMutableMap()
    }


    fun infection(bursts: Int): Int {
        var infected = 0

        repeat(bursts) {
            when (map[carrier] ?: '.') {
                '#' -> {
                    dir = directions[(directions.indexOf(dir) + 1) % 4]
                    map[carrier] = '.'
                }
                else -> {
                    dir = directions[(directions.indexOf(dir) + 3) % 4]
                    map[carrier] = '#'
                    infected++
                }
            }
            carrier = carrier.step(dir)
        }

        return infected
    }
    
    fun evolved(bursts: Int): Int {
        var infected = 0

        repeat(bursts) {
            when (map[carrier] ?: '.') {
                '.' -> {
                    dir = directions[(directions.indexOf(dir) + 3) % 4]
                    map[carrier] = 'W'
                }
                '#' -> {
                    dir = directions[(directions.indexOf(dir) + 1) % 4]
                    map[carrier] = 'F'
                }
                'F' -> {
                    dir = directions[(directions.indexOf(dir) + 2) % 4]
                    map[carrier] = '.'
                }
                else -> {
                    map[carrier] = '#'
                    infected++
                } // W
            }
            carrier = carrier.step(dir)
        }
        
        return infected
    }
}