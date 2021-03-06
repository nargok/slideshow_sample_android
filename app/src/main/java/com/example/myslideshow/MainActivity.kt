package com.example.myslideshow

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private lateinit var player: MediaPlayer

    // ViewPagerの使い方
    // 1. FragmentPageAdapterを継承したクラスを作成する
    // 2. getCountメソッドをoverride, ページ総数を返す
    // 3. getItemメソッドをoverride, ページに対応するフラグメントを返すようにする
    // 4. ViewPagerのsetAdapterメソッドで、1で作成したクラスのインスタンスを関連づける

    // MyAdapterはMainActivity専用のクラスであることを明示するためネストクラスにする
    class MyAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val resources = listOf(
            R.drawable.slide00, R.drawable.slide01,
            R.drawable.slide02, R.drawable.slide03,
            R.drawable.slide04, R.drawable.slide05,
            R.drawable.slide06, R.drawable.slide07,
            R.drawable.slide08, R.drawable.slide09
        )

        override fun getCount(): Int {
            return resources.size
        }

        override fun getItem(position: Int): Fragment {
            return ImageFragment.newInstance(resources[position])
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pager.adapter = MyAdapter(supportFragmentManager)


        // タイマー処理を追加して、5秒ごとにスライドおくりする
        // Handlerクラスを使って別スレッド処理を実現する
        // 別スレッドで実現したい処理をpostメソッドの中に実装する
        val handler = Handler()
        timer(period = 5000) {
            handler.post {
                pager.currentItem = (pager.currentItem + 1) % 10
            }
        }

        player = MediaPlayer.create(this, R.raw.getdown)
        player.isLooping = true
    }

    override fun onResume() {
        super.onResume()
        // 画面描画されるときに音楽スタート
        player.start()
    }

    override fun onPause() {
        super.onPause()
        // アプリが中断されるときに音楽停止
        player.pause()
    }
}
