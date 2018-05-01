package com.example.max.myroulette;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    ImageView iv_ruletka;
    Button button3;
    Button button4;
    Button button5;
    Button reFill;
    TextView score;
    ImageView oval;
    MediaPlayer mySong;
    Random r;

    int degree = 0, degree_old = 0;
    private static final float FACTOR = 4.86f; //because there is 37 sectors on the whell
    String reFillScore = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);
        iv_ruletka = (ImageView) findViewById(R.id.iv_ruletka);
        button3 = (Button) findViewById(R.id.button);
        button4 = (Button) findViewById(R.id.button3);
        button5 = (Button) findViewById(R.id.button4);
        reFill = (Button) findViewById(R.id.button5);
        score = (TextView) findViewById(R.id.textView6);
        oval = (ImageView) findViewById(R.id.oval);

        mySong = MediaPlayer.create(MainActivity.this,R.raw.song);
        score.setText("0");
        r = new Random();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                degree_old = degree % 360;
                degree = r.nextInt(3600) + 720;
                RotateAnimation rotate = new RotateAnimation(degree_old,degree, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(3600); //How long this animation must be?
                rotate.setFillAfter(true); //If fillAfter is value true, the transformation that this animation performs will persist when it is completed.
                rotate.setInterpolator(new DecelerateInterpolator()); //Sets the acceleration curve for this animation.
                rotate.setAnimationListener(new Animation.AnimationListener() { //Binds the animation to this animation.
                    @Override
                    public void onAnimationStart(Animation animation) {
                        textView.setText("");
                    }
                    @SuppressLint("Range")
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        String color = currentNumber(360-(degree % 360));
                        textView.setText(color);
                        textView.setTextColor(Color.parseColor(color));
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                        textView.setTypeface(null, Typeface.ITALIC);

                       if (button3.isActivated()) {
                           if (Color.parseColor(color) == Color.RED) {
                               score.setText(String.valueOf((Integer.parseInt(reFillScore) * 2)));
                               reFillScore = String.valueOf((Integer.parseInt(reFillScore) * 2));
                               mySong.start();

                            } else {
                               score.setText("0");
                               reFillScore = "0";
                           }
                        }

                        if (button4.isActivated()) {
                            if (Color.parseColor(color) == Color.BLACK) {
                                score.setText(String.valueOf((Integer.parseInt(reFillScore) * 2)));
                                reFillScore = (String.valueOf((Integer.parseInt(reFillScore) * 2)));
                                mySong.start();
                            } else {
                                score.setText("0");
                                reFillScore = "0";
                            }
                        }

                        if (button5.isActivated()) {
                            if (Color.parseColor(color) == Color.GREEN) {
                                score.setText(String.valueOf((Integer.parseInt(reFillScore) * 15)));
                                reFillScore = (String.valueOf((Integer.parseInt(reFillScore) * 15)));
                                mySong.start();
                            } else {
                                score.setText("0");
                                reFillScore = "0";
                            }
                        }
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
                iv_ruletka.startAnimation(rotate);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oval.setColorFilter(Color.RED);
                button3.setActivated(true);

                if (button4.isActivated() | button5.isActivated()) {
                    button4.setActivated(false);
                    button5.setActivated(false);
                }


            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oval.setColorFilter(Color.BLACK);
                button4.setActivated(true);

                if (button3.isActivated() | button5.isActivated()) {
                    button3.setActivated(false);
                    button5.setActivated(false);
                }

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oval.setColorFilter(Color.GREEN);
                button5.setActivated(true);

                if (button3.isActivated() | button4.isActivated()) {
                    button3.setActivated(false);
                    button4.setActivated(false);
                }
            }
        });

        reFill.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if ((score.getText() == "0") || (score.getText() == "20")) {
                    score.setText("20");
                    reFillScore = "20";
                }
            }
        });
    }


    private String currentNumber(int degrees) {
        String text = "";

        if(degrees >= (FACTOR * 1) && degrees < (FACTOR * 3)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 3) && degrees < (FACTOR * 5)) {
            text = "BLACK";

        }

        if(degrees >= (FACTOR * 5) && degrees < (FACTOR * 7)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 7) && degrees < (FACTOR * 9)) {
            text = "BLACK";

        }

        if(degrees >= (FACTOR * 9) && degrees < (FACTOR * 11)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 11) && degrees < (FACTOR * 13)) {
            text = "BLACK";
        }

        if(degrees >= (FACTOR * 13) && degrees < (FACTOR * 15)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 15) && degrees < (FACTOR * 17)) {
            text = "BLACK";
        }

        if(degrees >= (FACTOR * 17) && degrees < (FACTOR * 19)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 19) && degrees < (FACTOR * 21)) {
            text = "BLACK";
        }

        if(degrees >= (FACTOR * 21) && degrees < (FACTOR * 23)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 23) && degrees < (FACTOR * 25)) {
            text = "BLACK";
        }

        if(degrees >= (FACTOR * 25) && degrees < (FACTOR * 27)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 27) && degrees < (FACTOR * 29)) {
            text = "BLACK";
        }

        if(degrees >= (FACTOR * 29) && degrees < (FACTOR * 31)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 31) && degrees < (FACTOR * 33)) {
            text = "BLACK";
        }

        if(degrees >= (FACTOR * 33) && degrees < (FACTOR * 35)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 35) && degrees < (FACTOR * 37)) {
            text = "BLACK";
        }

        if(degrees >= (FACTOR * 37) && degrees < (FACTOR * 39)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 39) && degrees < (FACTOR * 41)) {
            text = "BLACK";
        }

        if(degrees >= (FACTOR * 41) && degrees < (FACTOR * 43)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 43) && degrees < (FACTOR * 45)) {
            text = "BLACK";
        }

        if(degrees >= (FACTOR * 45) && degrees < (FACTOR * 47)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 47) && degrees < (FACTOR * 49)) {
            text = "BLACK";
        }

        if(degrees >= (FACTOR * 49) && degrees < (FACTOR * 51)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 51) && degrees < (FACTOR * 53)) {
            text = "BLACK";
        }

        if(degrees >= (FACTOR * 53) && degrees < (FACTOR * 55)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 55) && degrees < (FACTOR * 57)) {
            text = "BLACK";
        }

        if(degrees >= (FACTOR * 57) && degrees < (FACTOR * 59)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 59) && degrees < (FACTOR * 61)) {
            text = "BLACK";
        }

        if(degrees >= (FACTOR * 61) && degrees < (FACTOR * 63)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 63) && degrees < (FACTOR * 65)) {
            text = "BLACK";
        }

        if(degrees >= (FACTOR * 65) && degrees < (FACTOR * 67)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 67) && degrees < (FACTOR * 69)) {
            text = "BLACK";
        }

        if(degrees >= (FACTOR * 69) && degrees < (FACTOR * 71)) {
            text = "RED";
        }

        if(degrees >= (FACTOR * 71) && degrees < (FACTOR * 73)) {
            text = "BLACK";
        }

        if (((degrees >= (FACTOR * 73) && degrees < 360) || (degrees) >= 0 && degrees < (FACTOR * 1))) {
            text = "GREEN";
        }
        return text;
    }
}
