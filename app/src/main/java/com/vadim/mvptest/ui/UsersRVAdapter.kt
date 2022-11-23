package com.vadim.mvptest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vadim.mvptest.databinding.RecyclerViewItemBinding

/**
 * Адаптер для отображения данных пользователей в recyclerView
 */
class UsersRVAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            RecyclerViewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val vb: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(vb.root), UserItemView {

        override var pos = -1

        override fun setLogin(text: String) = with(vb) {
            tvLogin.text = text
        }
    }
}