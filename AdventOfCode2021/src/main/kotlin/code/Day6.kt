package code

import input.INPUT_DAY6_START_NUMBERS

fun main() {
    day6FirstPuzzleAgain()
    day6SecondPuzzleAgain()
    //day6FirstPuzzle()
}

fun day6FirstPuzzleAgain() {
    val days = 80
    var mappedNumbers : MutableMap<Int, Long> = mutableMapOf()
    var newNumbers : MutableMap<Int, Long>

    INPUT_DAY6_START_NUMBERS.groupingBy { it }.eachCount().forEach {
        mappedNumbers[it.key] = it.value.toLong()
    }

    repeat(days) {
        newNumbers = mutableMapOf()
        mappedNumbers.forEach { entry ->
            if (entry.key == 0) {
                newNumbers[6] = newNumbers[6]?.let { it + entry.value } ?: entry.value
                newNumbers[8] = entry.value
            } else {
                newNumbers[entry.key - 1] = newNumbers[entry.key - 1]?.let { it + entry.value } ?: entry.value
            }
        }
        mappedNumbers = newNumbers
    }

    println(mappedNumbers)
    println(mappedNumbers.values.sum())
}

fun day6SecondPuzzleAgain() {
    val days = 256
    var mappedNumbers : MutableMap<Int, Long> = mutableMapOf()
    var newNumbers : MutableMap<Int, Long>

    INPUT_DAY6_START_NUMBERS.groupingBy { it }.eachCount().forEach {
        mappedNumbers[it.key] = it.value.toLong()
    }

    repeat(days) {
        newNumbers = mutableMapOf()
        mappedNumbers.forEach { entry ->
            if (entry.key == 0) {
                newNumbers[6] = newNumbers[6]?.let { it + entry.value } ?: entry.value
                newNumbers[8] = entry.value
            } else {
                newNumbers[entry.key - 1] = newNumbers[entry.key - 1]?.let { it + entry.value } ?: entry.value
            }
        }
        mappedNumbers = newNumbers
    }

    println(mappedNumbers)
    println(mappedNumbers.values.sum())
}

fun day6FirstPuzzle() {
    var currentNumbers = INPUT_DAY6_START_NUMBERS.toMutableList()
    val days = 80
    var newNumbers = mutableListOf<Int>()
    repeat(days) {
        newNumbers = currentNumbers.map { setNewNumber(it) }.toMutableList()
        val zeroCounter = currentNumbers.filter { it == 0 }.size
        currentNumbers = newNumbers
        currentNumbers.addAll(IntArray(zeroCounter) { 8 }.toMutableList())
    }
    println(newNumbers.size)
}

fun setNewNumber(currentNumber: Int) : Int {
     return if(currentNumber - 1 == -1) 6 else  currentNumber - 1
}