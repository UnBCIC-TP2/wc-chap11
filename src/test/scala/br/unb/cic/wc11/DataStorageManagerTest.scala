package br.unb.cic.wc11

import org.scalatest.funsuite.AnyFunSuite

class DataStorageManagerTest extends AnyFunSuite  {

  test("init data storage manager") {
    val dsm = new DataStorageManager()
    val message = dsm.dispatch(InitMessage("target/scala-2.13/test-classes/frankenstein.txt"))
    assert(message == EmptyMessage)
  }

  test("check total number of lines") {
    val dsm = new DataStorageManager()
    val m1 = dsm.dispatch(InitMessage(("target/scala-2.13/test-classes/frankenstein.txt")))
    assert(m1 == EmptyMessage)

    val m2 = dsm.dispatch(CountLinesMessage)
    assert(m2 == TotalLinesMessage(7743))
  }

}
