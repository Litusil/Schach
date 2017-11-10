import org.specs2.mutable._

class RookSpec extends Specification{

  "A new Rook" should {
    Rook r = new Rook(true);

    "have toString() that is T" in {
      r.toString() must be_== "T"
    }
  }


}