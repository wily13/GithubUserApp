package com.example.githubuserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import java.io.IOException
import java.io.InputStream
import org.json.JSONObject
import androidx.annotation.RawRes
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private var listUsers: ArrayList<Githubuser> = arrayListOf()

    companion object {
        private const val TAG = "MainActivity"
        const val EXTRA_GITHUBUSER = "extra_githubuser"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar!!.title = "Github User's"
        }

        rv_githubuser.setHasFixedSize(true)

        showRecyclerList()
    }

    private fun showRecyclerList() {
        rv_githubuser.layoutManager = LinearLayoutManager(this)
        val githubUserAdapter = GithubUserAdapter(listUsers)
        rv_githubuser.adapter = githubUserAdapter

        getListItemsFromJSON()

        // click on item list
        githubUserAdapter.setOnItemClickCallBack(object : GithubUserAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: Githubuser) {
                showSelectedUser(data)
                Log.d(TAG, "cek data: "+data)
            }
        })
    }

    private fun showSelectedUser(githubuser: Githubuser) {
        val detailUserIntent = Intent(this@MainActivity, DetailUsersActivity::class.java)
        detailUserIntent.putExtra(EXTRA_GITHUBUSER, githubuser)
        startActivity(detailUserIntent)
    }

    private fun getListItemsFromJSON() {
        try {

            val readDataFromFile = ReadDataFromFile()
            val jsonString = readDataFromFile.readRawResource(this, R.raw.githubuser)
            val jsonObject = JSONObject(jsonString)
            val jsonArray = jsonObject.getJSONArray("users")

            for (i in 0 until jsonArray.length()) {

                val itemObj = jsonArray.getJSONObject(i)

//                val githubUser = Githubuser()
//                githubUser.username = itemObj.getString("username")
//                githubUser.name = itemObj.getString("name")
//                githubUser.company = itemObj.getString("company")
//                githubUser.location = itemObj.getString("location")
//                githubUser.repository = itemObj.getString("repository").toInt()
//                githubUser.follower = itemObj.getString("follower").toInt()
//                githubUser.following = itemObj.getString("following").toInt()
//                githubUser.avatar = itemObj.getString("avatar").replace("@", ":")

                val username = itemObj.getString("username")
                val name = itemObj.getString("name")
                val company = itemObj.getString("company")
                val location = itemObj.getString("location")
                val repository = itemObj.getString("repository").toInt()
                val follower = itemObj.getString("follower").toInt()
                val following = itemObj.getString("following").toInt()

                val avatar = itemObj.getString("avatar").replace("@", ":")

                val githubUser = Githubuser(
                    username,
                    name,
                    avatar,
                    company,
                    location,
                    repository,
                    follower,
                    following
                )
                listUsers.add(githubUser)
            }

        } catch (e: JSONException) {
            Log.d(TAG, "getListItemsFromJSON - JSONException: ", e)
        } catch (e: IOException) {
            Log.d(TAG, "getListItemsFromJSON - IOException: ", e)
        }

    }
}
