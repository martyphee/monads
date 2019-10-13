package fplibrary

trait Monad[C[_]] {
  def flatMap[A, B](ca: C[A])(acb: A => C[B]): C[B]
  def bind[A, B](ca: C[A])(acb: A => C[B]): C[B] = flatMap(ca)(acb)
  def >==[A, B](ca: C[A])(acb: A => C[B]): C[B] = flatMap(ca)(acb)
}

object Monad {
  def apply[C[_]: Monad]: Monad[C] = implicitly[Monad[C]]
}
