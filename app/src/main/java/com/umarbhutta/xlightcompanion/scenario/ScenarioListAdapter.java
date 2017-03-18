package com.umarbhutta.xlightcompanion.scenario;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umarbhutta.xlightcompanion.R;
import com.umarbhutta.xlightcompanion.okHttp.model.Rows;
import com.umarbhutta.xlightcompanion.okHttp.model.SceneListResult;

/**
 * Created by Umar Bhutta.
 */
public class ScenarioListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private SceneListResult mSceneListResult;


    public ScenarioListAdapter(Context context) {
        this.mSceneListResult = new SceneListResult();
        this.mContext = context;
    }

    public ScenarioListAdapter(Context context, SceneListResult mSceneListResult) {
        this.mSceneListResult = mSceneListResult;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scenario_list_item, parent, false);
        return new ScenarioListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ScenarioListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mSceneListResult.rows.size();
    }

    private class ScenarioListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView scenarioIndex, scenarioTitle, scenarioDescription;
        private ImageView scenarioDelete;

        private ImageView scenarioIcon;
        private TextView scenarioText;
        private int mPosition;
        private Rows infos;

        public ScenarioListViewHolder(View itemView) {
            super(itemView);
            scenarioIndex = (TextView) itemView.findViewById(R.id.scenarioIndex);
            scenarioTitle = (TextView) itemView.findViewById(R.id.scenarioTitle);
            scenarioDescription = (TextView) itemView.findViewById(R.id.scenarioDescription);
            scenarioDelete = (ImageView) itemView.findViewById(R.id.scenarioDelete);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            scenarioDelete.setOnClickListener(this);

            scenarioIcon = (ImageView) itemView.findViewById(R.id.icon_scenario);
            scenarioText = (TextView) itemView.findViewById(R.id.text_scenario);
        }

        public void bindView(int position) {

            mPosition = position;
            infos = mSceneListResult.rows.get(position);

//            int displayNum = position + 1;
//            scenarioIndex.setText(displayNum + "");
//            scenarioTitle.setText(ScenarioFragment.name.get(position));
//            scenarioDescription.setText(ScenarioFragment.info.get(position));
            scenarioText.setText(infos.scenarioname);
//            switch (position) {
//                case 0:
//                    scenarioIcon.setImageResource(R.drawable.icon_book);
//                    break;
//                case 1:
//                    scenarioIcon.setImageResource(R.drawable.icon_tv);
//                    scenarioText.setText("看电视");
//                    break;
//                case 2:
//                    scenarioText.setText("睡觉");
//                    break;
//                default:
//                    break;
//            }

            //如果设置了回调，就设置点击事件
//            if (mOnItemClickListener != null){
//                itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mOnItemClickListener.onItemClick(itemView,position);
//                    }
//                });
//            }
//            if (mOnItemLongClickListener != null){
//                itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mOnItemLongClickListener.onItemLongClick(itemView,position);
//                    }
//                });
//            }

        }

        @Override
        public void onClick(View v) {
            onFabPressed(v, infos);
        }

        @Override
        public boolean onLongClick(View view) {
            Toast.makeText(mContext, "删除", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private void onFabPressed(View view, Rows infos) {
        Intent intent = new Intent(mContext, AddScenarioNewActivity.class);
        intent.putExtra("from", "list");
        intent.putExtra("infos", infos);
        mContext.startActivity(intent);
    }
//    /** * ItemClick的回调接口 */
//    public interface OnItemClickListener{
//        void onItemClick(View view,int position);
//    }
//
//    private ScenarioListAdapter.OnItemClickListener mOnItemClickListener;
//
//    public void setmOnItemClickListener(ScenarioListAdapter.OnItemClickListener mOnItemClickListener){
//        this.mOnItemClickListener = mOnItemClickListener;
//    }
//    /** * ItemLongClick的回调接口 */
//    public interface OnItemLongClickListener{
//        void onItemLongClick(View view,int position);
//    }
//
//    private ScenarioListAdapter.OnItemLongClickListener mOnItemLongClickListener;
//
//    public void setmOnItemLongClickListener(ScenarioListAdapter.OnItemLongClickListener mOnItemLongClickListener){
//        this.mOnItemLongClickListener = mOnItemLongClickListener;
//    }
}
