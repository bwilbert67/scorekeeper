package org.example;

import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        CLIUtil scanner = new CLIUtil();
        List<String> test = scanner.getPlayerNames();
        for(String cur : test) {
            System.out.println(cur);
        }
    }
}
