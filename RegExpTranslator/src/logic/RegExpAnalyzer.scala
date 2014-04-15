package logic

import parser._
import scala.collection.mutable._

class RegExpAnalyzer {

  def check(regEx1: String, regEx2: String): String = {
    if (regEx1.isEmpty()) {
      throw new IllegalArgumentException("RegEx 1 cannot be empty!");
    }
    if (regEx2.isEmpty()) {
      throw new IllegalArgumentException("RegEx 2 cannot be empty!");
    }

    var enfa1 = getEnfa(regEx1)
    var enfa2 = getEnfa(regEx2)

    var dfa1 = convertEnfaToDfa(enfa1)
    var dfa2 = convertEnfaToDfa(enfa2)

    return getDfasIntersection(dfa1, dfa2)
  }

  def getEnfa(regEx: String): Enfa = {
    return RegExCompiler.getEnfa(regEx)
  }

  def convertEnfaToDfa(enfa: Enfa): Dfa = {
    var dfa = new Dfa

    var start = new DfaNode
    dfa.start = start
    start.IsStart = true
    start.nodes.add(enfa.start)

    discoverEtransitions(enfa.start, dfa.start);

    dfa.nodes += dfa.start

    discoverTransitions(dfa.start, dfa);

    for (n <- dfa.nodes) {
      if (isFinal(n.nodes)) {
        n.IsFinal = true
        dfa.end += n
      }
    }
    return dfa;
  }

  def discoverTransitions(node: DfaNode, dfa: Dfa) {
    for (n <- node.nodes) {
      var it = n.On0.iterator
      while (it.hasNext()) {
        var n0 = it.next
        if (node.On0 == null) {
          node.On0 = new DfaNode
        }
        node.On0.nodes.add(n0)
        discoverEtransitions(n0, node.On0)
      }

      it = n.On1.iterator
      while (it.hasNext()) {
        var n1 = it.next
        if (node.On1 == null) {
          node.On1 = new DfaNode
        }
        node.On1.nodes.add(n1)
        discoverEtransitions(n1, node.On1)
      }

      it = n.On2.iterator
      while (it.hasNext()) {
        var n2 = it.next
        if (node.On2 == null) {
          node.On2 = new DfaNode
        }
        node.On2.nodes.add(n2)
        discoverEtransitions(n2, node.On2)
      }
    }

    if (node.On0 != null) {
      if (isDiscovered(node.On0, dfa) == null) {
        dfa.nodes += node.On0
        discoverTransitions(node.On0, dfa)
      } else {
        node.On0 = isDiscovered(node.On0, dfa)
      }
    }

    if (node.On1 != null) {
      if (isDiscovered(node.On1, dfa) == null) {
        dfa.nodes += node.On1
        discoverTransitions(node.On1, dfa)
      } else {
        node.On1 = isDiscovered(node.On1, dfa)
      }
    }

    if (node.On2 != null) {
      if (isDiscovered(node.On2, dfa) == null) {
        dfa.nodes += node.On2
        discoverTransitions(node.On2, dfa)
      } else {
        node.On2 = isDiscovered(node.On2, dfa)
      }
    }
  }

  def discoverEtransitions(node: parser.Node, state: DfaNode) {
    var it = node.OnE.iterator
    while (it.hasNext()) {
      var n = it.next
      if (state.nodes.contains(n) == false) {
        state.nodes.add(n);
        if (n.IsFinal) state.IsFinal = true;
        discoverEtransitions(n, state);
      }
    }
  }

  def isDiscovered(node: DfaNode, dfa: Dfa): DfaNode = {
    for (discovered <- dfa.nodes) {
      var containsAll = true;
      for (n <- node.nodes) {
        if (!discovered.nodes.contains(n)) {
          containsAll = false;
        }
      }
      if (containsAll && node.nodes.size == discovered.nodes.size) {
        return discovered;
      }
    }
    return null;
  }

  def isFinal(state: HashSet[parser.Node]): Boolean = {
    for (n <- state) {
      if (n.IsFinal) return true;
    }
    return false;
  }

  def getDfasIntersection(dfa1: Dfa, dfa2: Dfa): String = {
    var dfa = new DfaIntersection
    var start = new DfaIntersectionNode
    start.node1 = dfa1.start
    start.node2 = dfa2.start
    dfa.nodes += start
    var result = ""
    try {
      getUnionTransition(start, dfa, result)
    } catch {
      case e: Exception => return if (e.getMessage.length() > 0) e.getMessage else "E"
    }
    return "true";
  }

  def getUnionTransition(unionNode: DfaIntersectionNode, dfa: DfaIntersection, result: String) {
    if (unionNode.node1.IsFinal && !unionNode.node2.IsFinal) {
      throw new Exception(result);
    }

    //on 0
    if (unionNode.node1.On0 == null) {
      // dead state in E1: String can be rejected by E1, while E2 accept it
    } else if (unionNode.node2.On0 == null) {
      throw new Exception(result + "0")
    } else if (dfa.getNode(unionNode.node1.On0, unionNode.node2.On0) == null) {
      unionNode.On0 = new DfaIntersectionNode
      unionNode.On0.node1 = unionNode.node1.On0
      unionNode.On0.node2 = unionNode.node2.On0
      dfa.nodes += unionNode.On0
      getUnionTransition(unionNode.On0, dfa, result + "0")
    } else {
      unionNode.On0 = dfa.getNode(unionNode.node1.On0, unionNode.node2.On0)
    }

    //on 1
    if (unionNode.node1.On1 == null) {
      // dead state in E1: String can be rejected by E1, while E2 accept it
    } else if (unionNode.node2.On1 == null) {
      throw new Exception(result + "1")
    } else if (dfa.getNode(unionNode.node1.On1, unionNode.node2.On1) == null) {
      unionNode.On1 = new DfaIntersectionNode()
      unionNode.On1.node1 = unionNode.node1.On1
      unionNode.On1.node2 = unionNode.node2.On1
      dfa.nodes += unionNode.On1
      getUnionTransition(unionNode.On1, dfa, result + "1");
    } else {
      unionNode.On1 = dfa.getNode(unionNode.node1.On1, unionNode.node2.On1)
    }

    //on 2
    if (unionNode.node1.On2 == null) {
      // dead state in E1: String can be rejected by E1, while E2 accept it
    } else if (unionNode.node2.On2 == null) {
      throw new Exception(result + "2")
    } else if (dfa.getNode(unionNode.node1.On2, unionNode.node2.On2) == null) {
      unionNode.On2 = new DfaIntersectionNode
      unionNode.On2.node1 = unionNode.node1.On2
      unionNode.On2.node2 = unionNode.node2.On2
      dfa.nodes += unionNode.On2
      getUnionTransition(unionNode.On2, dfa, result + "2")
    } else {
      unionNode.On2 = dfa.getNode(unionNode.node1.On2, unionNode.node2.On2)
    }
  }
}