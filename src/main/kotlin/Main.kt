fun main() {
    val board = Array(3) { Array(3) { ' ' } }
    var currentPlayer = 'X'

    println("Welcome to Tic-Tac-Toe!")

    while (true) {
        printBoard(board)

        val (row, col) = getPlayerMove(currentPlayer, board)
        board[row][col] = currentPlayer

        if (checkWin(board, currentPlayer)) {
            printBoard(board)
            println("$currentPlayer wins!")
            break
        }

        if (isBoardFull(board)) {
            printBoard(board)
            println("It's a draw!")
            break
        }

        currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
    }
}

fun printBoard(board: Array<Array<Char>>) {
    println("-------------")
    for (i in board.indices) {
        print("| ")
        for (j in board[i].indices) {
            val cell = board[i][j]
            val displayCell = if (cell == ' ') (i * 3 + j + 1).toString() else cell.toString()
            print("$displayCell | ")
        }
        println("\n-------------")
    }
}

fun getPlayerMove(player: Char, board: Array<Array<Char>>): Pair<Int, Int> {
    while (true) {
        println("$player's turn. Enter cell number (1-9):")
        val cellNumber = readLine()?.toIntOrNull()?.dec() ?: -1

        val row = cellNumber / 3
        val col = cellNumber % 3

        if (cellNumber !in 0..8 || board[row][col] != ' ') {
            println("Invalid input. Try again.")
            continue
        }

        return row to col
    }
}

fun checkWin(board: Array<Array<Char>>, player: Char): Boolean {
    for (i in 0 until 3) {
        if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true
        if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true
    }
    if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true
    if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true
    return false
}

fun isBoardFull(board: Array<Array<Char>>): Boolean {
    for (row in board) {
        for (cell in row) {
            if (cell == ' ') return false
        }
    }
    return true
}
