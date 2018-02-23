package komponent.core

internal val camelToDashRegex = Regex("([a-zA-Z])(?=[A-Z])")
private val dashToCamelRegex = Regex("-([a-z])?")

fun String.fromCamelToDashCase(): String = replace(camelToDashRegex, "$1-").toLowerCase()

fun String.fromDashToCamelCase(): String = replace(dashToCamelRegex) { it.groups[1]?.value?.toUpperCase() ?: "" }