package model

import org.specs2.mutable._

class KnightSpec extends Specification{

  "A white model.Knight" should {
    val r = new Knight(true)
    "have toString() that is \u2658" in {
      r.toString must be_== ("\u2658")
    }


  }

  "A black model.Knight" should {
    val r = new Knight(false)
    "have toString that is \u265E" in {
      r.toString must be_== ("\u265E")
    }
  }
}