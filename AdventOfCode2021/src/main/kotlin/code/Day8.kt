package code

import input.INPUT_DAY8_PATTERN_AND_OUTPUT

fun main() {
    day8FirstPuzzle()
}

fun day8FirstPuzzle() {
    val mappingTable : Map<Int, Int> = mapOf(1 to 2, 4 to 4, 7 to 3, 8 to 7)
    val uniqueNumbersCount : MutableMap<Int, Int> = mutableMapOf(1 to 0, 4 to 0, 7 to 0, 8 to 0)

    val outputValues = INPUT_DAY8_PATTERN_AND_OUTPUT.map { it.substringAfterLast("|") }
    outputValues.forEach { outputValue ->
        val groupedUniqueNumbers = outputValue.split(" ").filter{ mappingTable.values.contains(it.length) }.groupingBy { it.length }.eachCount()

        uniqueNumbersCount[1] = uniqueNumbersCount[1]!!.plus(groupedUniqueNumbers[mappingTable[1]] ?: 0)
        uniqueNumbersCount[4] = uniqueNumbersCount[4]!!.plus(groupedUniqueNumbers[mappingTable[4]] ?: 0)
        uniqueNumbersCount[7] = uniqueNumbersCount[7]!!.plus(groupedUniqueNumbers[mappingTable[7]] ?: 0)
        uniqueNumbersCount[8] = uniqueNumbersCount[8]!!.plus(groupedUniqueNumbers[mappingTable[8]] ?: 0)
    }

    println(uniqueNumbersCount.values.sum())
}