package code

import input.INPUT_DAY_2

fun main() {
    day2FirstPuzzle()
    day2SecondPuzzle()
}

fun day2FirstPuzzle() {
    val groups = INPUT_DAY_2.groupBy { it.first }
    val sumForwardEntries = groups["forward"]?.sumBy { it.second }
    val sumDownEntries = groups["down"]?.sumBy { it.second }
    val sumUpEntries = groups["up"]?.sumBy { it.second }
    val result = sumForwardEntries!! * (sumDownEntries!!-sumUpEntries!!)
    println(result)
}

fun day2SecondPuzzle() {
    var horizontal = 0
    var depth = 0
    var aim = 0

    INPUT_DAY_2.forEach {
        when (it.first) {
            "forward" -> {
                horizontal += it.second
                depth += aim * it.second
            }
            "down" -> aim += it.second
            "up" -> aim -= it.second
            //else -> println("false")
        }
    }
    val result = horizontal * depth
    println(result)
}

