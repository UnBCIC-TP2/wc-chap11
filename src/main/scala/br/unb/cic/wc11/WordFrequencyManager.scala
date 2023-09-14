package br.unb.cic.wc11

import scala.collection.mutable.Map

import scala.collection.immutable.ListMap

class WordFrequencyManager extends MessageDispatcher {

  val mapping : Map[String, Int] = Map()

  override def dispatch(message: Message): Message = message match {
    case MapWordMessage(word) => mapWord(word)
    case SortWordsMessage     => sorted()  
    case _                    => InvalidMessage(message)
  }

  private def mapWord(word: String): Message = {
    val count = mapping.getOrElse(word, 0)

    mapping += (word -> (count + 1))

    EmptyMessage
  }

  private def sorted(): Message = {
    val sorted = ListMap(mapping.toSeq.sortWith(_._2 > _._2):_*)

    WordCountMessage(sorted.toList)
  }


}
