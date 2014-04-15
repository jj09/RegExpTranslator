package logic
import scala.collection.mutable.HashSet
import parser._

class DfaNode {
  var nodes = new HashSet[Node];
	
	var On0: DfaNode = null
	var On1: DfaNode = null
	var On2: DfaNode = null
	
	var IsStart = false
	var IsFinal = false

}