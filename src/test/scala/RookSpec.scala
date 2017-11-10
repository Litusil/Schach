import org.specs2.mutable._

class RookSpec extends Specification{

  "A white Rook" should {
    val r = new Rook(true)
    "have toString() that is \u2656" in {
      r.toString must be_== ("\u2656")
    }


  }

  "A black Rook" should {
    val r = new Rook(false)
    "have toString that is \u265C" in {
      r.toString must be_== ("\u265C")
    }
  }



}