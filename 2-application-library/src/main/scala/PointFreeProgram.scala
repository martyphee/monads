import fplibrary._

object PointFreeProgram {
  def createDescription(args: Array[String]): Description[Unit] = Description.create {
    display(
      hyphens(())
    )

    display(
      question(())
    )

    display(
      createMesssage(
        round(
          ensureAmountIsPositive(
            convertStringToInt(
              prompt(())
            )
          )
        )
      )
    )

    display(
      hyphens(())
    )
  }

  private def hyphens(input: Any): String =
    "\u2500" * 50

  private def question(input: Any): String =
    "How much money would you like to deposit"

  // side effect (writing to console)
  private def display(input: String): Unit = {
    println(input)
  }

  // side effecct (reading from the console
  private def prompt(input: Any): String = "5"
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
