package logic

class DfaIntersectionNode {
  var node1: DfaNode = null
  var node2: DfaNode = null

  var On0: DfaIntersectionNode = null
  var On1: DfaIntersectionNode = null
  var On2: DfaIntersectionNode = null

  def IsStart(): Boolean = {
    return node1.IsStart && node2.IsStart;
  }

  def IsFinal(): Boolean = {
    return node1.IsFinal && node2.IsFinal;
  }

  var inspected = false;
}