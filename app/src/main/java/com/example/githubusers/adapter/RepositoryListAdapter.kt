package com.example.githubusers.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusers.model.Repository

class RepositoryListAdapter(
    private val listRepository: List<Repository>,
    private val onClicked: (Repository) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val rootBinding = ResListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(rootBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is RepositoryViewHolder -> {
                holder.bind(listRepository[position], onClicked)
            }
        }
    }

    override fun getItemCount(): Int {
        return listRepository.size
    }

    class RepositoryViewHolder constructor(
        itemView: ResListItemBinding
    ) : RecyclerView.ViewHolder(itemView.root) {
        private val repositoryName = itemView.name
        private val repositoryLanguage = itemView.language

        fun bind(repository: Repository, onClicked: (Repository) -> Unit) {
            repositoryName.text = repository.name
            repositoryLanguage.text = repository.language

            itemView.setOnClickListener {
                onClicked(repository)
            }
        }
    }
}