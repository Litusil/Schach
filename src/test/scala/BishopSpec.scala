import org.specs2.mutable._


class BishopSpec extends Specification{

  "A white Bishop" should {
    val r = new Bishop(true)
    "have toString() that is \u2657" in {
      r.toString must be_== ("\u2657")
    }


  }

  "A black Bishop" should {
    val r = new Bishop(false)
    "have toString that is \u265D" in {
      r.toString must be_== ("\u265D")
    }
  }
}