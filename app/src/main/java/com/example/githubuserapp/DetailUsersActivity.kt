package com.example.githubuserapp

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_users.*

class DetailUsersActivity : AppCompatActivity() {

    companion object{
        const val URL_GITHUB = "https://github.com/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_users)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (supportActionBar != null) {
            supportActionBar!!.title = "Detail User"
        }

        // get data from intent
        val dataUser = intent.getParcelableExtra<Githubuser>(MainActivity.EXTRA_GITHUBUSER) as Githubuser

        tv_username.text = dataUser.username.toString()
        tv_name.text = dataUser.name.toString()
        tv_company.text = dataUser.company.toString()
        tv_location.text = dataUser.location.toString()
        tv_repository.text = dataUser.repository.toString()
        tv_follower.text = dataUser.follower.toString()
        tv_following.text = dataUser.following.toString()

        // image from drawable
        val PACKAGE_NAME: String = applicationContext.packageName
        val imgId: Int = resources.getIdentifier(PACKAGE_NAME + dataUser.avatar, null, null)

        Glide.with(this)
            .load(BitmapFactory.decodeResource(resources, imgId))
            .into(img_avatar)
        // ---------

        // Button click
        btn_follow.setOnClickListener { view ->
            val uriString: String = URL_GITHUB + tv_username.text.toString().trim()
            loadIntentImplicit(uriString)
        }

        // card view click
        cv_repository.setOnClickListener { view ->
            val uriString: String = URL_GITHUB + tv_username.text.toString().trim() + "?tab=repositories"
            loadIntentImplicit(uriString)
        }
        cv_follower.setOnClickListener { view ->
            val uriString: String = URL_GITHUB + tv_username.text.toString().trim() + "?tab=followers"
            loadIntentImplicit(uriString)
        }
        cv_following.setOnClickListener { view ->
            val uriString: String = URL_GITHUB + tv_username.text.toString().trim() + "?tab=following"
            loadIntentImplicit(uriString)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun loadIntentImplicit(uriString: String){
        val gmmIntentUri = Uri.parse(uriString)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.android.chrome")

        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        } else {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(URL_GITHUB)))
        }
    }
}
