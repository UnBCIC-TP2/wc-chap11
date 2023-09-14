package br.unb.cic.wc11

import org.scalatest.funsuite.AnyFunSuite

class WordFrequencyManagerTest extends AnyFunSuite {

  test("Test for mapping words") {
    val wfm = new WordFrequencyManager()

    assert(EmptyMessage == wfm.dispatch(MapWordMessage("scala")))
    assert(EmptyMessage == wfm.dispatch(MapWordMessage("haskell")))
    assert(EmptyMessage == wfm.dispatch(MapWordMessage("clojure")))
    assert(EmptyMessage == wfm.dispatch(MapWordMessage("java")))
    assert(EmptyMessage == wfm.dispatch(MapWordMessage("haskell")))
    assert(EmptyMessage == wfm.dispatch(MapWordMessage("haskell")))
    assert(EmptyMessage == wfm.dispatch(MapWordMessage("scala")))
    assert(EmptyMessage == wfm.dispatch(MapWordMessage("java")))
    
  }

  test("Test for sorting words") {
    val wfm = new WordFrequencyManager()

    wfm.dispatch(MapWordMessage("scala"))
    wfm.dispatch(MapWordMessage("haskell"))
    wfm.dispatch(MapWordMessage("clojure"))
    wfm.dispatch(MapWordMessage("java"))
    wfm.dispatch(MapWordMessage("haskell"))
    wfm.dispatch(MapWordMessage("haskell"))
    wfm.dispatch(MapWordMessage("scala"))
    wfm.dispatch(MapWordMessage("java"))

    val res = wfm.dispatch(SortWordsMessage)
    val expected = List(("haskell", 3),("java", 2), ("scala",2), ("clojure",1))

    assert(WordCountMessage(expected) == res)
  }


}
