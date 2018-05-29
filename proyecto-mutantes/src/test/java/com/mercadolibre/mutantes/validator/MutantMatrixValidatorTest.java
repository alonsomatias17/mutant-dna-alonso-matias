package com.mercadolibre.mutantes.validator;

import com.mercadolibre.mutantes.exception.BadDataException;
import com.mercadolibre.mutantes.exception.InvalidDataException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class MutantMatrixValidatorTest {

    @Test(expected = InvalidDataException.class)
    public void validateMatrixMinLengthTest(){
        MutantMatrixValidator.validateDnaMatrixLength(this.invalidSizeArray());
    }

    @Test(expected = InvalidDataException.class)
    public void validateNxNMatrixTest(){
        MutantMatrixValidator.validateDnaMatrixLength(this.notNxNMatrix());
    }

    @Test(expected = InvalidDataException.class)
    public void validateRowsEqualsLengthTest(){
        MutantMatrixValidator.validateDnaMatrixLength(this.unequalRowsLength());
    }

    @Test(expected = BadDataException.class)
    public void verifyDataBadDataExceptionTest(){
        MutantMatrixValidator.verifyData("K");
    }

    @Test
    public void verifyDataTest(){
        Assert.assertTrue(MutantMatrixValidator.verifyData("A"));
    }

    private String[] unequalRowsLength() {
        String[] dna = {"ATGCA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        return dna;
    }

    private String[] notNxNMatrix() {
        String[] dna = {"TTGCGA","CAGTAC","TTATGT"};
        return dna;
    }

    private String[] invalidSizeArray() {
        String[] dna = {"TTG","CAG","TTA"};
        return dna;
    }

}
