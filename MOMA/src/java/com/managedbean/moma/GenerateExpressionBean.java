/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedbean.moma;

import java.util.Random;

/**
 *
 * @author Administrator
 */
public class GenerateExpressionBean {
    private String exp;
    private int key;
    public static final String CODE_EXPRESSION_VALUE = "codeValue";
    public GenerateExpressionBean(){
   
    }
    private void generate(){
        Random random = new Random(System.currentTimeMillis());
        int a = random.nextInt(9) + 1;
        int b = random.nextInt(9) + 1;
        int temp = random.nextInt(3) + 1;
        switch(temp){
            case 1:
                exp = a + "+" + b + "=";
                key = a + b;
                break;
            case 2:
                exp = a + "-" + b + "=";
                key = a - b;
                break;
            default:
                exp = a + "*" + b + "=";
                key = a * b;
                break;
        }
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
    
    
    
}
