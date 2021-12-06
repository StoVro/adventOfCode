package code

import input.INPUT_DAY5_ALL_POINTS
import input.Point

fun main() {
    day5FirstAndSecondPuzzle()
}

fun day5FirstAndSecondPuzzle() {
    val validPairsOfPoints = mutableListOf<Pair<Point, Point>>()
    val diagram = mutableListOf<Point>()

    INPUT_DAY5_ALL_POINTS.forEach {
        if(checkValidityOfPair(it)) {
            validPairsOfPoints.add(it)
        }
    }

    println(validPairsOfPoints.size)

    validPairsOfPoints.forEach {
        diagram.addPointsToDiagram(it)
    }

    println(diagram.filter {it.times > 1 }.size)
}

fun checkValidityOfPair(pair: Pair<Point, Point>) : Boolean {
    return if(checkXorYValidity(pair).not()) {
        checkDiagonalValidity(pair)
    } else {
        true
    }
}

fun checkXorYValidity(pair: Pair<Point, Point>) : Boolean {
    return ((pair.first.x == pair.second.x) || (pair.first.y == pair.second.y))
}

fun checkDiagonalValidity(pair: Pair<Point, Point>) : Boolean {
    while(true) {
        if (pair.first.x < pair.second.x) {
            if (pair.first.y < pair.second.y) {
                var newX = pair.first.x
                var newY = pair.first.y
                while((newX <= pair.second.x) || (newY <= pair.second.y)) {
                    newX  += 1
                    newY  += 1
                    if ((newX == pair.second.x) && (newY == pair.second.y)) {
                        return true
                    }
                }
            } else {
                // pair.first.y >= pair.second.y
                var newX = pair.first.x
                var newY = pair.first.y
                while((newX <= pair.second.x) || (newY >= pair.second.y)) {
                    newX += 1
                    newY -= 1
                    if ((newX == pair.second.x) && (newY == pair.second.y)) {
                        return true
                    }
                }
            }
        } else {
            //pair.first.x >= pair.second.x
            if (pair.first.y < pair.second.y) {
                var newX = pair.first.x
                var newY = pair.first.y
                while((newX >= pair.second.x) || (newY <= pair.second.y)) {
                    newX -= 1
                    newY += 1
                    if ((newX == pair.second.x) && (newY == pair.second.y)) {
                        return true
                    }
                }
            } else {
                // pair.first.y >= pair.second.y
                var newX = pair.first.x
                var newY = pair.first.y
                while((newX >= pair.second.x) || (newY >= pair.second.y)) {
                    newX -= 1
                    newY -= 1
                    if ((newX == pair.second.x) && (newY == pair.second.y)) {
                        return true
                    }
                }
            }
        }
    }
    return false
}

fun MutableList<Point>.addPointsToDiagram(pair: Pair<Point, Point>) {
    if((pair.first.x == pair.second.x) || (pair.first.y == pair.second.y)) {
        if ((pair.first.x == pair.second.x)) {
            if ((pair.first.y < pair.second.y)) {
                val yRange = pair.first.y..pair.second.y
                yRange.forEach { y ->
                    if (this.checkIfPositionIsExistent(pair.first.x, y)) {
                        val index = this.indexOfFirst { it.x == pair.first.x && it.y == y }
                        val point = this[index]
                        val newTimes = point.times.plus(1)
                        this[index] = point.copy(times = newTimes)
                    } else {
                        this.add(Point(pair.first.x, y, 1))
                    }
                }
            } else {
                val yRange = pair.second.y..pair.first.y
                yRange.forEach { y ->
                    if (this.checkIfPositionIsExistent(pair.first.x, y)) {
                        val index = this.indexOfFirst { it.x == pair.first.x && it.y == y }
                        val point = this[index]
                        val newTimes = point.times.plus(1)
                        this[index] = point.copy(times = newTimes)
                    } else {
                        this.add(Point(pair.first.x, y, 1))
                    }
                }
            }
        } else {
            if ((pair.first.x < pair.second.x)) {
                val xRange = pair.first.x..pair.second.x
                xRange.forEach { x ->
                    if (this.checkIfPositionIsExistent(x, pair.first.y)) {
                        val index = this.indexOfFirst { it.x == x && it.y == pair.first.y }
                        val point = this[index]
                        val newTimes = point.times.plus(1)
                        this[index] = point.copy(times = newTimes)
                    } else {
                        this.add(Point(x, pair.first.y, 1))
                    }
                }
            } else {
                val xRange = pair.second.x..pair.first.x
                xRange.forEach { x ->
                    if (this.checkIfPositionIsExistent(x, pair.first.y)) {
                        val index = this.indexOfFirst { it.x == x && it.y == pair.first.y }
                        val point = this[index]
                        val newTimes = point.times.plus(1)
                        this[index] = point.copy(times = newTimes)
                    } else {
                        this.add(Point(x, pair.first.y, 1))
                    }
                }
            }
        }
    } else {
        if (pair.first.x < pair.second.x) {
            if (pair.first.y < pair.second.y) {
                var newX = pair.first.x
                var newY = pair.first.y
                while((newX <= pair.second.x) || (newY <= pair.second.y)) {
                    if (this.checkIfPositionIsExistent(newX, newY)) {
                        val index = this.indexOfFirst { it.x == newX && it.y == newY }
                        val point = this[index]
                        val newTimes = point.times.plus(1)
                        this[index] = point.copy(times = newTimes)
                    } else {
                        this.add(Point(newX, newY, 1))
                    }
                    newX  += 1
                    newY  += 1
                    if ((newX == pair.second.x) && (newY == pair.second.y)) {
                        if (this.checkIfPositionIsExistent(newX, newY)) {
                            val index = this.indexOfFirst { it.x == newX && it.y == newY }
                            val point = this[index]
                            val newTimes = point.times.plus(1)
                            this[index] = point.copy(times = newTimes)
                        } else {
                            this.add(Point(newX, newY, 1))
                        }
                        break
                    }
                }
            } else {
                // pair.first.y >= pair.second.y
                var newX = pair.first.x
                var newY = pair.first.y
                while((newX <= pair.second.x) || (newY >= pair.second.y)) {
                    if (this.checkIfPositionIsExistent(newX, newY)) {
                        val index = this.indexOfFirst { it.x == newX && it.y == newY }
                        val point = this[index]
                        val newTimes = point.times.plus(1)
                        this[index] = point.copy(times = newTimes)
                    } else {
                        this.add(Point(newX, newY, 1))
                    }
                    newX  += 1
                    newY  -= 1
                    if ((newX == pair.second.x) && (newY == pair.second.y)) {
                        if (this.checkIfPositionIsExistent(newX, newY)) {
                            val index = this.indexOfFirst { it.x == newX && it.y == newY }
                            val point = this[index]
                            val newTimes = point.times.plus(1)
                            this[index] = point.copy(times = newTimes)
                        } else {
                            this.add(Point(newX, newY, 1))
                        }
                        break
                    }
                }
            }
        } else {
            //pair.first.x >= pair.second.x
            if (pair.first.y < pair.second.y) {
                var newX = pair.first.x
                var newY = pair.first.y
                while((newX >= pair.second.x) || (newY <= pair.second.y)) {
                    if (this.checkIfPositionIsExistent(newX, newY)) {
                        val index = this.indexOfFirst { it.x == newX && it.y == newY }
                        val point = this[index]
                        val newTimes = point.times.plus(1)
                        this[index] = point.copy(times = newTimes)
                    } else {
                        this.add(Point(newX, newY, 1))
                    }
                    newX  -= 1
                    newY  += 1
                    if ((newX == pair.second.x) && (newY == pair.second.y)) {
                        if (this.checkIfPositionIsExistent(newX, newY)) {
                            val index = this.indexOfFirst { it.x == newX && it.y == newY }
                            val point = this[index]
                            val newTimes = point.times.plus(1)
                            this[index] = point.copy(times = newTimes)
                        } else {
                            this.add(Point(newX, newY, 1))
                        }
                        break
                    }
                }
            } else {
                // pair.first.y >= pair.second.y
                var newX = pair.first.x
                var newY = pair.first.y
                while((newX >= pair.second.x) || (newY >= pair.second.y)) {
                    if (this.checkIfPositionIsExistent(newX, newY)) {
                        val index = this.indexOfFirst { it.x == newX && it.y == newY }
                        val point = this[index]
                        val newTimes = point.times.plus(1)
                        this[index] = point.copy(times = newTimes)
                    } else {
                        this.add(Point(newX, newY, 1))
                    }
                    newX  -= 1
                    newY  -= 1
                    if ((newX == pair.second.x) && (newY == pair.second.y)) {
                        if (this.checkIfPositionIsExistent(newX, newY)) {
                            val index = this.indexOfFirst { it.x == newX && it.y == newY }
                            val point = this[index]
                            val newTimes = point.times.plus(1)
                            this[index] = point.copy(times = newTimes)
                        } else {
                            this.add(Point(newX, newY, 1))
                        }
                        break
                    }
                }
            }
        }
    }
}

fun MutableList<Point>.checkIfPositionIsExistent(x: Int, y: Int) : Boolean {
    this.forEach {
        if((it.x == x) && (it.y == y)) {
            return true
        }
    }
    return false
}



