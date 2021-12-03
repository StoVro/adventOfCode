package code

import input.INPUT_DAY_1

fun main() {
    day1FirstPuzzle()
    day1SecondPuzzle()
}

fun day1FirstPuzzle() {
    val result = INPUT_DAY_1.zipWithNext().filter { it.first < it.second }.size
    println(result)
}

fun day1SecondPuzzle() {

}