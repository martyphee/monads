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
}
