import logic.RegExpAnalyzer
import org.antlr.runtime.ANTLRStringStream
import org.antlr.runtime.CommonTokenStream
import org.antlr.runtime.RecognitionException
import org.antlr.runtime.TokenStream
import org.antlr.runtime.tree.CommonTree;

object Assignment2 {

  def main(args: Array[String]): Unit = {    
    val regEx1: String = "(0+01)*"
    val regEx2: String = "(1+E)((0)*+01)*0"
    
//    print("E1: ")
//    val regEx1 = readLine()
//    print("E2: ")
//    val regEx2 = readLine()
    
    val rea = new RegExpAnalyzer
    val result = rea.check(regEx1, regEx2) 
    
    println(result)    
  }
}