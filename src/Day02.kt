import kotlin.math.absoluteValue

fun main() {
    fun parseReport(line: String): List<Int> {
        return line.split(" ").map(String::toInt)
    }

    fun isReportValidAndConsistent(report: List<Int>): Boolean {
        val decreasingOrIncreasing = report[1] > report[0]
        return report.zipWithNext().all { (prev, current) ->
            (current - prev).absoluteValue in 1..3 && (decreasingOrIncreasing == (current > prev))
        }
    }

    fun canMaintainConsistencyOnRemoval(report: List<Int>): Boolean {
        return report.indices.any { index ->
            val modifiedReport = report.toMutableList().apply { removeAt(index) }
            isReportValidAndConsistent(modifiedReport)
        }
    }

    fun part1(input: List<String>): Int {
        return input
            .map { parseReport(it) }
            .filter { report ->
                isReportValidAndConsistent(report)
            }.size
    }

    fun part2(input: List<String>): Int {
        return input
            .map { parseReport(it) }
            .count { report ->
                isReportValidAndConsistent(report) || canMaintainConsistencyOnRemoval(report)
            }
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
