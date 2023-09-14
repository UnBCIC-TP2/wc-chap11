package br.unb.cic.wc11

import scala.io.Source
import scala.collection.mutable.Set

class StopWordManager extends MessageDispatcher {

  val stopWords: Set[String] = Set()

  override def dispatch(message: Message): Message = message match {
    case InitMessage(path)       => init(path)
    case IsStopWordMessage(word) => isStopWord(word)
    case _                       => InvalidMessage(message)
  }

  private def init(path: String): Message = {
    Source.fromFile(path).getLines.toList.foreach(s => stopWords += s)
    return EmptyMessage
  }

  private def isStopWord(word: String): Message =
    if (stopWords.contains(word)) TrueMessage
    else FalseMessage
}
