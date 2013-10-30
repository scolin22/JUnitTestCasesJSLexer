package org.mozilla.javascript;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.Test;

public class TokenStreamTest {

  @Test
  public void testTokenStream() {
    fail("Not yet implemented");
  }

  @Test
  public void testTokenStream_bb_ssnull() throws IllegalAccessException, IllegalArgumentException {
    //BlackBox test on the specs
    //Input: sourceReader = "var a = 5", sourceString = "", Lineno = 1
    //Expected Output: this.sourceString == null, this.sourceReader != null, this.sourceEnd >= 0, this.sourceCursor == 0, this.cursor == 0
    TokenStream instance = new TokenStream(new StringReader("var a = 5"), "",1);

    int reflectedSourceEnd = -1;
    Object reflectedSourceReader = null;

    Class secretClass = instance.getClass();
    Field fields[] = secretClass.getDeclaredFields();
    // System.out.println("Access all the fields");
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
  public void testTokenStream_bb_ssnotnull() throws IllegalAccessException, IllegalArgumentException {
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
  public void testStringToKeyword_bb_else() {
    //Tests if the method returns the correct token for a given string
    //Input: name = "else"
    //Expected Output: id = Token.ELSE
    Integer[] array = {0, 113, 44, 109, 112, 4, 45};
    assertTrue(TokenStream.stringToKeyword("else") == Token.ELSE);
    assertTrue(Arrays.asList(array).contains(TokenStream.stringToKeyword("else")));
  }

  @Test
  public void testStringToKeyword_bb_false() {
    //Tests if the method returns the correct token for a given string
    //Input: name = "false"
    //Expected Output: id = Token.FALSE
    Integer[] array = {0, 113, 44, 109, 112, 4, 45};
    assertTrue(TokenStream.stringToKeyword("false") == Token.FALSE);
    assertTrue(Arrays.asList(array).contains(TokenStream.stringToKeyword("false")));
  }

  @Test
  public void testStringToKeyword_bb_function() {
    //Tests if the method returns the correct token for a given string
    //Input: name = "function"
    //Expected Output: id = Token.FUNCTION
    Integer[] array = {0, 113, 44, 109, 112, 4, 45};
    assertTrue(TokenStream.stringToKeyword("function") == Token.FUNCTION);
    assertTrue(Arrays.asList(array).contains(TokenStream.stringToKeyword("function")));
  }

  @Test
  public void testStringToKeyword_bb_if() {
    //Tests if the method returns the correct token for a given string
    //Input: name = "if"
    //Expected Output: id = Token.IF
    Integer[] array = {0, 113, 44, 109, 112, 4, 45};
    assertTrue(TokenStream.stringToKeyword("if") == Token.IF);
    assertTrue(Arrays.asList(array).contains(TokenStream.stringToKeyword("if")));
  }

  @Test
  public void testStringToKeyword_bb_return() {
    //Tests if the method returns the correct token for a given string
    //Input: name = "return"
    //Expected Output: id = Token.RETURN
    Integer[] array = {0, 113, 44, 109, 112, 4, 45};
    assertTrue(TokenStream.stringToKeyword("return") == Token.RETURN);
    assertTrue(Arrays.asList(array).contains(TokenStream.stringToKeyword("return")));
  }

  @Test
  public void testStringToKeyword_bb_true() {
    //Tests if the method returns the correct token for a given string
    //Input: name = "true"
    //Expected Output: id = Token.TRUE
    Integer[] array = {0, 113, 44, 109, 112, 4, 45};
    assertTrue(TokenStream.stringToKeyword("true") == Token.TRUE);
    assertTrue(Arrays.asList(array).contains(TokenStream.stringToKeyword("true")));
  }

  @Test
  public void testStringToKeyword_bb_noneoftheabove() {
    //Tests if EOF is returned for none of the tokens
    //Input: name = ""
    //Expected Output: id = Token.EOF
    Integer[] array = {0, 113, 44, 109, 112, 4, 45};
    assertTrue(TokenStream.stringToKeyword("") == Token.EOF);
    assertTrue(Arrays.asList(array).contains(TokenStream.stringToKeyword("")));
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
