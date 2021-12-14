package code

fun main() {
    day14FirstPuzzle()
    // day14SecondPuzzle() => bad performance ;-) does not work!
}

val INPUT_DAY14_MAPPING_TABLE : Map<String, Char>
    get() = mapOf("FV" to 'H',"SB" to 'P',"NV" to 'S',"BS" to 'K',"KB" to 'V',"HB" to 'H',"NB" to 'N',"VB" to 'P',"CN" to 'C',"CF" to 'N',"OF" to 'P',"FO" to 'K',"OC" to 'F',"BN" to 'V',"PO" to 'O',"OS" to 'B',"KH" to 'N',"BB" to 'C',"PV" to 'K',"ON" to 'K',"NF" to 'H',"BV" to 'K',"SN" to 'N',"PB" to 'S',"PK" to 'F',"PF" to 'S',"BP" to 'K',"SP" to 'K',"NN" to 'K',"FP" to 'N',"NK" to 'N',"SF" to 'P',"HS" to 'C',"OH" to 'C',"FS" to 'H',"VH" to 'N',"CO" to 'P',"VP" to 'H',"FF" to 'N',"KP" to 'B',"BH" to 'B',"PP" to 'F',"SS" to 'P',"CV" to 'S',"HO" to 'P',"PN" to 'K',"SO" to 'O',"NO" to 'O',"NH" to 'V',"HH" to 'F',"KK" to 'C',"VO" to 'B',"KS" to 'B',"SV" to 'O',"OP" to 'S',"VK" to 'H',"KF" to 'O',"CP" to 'H',"SH" to 'H',"NC" to 'S',"KC" to 'O',"CK" to 'H',"CH" to 'B',"KO" to 'O',"OV" to 'P',"VF" to 'V',"HN" to 'P',"FH" to 'P',"BC" to 'V',"HV" to 'N',"BO" to 'V',"PH" to 'P',"NP" to 'F',"FN" to 'F',"FK" to 'P',"SC" to 'C',"KN" to 'S',"NS" to 'S',"OK" to 'S',"HK" to 'O',"PC" to 'O',"BK" to 'O',"OO" to 'P',"BF" to 'N',"SK" to 'V',"VS" to 'B',"HP" to 'H',"VC" to 'V',"KV" to 'P',"FC" to 'H',"HC" to 'O',"HF" to 'S',"CB" to 'H',"CC" to 'B',"PS" to 'C',"OB" to 'B',"CS" to 'S',"VV" to 'S',"VN" to 'H',"FB" to 'N')

val charCount = mutableMapOf<Char, MutableList<Int>>()

val input = mapOf("CH" to 'B',
        "HH" to 'N',
        "CB" to 'H',
        "NH" to 'C',
        "HB" to 'C',
        "HC" to 'B',
        "HN" to 'C',
        "NN" to 'C',
        "BH" to 'H',
        "NC" to 'B',
        "NB" to 'B',
        "BN" to 'B',
        "BB" to 'N',
        "BC" to 'B',
        "CC" to 'N',
        "CN" to 'C')

fun day14FirstPuzzle() {
    val startString = "KHSNHFKVVSVPSCVHBHNP"
    addStartStringToMap(startString)
    val numberOfRepetitions = 10
    val firstIndex = 0
    val secondIndex = 1
    repeat(numberOfRepetitions) {
        insertCharsForOneStep(firstIndex,secondIndex)
        println("Runde: $it")
    }

    val result = calculateDifferenceOfMaxMinCountInt()
    println("result: $result")
}

fun day14SecondPuzzle() {
    val startString = "KHSNHFKVVSVPSCVHBHNP"
    addStartStringToMap(startString)
    val numberOfRepetitions = 40
    val firstIndex = 0
    val secondIndex = 1
    repeat(numberOfRepetitions) {
        insertCharsForOneStep(firstIndex,secondIndex)
        println("Runde: $it")
    }

    val result = calculateDifferenceOfMaxMinCountLong()
    println("result: $result")
}

fun insertCharsForOneStep(startFirstIndex: Int, startSecondIndex: Int) {
    var firstIndex = startFirstIndex
    var secondIndex = startSecondIndex

    while(secondIndex <= getMaximumIndex()) {
        insertChar(firstIndex, secondIndex)
        firstIndex += 2
        secondIndex += 2
    }
}

fun insertChar(firstIndex: Int, secondIndex: Int)  {
    val stringToBeConsidered = "${getCharAtIndex(firstIndex)}${getCharAtIndex(secondIndex)}"
    val charToBeInserted = getValueToBeInserted(stringToBeConsidered)
    provideNewNumbersAfterIndex(firstIndex)
    addIndexForChar(secondIndex, charToBeInserted)
}

fun getValueToBeInserted(key: String) : Char {
    return INPUT_DAY14_MAPPING_TABLE[key] ?: ' '
}

fun calculateDifferenceOfMaxMinCountInt() : Int {
    val count = charCount.values.groupingBy { it.size }.eachCount()
    val maximum = count.maxByOrNull { it.key }!!.key
    val minimum = count.minByOrNull { it.key }!!.key
    return maximum - minimum
}

fun calculateDifferenceOfMaxMinCountLong() : Long {
    val maximum = charCount.maxByOrNull { it.value.size }!!.value.size.toLong()
    val minimum = charCount.minByOrNull { it.value.size }!!.value.size.toLong()
    return maximum - minimum
}

fun getCharAtIndex(index: Int) : Char {
    return charCount.filter { it.value.contains(index) }.keys.first()
}

fun addIndexForChar(index: Int, char: Char) {
    if(charCount.contains(char)) {
        charCount[char]!!.add(index)
    } else {
        charCount[char] = mutableListOf(index)
    }
}

fun addStartStringToMap(startString: String) {
    startString.forEachIndexed { index, char ->
        addIndexForChar(index, char)
    }
}

fun getMaximumIndex() : Int {
    return charCount.values.flatten().maxByOrNull { it }!!
}

fun provideNewNumbersAfterIndex(firstIndex: Int) {
    charCount.forEach {
            if(it.value.any { value -> value > firstIndex }) {
                val newList = it.value.map { value ->
                    if(value > firstIndex) value.plus(1) else value
                }
                charCount[it.key] = newList.toMutableList()
            }
    }
}

