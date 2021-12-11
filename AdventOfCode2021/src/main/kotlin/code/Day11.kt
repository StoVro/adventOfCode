package code

import helpers.Point
import helpers.getAllDirections
import input.INPUT_DAY11_ENERGIES

fun main() {
    day11FirstPuzzle()
}

fun day11FirstPuzzle() {
    var allFlashes = 0

    var input = INPUT_DAY11_ENERGIES

    val xMaximum = input[0].size - 1
    val yMaximum = input.size - 1

    repeat(100) {
        val allStepFlashedPoints = mutableListOf<Point>()
        var allIterationFlashedPoints = mutableListOf<Point>()

        input = input.increaseEnergyByOne()

        while(true) {
            allIterationFlashedPoints.clear()
            if(input.hasFlashingOctopuses(allStepFlashedPoints).not()) {
                if(allStepFlashedPoints.isNotEmpty()) {
                    input = input.resetAlreadyFlashedPoints()
                }
                break
            }

            allIterationFlashedPoints = input.pointsFlashInIteration()

            allIterationFlashedPoints.forEach { currentPoint ->
                if(allStepFlashedPoints.containsCurrentPoint(currentPoint).not()) {
                    allStepFlashedPoints.add(Point(currentPoint.y, currentPoint.x))
                    val directions = getAllDirections()
                    allFlashes += 1

                    directions.forEach directions@{ direction ->
                        val testPointX = currentPoint.x + direction.horizontalChange
                        val testPointY = currentPoint.y + direction.verticalChange

                        if(testPointX < 0 || testPointY < 0 || testPointX > xMaximum || testPointY > yMaximum) {
                            return@directions
                        }

                        val oldValue = input[testPointY][testPointX]
                        input[testPointY][testPointX] = oldValue + 1
                    }
                }
            }
        }
    }

    println(allFlashes)
}

fun List<List<Int>>.pointsFlashInIteration() : MutableList<Point> {
    val pointsToFlash = mutableListOf<Point>()
    this.forEachIndexed { yIndex, row ->
        row.forEachIndexed { xIndex, number ->
            if(number > 9) {
                pointsToFlash.add(Point(yIndex, xIndex))
            }
        }
    }
    return pointsToFlash
}

fun MutableList<MutableList<Int>>.increaseEnergyByOne() : MutableList<MutableList<Int>> {
    return this.map { row ->
        row.map { it + 1 }.toMutableList()
    }.toMutableList()
}

fun MutableList<MutableList<Int>>.resetAlreadyFlashedPoints() : MutableList<MutableList<Int>> {
    return this.map { row ->
        row.map { handleOverflowIfNeeded(it) }.toMutableList()
    }.toMutableList()
}

fun handleOverflowIfNeeded(currentEnergy: Int) : Int {
    return if(currentEnergy > 9) {
        0
    } else {
        currentEnergy
    }
}

fun List<List<Int>>.hasFlashingOctopuses(allStepFlashedPoints: MutableList<Point>) : Boolean {

    this.forEachIndexed { yIndex, it ->
        it.forEachIndexed { xIndex, number ->
            if(number > 9) {
                if(allStepFlashedPoints.containsCurrentPoint(Point(yIndex, xIndex)).not()) {
                    return true
                }
            }
        }
    }
    return false
}

fun List<Point>.containsCurrentPoint(currentPoint: Point) : Boolean {
    this.forEach {
        if(it.x == currentPoint.x && it.y == currentPoint.y) {
            return true
        }
    }
    return false
}



