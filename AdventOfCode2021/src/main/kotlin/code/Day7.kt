package code

import input.INPUT_DAY7_HORIZONTAL_VALUES
import kotlin.math.abs

fun main() {
    day7FirstPuzzle()
    day7SecondPuzzle()
}

fun day7FirstPuzzle() {
    val minValue = INPUT_DAY7_HORIZONTAL_VALUES.minByOrNull { it }
    val maxValue = INPUT_DAY7_HORIZONTAL_VALUES.maxByOrNull { it }

    val range = minValue!!..maxValue!!

    val groupedNumbers = INPUT_DAY7_HORIZONTAL_VALUES.groupingBy { it }.eachCount()
    var minPosFuel : Pair<Int, Int> = Pair(0, 0)

    range.forEachIndexed { index, currentRange ->
        val fuel = groupedNumbers.map { abs(it.key - currentRange) * it.value }.sum()
        if(index == 0) {
            minPosFuel = Pair(currentRange, fuel)
        }
        if( fuel < minPosFuel.second ) {
            minPosFuel = Pair(currentRange, fuel)
        }
    }

    println(minPosFuel)
}

fun day7SecondPuzzle() {
    val minValue = INPUT_DAY7_HORIZONTAL_VALUES.minByOrNull { it }
    val maxValue = INPUT_DAY7_HORIZONTAL_VALUES.maxByOrNull { it }

    val range = minValue!!..maxValue!!

    val groupedNumbers = INPUT_DAY7_HORIZONTAL_VALUES.groupingBy { it }.eachCount()
    var minPosFuel : Pair<Int, Int> = Pair(0, 0)

    range.forEachIndexed { index, currentRange ->
        val fuel = groupedNumbers.map { getFuelForOnePosition(it.key, currentRange) * it.value }.sum()
        if(index == 0) {
            minPosFuel = Pair(currentRange, fuel)
        }
        if( fuel < minPosFuel.second ) {
            minPosFuel = Pair(currentRange, fuel)
        }
    }

    println(minPosFuel)
}

fun getFuelForOnePosition(key: Int, currentRange: Int) : Int {
    return (1..abs(key - currentRange)).sum()
}