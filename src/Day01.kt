import kotlin.math.absoluteValue

fun main() {
    fun parseInput(input: List<String>): Pair<List<Int>, List<Int>> {
        val firstList = mutableListOf<Int>()
        val secondList = mutableListOf<Int>()

        input.map {
            it.trim().split("   ")
        }.forEach {
            firstList.add(it[0].toInt())
            secondList.add(it[1].toInt())
        }

        return firstList to secondList
    }

    fun part1(firstList: List<Int>, secondList: List<Int>): Int {
        var totalDistance = 0

        val sortedFirstList = firstList.sorted()
        val sortedSecondList = secondList.sorted()

        sortedFirstList.forEachIndexed { index, number ->
            totalDistance += (number - sortedSecondList[index]).absoluteValue
        }

        return totalDistance
    }

    fun part2(firstList: List<Int>, secondList: List<Int>): Int {
        var totalDistance = 0

        firstList.forEach { number ->
            totalDistance += number * secondList.filter { it == number }.size
        }

        return totalDistance
    }

    val testInput = readInput("Day01_test")
    val (testFirstList, testSecondList) = parseInput(testInput)
    check(part1(testFirstList, testSecondList) == 11)
    check(part2(testFirstList, testSecondList) == 31)

    val input = readInput("Day01")
    val (firstList, secondList) = parseInput(input)
    part1(firstList, secondList).println()
    part2(firstList, secondList).println()
}
