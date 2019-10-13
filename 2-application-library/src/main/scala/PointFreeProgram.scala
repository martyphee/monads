import fplibrary._

object PointFreeProgram {
  //  lazy val createDescription: Array[String] => Description[Unit] = args =>
  //    Description.brokenCreate(
  //      display(
  //        question(
  //          display(
  //            hyphens(
  //              display(
  //                createMessage(
  //                  round(
  //                    ensureAmountIsPositive(
  //                      convertStringToInt(
  //                        prompt(
  //                          display(
  //                            hyphens(
  //                              args
  //                            )
  //                          )
  //                        )
  //                      )
  //                    )
  //                  )
  //                )
  //              )
  //            )
  //          )
  //        )
  //      )
  //    )

  lazy val createDescription: Array[String] => Description[Unit] =
    ignoreArgs          --> hyphens --> displayKleisli  >==>
    question            --> displayKleisli              >==>
    promptKleisli                                       >==>
    convertStringToInt  --> ensureAmountIsPositive -->
      round --> createMessage --> displayKleisli        >==>
    hyphens             --> displayKleisli              >==>
    Description.brokenCreate

  private lazy val ignoreArgs: Array[String] => Unit = _ =>
    ()

  private lazy val hyphens: Any => String = _ =>
    "-" * 50

  private lazy val question: Any => String = _ =>
    "How much money would you like to deposit"

  private lazy val displayKleisli: Any => Description[Unit] = input => Description.create {
    println(input)
  }

  private lazy val display: Any => Unit = input =>
    println(input)

  // side effecct (reading from the console
  private lazy val prompt: Any => String = _ => "5"

  private lazy val promptKleisli: Any => Description[String] = _ => Description.create("5")

  // potential side effect (throwing of a NumberFormatException
  private lazy val convertStringToInt: String => Int = input =>
    input.toInt

  private lazy val ensureAmountIsPositive: Int => Int = amount =>
    if (amount < 1)
      1
    else
      amount

  private lazy val round: Int => Int = amount =>
    if (isDivisibleByHundred(amount))
      amount
    else
      round(amount + 1)

  private lazy val isDivisibleByHundred: Int => Boolean = amount =>
    amount % 100 == 0

  private lazy val createMessage: Int => String = balance =>
    s"Congratulations, you now have USD $balance"

}
