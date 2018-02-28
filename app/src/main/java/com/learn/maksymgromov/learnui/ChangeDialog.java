package com.learn.maksymgromov.learnui;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeDialog extends Dialog implements View.OnClickListener {
    @BindView(R.id.cancel_action) ImageView imageViewCancel;

    @BindView(R.id.model) EditText editTextModel;
    @BindView(R.id.series) EditText editTextSeries;

    @BindView(R.id.ok) Button buttonOk;

    private OkCallBack okCallBack;

    public ChangeDialog(@NonNull Context context, OkCallBack callBack) {
        super(context);

        this.setContentView(R.layout.change_dialog);
        ButterKnife.bind(this);

        imageViewCancel.setOnClickListener(v -> dismiss());

        okCallBack = callBack;
        buttonOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        okCallBack.onOK(editTextModel.getText().toString(),editTextSeries.getText().toString());
        dismiss();
    }

    public interface OkCallBack {
        void onOK(String model, String series);
    }
}
