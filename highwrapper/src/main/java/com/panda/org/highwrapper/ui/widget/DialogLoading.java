package com.panda.org.highwrapper.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.panda.org.highwrapper.R;

/**
 * Created by rd0348 on 2017/12/21 0021.
 */

public class DialogLoading extends Dialog {
    private TextView loadingLabel;

    public DialogLoading(Context context) {
        super(context, R.style.Dialog);
        setContentView(R.layout.ui_dialog_loading);
        setCanceledOnTouchOutside(false);
        loadingLabel = (TextView) findViewById(R.id.loading_text);
    }

    public void setDialogLabel(String label) {
        loadingLabel.setText(label);
    }
}
