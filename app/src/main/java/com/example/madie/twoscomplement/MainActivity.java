package com.example.madie.twoscomplement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import java.util.*;

public class MainActivity extends AppCompatActivity
{
    private TextView answerTV;
    private EditText inputET;
    private Switch negativeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.answerTV = (TextView)this.findViewById(R.id.answerTV);
        this.inputET = (EditText)this.findViewById(R.id.inputET);
        this.negativeSwitch = (Switch)this.findViewById(R.id.negativeSwitch);
    }

    private String flipTheBits(String bin)
    {
        String answer = "";

        for(int i = 0; i < bin.length(); i++)
        {
            if (bin.charAt(i) == '0')
            {
                answer += '1';
            }
            else
            {
                answer += '0';
            }
        }
        return answer;
    }

    private String addOne(String bin)
    {
        //will return a new String that is the given string with 1 added to it
        if(bin.charAt(bin.length()-1) == 0)
        {
            bin = setChar(bin, 1);
            return bin;
        }

        else
        {
            //convert to decimal, then add one, then convert back to binary

            int tempNum = binToDecimal(bin) + 1;
            return decimalToBin(tempNum);
        }

    }

    private String setChar(String s, int num)
    {
        String answer = "";
        for(int i = 0; i < s.length(); i++)
        {
            if(i == s.length()-1)
            {
                answer += num;
            }

            else
            {
                answer += s.charAt(i);
            }
        }

        return answer;
    }

    // Commented code taken from https://beginnersbook.com/2014/07/java-program-for-binary-to-decimal-conversion/
    private int binToDecimal(String bin)
    {
//        int decimal = 0;
//        int p = 0;
//        while(true)
//        {
//            if(bin == 0)
//            {
//                break;
//            }
//            else {
//                int temp = bin % 10;
//                decimal += temp * Math.pow(2, p);
//                bin = bin / 10;
//                p++;
//            }
//        }
//        return decimal;
        return Integer.parseInt(bin, 2);
    }

    private String decimalToBin(int num)
    {
        return Integer.toBinaryString(num);
    }

    private String encodeAsTwosComplement(String bin)
    {
        String bitsFlipped = this.flipTheBits(bin);
        String oneAdded = this.addOne(bitsFlipped);
        return oneAdded;
    }

    public void onConvertButtonPressed(View v)
    {
        String theBinaryNumber = this.inputET.getText().toString();

        if(this.negativeSwitch.isChecked())
        {
            this.answerTV.setText(this.encodeAsTwosComplement(theBinaryNumber));
        }
        else
        {
            this.answerTV.setText(theBinaryNumber);
        }

    }
}
