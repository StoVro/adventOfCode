package code

import input.INPUT_DAY_4_BOARDS
import input.INPUT_DAY_4_RANDOM_NUMBERS


fun main() {
    day4FirstPuzzle()
    day4SecondPuzzle()
}

fun day4FirstPuzzle() {
    val numbersToBeConsidered = INPUT_DAY_4_RANDOM_NUMBERS.take(5).toMutableList()
    val sizeRandomNumbers = INPUT_DAY_4_RANDOM_NUMBERS.size
    var nextIndex = 5
    val columnRowLength = 5
    var foundNumber = -1
    var indexWinnerBoard = -1

    run stop@{
        repeat(sizeRandomNumbers - 4) {
            INPUT_DAY_4_BOARDS.forEachIndexed { index, array ->
                val column = mutableListOf<Int>()
                val row = mutableListOf<Int>()

                repeat(columnRowLength) { outerIndex ->
                    for (columnInnerIndex in 0..4) {
                        column.add(array[outerIndex][columnInnerIndex])
                    }
                    for (rowInnerIndex in 0..4) {
                        row.add(array[rowInnerIndex][outerIndex])
                    }
                    if (column.intersect(numbersToBeConsidered).size == 5) {
                        foundNumber = numbersToBeConsidered.last()
                        indexWinnerBoard = index
                        return@stop
                    }
                    if (row.intersect(numbersToBeConsidered).size == 5) {
                        foundNumber = numbersToBeConsidered.last()
                        indexWinnerBoard = index
                        return@stop
                    }
                    column.clear()
                    row.clear()
                }
            }
            numbersToBeConsidered.add(INPUT_DAY_4_RANDOM_NUMBERS[nextIndex])
            if (nextIndex < sizeRandomNumbers) {
                nextIndex += 1
            }
        }
    }

    println(foundNumber)

    val winnerBoardNumbers = mutableListOf<Int>()
    winnerBoardNumbers.addAll(INPUT_DAY_4_BOARDS[indexWinnerBoard].flatten())

    val index = INPUT_DAY_4_RANDOM_NUMBERS.indexOfFirst { it == foundNumber }
    val markedNumbers = INPUT_DAY_4_RANDOM_NUMBERS.take(index + 1)
    winnerBoardNumbers.removeAll(markedNumbers)
    val sumUnmarkedNumbers = winnerBoardNumbers.sum()

    val result = foundNumber *  sumUnmarkedNumbers
    println(result)
}

fun day4SecondPuzzle() {
    val numbersToBeConsidered = INPUT_DAY_4_RANDOM_NUMBERS.take(5).toMutableList()
    val sizeRandomNumbers = INPUT_DAY_4_RANDOM_NUMBERS.size
    var nextIndex = 5
    val columnRowLength = 5
    var foundNumber = -1
    var indexLastBoard = -1
    val alreadyWonBoards = mutableListOf<Int>()
    val numberAllBoards = INPUT_DAY_4_BOARDS.size

    run stop@{
        repeat(sizeRandomNumbers - 4) {
            if (numberAllBoards != alreadyWonBoards.size) {
                INPUT_DAY_4_BOARDS.forEachIndexed { index, array ->
                    val column = mutableListOf<Int>()
                    val row = mutableListOf<Int>()

                    repeat(columnRowLength) { outerIndex ->
                        for (columnInnerIndex in 0..4) {
                            column.add(array[outerIndex][columnInnerIndex])
                        }
                        for (rowInnerIndex in 0..4) {
                            row.add(array[rowInnerIndex][outerIndex])
                        }
                        if (column.intersect(numbersToBeConsidered).size == 5) {
                            if (alreadyWonBoards.contains(index).not()) {
                                alreadyWonBoards.add(index)
                            }
                        }
                        if (row.intersect(numbersToBeConsidered).size == 5) {
                            if (alreadyWonBoards.contains(index).not()) {
                                alreadyWonBoards.add(index)
                            }
                        }
                        column.clear()
                        row.clear()
                    }
                }
                numbersToBeConsidered.add(INPUT_DAY_4_RANDOM_NUMBERS[nextIndex])
                if (nextIndex < sizeRandomNumbers) {
                    nextIndex += 1
                }
            } else {
                println("$numberAllBoards und ${alreadyWonBoards.size}")
                numbersToBeConsidered.removeLast()
                foundNumber = numbersToBeConsidered.last()
                indexLastBoard = alreadyWonBoards.last()
                return@stop
            }
        }
    }

    println("foundNumber: $foundNumber")

    val lastBoardNumbers = mutableListOf<Int>()
    lastBoardNumbers.addAll(INPUT_DAY_4_BOARDS[indexLastBoard].flatten())

    val index = INPUT_DAY_4_RANDOM_NUMBERS.indexOfFirst { it == foundNumber }
    val markedNumbers = INPUT_DAY_4_RANDOM_NUMBERS.take(index + 1)
    lastBoardNumbers.removeAll(markedNumbers)
    val sumUnmarkedNumbers = lastBoardNumbers.sum()

    val result = foundNumber *  sumUnmarkedNumbers
    println(result)
}




