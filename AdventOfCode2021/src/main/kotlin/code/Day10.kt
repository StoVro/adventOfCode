package code

import input.INPUT_DAY10_CHUNKS

fun main() {
    day10FirstPuzzle()
}

val mappingTable = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
val crunkScoreMapping = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)

fun day10FirstPuzzle() {
    val input = arrayOf("[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]")

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

val Char.isOpeningCrunk : Boolean
    get() {
        return mappingTable.keys.contains(this)
    }

fun crunksMatchTogether(currentCrunk: Char, lastOpeningCrunk: Char) : Boolean {
    return mappingTable.get(lastOpeningCrunk) == currentCrunk
}

fun MutableList<Char>.calculateSyntaxErrorScore() : Int {
    return this.mapNotNull { crunkScoreMapping[it] }.sum()
}