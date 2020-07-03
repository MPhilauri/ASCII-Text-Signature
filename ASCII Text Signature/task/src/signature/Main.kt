package signature

import java.io.File
import java.lang.Integer.max
import java.util.*
import kotlin.collections.HashMap


fun main() {
    val romansFilePath = "C:\\Users\\Mariam\\Desktop\\roman.txt"
    val romanFont = getFileData(romansFilePath)

    val mediumFilePath = "C:\\Users\\Mariam\\Desktop\\medium.txt"
    val mediumFont = getFileData(mediumFilePath)

    val scanner = Scanner(System.`in`)
    val name = scanner.nextLine()
    val s = scanner.nextLine()

    printSignature(name, s, romanFont, mediumFont)
}

fun getFileData(fileName: String): HashMap<Char, Array<String>> {
    val scanner = Scanner(File(fileName))
    var lines = scanner.nextInt()
    var charsNumber = scanner.nextInt()
    val result = HashMap<Char, Array<String>>()
    repeat(charsNumber) {
        val a = scanner.next().first()
        scanner.nextInt()
        scanner.skip("\n")
        val arr = Array(lines) { "" }
        for (j in 0 until lines) {
            arr[j] = scanner.nextLine()
        }
        result[a] = arr
    }
    return result
}

fun savePrintedData(name: String, romanFont: HashMap<Char, Array<String>>, rowSize: Int,
                    whitespace: Int)
        : Array<String> {
    val signature: Array<String> = Array(rowSize) { "" }
    for (c in name) {
        var index = 0
        if (c == ' ') {
            for (i in signature.indices) {
                signature[i] += " ".repeat(whitespace)
            }
        } else {
            for (s in romanFont.get(c)!!) {
                signature[index++] += s
            }
        }
    }
    return signature
}

fun drawHorizontalBorder(length: Int) {
    println("8".repeat(length))
}

fun printSignature(name: String, s: String, romanFont: HashMap<Char, Array<String>>,
                   mediumFont: HashMap<Char, Array<String>>) {
    val signature: Array<String> = savePrintedData(name, romanFont, 10, 10)
    val status: Array<String> = savePrintedData(s, mediumFont, 3, 5)
    val signatureLength = signature[0].length
    val statusLength = status[0].length
    val borderTotalWidth = 4
    val defaultPadding = 4

    val innerLength = max(signatureLength, statusLength)
    val lengthWithoutBorders = innerLength + defaultPadding

    val nameLeftPadding = (lengthWithoutBorders - signatureLength) / 2
    val nameRightPadding = lengthWithoutBorders - nameLeftPadding - signatureLength

    val statusLeftPadding = (lengthWithoutBorders - statusLength) / 2
    val statusRightPadding = lengthWithoutBorders - statusLeftPadding - statusLength

    drawHorizontalBorder(lengthWithoutBorders + borderTotalWidth)
    for (s in signature) {
        printContentLine(nameLeftPadding, nameRightPadding, s)
    }

    for (s in status) {
        printContentLine(statusLeftPadding, statusRightPadding, s)
    }
    drawHorizontalBorder(lengthWithoutBorders + borderTotalWidth)
}

fun printContentLine(leftPadding: Int, rightPadding: Int, line: String) {
    print("88")
    print(" ".repeat(leftPadding))
    print(line)
    print(" ".repeat(rightPadding))
    print("88")
    println()
}