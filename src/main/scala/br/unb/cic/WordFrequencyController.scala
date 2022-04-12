package br.unb.cic

class WordFrequencyController extends MessageDispatcher {

  var storageManager       : DataStorageManager   = _
  var stopWordManager      : StopWordManager      = _
  var wordFrequencyManager : WordFrequencyManager = _

  override def dispatch(message: Message): Message = message match {

    case InitProgramMessage(p1: String, p2: String) => init(p1, p2)
    case ExecuteMessage(n)                          => execute(n)
    case _                                          => InvalidMessage(message)  

  }

  def init(path1: String, path2: String): Message = {
    storageManager       = new DataStorageManager()
    stopWordManager      = new StopWordManager()
    wordFrequencyManager = new WordFrequencyManager

    storageManager.dispatch(InitMessage(path1))
    stopWordManager.dispatch(InitMessage(path2))

    EmptyMessage
  }

  def execute(n: Int): Message = {
    val WordListMessage(words) = storageManager.dispatch(WordsMessage)

    for(w <- words) {
      stopWordManager.dispatch(IsStopWordMessage(w)) match {
        case FalseMessage => wordFrequencyManager.dispatch(MapWordMessage(w))
        case _            => // do nothing  
      }
    }

    val WordCountMessage(res) = wordFrequencyManager.dispatch(SortWordsMessage)

    for((w,c) <- res.take(n)) {
      println(s"$w - $c")
    }
    EmptyMessage
  }

}
