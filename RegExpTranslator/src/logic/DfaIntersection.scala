package logic
import scala.collection.mutable.ArrayBuffer

class DfaIntersection {
  var start: DfaIntersectionNode = null
  var end: DfaIntersectionNode = null

  var nodes = new ArrayBuffer[DfaIntersectionNode]

  def getNode(node1: DfaNode, node2: DfaNode): DfaIntersectionNode = {
    for (node <- nodes) yield {
      if (node.node1 == node1 && node.node2 == node2) {
        return node;
      }
    }
    return null;
  }

}