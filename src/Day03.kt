fun main() {

    val regex = Regex("do\\(\\)|don't\\(\\)|mul\\(\\d+,\\d+\\)")

    fun calculateScoreUsingRegex(input: List<String>, ignoreInstructions: Boolean = true): Int {
        var result = 0
        var instructionsEnabled = true

        input.map {
            regex.findAll(it)
        }.forEach {
            it.forEach {
                if (it.value.contains("don't")) {
                    instructionsEnabled = false
                } else if (it.value.contains("do")) {
                    instructionsEnabled = true
                } else if (instructionsEnabled || ignoreInstructions) {
                    // mul(xxx,xxx)
                    val numbers = it.value.substring(4, it.value.length - 1).split(",")
                    result += numbers[0].toInt() * numbers[1].toInt()
                }
            }
        }

        return result
    }

    fun part1(input: List<String>) = calculateScoreUsingRegex(input)

    fun part2(input: List<String>) = calculateScoreUsingRegex(input, false)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
