package com.jasonwiram.golfscorecard

class Hole(private var mLabel: String?, private var mStrokeCount: Int) {

    fun getmLabel(): String? {
        return mLabel
    }

    fun setmLabel(mLabel: String) {
        this.mLabel = mLabel
    }

    fun getmStrokeCount(): Int {
        return mStrokeCount
    }

    fun setmStrokeCount(mStrokeCount: Int) {
        this.mStrokeCount = mStrokeCount
    }
}
