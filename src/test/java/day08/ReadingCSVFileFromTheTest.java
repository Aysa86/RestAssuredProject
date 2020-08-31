package day08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadingCSVFileFromTheTest {
    // this is to specify this test is now parameterized test
    @ParameterizedTest
    // this line is to specify, we are using csv as source
    // and provide the path for csv file under resources folder
    // and we skipped the first line which is the header in our case
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void simpleRead(int num1, int num2){

        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);

    }

    // Please add another csv file called numbers.csv
// num1,num2, additionResult
// 5,4,9
// 4,7,11
// 3,8,11
// 6,10,16
/// Please add a @ParameterizedTest
/// specify the file source as numbers.csv
///  in the meantime add 3rd parameter to your test called int result
/// assert that num1 + num2  = result

    @ParameterizedTest(name = "Iteration {index} -> {0} + {1} = {2} ")
    @CsvFileSource(resources = "/numbers.csv", numLinesToSkip = 1)
    public void ReadingCSVFile(int num1, int num2, int result){

        assertEquals(result, num1 + num2, "Something went WRONG");
    }

    // What if I want some custom name
    // @ParameterizedTest(name = "Some custom name here")
    // How do I refer the row number in my csv file
       // you can refer row number using {index} in the name String
    // How do I refer the column data in my display name
       // // in the name String we can add {yourColumnIndexHere}
    //    // {0} for first column
    //    // {1} for second column
    //    // {2} for third column
    // WHERE DO I ADD the display name to start with ?  add it to @ParameterizedTest (name = " here")
    // we want display like this so we know exactly what we tested in each iteration
    // Row1 : 5+4=9
    // Row2 : 4+7=1



    @ParameterizedTest
    @CsvFileSource(resources = "/numbers.csv", numLinesToSkip = 1)
    public void testAdditional(int num1, int num2, int result){

        assertEquals(result, num1 + num2, "Something went WRONG");
    }


}
