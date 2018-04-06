package com.ebelli.auctions.app.activity

/**
 * Progress Dialog to display during Asynchronous tasks
 */


import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle;
import android.view.*
import com.ebelli.auctions.R


class ProgressDialog(context: Context) : Dialog(context) {


    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        this.setContentView(R.layout.progress_dialog)

        this.setCancelable(false)
    }

    override fun onStart() {
        this.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        this.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.window.setGravity(Gravity.CENTER)
    }
}
