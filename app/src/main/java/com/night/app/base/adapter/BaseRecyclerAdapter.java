package com.night.app.base.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder> {

    private OnItemClickListener<T>                     onItemClickListener;

    private BaseRecyclerAdapter.OnItemTouchListener<T> onItemTouchListener;

    // 上下文环境
    public Context                                     context;

    // 数据集合
    private List<T>                                    data;

    /**
     * 刷新数据
     *
     * @param data
     */
    public void refresh(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void append(List<T> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public BaseRecyclerAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getItemLayoutId(), parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerAdapter.BaseViewHolder holder, int position) {
        T t = data.get(position);
        holder.setT(t);
        holder.setPosition(position);
        bindData(holder, t);

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemTouchListener(BaseRecyclerAdapter.OnItemTouchListener<T> onItemTouchListener) {
        this.onItemTouchListener = onItemTouchListener;
    }

    /**
     * 将数据绑定到itemView视图上
     *
     * @param holder
     * @param t
     */
    public abstract void bindData(BaseViewHolder holder, T t);

    /**
     * 子类通过重写此方法设置itemView项的布局视图
     *
     * @return
     */
    public abstract int getItemLayoutId();

    /**
     * 封装 ViewHolder
     */
    public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnTouchListener {

        // itemView 中的控件缓存在这里
        private SparseArray<View> views;

        // 数据
        private T                 t;

        // 位置
        private int               position;

        public BaseViewHolder(View itemView) {
            super(itemView);
            views = new SparseArray<>();
        }

        public void setT(T t) {
            this.t = t;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getDataPosition() {
            return position;
        }

        /**
         * 获取 itemView 中的控件
         *
         * @param id
         * @return
         */
        public View getView(int id) {
            View view = views.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                views.put(id, view);
                view.setOnClickListener(this);
                view.setOnTouchListener(this);
            }
            return view;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, t, position);
            }
        }

        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            if (onItemTouchListener != null) {
                onItemTouchListener.onItemTouch(v, t, position,motionEvent);
            }
            if (motionEvent.getAction() == MotionEvent.ACTION_HOVER_MOVE) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 回调接口
     * @param <T>
     */
    public interface OnItemClickListener<T> {

        void onItemClick(View view, T t, int position);
    }

    public interface OnItemTouchListener<T> {
        void onItemTouch(View view, T t, int position,MotionEvent motionEvent);
    }




}
