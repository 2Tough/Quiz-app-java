package com.geoapp.geoquiz;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity<data> extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;


    private Button mTrueButton;
    private Button mCheatButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;


    private Questions[] mQuestionBank = new Questions[] {

            new Questions(R.string.question_australia, true),
            new Questions(R.string.question_oceans, true),
            new Questions(R.string.question_mideast, false),
            new Questions(R.string.question_africa, false),
            new Questions(R.string.question_americas, true),
            new Questions(R.string.question_asia, true),
    };
    private int mCurrentIndex = 0;
    private boolean mIsCheater;
    private boolean mAlreadyAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);


        mTrueButton = (Button) findViewById(R.id.true_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(true);

                System.out.println("testing true button deactivated");
                //Button gets inactivated but applies for every question unfortunately.
                System.out.println(findViewById(R.id.true_button));
                //findViewById(R.id.true_button).setEnabled(false);
                System.out.println(mQuestionBank[mCurrentIndex].isAnswerTrue());
                System.out.println(findViewById(R.id.true_button));

                mTrueButton.setTag(mCurrentIndex);
                int tag = (int) mTrueButton.getTag();
                System.out.println(tag);
                View buttonTag = mTrueButton.findViewWithTag(tag);
                System.out.println(buttonTag);
                System.out.println("This is the button tag: " + buttonTag);

                if (tag == 0) {
                    System.out.println("This is the button tag for the first question: " + buttonTag);
                    System.out.println("It works. This is question 0.");

                }
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(false);
            }
        });

        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex - 1 ) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start CheatActivity
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(MainActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

        updateQuestion();



    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called and is working");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called and comes second");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called and app is now paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called and app stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (mIsCheater) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.correct_toast;
            }
        }

        mQuestionBank[mCurrentIndex].setAlreadyAnswered(true);

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();


    }
}