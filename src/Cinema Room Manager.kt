package cinema

var CurrentIncome = 0

fun drawSeats(b: Int, lst: MutableList<MutableList<Char>>) {
    print("Cinema:\n ")
    for (i in 1..b) {
        print(" $i")
    }
    for (i in lst.indices) {
        print("\n${i + 1}")
        for (j in lst[i]) {
            print(" $j")
        }
    }
    println()
}

fun isFrontRows(a: Int, b: Int, row: Int, seat: Int): Boolean {
    return (row - 1) * b + seat <= a * b - (a * b - a / 2 * b)
}

fun buyTicket(a: Int, b: Int, lst: MutableList<MutableList<Char>>) {
    while (true) {
        println("\nEnter a row number:")
        val row = readLine()!!.toInt()
        println("Enter a seat number in that row:")
        val seat = readLine()!!.toInt()

        if (row !in 1..a || seat !in 1..b) {
            println("Wrong input!")
            continue
        }
        if (lst[row - 1][seat - 1] == 'B'){
            println("That ticket has already been purchased!")
            continue
        }
        CurrentIncome += if (a * b > 60 && !isFrontRows(a, b, row, seat)) {
            println("Ticket price: $8")
            8
        } else {
            println("Ticket price: $10")
            10
        }
        lst[row - 1][seat - 1] = 'B'
        break
    }
}

fun statistic(a: Float, b: Float, numOfTickets: Int, totalIncome: Int) {
    println("Number of purchased tickets: $numOfTickets")
    println("Percentage: ${String.format("%.2f", numOfTickets / (a * b) * 100)}%")
    println("Current income: $$CurrentIncome")
    println("Total income: $$totalIncome")
}

fun countTotalIncome(a: Int, b: Int): Int {
    return if (a * b > 60) {
        a / 2 * b * 10 + (a - a / 2) * b * 8
    } else a * b * 10
}

fun main() {
    println("Enter the number of rows:")
    val a = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val b = readLine()!!.toInt()
    val lst = MutableList(a) { MutableList(b) { 'S' } }
    var numOfTickets = 0
    val totalIncome = countTotalIncome(a, b)

    while (true) {
        println(
            """
1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
            """
        )
        when (readLine()!!.toInt()) {
            1 -> drawSeats(b, lst)
            2 -> {buyTicket(a, b, lst); numOfTickets++;}
            3 -> statistic(a.toFloat(), b.toFloat(), numOfTickets, totalIncome)
            0 -> break
        }
    }
}