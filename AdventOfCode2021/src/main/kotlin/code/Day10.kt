package code

import input.INPUT_DAY10_CHUNKS

fun main() {
    day10FirstPuzzle()
    day10SecondPuzzle()
}

val crunkOpenCloseMapping = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
val crunkScoreMapping = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
val crunkCloseScoreMapping = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)

fun day10FirstPuzzle() {
    val openingCrunks = emptyList<Char>().toMutableList()
    val illegalCrunks = emptyList<Char>().toMutableList()

    INPUT_DAY10_CHUNKS.forEach nextString@{
        it.forEach  { char ->
            if (char.isOpeningCrunk) {
                openingCrunks.add(char)
            } else {
                if (openingCrunks.isNotEmpty()) {
                    if (crunksMatchTogether(char, openingCrunks.last())) {
                        openingCrunks.removeLast()
                    } else {
                        illegalCrunks.add(char)
                        return@nextString
                    }
                }
            }
        }
    }

    println(illegalCrunks.calculateSyntaxErrorScore())
}

fun day10SecondPuzzle() {
    val openingCrunks = emptyList<Char>().toMutableList()

    val incompleteLines = INPUT_DAY10_CHUNKS.mapNotNull nextString@{
        openingCrunks.clear()
        it.forEach  { char ->
            if (char.isOpeningCrunk) {
                openingCrunks.add(char)
            } else {
                if (openingCrunks.isNotEmpty()) {
                    if (crunksMatchTogether(char, openingCrunks.last())) {
                        openingCrunks.removeLast()
                    } else {
                        return@nextString null
                    }
                }
            }
        }
        return@nextString it
    }

    val linesScores = emptyList<Long>().toMutableList()

    incompleteLines.forEach { string ->
        openingCrunks.clear()
        string.forEach { char ->
            if (char.isOpeningCrunk) {
                openingCrunks.add(char)
            } else {
                if (openingCrunks.isNotEmpty()) {
                    if (crunksMatchTogether(char, openingCrunks.last())) {
                        openingCrunks.removeLast()
                    }
                }
            }
        }
        val crunksToBeAddedInLine = openingCrunks.mapNotNull { char ->
            crunkOpenCloseMapping[char]
        }
        linesScores.add(crunksToBeAddedInLine.reversed().calculateCompletionScore())
    }

    println(linesScores.sortedByDescending { it }[(linesScores.size-1)/2])
}

val Char.isOpeningCrunk : Boolean
    get() {
        return crunkOpenCloseMapping.keys.contains(this)
    }

fun crunksMatchTogether(currentCrunk: Char, lastOpeningCrunk: Char) : Boolean {
    return crunkOpenCloseMapping[lastOpeningCrunk] == currentCrunk
}

fun MutableList<Char>.calculateSyntaxErrorScore() : Int {
    return this.mapNotNull { crunkScoreMapping[it] }.sum()
}

fun List<Char>.calculateCompletionScore() : Long {
    var totalLineScore = 0L
    this.forEach { char ->
        totalLineScore *= 5
        crunkCloseScoreMapping[char]?.let { totalLineScore += it }
    }
    return  totalLineScore
}