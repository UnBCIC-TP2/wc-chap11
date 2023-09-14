package br.unb.cic

import br.unb.cic.wc11.{ExecuteMessage, InitProgramMessage, WordFrequencyController}
import org.backuity.clist._

object MainProgram extends CliMain[Unit] (
  name="Word Count",
  description="a simple word count implementation using the \"LetterBox\" style") {

  var input = arg[String](description = "the input file")
  var stopWords = arg[String](description = "the stop words file")  
  var n  = opt[Int](default = 20)

  def run: Unit = {
    val controller = new WordFrequencyController()

    controller.dispatch(InitProgramMessage(input, stopWords))
    controller.dispatch(ExecuteMessage(n))
  }
}
