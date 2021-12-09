package code

import input.INPUT_DAY9_HEIGHTS

fun main() {
    day9FirstPuzzle()
}

fun day9FirstPuzzle() {
    val lowPoints = mutableListOf<Int>()

    val directions = getDirections()

    val xMaximum = INPUT_DAY9_HEIGHTS[0].length - 1
    val yMaximum = INPUT_DAY9_HEIGHTS.size - 1

    var testPoints = mutableListOf<Int>()

    for(y in 0..yMaximum) {
        for(x in 0..xMaximum) {
            val currentPoint = (INPUT_DAY9_HEIGHTS[y][x] - '0')

            directions.forEach {
                val testPointX = x + it.horizontalChange
                val testPointY = y + it.verticalChange

                if(testPointX < 0 || testPointY < 0 || testPointX > xMaximum || testPointY > yMaximum) {
                    return@forEach
                }
                
                testPoints.add(INPUT_DAY9_HEIGHTS[testPointY][testPointX] - '0')
            }

            testPoints.minOrNull()?.let {
                if(currentPoint < it) {
                    lowPoints.add(currentPoint + 1)
                }
            }
            testPoints = mutableListOf()
        }
    }

    println(lowPoints.sum())
}

fun getDirections() : Array<Direction> {
    return arrayOf(Direction(-1,0), Direction(0,1), Direction(1,0), Direction(0,-1))
}

data class Direction(val horizontalChange: Int, val verticalChange: Int) {

}