package logic
import scala.collection.mutable.ArrayBuffer

class Dfa {
  var start: DfaNode = null
  var end = new ArrayBuffer[DfaNode]
  var nodes = new ArrayBuffer[DfaNode]
}