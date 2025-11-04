package ru.netology.nmedia.activity

fun quantityWritingRule(number: Int): String {
    var num: Int = number
    var numHundred: Int
    var numThousand: Int
    var numMill: Int
    var numHundOfThous: Int
    if (num < 1000) {
        return num.toString()
    } else if (num < 10000) {
        numThousand = num / 1000
        numHundred = (num % 1000) / 100
        if (numHundred == 0) {
            return numThousand.toString() + "K"
        } else return numThousand.toString() + "." + numHundred + "K"
    } else if (num < 1000000) {
        numThousand = num / 1000
        return numThousand.toString() + "K"
    } else {
        numMill = num / 1000000
        numHundOfThous = (num % 1000000) / 100000
        if (numHundOfThous == 0) {
            return numMill.toString() + "M"
        } else return numMill.toString() + "." + numHundOfThous + "M"
    }
}