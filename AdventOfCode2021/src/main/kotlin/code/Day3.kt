package code

import input.INPUT_DAY_3

fun main() {
    day3FirstPuzzle()
    day3SecondPuzzle()
}

fun day3FirstPuzzle() {
    val lengthInputValues = INPUT_DAY_3[0].length
    val numberInputValues = INPUT_DAY_3.size
    var tmpGammaRate = ""
    var gammaRate = 0
    var epsilonRate = 0
    var result = 0

    repeat(lengthInputValues) { index ->
        val zeroCounter = INPUT_DAY_3.map { it[index] }.filter { it == '0' }.count()
        val oneCounter = numberInputValues - zeroCounter
        tmpGammaRate += if(zeroCounter > oneCounter) "0" else "1"
        println(tmpGammaRate)
    }
    gammaRate = tmpGammaRate.toInt(2)
    epsilonRate = tmpGammaRate.getEpsilonRate().toInt(2)
    result = gammaRate * epsilonRate
    println(result)
}

fun day3SecondPuzzle() {
    val lengthInputValues = INPUT_DAY_3[0].length
    var filteredList = INPUT_DAY_3

    repeat(lengthInputValues) { index ->
        if(filteredList.size > 1) {
            val zeroCounter = filteredList.map { it[index] }.filter { it == '0' }.count()
            val oneCounter = filteredList.size - zeroCounter
            filteredList = if (zeroCounter > oneCounter) {
                filteredList.filter { it[index] == '0' }.toMutableList()
            } else {
                filteredList.filter { it[index] == '1' }.toMutableList()
            }
        }
    }
    val oxygenGeneratorRating = filteredList[0].toInt(2)
    println("oxygenGeneratorRating: $oxygenGeneratorRating")

    filteredList = INPUT_DAY_3

    repeat(lengthInputValues) { index ->
        if(filteredList.size > 1) {
            val zeroCounter = filteredList.map { it[index] }.filter { it == '0' }.count()
            val oneCounter = filteredList.size - zeroCounter
            filteredList = if (zeroCounter <= oneCounter) {
                filteredList.filter { it[index] == '0' }.toMutableList()
            } else {
                filteredList.filter { it[index] == '1' }.toMutableList()
            }
        }
    }
    val cO2ScrubberRating = filteredList[0].toInt(2)
    println("cO2ScrubberRating: $cO2ScrubberRating")

    val result = oxygenGeneratorRating * cO2ScrubberRating
    println(result)
}

fun String.getEpsilonRate() : String {
    var epsilonRate = ""
    repeat(this.length) {
        epsilonRate += if(this[it] == '0') "1" else "0"
    }
    return epsilonRate
}