import model.Queen
import org.specs2.mutable._

class QueenSpec extends Specification{

  "A white model.Queen" should {
    val r = new Queen(true)
    "have toString() that is \u2655" in {
      r.toString must be_== ("\u2655")
    }


  }

  "A black model.Queen" should {
    val r = new Queen(false)
    "have toString that is \u265C" in {
      r.toString must be_== ("\u265B")
    }
  }



}

