package com.number.updown

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.EditText
import android.widget.TextView
import com.number.updown.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var points = 0
    private var randomNumber = Random.nextInt(1, 100)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰 컴포넌트를 ID로 찾아옵니다.
        val userInputEditText = findViewById<EditText>(R.id.userInputEditText)
        val submitGuessButton = findViewById<Button>(R.id.submitGuessButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val newGameButton = findViewById<ImageButton>(R.id.newGameButton)
        val pointsTextView = findViewById<TextView>(R.id.pointsTextView)
        MobileAds.initialize(this) {}
        val adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        val sharedPreferences = getSharedPreferences("gameData", Context.MODE_PRIVATE)
        points = sharedPreferences.getInt("points", 0) // 저장된 포인트 불러오기
        pointsTextView.text = "Point: $points"

        // 버튼 클릭 이벤트 리스너 설정
        submitGuessButton.setOnClickListener {
            val userGuess = userInputEditText.text.toString().toIntOrNull() // 사용자 입력 값을 숫자로 변환
            submitGuessButton.isEnabled = true
            if (userGuess != null) {
                when {
                    userGuess < randomNumber -> resultTextView.text = "UP"
                    userGuess > randomNumber -> resultTextView.text = "DOWN"
                    else -> {
                        resultTextView.text = "정답입니다!!"
                        // randomNumber = Random.nextInt(1, 100) // 새로운 랜덤 숫자 생성
                        points++
                        pointsTextView.text = "Point: $points"
                        val editor = sharedPreferences.edit()
                        editor.putInt("points", points)
                        editor.apply()
                        submitGuessButton.isEnabled = false
                    }
                }
            } else {
                resultTextView.text = "숫자를 입력해주세요."
            }
        }

        // 새 게임 시작 버튼 클릭 리스너 설정
        newGameButton.setOnClickListener {
            randomNumber = Random.nextInt(1, 100) // 새 랜덤 숫자 생성
            resultTextView.text = "" // 결과 텍스트 초기화
            userInputEditText.text.clear() // 사용자 입력 필드 초기화
            submitGuessButton.isEnabled = true

        }
    }
}

