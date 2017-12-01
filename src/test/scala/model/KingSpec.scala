package model

import org.specs2.mutable._

class KingSpec extends Specification{

  "A white model.King" should {
    val r = new King(true)
    "have toString() that is \u2654" in {
      r.toString must be_== ("\u2654")
    }


  }

  "A black model.King" should {
    val r = new King(false)
    "have toString that is \u265A" in {
      r.toString must be_== ("\u265A")
    }
  }
}