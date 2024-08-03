import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    @Test
    //Testing a short string for combination
    public void testCombinationSmallString() {
        String str1 = "ok";
        String str2 = "no";
        int overlap = 1;
        String expectedString = str1.substring(0, str1.length() - overlap)
                + str2;
        StringReassembly2.combination(str1, str2, overlap);
        assertEquals(expectedString, str1, str2);
    }

    @Test
    //testing a longer string for combination
    public void testCombinationLongString() {
        String str1 = "there's always tomorrow";
        String str2 = "never say never";
        int overlap = 1;
        String expectedString = str1.substring(0, str1.length() - overlap)
                + str2;
        StringReassembly2.combination(str1, str2, overlap);
        assertEquals(expectedString, str1, str2);
    }

    @Test
    //testing a bigger number for overlap
    public void testCombinationBigOverlap() {
        String str1 = "tomorrow";
        String str2 = "never";
        int overlap = 4;
        String expectedString = str1.substring(0, str1.length() - overlap)
                + str2;
        StringReassembly2.combination(str1, str2, overlap);
        assertEquals(expectedString, str1, str2);
    }

    @Test
    //testing to see if it'll avoid the overlapping strings
    public void testAddToSetAvoidingSubstrings1() {
        Set<String> strSet = new Set1L<>();
        strSet.add("tomorrow");
        strSet.add("there");
        strSet.add("storm");
        Set<String> strSet2 = new Set1L<>();
        strSet2.add("tomorrow");
        strSet2.add("there");
        strSet2.add("storm");
        String str = "nothing";
        strSet2.add(str);
        StringReassembly2.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSet, strSet2);
    }

    @Test
    //Testing to see if it'll avoid the substrings even though it's the
    //same set.
    public void testAddToSetAvoidingSubstrings2() {
        Set<String> strSet = new Set1L<>();
        strSet.add("tomorrow");
        strSet.add("there");
        strSet.add("storm");
        Set<String> strSet2 = new Set1L<>();
        strSet2.add("tomorrow");
        strSet2.add("there");
        strSet2.add("storm");
        String str = "a";
        StringReassembly2.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSet, strSet2);
    }

    @Test
    //Testing the individual lines and if it's a substring of another set
    //this is harder because it's from the file.
    public void testLinesFromInput() {
        Set<String> strSet = new Set1L<>();
        strSet.add("Go Bucks");
        strSet.add("Beat Mich --");
        strSet.add("-- igan");

        SimpleReader input = new SimpleReader1L("data/cheer-8-2.txt");
        Set<String> strSet2 = StringReassembly2.linesFromInput(input);
        input.close();
        assertEquals(strSet, strSet2);
    }

    @Test
    //Testing the individual lines and if it's a substring of another set
    //this is harder because it doesn't exist in the file
    public void testLinesFromInput2() {
        Set<String> strSet = new Set1L<>();
        strSet.add("No --");
        strSet.add("There's --");
        strSet.add(" no such - thing");

        SimpleReader input = new SimpleReader1L("data/cheer-8-2.txt");
        Set<String> strSet2 = StringReassembly2.linesFromInput(input);
        input.close();
        assertEquals(strSet, strSet2);
    }

    @Test
    //testing if the squiggly will be replaced with a space with just
    //one squiggly
    public void testPrintWithLineSeparators() {
        SimpleWriter out = new SimpleWriter1L();
        String str = "There is~nothing I wouldn't do for you";
        StringReassembly2.printWithLineSeparators(str, out);
        out.close();
    }

    @Test
    //testing if the squiggly will be replaced with a space w/ multiple
    //squiggly lines
    public void testPrintWithLineSeparators2() {
        SimpleWriter out = new SimpleWriter1L();
        String str = "School ~ is getting ~ way ~ harder than ~ I thought";
        StringReassembly2.printWithLineSeparators(str, out);
        out.close();
    }

}
