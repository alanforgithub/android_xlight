package com.umarbhutta.xlightcompanion.control.activity.condition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umarbhutta.xlightcompanion.App;
import com.umarbhutta.xlightcompanion.R;
import com.umarbhutta.xlightcompanion.Tools.Logger;
import com.umarbhutta.xlightcompanion.Tools.ToastUtil;
import com.umarbhutta.xlightcompanion.control.activity.AddControlRuleActivity;
import com.umarbhutta.xlightcompanion.control.activity.dialog.DialogActivity;
import com.umarbhutta.xlightcompanion.control.activity.dialog.DialogTemActivity;
import com.umarbhutta.xlightcompanion.control.adapter.DialogListAdapter;
import com.umarbhutta.xlightcompanion.control.bean.Ruleconditions;
import com.umarbhutta.xlightcompanion.okHttp.model.Condition;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/13.
 * 温度控制设置
 */

public class TemControlActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = TemControlActivity.class.getSimpleName();

    private LinearLayout llBack;
    private TextView btnSure;
    private TextView tvTitle;
    private RelativeLayout llTem,llMore;
    private int requestCode = 310;

    private Condition mCondition;

    private int type;

    private Ruleconditions ruleconditions;

    private TextView tv_tem,tv_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tem);
        //hide nav bar
        getSupportActionBar().hide();
        mCondition = (Condition) getIntent().getBundleExtra("BUNDLE").getSerializable("CONDITION");
        type = getIntent().getBundleExtra("BUNDLE").getInt("TYPE");
        ruleconditions = (Ruleconditions) getIntent().getBundleExtra("BUNDLE").getSerializable("RULECONDITIONS");
        ((App)getApplicationContext()).setActivity(this);
        initViews();
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        tv_tem = (TextView) findViewById(R.id.tv_tem);
        tv_more = (TextView) findViewById(R.id.tv_more);
        llBack = (LinearLayout) findViewById(R.id.ll_back);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSure = (TextView) findViewById(R.id.tvEditSure);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText("温度设置");
        llTem = (RelativeLayout) findViewById(R.id.llTem);
        llTem.setOnClickListener(this);
        llMore = (RelativeLayout) findViewById(R.id.llMore);
        llMore.setOnClickListener(this);
        btnSure.setOnClickListener(this);
    }
    private void onFabPressed(Class activity,int type) {
        Intent intent = new Intent(this, activity);
        Bundle bundle = new Bundle();
        bundle.putSerializable("CONDITION",mCondition);
        bundle.putSerializable("RULECONDITIONS",ruleconditions);
        bundle.putInt("TYPE",type);
        intent.putExtra("BUNDLE",bundle);
        startActivityForResult(intent,100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){ // 8是大于，小于，等于 ，9是温度
            case R.id.tvEditSure:
                if(TextUtils.isEmpty(tv_more.getText().toString())){
                    ToastUtil.showToast(this,"请选择状态");
                    return;
                }
                if(TextUtils.isEmpty(tv_tem.getText().toString())){
                    ToastUtil.showToast(this,"请选择温度");
                    return;
                }
                //确定按钮
                AddControlRuleActivity.mConditionList.add(mCondition);
                ((App)getApplicationContext()).finishActivity();
                break;
            case R.id.llMore:
                requestCode = 313;
                onFabPressed(DialogActivity.class,8);
                break;
            case R.id.llTem:
                requestCode = 314;
                onFabPressed(DialogTemActivity.class,9);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 31:
                mCondition = (Condition) data.getSerializableExtra("MCONDITION");
                if(mCondition.operator.equals(">")) {
                    tv_more.setText("高于");
                }
                if(mCondition.operator.equals("=")) {
                    tv_more.setText("等于");
                }
                if(mCondition.operator.equals("<")) {
                    tv_more.setText("低于");
                }
                Logger.e(TAG,"mCondition="+mCondition.toString());
                break;
            case 32:
                mCondition = (Condition) data.getSerializableExtra("MCONDITION");
                tv_tem.setText(mCondition.rightValue);
                Logger.e(TAG,"mCondition="+mCondition.toString());
                break;
        }
    }
}
