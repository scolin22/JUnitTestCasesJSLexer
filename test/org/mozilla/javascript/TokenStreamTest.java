package org.mozilla.javascript;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.Test;

public class TokenStreamTest {
  public static String sampleString = "var a = 5";

  @Test
  public void testTokenStream_gb_ssnull() throws IllegalAccessException, IllegalArgumentException {
    //GlassBox test on the specs
    //Input: sourceReader = sampleString, sourceString = "", Lineno = 1
    //Expected Output: this.sourceString == null, this.sourceReader != null, this.sourceEnd >= 0, this.sourceCursor == 0, this.cursor == 0
    Reader sr = new StringReader(sampleString);
    TokenStream instance = new TokenStream(sr, "",1);

    int reflectedSourceEnd = -1;
    Object reflectedSourceReader = null;    
    Object reflectedSourceBuffer = null;

    Class secretClass = instance.getClass();
    Field fields[] = secretClass.getDeclaredFields();
    for (int i = 0; i < fields.length; i++){
       if (fields[i].getName() == "sourceEnd") {
         fields[i].setAccessible(true);
         reflectedSourceEnd = (Integer)fields[i].get(instance);
       } else if (fields[i].getName() == "sourceReader") {
         fields[i].setAccessible(true);
         reflectedSourceReader = fields[i].get(instance);
       } else if (fields[i].getName() == "sourceBuffer") {
         fields[i].setAccessible(true);
         reflectedSourceBuffer = fields[i].get(instance);
        }
    }

    assertNull(instance.getSourceString());
    assertNotNull(reflectedSourceBuffer);
    assertTrue(reflectedSourceReader == sr);
    assertTrue(reflectedSourceEnd == 0);
    assertTrue(instance.sourceCursor == 0);
    assertTrue(instance.cursor == 0);
  }

  @Test
  public void testTokenStream_bb_ssnull() throws IllegalAccessException, IllegalArgumentException {
    //BlackBox test on the specs
    //Input: sourceReader = sampleString, sourceString = "", Lineno = 1
    //Expected Output: this.sourceString == null, this.sourceReader != null, this.sourceEnd >= 0, this.sourceCursor == 0, this.cursor == 0
    TokenStream instance = new TokenStream(new StringReader(sampleString), "",1);

    int reflectedSourceEnd = -1;
    Object reflectedSourceReader = null;

    Class secretClass = instance.getClass();
    Field fields[] = secretClass.getDeclaredFields();
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
  public void testTokenStream_gb_ssnotnull() throws IllegalAccessException, IllegalArgumentException {
    //BlackBox test on the specs
    //Input: sourceReader = "", sourceString = sampleString, Lineno = 1
    //Expected Output: this.sourceString != null, this.sourceReader == null, this.sourceEnd >= 0, this.sourceCursor == 0, this.cursor == 0
    TokenStream instance = new TokenStream(null, sampleString,1);

    int reflectedSourceEnd = -1;
    Object reflectedSourceReader = null;
    Object reflectedSourceBuffer = null;

    Class secretClass = instance.getClass();
    Field fields[] = secretClass.getDeclaredFields();
    for (int i = 0; i < fields.length; i++){
       if (fields[i].getName() == "sourceEnd") {
         fields[i].setAccessible(true);
         reflectedSourceEnd = (Integer)fields[i].get(instance);
       } else if (fields[i].getName() == "sourceReader") {
         fields[i].setAccessible(true);
         reflectedSourceReader = fields[i].get(instance);
       } else if (fields[i].getName() == "sourceBuffer") {
         fields[i].setAccessible(true);
         reflectedSourceBuffer = fields[i].get(instance);
        }
    }

    assertTrue(instance.getSourceString() == sampleString);
    assertNull(reflectedSourceBuffer);
    assertNull(reflectedSourceReader);
    assertTrue(reflectedSourceEnd == instance.getSourceString().length());
    assertTrue(instance.sourceCursor == 0);
    assertTrue(instance.cursor == 0);
  }

  @Test
  public void testTokenStream_bb_ssnotnull() throws IllegalAccessException, IllegalArgumentException {
    //BlackBox test on the specs
    //Input: sourceReader = "", sourceString = sampleString, Lineno = 1
    //Expected Output: this.sourceString != null, this.sourceReader == null, this.sourceEnd >= 0, this.sourceCursor == 0, this.cursor == 0
    TokenStream instance = new TokenStream(null, sampleString,1);

    int reflectedSourceEnd = -1;
    Object reflectedSourceReader = null;

    Class secretClass = instance.getClass();
    Field fields[] = secretClass.getDeclaredFields();
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
  //Test if string is returned
  //Input: none
  //Expected Output instance.getString() == string
  public void testGetString_bb() throws IllegalAccessException, IllegalArgumentException {
    TokenStream instance = new TokenStream(null, sampleString, 0);

    String reflectedString = "";

    Class secretClass = instance.getClass();
    Field fields[] = secretClass.getDeclaredFields();
    for (int i = 0; i < fields.length; i++){
       if (fields[i].getName() == "string") {
         fields[i].setAccessible(true);
         reflectedString = (String)fields[i].get(instance);
       }
    }

    assertTrue(instance.getString() == reflectedString);
  }

  @Test
  //Test if number is returned
  //Input: none
  //Expected Output instance.getNumber() == number
  public void testGetNumber_bb() throws IllegalAccessException, IllegalArgumentException {
    TokenStream instance = new TokenStream(null, sampleString, 0);

    double reflectedNumber = 0.0;

    Class secretClass = instance.getClass();
    Field fields[] = secretClass.getDeclaredFields();
    for (int i = 0; i < fields.length; i++){
       if (fields[i].getName() == "number") {
         fields[i].setAccessible(true);
         reflectedNumber = (Double)fields[i].get(instance);
       }
    }

    assertTrue(instance.getNumber() == reflectedNumber);
  }

  @Test
  //Test if isOctal is returned
  //Input: none
  //Expected Output instance.isNumberOctal() == isOctal
  public void testIsNumberOctal_bb() throws IllegalAccessException, IllegalArgumentException {
    TokenStream instance = new TokenStream(null, sampleString, 0);

    boolean reflectedIsOctal = false;

    Class secretClass = instance.getClass();
    Field fields[] = secretClass.getDeclaredFields();
    for (int i = 0; i < fields.length; i++){
       if (fields[i].getName() == "isOctal") {
         fields[i].setAccessible(true);
         reflectedIsOctal = (Boolean)fields[i].get(instance);
       }
    }

    assertTrue(instance.isNumberOctal() == reflectedIsOctal);
  }

  @Test
  //Test if hitEOF is returned
  //Input: none
  //Expected Output instance.eof() == hitEOF
  public void testEof_bb() throws IllegalAccessException, IllegalArgumentException {
    TokenStream instance = new TokenStream(null, sampleString, 0);

    boolean reflectedHitEOF = false;

    Class secretClass = instance.getClass();
    Field fields[] = secretClass.getDeclaredFields();
    for (int i = 0; i < fields.length; i++){
       if (fields[i].getName() == "hitEOF") {
         fields[i].setAccessible(true);
         reflectedHitEOF = (Boolean)fields[i].get(instance);
       }
    }

    assertTrue(instance.eof() == reflectedHitEOF);
  }

  @Test
  public void testGetToken_gb_EOF() throws IOException {
    //Test if the method returns a EOF
    //Input: empty String simulating an empty FileReader
    //Expect: Token.EOF
    TokenStream instance = new TokenStream(new StringReader(""), null, 1);
    int result = instance.getToken();
    assertTrue(result >= -1 && result <= 162);
    assertTrue(result == Token.EOF);
  }

  @Test
  public void testGetToken_gb_EOL() throws IOException {
    //Test if the method returns a EOL
    //Input: \n String simulating a new line in FileReader
    //Expect: Token.EOL
    TokenStream instance = new TokenStream(new StringReader("\n"), null, 1);
    int result = instance.getToken();
    assertTrue(result >= -1 && result <= 162);
    assertTrue(result == Token.EOL);
  }

  @Test
  public void testGetToken_gb_ERROR_1() throws IOException {
    //Test if the method returns a ERROR
    //Input: bad unicode String simulating a bad unicode FileReader
    //Expect: Token.ERROR
    //exits on line 260 prove with debugger
    TokenStream instance = new TokenStream(new StringReader("\\u+"), null, 1);
    int result = instance.getToken();
    assertTrue(result >= -1 && result <= 162);
    assertTrue(result == Token.ERROR);
  }

  @Test
  public void testGetToken_gb_ERROR_2() throws IOException {
    //Test if the method returns a ERROR
    //Input: bad unicode String simulating a bad unicode FileReader
    //Expect: Token.ERROR
    TokenStream instance = new TokenStream(new StringReader("\\*"), null, 1);
    int result = instance.getToken();
    assertTrue(result >= -1 && result <= 162);
    assertTrue(result == Token.ERROR);
  }

  @Test
  public void testGetToken_gb_NAME() throws IOException {
    //Test if the method returns a NAME
    //Input: regular String simulating a regular FileReader
    //Expect: Token.NAME
    TokenStream instance = new TokenStream(new StringReader("a"), null, 1);
    int result = instance.getToken();
    assertTrue(result >= -1 && result <= 162);
    assertTrue(result == Token.NAME);
  }

  @Test
  public void testGetToken_gb_NUMBER() throws IOException {
    //Test if the method returns a NUMBER
    //Input: regular String simulating a regular FileReader
    //Expect: Token.NUMBER
    TokenStream instance = new TokenStream(new StringReader("1"), null, 1);
    int result = instance.getToken();
    assertTrue(result >= -1 && result <= 162);
    assertTrue(result == Token.NUMBER);
  }

  @Test
  public void testIsJSSpace_bb_cis126() {
    //Tests if the method returns the boolean for a given int
    //Input: c = 126
    //Expected Output: c == 0x20 || c == 0x9 || c == 0xC || c == 0xB
    int c = 126;
    assertTrue(TokenStream.isJSSpace(c) == (c == 0x20 || c == 0x9 || c == 0xC || c == 0xB));
  }

  @Test
  public void testIsJSSpace_bb_cis127() {
    //Tests if the method returns the boolean for a given int
    //Input: c = 127
    //Expected Output: c == 0x20 || c == 0x9 || c == 0xC || c == 0xB
    int c = 127;
    assertTrue(TokenStream.isJSSpace(c) == (c == 0x20 || c == 0x9 || c == 0xC || c == 0xB));
  }

  @Test
  public void testIsJSSpace_bb_cis128() throws IllegalAccessException, IllegalArgumentException {
    //Tests if the method returns the boolean for a given int
    //Input: c = 128
    //Expected Output: c == 0xA0 || c == BYTE_ORDER_MARK || Character.getType((char)c) == Character.SPACE_SEPARATOR
    int c = 128;

    TokenStream instance = new TokenStream(null, sampleString, 0);

    char reflectedBYTE_ORDER_MARK = '\u0000';

    Class secretClass = instance.getClass();
    Field fields[] = secretClass.getDeclaredFields();
    for (int i = 0; i < fields.length; i++){
       if (fields[i].getName() == "BYTE_ORDER_MARK") {
         fields[i].setAccessible(true);
         reflectedBYTE_ORDER_MARK = (Character)fields[i].get(instance);
       }
    }

    assertTrue(TokenStream.isJSSpace(c) == (c == 0xA0 || c == reflectedBYTE_ORDER_MARK
                || Character.getType((char)c) == Character.SPACE_SEPARATOR));
  }

  @Test
  public void testIsJSSpace_gb_cis0x12() {
    //Tests if the method returns the boolean for a given int
    //Input: c = 0x12
    //Expected Output: bool false
    int c = 0x12;
    assertFalse(TokenStream.isJSSpace(c));
  }

  @Test
  public void testIsJSSpace_gb_cis0x20() {
    //Tests if the method returns the boolean for a given int
    //Input: c = 0x20
    //Expected Output: c == 0x20 || c == 0x9 || c == 0xC || c == 0xB
    int c = 0x20;
    assertTrue(TokenStream.isJSSpace(c) == (c == 0x20 || c == 0x9 || c == 0xC || c == 0xB));
  }

  @Test
  public void testIsJSSpace_gb_cis0x9() {
    //Tests if the method returns the boolean for a given int
    //Input: c = 0x9
    //Expected Output: c == 0x20 || c == 0x9 || c == 0xC || c == 0xB
    int c = 0x9;
    assertTrue(TokenStream.isJSSpace(c) == (c == 0x20 || c == 0x9 || c == 0xC || c == 0xB));
  }

  @Test
  public void testIsJSSpace_gb_cis0xC() {
    //Tests if the method returns the boolean for a given int
    //Input: c = 0xC
    //Expected Output: c == 0x20 || c == 0x9 || c == 0xC || c == 0xB
    int c = 0xC;
    assertTrue(TokenStream.isJSSpace(c) == (c == 0x20 || c == 0x9 || c == 0xC || c == 0xB));
  }

  @Test
  public void testIsJSSpace_gb_cis0xB() {
    //Tests if the method returns the boolean for a given int
    //Input: c = 0xB
    //Expected Output: c == 0x20 || c == 0x9 || c == 0xC || c == 0xB
    int c = 0xB;
    assertTrue(TokenStream.isJSSpace(c) == (c == 0x20 || c == 0x9 || c == 0xC || c == 0xB));
  }

  @Test
  public void testIsJSSpace_gb_cisBYTE_ORDER_MARK() throws IllegalAccessException, IllegalArgumentException {
    //Tests if the method returns the boolean for a given int
    //Input: c = BYTE_ORDER_MARK
    //Expected Output: c == 0xA0 || c == reflectedBYTE_ORDER_MARK || Character.getType((char)c) == Character.SPACE_SEPARATOR
    TokenStream instance = new TokenStream(null, sampleString, 0);

    char reflectedBYTE_ORDER_MARK = '\u0000';

    Class secretClass = instance.getClass();
    Field fields[] = secretClass.getDeclaredFields();
    for (int i = 0; i < fields.length; i++){
       if (fields[i].getName() == "BYTE_ORDER_MARK") {
         fields[i].setAccessible(true);
         reflectedBYTE_ORDER_MARK = (Character)fields[i].get(instance);
       }
    }

    int c = reflectedBYTE_ORDER_MARK;
    assertTrue(TokenStream.isJSSpace(c) == (c == 0xA0 || c == reflectedBYTE_ORDER_MARK
                || Character.getType((char)c) == Character.SPACE_SEPARATOR));
  }

  @Test
  public void testIsJSSpace_gb_cis0xA0() throws IllegalAccessException, IllegalArgumentException {
    //Tests if the method returns the boolean for a given int
    //Input: c = 0xA0
    //Expected Output: c == 0xA0 || c == reflectedBYTE_ORDER_MARK || Character.getType((char)c) == Character.SPACE_SEPARATOR
    TokenStream instance = new TokenStream(null, sampleString, 0);

    char reflectedBYTE_ORDER_MARK = '\u0000';

    Class secretClass = instance.getClass();
    Field fields[] = secretClass.getDeclaredFields();
    for (int i = 0; i < fields.length; i++){
       if (fields[i].getName() == "BYTE_ORDER_MARK") {
         fields[i].setAccessible(true);
         reflectedBYTE_ORDER_MARK = (Character)fields[i].get(instance);
       }
    }

    int c = 0xA0;
    assertTrue(TokenStream.isJSSpace(c) == (c == 0xA0 || c == reflectedBYTE_ORDER_MARK
                || Character.getType((char)c) == Character.SPACE_SEPARATOR));
  }

  @Test
  public void testIsJSSpace_gb_getTypec() {
    //Tests if the method returns the boolean for a given int
    //Input: c = Character.SPACE_SEPERATOR
    //Expected Output: c == 0xA0 || c == reflectedBYTE_ORDER_MARK || Character.getType((char)c) == Character.SPACE_SEPARATOR
    assertTrue(TokenStream.isJSSpace(Character.SPACE_SEPARATOR));
  }

  @Test
  //Tests if the method returns to correct decimal value for the string number
  //Input: s = "123", start = 0, radix = 10
  //Output: result = 123.0
  public void testStringToNumber_bb_rad10_start0() {
    Double result = TokenStream.stringToNumber("123",0,10);
    assertTrue(result == 123.0);
  }

  @Test
  //Tests if the method returns to correct decimal value for the string number
  //Input: s = "123", start = 1, radix = 10
  //Output: result = 23.0
  public void testStringToNumber_bb_rad10_start1() {
    Double result = TokenStream.stringToNumber("123",1,10);
    assertTrue(result == 23.0);
  }

  @Test
  //Tests if the method returns to correct decimal value for the string number
  //Input: s = "2322", start = 0, radix = 8
  //Output: result = 1234.0
  public void testStringToNumber_bb_rad8_start0() {
    Double result = TokenStream.stringToNumber("2322",0,8);
    assertTrue(result == 1234.0);
  }

  @Test
  //Tests if the method returns to correct decimal value for the string number
  //Input: s = "2322", start = 0, radix = 10
  //Output: result = 210.0
  public void testStringToNumber_bb_rad8_start1() {
    Double result = TokenStream.stringToNumber("2322",1,8);
    assertTrue(result == 210.0);
  }

  @Test
  //Tests if the method returns to correct decimal value for the string number with digitmax
  //Input: s = "77", start = 0, radix = 8
  //Output: result = 63.0
  public void testStringToNumber_gb_rad8_start0_digitmax() {
    Double result = TokenStream.stringToNumber("77",0,8);
    assertTrue(result == 63.0);
  }

  @Test
  //Tests if the method returns to correct decimal value for the string number
  //Input: s = "10E1", start = 0, radix = 16
  //Output: result = 4321.0
  public void testStringToNumber_bb_rad16_start0() {
    Double result = TokenStream.stringToNumber("10E1",0,16);
    assertTrue(result == 4321.0);
  }

  @Test
  //Tests if the method returns to correct decimal value for the string number
  //Input: s = "10E1", start = 1, radix = 16
  //Output: result = 225.0
  public void testStringToNumber_bb_rad16_start1() {
    Double result = TokenStream.stringToNumber("10E1",1,16);
    assertTrue(result == 225.0);
  }

  @Test
  //Tests if the method returns to correct decimal value for the string number
  //Input: s = "FFFF", start = 0, radix = 16
  //Output: result = 65535.0
  public void testStringToNumber_gb_rad16_start0_boundary() {
    Double result = TokenStream.stringToNumber("FFFF",0,16);
    assertTrue(result == 65535.0);
  }

  @Test
  //Returns a bug
  //Tests if the method returns to correct decimal value for the string number
  //Input: s = "ffff", start = 0, radix = 16
  //Output: result = 65535.0
  public void testStringToNumber_gb_rad16_start0_boundary_up() {
    Double result = TokenStream.stringToNumber("ffff",0,16);
    assertTrue(result == 65535.0);
  }

  @Test
  //Tests if the method returns to correct decimal value for the string number
  //Input: s = "Z", start = 0, radix = 10
  //Output: result = NaN
  public void testStringToNumber_gb_NaN() {
    Double result = TokenStream.stringToNumber("Z",0,10);
    assertTrue(Double.isNaN(result));
  }

  @Test
  //Tests if the method returns to correct decimal value for the string number
  //Input: s = "5", start = 50, radix = 10
  //Output: result = NaN
  public void testStringToNumber_gb_NaN_1() {
    Double result = TokenStream.stringToNumber("5",50,10);
    assertTrue(Double.isNaN(result));
  }
}
