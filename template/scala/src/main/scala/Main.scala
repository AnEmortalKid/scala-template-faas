import scala.io.StdIn
import function.Handler

object Main {
  def main(args: Array[String]): Unit = {
    val in = input()
    Handler.handle(in)
  }

  def input() = {
    var read = true
    var in = ""
    while (read) {
      val line = StdIn.readLine()
      if (line != null) {
        in += s"${line}\n"
      }
      else {
        read = false
      }
    }
    in
  }

}