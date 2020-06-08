package comp3350.MovieGoers.presentation;


import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class InputHelper {

    public static boolean checkNullBox(EditText textBox, String str) {
        boolean result = false;
        if(TextUtils.isEmpty(str)) {
            textBox.setError("This text box cannot be left empty.");
            result = true;
        }
        return result;
    }

    public static ArrayList<EditText> collectInput(EditText... textBox) {
        ArrayList<EditText> editTextList = new ArrayList<EditText>();

        for(EditText e : textBox) {
            editTextList.add(e);
        }

        return editTextList;
    }

    public static boolean checkAllBoxes(ArrayList<EditText> editTextList) {
        boolean result = true;

        for(EditText input : editTextList)
        {
            if(checkNullBox(input, input.getText().toString()))
            {
                result = false;
            }
        }
        return result;
    }

    public static void toastMaker(Activity context, String message){
        Toast t = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 40);
        t.show();
    }
}
