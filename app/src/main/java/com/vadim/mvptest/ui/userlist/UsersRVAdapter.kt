package com.vadim.mvptest.ui.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vadim.mvptest.R
import com.vadim.mvptest.databinding.RecyclerViewUsersItemBinding
import com.vadim.mvptest.ui.IUserListPresenter
import com.vadim.mvptest.utils.loadImageFromUrl

/**
 * Адаптер для отображения данных пользователей в recyclerView
 */
class UsersRVAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(RecyclerViewUsersItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)).apply {
                itemView.setOnClickListener {
                    presenter.itemClickListener?.invoke(this)
                }
        }
        return viewHolder
    }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val vb: RecyclerViewUsersItemBinding) :
        RecyclerView.ViewHolder(vb.root), UserItemView {

        override var pos = -1

        override fun setLogin(text: String, url: String?) = with(vb) {
            tvLogin.text = text
            if (url != null){
                ivUserAvatar.loadImageFromUrl(url)
            }
            else{
                ivUserAvatar.loadImageFromUrl(R.drawable.ic_user_placeholder)
            }
        }
    }
}