package com.basit.additioncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.basit.additioncalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(binding.tvmain.getText()+"1");
            }
        });
        binding.b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(binding.tvmain.getText()+"2");
            }
        });
        binding.b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(binding.tvmain.getText()+"3");
            }
        });
        binding.b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(binding.tvmain.getText()+"4");
            }
        });
        binding.b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(binding.tvmain.getText()+"5");
            }
        });
        binding.b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(binding.tvmain.getText()+"6");
            }
        });
        binding.b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(binding.tvmain.getText()+"7");
            }
        });
        binding.b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(binding.tvmain.getText()+"8");
            }
        });
        binding.b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(binding.tvmain.getText()+"9");
            }
        });
        binding.b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(binding.tvmain.getText()+"0");
            }
        });

        binding.bdot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(   binding.tvmain.getText()+".");
            }
        });
        binding.bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText("");
                binding.tvsec.setText("");
            }
        });


        binding.bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.tvmain != null) {
                    String val = binding.tvmain.getText().toString();
                    if (!val.isEmpty()) {
                        val = val.substring(0, val.length() - 1);
                        binding.tvmain.setText(val);
                    }
                }
            }
        });

        binding.bplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(binding.tvmain.getText()+"+");
            }
        });
        binding.bmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(binding.tvmain.getText()+"-");
            }
        });
        binding.bmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(binding.tvmain.getText()+"×");
            }
        });
        binding.bdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(binding.tvmain.getText()+"÷");
            }
        });

        binding.bdot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(binding.tvmain.getText()+".");
            }
        });


        binding.bb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(   binding.tvmain.getText()+"(");
            }
        });
        binding.bb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvmain.setText(   binding.tvmain.getText()+")");
            }
        });


        binding.bequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val =  binding.tvmain.getText().toString();
                String replacedstr = val.replace('÷','/').replace('×','*');
                double result = eval(replacedstr);
                binding.tvmain.setText(String.valueOf(result));
                binding.tvsec.setText(val);
            }
        });

    }
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
                // Skip any unsupported characters
                while (ch != -1 && !isValidCharacter(ch)) {
                    ch = (++pos < str.length()) ? str.charAt(pos) : -1;
                }
            }

            // Helper method to check if a character is valid for the expression
            private boolean isValidCharacter(int ch) {
                return (ch >= '0' && ch <= '9') || ch == '.' || ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')';
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x;
                if (ch == '.') {
                    // If the input starts with a decimal point, prepend "0" to make it a valid number
                    x = 0;
                } else {
                    x = parseExpression();
                }

                // Check if the expression ends with an operator
                if (ch != -1 && ch != ')' && ch != '+' && ch != '-' && ch != '*' && ch != '/') {
                    throw new RuntimeException("Invalid expression: Operator expected");
                }

                if (pos < str.length()) {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                return x;
            }



            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }
            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus (negation)

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    boolean hasDecimalPoint = false;
                    while ((ch >= '0' && ch <= '9') || (!hasDecimalPoint && ch == '.')) {
                        if (ch == '.') {
                            hasDecimalPoint = true;
                        }
                        nextChar();
                    }
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    // Handle unexpected character
                    // You can either return a special value or log a message and continue
                    // For example, you can return Double.NaN to indicate an error
                    x = Double.NaN;
                    // or log a message
                    Log.e("Calculator", "Unexpected character: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }


        }.parse();
    }



}