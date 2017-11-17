import model.Pawn
import org.specs2.mutable._

class PawnSpec extends Specification{

  "A white model.Pawn" should {
    val r = new Pawn(true)
    "have toString() that is \u2659" in {
      r.toString must be_== ("\u2659")
    }


  }

  "A black model.Pawn" should {
    val r = new Pawn(false)
    "have toString that is \u265F" in {
      r.toString must be_== ("\u265F")
    }
  }



}

