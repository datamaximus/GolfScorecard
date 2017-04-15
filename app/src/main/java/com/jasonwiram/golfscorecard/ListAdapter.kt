package com.jasonwiram.golfscorecard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class ListAdapter(private val mContext: Context, private val mHoles: Array<Hole?>) : BaseAdapter() {

    override fun getCount(): Int {
        return mHoles.size
    }

    override fun getItem(i: Int): Hole? {
        return mHoles[i]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view
        val holder: ViewHolder

        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item, null)

            holder = ViewHolder()
            holder.holeLabel = view!!.findViewById(R.id.holeLabel) as TextView
            holder.strokeCount = view.findViewById(R.id.strokeCount) as TextView
            holder.removeStrokeButton = view.findViewById(R.id.removeStrokeButton) as Button
            holder.addStrokeButton = view.findViewById(R.id.addStrokeButton) as Button

            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        holder.holeLabel!!.text = mHoles[i]!!.getmLabel()
        holder.strokeCount!!.text = mHoles[i]!!.getmStrokeCount().toString() + ""
        holder.removeStrokeButton!!.setOnClickListener {
            var updatedStrokeCount = mHoles[i]!!.getmStrokeCount() - 1
            if (updatedStrokeCount < 0) updatedStrokeCount = 0
            mHoles[i]!!.setmStrokeCount(updatedStrokeCount)
            holder.strokeCount!!.text = updatedStrokeCount.toString() + ""
        }
        holder.addStrokeButton!!.setOnClickListener {
            val updatedStrokeCount = mHoles[i]!!.getmStrokeCount() + 1
            mHoles[i]!!.setmStrokeCount(updatedStrokeCount)
            holder.strokeCount!!.text = updatedStrokeCount.toString() + ""
        }

        return view
    }

    private class ViewHolder {
        internal var holeLabel: TextView? = null
        internal var strokeCount: TextView? = null
        internal var removeStrokeButton: Button? = null
        internal var addStrokeButton: Button? = null
    }
}
