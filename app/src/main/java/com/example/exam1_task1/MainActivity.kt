package com.example.exam1_task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources

class MainActivity : AppCompatActivity(), RestartGameDialog.DialogCallBack {

    var whoWon = 0
    private var indicatorForQueue = 0
    private var krestiki = mutableListOf<Int>()
    private var noliki = mutableListOf<Int>()

    private lateinit var listOfAlgorithms: List<Set<Int>>
    private lateinit var turOfString: TextView
    private lateinit var listOfButtons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        turOfString = findViewById(R.id.textView2)

        listOfButtons = listOf<Button>(
            findViewById(R.id.button_one),
            findViewById(R.id.button_two),
            findViewById(R.id.button_three),
            findViewById(R.id.button_four),
            findViewById(R.id.button_five),
            findViewById(R.id.button_six),
            findViewById(R.id.button_seven),
            findViewById(R.id.button_eight),
            findViewById(R.id.button_nine)
        )

        listOfAlgorithms = listOf(
            setOf(0, 1, 2),
            setOf(3, 4, 5),
            setOf(6, 7, 8),
            setOf(0, 3, 6),
            setOf(1, 4, 7),
            setOf(2, 5, 8),
            setOf(0, 4, 8),
            setOf(2, 4, 6)
        )


        for (button in listOfButtons) {
            button.setOnClickListener {
                gameLogic(button)
            }
        }
    }

    private fun gameLogic(button: Button) {
        if (indicatorForQueue % 2 == 0) {
            button.background =
                AppCompatResources.getDrawable(this, R.drawable.button_clicked_krestik)
            turOfString.text = "Ходит: нолик"
            krestiki.add(listOfButtons.indexOf(button))
            for (i in listOfAlgorithms) {
                if (krestiki.containsAll(i)) {
                    whoWon = 1
                    krestiki.clear()
                    noliki.clear()
                    indicatorForQueue = 1
                    supportFragmentManager.beginTransaction().add(R.id.dialogFrame, RestartGameDialog()).commit()
                    for (anyButton in listOfButtons){
                        anyButton.isClickable = false
                    }
                }
            }
        }
        else {
            button.background =
                AppCompatResources.getDrawable(this, R.drawable.button_clicked_nolik)
            turOfString.text = "Ходит: крестик"
            noliki.add(listOfButtons.indexOf(button))
            for (i in listOfAlgorithms) {
                if (noliki.containsAll(i)) {
                    whoWon = 2
                    noliki.clear()
                    krestiki.clear()
                    indicatorForQueue = 1
                    supportFragmentManager.beginTransaction().add(R.id.dialogFrame, RestartGameDialog()).commit()
                    for (anyButton in listOfButtons){
                        anyButton.isClickable = false
                    }
                }
            }

        }
        indicatorForQueue++
        button.isClickable = false
    }

    override fun restartGame() {
        for (button in listOfButtons) {
            button.background =
                AppCompatResources.getDrawable(this, R.drawable.button_default_shape)
            button.isClickable = true
        }
    }
}