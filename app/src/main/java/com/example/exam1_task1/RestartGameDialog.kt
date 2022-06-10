package com.example.exam1_task1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class RestartGameDialog : DialogFragment(R.layout.dialog_custom) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if((activity as MainActivity).whoWon==1){
            view.findViewById<TextView>(R.id.whoWon).text = "Выиграл крестик!"
        }
        if((activity as MainActivity).whoWon==2){
            view.findViewById<TextView>(R.id.whoWon).text = "Выиграл нолик!"
        }
        if((activity as MainActivity).whoWon==0){
            view.findViewById<TextView>(R.id.whoWon).text = "Ничья"
        }
        with(view) {
            findViewById<Button>(R.id.restartBtn).setOnClickListener {
                (activity as DialogCallBack).restartGame()
                dismiss()
            }
        }


    }

    interface DialogCallBack{
        fun restartGame()
    }
}