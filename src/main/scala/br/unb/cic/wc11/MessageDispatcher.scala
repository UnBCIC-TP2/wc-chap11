package br.unb.cic.wc11

sealed trait Message

case object CountLinesMessage                                           extends Message
case object EmptyMessage                                                extends Message
case object FalseMessage                                                extends Message
case object SortWordsMessage                                            extends Message
case object TrueMessage                                                 extends Message
case object WordsMessage                                                extends Message

case class ExecuteMessage(n: Int)                                       extends Message
case class InitMessage(path: String)                                    extends Message
case class InitProgramMessage(bookFile: String, stopWordsFile: String)  extends Message
case class InvalidMessage(message: Message)                             extends Message
case class IsStopWordMessage(word: String)                              extends Message
case class MapWordMessage(word: String)                                 extends Message
case class TotalLinesMessage(total : Int)                               extends Message
case class WordCountMessage(words: List[(String, Int)])                 extends Message 
case class WordListMessage(words: List[String])                         extends Message


trait MessageDispatcher {
  def dispatch(message : Message) : Message
}
