package com.example.mvvmapp.ui.recycleview.diffUtil

import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil


class DiffDemoCallback : DiffUtil.ItemCallback<DiffUtilDemoEntity>() {

    /**
     * 判断是否是同一个item
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    override fun areItemsTheSame(@NonNull oldItem: DiffUtilDemoEntity, @NonNull newItem: DiffUtilDemoEntity): Boolean {
        return oldItem.id === newItem.id
    }

    /**
     * 当是同一个item时，再判断内容是否发生改变
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    override fun areContentsTheSame(@NonNull oldItem: DiffUtilDemoEntity, @NonNull newItem: DiffUtilDemoEntity): Boolean {
        return (oldItem.id.equals(newItem.id)
                && oldItem.content.equals(newItem.content)
                && oldItem.date.equals(newItem.date))
    }

    /**
     * 可选实现
     * 如果需要精确修改某一个view中的内容，请实现此方法。
     * 如果不实现此方法，或者返回null，将会直接刷新整个item。
     *
     * @param oldItem Old data
     * @param newItem New data
     * @return Payload info. if return null, the entire item will be refreshed.
     */
    override fun getChangePayload(@NonNull oldItem: DiffUtilDemoEntity, @NonNull newItem: DiffUtilDemoEntity): Any? {
        return null
    }
}

/** 使用  **/
// 设置Diff Callback
// 只需要设置一次就行了！建议初始化Adapter的时候就设置好。
//mAdapter.setDiffCallback(new DiffDemoCallback());


/**  Diff 变化数据 **/
//// 获取新数据
//List<DiffUtilDemoEntity> newData = getNewList();
//// 设置diff数据（默认就为异步Diff，不需要担心卡顿）
//mAdapter.setDiffNewData(newData);
//
//// 第二次改变数据
//mAdapter.setDiffNewData(newData2);


/** 目前提供自定义线程 **/
//首先还是需要实现DiffUtil.ItemCallback.
//
//BrvahAsyncDifferConfig config =  new BrvahAsyncDifferConfig.Builder(new DiffDemoCallback())
//.setMainThreadExecutor(你的主线程)
//.setBackgroundThreadExecutor(你的工作线程)
//.build();
//// 设置配置
//mAdapter.setDiffConfig(config);
//
//// 设置diff数据
//mAdapter.setDiffNewData(newData);