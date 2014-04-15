package test.logic

import logic.RegExpAnalyzer
import org.junit.Assert._
import org.junit.Test
import org.junit._

class TestRegExpAnalyzer {
  
  @Test def e1ContainedInE2 {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "(0+1)*"
    val regEx2 = "(0+1)*"
    
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("true", result)
  }
  
  @Test def e1NotContainedInE2 {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "(0+1+2)*"
    val regEx2 = "(0+1)*"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertNotEquals("true", result)
  }
  
  @Test def e1NotContainedInE2WithResultCheck {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "(0(0+2)*+1)*+2"
    val regEx2 = "(01+12+0+2)*(1)*"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("00110", result)
  }
  
  @Test def complicatedExpContainedInStringsExp {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "(0(0+2)*+1)*+2+12+(00+21)*+0000110102"
    val regEx2 = "(0+1+2)*"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("true", result)
  }
  
  @Test  def emptyExp {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = ""
    val regEx2 = ""
    
    // Act
    try {
      rea.check(regEx1, regEx2)   
      fail()
    } catch {
      case e: IllegalArgumentException => //Expected
    }
    
    // Assert
  }
  
  @Test def expWithEpsilon {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "(0+01)*"
    val regEx2 = "(1+E)((0)*+01)*"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("true", result)
  }
  
  @Test def firstCanGenerateEpsilonWhileSecondCannot {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "(0+01)*"
    val regEx2 = "(1+E)((0)*+01)*0"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("E", result)
  }
  
  // complex examples
  /*
    1. ((1+e)(0+01)* + (10+0)*11(01+0)*)
    2. ((1+e)0(0)*)*(11+1+e)(0(0)*(1+e))*
    3. ((1+e)0(0)* )*(11+e)(0(0)*(1+e))*
    4. (1 + 101 + (0+10)*(11+e)(0+01)*)
   */
  
  @Test def complexExample1and2 {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "((1+e)(0+01)* + (10+0)*11(01+0)*)"
    val regEx2 = "((1+e)0(0)*)*(11+1+e)(0(0)*(1+e))*"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("true", result)
  }
  
  @Test def complexExample1and3 {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "((1+e)(0+01)* + (10+0)*11(01+0)*)"
    val regEx2 = "((1+e)0(0)* )*(11+e)(0(0)*(1+e))*"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("1", result)
  }
  
  @Test def complexExample1and4 {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "((1+e)(0+01)* + (10+0)*11(01+0)*)"
    val regEx2 = "(1 + 101 + (0+10)*(11+e)(0+01)*)"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("true", result)
  }
  
  @Test def complexExample2and3 {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "((1+e)0(0)*)*(11+1+e)(0(0)*(1+e))*"
    val regEx2 = "((1+e)0(0)* )*(11+e)(0(0)*(1+e))*"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("1", result)
  }
  
  @Test def complexExample2and4 {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "((1+e)0(0)*)*(11+1+e)(0(0)*(1+e))*"
    val regEx2 = "(1 + 101 + (0+10)*(11+e)(0+01)*)"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("true", result)
  }
  
  @Test def complexExample3and4 {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "((1+e)0(0)* )*(11+e)(0(0)*(1+e))*"
    val regEx2 = "(1 + 101 + (0+10)*(11+e)(0+01)*)"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("true", result)
  }
  
  @Test def complexExample2and1 {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "((1+e)0(0)*)*(11+1+e)(0(0)*(1+e))*"
    val regEx2 = "((1+e)(0+01)* + (10+0)*11(01+0)*)"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("true", result)
  }
  
  @Test def complexExample3and1 {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "((1+e)0(0)* )*(11+e)(0(0)*(1+e))*"
    val regEx2 = "((1+e)(0+01)* + (10+0)*11(01+0)*)"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("true", result)
  }
  
  @Test def complexExample4and1 {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "(1 + 101 + (0+10)*(11+e)(0+01)*)"
    val regEx2 = "((1+e)(0+01)* + (10+0)*11(01+0)*)"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("true", result)
  }
  
  @Test def complexExample3and2 {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "((1+e)0(0)* )*(11+e)(0(0)*(1+e))*"
    val regEx2 = "((1+e)0(0)*)*(11+1+e)(0(0)*(1+e))*"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("true", result)
  }
  
  @Test def complexExample4and2 {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "(1 + 101 + (0+10)*(11+e)(0+01)*)"
    val regEx2 = "((1+e)0(0)*)*(11+1+e)(0(0)*(1+e))*"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("true", result)
  }
  
  @Test def complexExample4and3 {
    // Arrange
    val rea = new RegExpAnalyzer
    val regEx1 = "(1 + 101 + (0+10)*(11+e)(0+01)*)"
    val regEx2 = "((1+e)0(0)* )*(11+e)(0(0)*(1+e))*"
      
    // Act
    val result = rea.check(regEx1, regEx2)
    
    // Assert
    assertEquals("1", result)
  }

}