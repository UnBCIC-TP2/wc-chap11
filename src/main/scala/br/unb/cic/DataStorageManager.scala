package br.unb.cic

import scala.io.Source

class DataStorageManager extends MessageDispatcher {

  private var lines : List[String] = _

  override def dispatch(message: Message): Message = message match {
    case InitMessage(path) => init(path)
    case WordsMessage => words()
    case CountLinesMessage => TotalLinesMessage(lines.size)
    case _ => InvalidMessage(message)
  }

  private def init(path: String): Message = {
    lines = Source.fromFile(path).getLines.toList
    EmptyMessage
  }

  private def words(): Message = {
    val words = lines.flatMap(line => line.split(" "))
                     .filter(w => w.size >= 3)
                     .map(w => w.replaceAll("[^a-zA-Z]", ""))
    WordListMessage(words)
  }

}
