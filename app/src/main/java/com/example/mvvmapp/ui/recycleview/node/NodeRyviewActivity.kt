package com.example.mvvmapp.ui.recycleview.node

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mvvmapp.BR
import com.example.mvvmapp.databinding.ActivityRyviewNewBinding
import com.example.mvvmapp.ui.NullBaseViewModel
import com.example.mvvmapp.ui.recycleview.been.MultiItemViewModel
import com.example.mvvmapp.ui.recycleview.node.been.FirstNodeBeen
import com.example.mvvmapp.ui.recycleview.node.been.FootNodeBeen
import com.example.mvvmapp.ui.recycleview.node.been.SecondNodeBeen
import com.example.mvvmapp.ui.recycleview.node.been.ThreeNodeBeen
import com.example.mvvmapp.ui.recycleview.node.nodeProvider.RootNodeProvider
import me.goldze.mvvmhabit.base.BaseActivity
import me.goldze.mvvmhabit.base.BaseViewModel
import com.trello.rxlifecycle2.RxLifecycle.bindUntilEvent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import com.example.mvvmapp.R


class NodeRyviewActivity : BaseActivity<ActivityRyviewNewBinding, NullBaseViewModel>() {

    val dataList = ArrayList<BaseNode>()
    val adapter = NodeAdapter()

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_ryview_new
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initData() {
        super.initData()
        binding.recycleview.apply {
            layoutManager = LinearLayoutManager(this@NodeRyviewActivity)
            layoutManager = GridLayoutManager(this@NodeRyviewActivity, 4).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (adapter?.getItemViewType(position)) {
                            FirstNodeBeen.VIEW_TYPE -> 4
                            SecondNodeBeen.VIEW_TYPE -> 4
                            ThreeNodeBeen.VIEW_TYPE -> 2
                            FootNodeBeen.VIEW_TYPE -> 4
                            else -> 4
                        }
                    }
                }
            }
            adapter = this@NodeRyviewActivity.adapter
            setHasFixedSize(true)
        }

        for (number1 in 1..20){//一级
            val secondMultiList = mutableListOf<BaseNode>()
            val firstNodeBeen = FirstNodeBeen(secondMultiList).setContentOb("一级：${number1}");
            dataList.add(firstNodeBeen)
            for (number2 in 1..3){ //二级
                val threeNodeMultiList = mutableListOf<BaseNode>()
                val secondNodeBeen = SecondNodeBeen(threeNodeMultiList).setContentOb("二级：${number2}")
                secondMultiList.add(secondNodeBeen)

                for (number2 in 1..6) { //三级
                    val threeNodeBeen = ThreeNodeBeen().setContentOb("三级：${number1}");
                    threeNodeMultiList.add(threeNodeBeen)
                }
            }

        }

        dataList.add(FootNodeBeen())
        adapter.setNewData(dataList)

        adapter.setOnItemClickListener(null)

//        binding.recycleview?.adapter?.notifyDataSetChanged();

    }

    fun otherFunction(){
        //查找父节点
//        adapter.findParentNode(0)
//        adapter.findParentNode(BaseNode())
        //

        //指定父节点增删改查
        val footNodeBeen = FootNodeBeen();
        adapter.nodeAddData(footNodeBeen,footNodeBeen)
//        adapter.nodeAddData(footNodeBeen,childIndex = 1,footNodeBeen)

        //删除子node
//        adapter.nodeReplaceChildData()


        //替换指定位置的数据
//        adapter.nodeSetData(footNodeBeen,childIndex = 1,footNodeBeen)

        //替换所有的子view
//        adapter.nodeReplaceChildData(footNodeBeen, footNodeBeen)
    }

}