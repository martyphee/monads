package fplibrary

// Constraints Liberate, Liberties Constraint Runar B
object PointFree {
  // val ac = compose(ab, bc)
  def compose[A, B, C](ab: A => B, bc: B => C): A => C = a => {
    // produce a C over here
    val b = ab(a)
    val c = bc(b)
    c
  }

  def composeKleisli[A, B, C](adb: A => Description[B], bdc: B => Description[C]): A => Description[C] = a => {
    // we need to product a Description[C]
    val db = adb(a)
    val b = db.apply()

    val dc = bdc(b)

    dc
  }

  def composeKleisli2[A, B, C, D[_]](adb: A => D[B], bdc: B => D[C]): A => D[C] = a => {
    // we need to product a Description[C]
    val db = adb(a)
//    val b = db.apply()
//
//    val dc = bdc(b)
//
//    dc

    val dc = helper(db, bdc)
    dc
  }

  def helper[A, B, C[_]](ca: C[A], acd: A => C[B]): C[B] = ???

  trait Monad[C[_]] {
    def flatMap[A, B](ca: C[A])(acd: A => C[B]): C[B]
  }
}
