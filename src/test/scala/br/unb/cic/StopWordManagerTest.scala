package br.unb.cic

import org.scalatest.funsuite.AnyFunSuite

class StopWordManagerTest extends AnyFunSuite {

  test("init data storage manager") {
    val dsm = new StopWordManager()
    val message = dsm.dispatch(InitMessage("target/scala-2.13/test-classes/stop-words.txt"))
    assert(message == EmptyMessage)
  }

}
