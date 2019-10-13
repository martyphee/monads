import fplibrary._

object Program {
  def createDescription(args: Array[String]): Description[Unit] = () => {
    display(hyphens)

    display(question)

    val input: String = prompt()
    val integerAmount: Int = convertStringToInt(input)
    val positiveAmount: Int = ensureAmountIsPositive(integerAmount)
    val balance: Int = round(positiveAmount)
    val message: String = createMesssage(balance)

    display(message)

    display(hyphens)
  }

  private val hyphens: String =
    "\u2500" * 50

  private val question: String =
    "How much money would you like to deposit"

  // side effect (writing to console)
  private def display(input: String): Unit = {
    println(input)
  }

  // side effecct (reading from the console
  private def prompt(): String = "5"
//    scala.io.StdIn.readLine

  // potential side effect (throwing of a NumberFormatException
  private def convertStringToInt(input: String): Int =
    input.toInt

  private def ensureAmountIsPositive(amount: Int): Int =
    if(amount < 1)
      1
    else
      amount

  @scala.annotation.tailrec
  private def round(amount: Int): Int =
    if(isDivisibleByHundred(amount))
      amount
    else
      round(amount + 1)

  private def isDivisibleByHundred(amount: Int): Boolean =
    amount % 100 == 0

  private def createMesssage(balance: Int): String =
    s"Congratulations, you now have USD $balance"
}
