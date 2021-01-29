package com.example.githubuserapp

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_githubuser.view.*

class GithubUserAdapter (private val listUsers: ArrayList<Githubuser>) :
    RecyclerView.Adapter<GithubUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack){
        this.onItemClickCallback = onItemClickCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserAdapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_githubuser, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    override fun onBindViewHolder(holder: GithubUserAdapter.ListViewHolder, position: Int) {
        holder.bind(listUsers[position])
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(githubuser: Githubuser){
            with(itemView){
                tv_username_github.text = githubuser.username
                tv_name_github.text = githubuser.name
                tv_follower_github.text = githubuser.follower.toString().trim()

                // image from drawable
                val PACKAGE_NAME: String = context.applicationContext.packageName
                val imgId: Int = context.resources.getIdentifier(PACKAGE_NAME + githubuser.avatar, null, null)

                Glide.with(itemView.context)
                    .load(BitmapFactory.decodeResource(context.resources, imgId))
                    .apply(RequestOptions().override(350, 550))
                    .into(itemView.img_avatar_github)

                // item click call back
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(githubuser) }
            }
        }
    }

    interface OnItemClickCallBack{
        fun onItemClicked(data: Githubuser)
    }
}