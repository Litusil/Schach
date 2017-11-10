import org.specs2.mutable._

class RookSpec extends Specification{

  "A new Rook" should {
    val r = new Rook(true)

    "have toString() that is T" in {
      r.toString must be_== ("\u2656")
    }
  }
}