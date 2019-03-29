package com.infobite.niramayahospital;

import java.util.regex.Pattern;

public class RegexPatternDemo {

    public static void main(String args[]){
        //System.out.println(Pattern.matches("[A-Z]{2}[-]{1}[0-1]{2}[-]{1}[A-Z]{2}[-]{1}[A-Z]{4}", "NH-01-AC-1001"));
        /*System.out.println(Pattern.matches("[A-Z]{2}", "NH"));
        System.out.println(Pattern.matches("[A-Z]{2}[0-9]{2}", "NH21"));
        System.out.println(Pattern.matches("[A-Z]{2}[-]{1}[0-9]{2}", "NH-21"));
        System.out.println(Pattern.matches("[A-Z]{2}[-]{1}[0-9]{2}[-]{1}[A-Z]{2}", "NH-21-AC"));

        //Regex for the middle numeric character at least two or more than two times
        System.out.println(Pattern.matches("[A-Z]{2}[-]{1}[0-9]{2,}+[-]{1}[A-Z]{2}", "NH-02-AC"));*/

        //Regex for the middle numeric character at least two or more than two times and the last numeric char at least four times
        System.out.println(Pattern.matches("[A-Z]{2}[-]{1}[0-9]{2,}[-]{1}[A-Z]{2}[-]{1}[0-9]{4,}", "NH-01-AC-0920"));
    }

    private boolean validateUserId(String userId){
        return Pattern.matches("[A-Z]{2}[-]{1}[0-9]{2,}[-]{1}[A-Z]{2}[-]{1}[0-9]{4,}", userId);
    }

    //NH-01-AC-1001

}
