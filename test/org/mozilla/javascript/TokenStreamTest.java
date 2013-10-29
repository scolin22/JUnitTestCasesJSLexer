package org.mozilla.javascript;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.StringReader;
import java.lang.reflect.Field;

import org.junit.Test;

public class TokenStreamTest {

  @Test
  public void testTokenStream() {
    fail("Not yet implemented");
  }

  @Test
  public void testTokenStream_BlackBox_ssnull() throws IllegalAccessException, IllegalArgumentException {
    //BlackBox test on the specs
    //Input: sourceReader = "var a = 5", sourceString = "", Lineno = 1
    //Expected Output: this.sourceString == null, this.sourceReader != null, this.sourceEnd >= 0, this.sourceCursor == 0, this.cursor == 0
    TokenStream instance = new TokenStream(new StringReader("var a = 5"), "",1);

    int reflectedSourceEnd = -1;
    Object reflectedSourceReader = null;

    Class secretClass = instance.getClass();
    Field fields[] = secretClass.getDeclaredFields();
    System.out.println("Access all the fields");
    for (int i = 0; i < fields.length; i++){
       if (fields[i].getName() == "sourceEnd") {
         fields[i].setAccessible(true);
         reflectedSourceEnd = (Integer)fields[i].get(instance);
       } else if (fields[i].getName() == "sourceReader") {
         fields[i].setAccessible(true);
         reflectedSourceReader = fields[i].get(instance);
       }
    }

    assertNull(instance.getSourceString());
    assertNotNull(reflectedSourceReader);
    assertTrue(reflectedSourceEnd >= 0);
    assertTrue(instance.sourceCursor == 0);
    assertTrue(instance.cursor == 0);
  }

  @Test
  public void testTokenStream_BlackBox_ssnotnull() throws IllegalAccessException, IllegalArgumentException {
    //BlackBox test on the specs
    //Input: sourceReader = "", sourceString = "var a = 5", Lineno = 1
    //Expected Output: this.sourceString != null, this.sourceReader == null, this.sourceEnd >= 0, this.sourceCursor == 0, this.cursor == 0
    TokenStream instance = new TokenStream(null, "var a = 5",1);

    int reflectedSourceEnd = -1;
    Object reflectedSourceReader = null;

    Class secretClass = instance.getClass();
    Field fields[] = secretClass.getDeclaredFields();
    System.out.println("Access all the fields");
    for (int i = 0; i < fields.length; i++){
       if (fields[i].getName() == "sourceEnd") {
         fields[i].setAccessible(true);
         reflectedSourceEnd = (Integer)fields[i].get(instance);
       } else if (fields[i].getName() == "sourceReader") {
         fields[i].setAccessible(true);
         reflectedSourceReader = fields[i].get(instance);
       }
    }

    assertNotNull(instance.getSourceString());
    assertNull(reflectedSourceReader);
    assertTrue(reflectedSourceEnd >= 0);
    assertTrue(instance.sourceCursor == 0);
    assertTrue(instance.cursor == 0);
  }

  @Test
  public void testStringToKeyword() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetString() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetNumber() {
    fail("Not yet implemented");
  }

  @Test
  public void testIsNumberOctal() {
    fail("Not yet implemented");
  }

  @Test
  public void testEof() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetToken() {
    fail("Not yet implemented");
  }

  @Test
  public void testIsJSSpace() {
    fail("Not yet implemented");
  }

  @Test
  public void testStringToNumber() {
    fail("Not yet implemented");
  }

}
