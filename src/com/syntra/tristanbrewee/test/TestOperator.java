package com.syntra.tristanbrewee.test;

import com.syntra.tristanbrewee.model.Operator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class TestOperator {

    private static Random random;

    @BeforeEach
    public void setUp(){
        random = new Random();
    }

    @AfterEach
    public void tearDown(){
        random = null;
    }

    //GetOperator
    @Test
    public void  getOperatorAdd(){
        assertEquals(Operator.getOperator("+"), Operator.ADD);
    }

    @Test
    public void getOperatorSubtract(){
        assertEquals(Operator.getOperator("-"), Operator.SUBTRACT);
    }

    @Test
    public void getOperatorMultiply(){
        assertEquals(Operator.getOperator("*"), Operator.MULTIPLY);
    }

    @Test
    public void getOperatorDivide(){
        assertEquals(Operator.getOperator("/"), Operator.DIVIDE);
    }

    @Test
    public void getOperatorPower(){
        assertEquals(Operator.getOperator("^"), Operator.POWER);
    }

    @Test
    public void getOperatorInvalid(){
        assertEquals(Operator.getOperator("something different"), null);
    }

    //getRandomOperator
    @Test
    public void getRandomOperator(){
        for (int i = 0; i < 100; i++){
            Operator operator = Operator.getRandomOperator(random);
            assertTrue(operator instanceof Operator);
        }
    }

    //ToString
    @Test
    public void toStringAdd(){
        assertEquals(Operator.ADD.toString(), "+");
    }

    @Test
    public void toStringSubtract(){
        assertEquals(Operator.SUBTRACT.toString(), "-");
    }

    @Test
    public void toStringMultiply(){
        assertEquals(Operator.MULTIPLY.toString(), "*");
    }

    @Test
    public void toStringDivide(){
        assertEquals(Operator.DIVIDE.toString(), "/");
    }

    @Test
    public void toStringPower(){
        assertEquals(Operator.POWER.toString(), "^");
    }
}
