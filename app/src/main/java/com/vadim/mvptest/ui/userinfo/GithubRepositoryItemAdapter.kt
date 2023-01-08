package com.vadim.mvptest.ui.userinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vadim.mvptest.databinding.RecyclerViewUsersItemBinding
import com.vadim.mvptest.ui.IGithubRepositoryListPresenter

/**
 * Адаптер для отображения данных пользователей в recyclerView
 */
class GithubRepositoryItemAdapter(private val presenter: IGithubRepositoryListPresenter) :
    RecyclerView.Adapter<GithubRepositoryItemAdapter.ViewHolder>() {

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
        RecyclerView.ViewHolder(vb.root), GithubRepositoryItemView {

        override var pos = -1

        override fun setRepositoryName(text: String) = with(vb) {
            tvLogin.text = text

        }
    }
}