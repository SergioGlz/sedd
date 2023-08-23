package edu.uptsgm.sedd.framework.android.ui.student.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.uptsgm.sedd.application.model.Group
import edu.uptsgm.sedd.databinding.ItemGroupBinding

class GroupsAdapter(private val groups: List<Group>) : RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder>() {

    inner class GroupsViewHolder(val dataBinding: ItemGroupBinding) : RecyclerView.ViewHolder(dataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsViewHolder = GroupsViewHolder(
        ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = groups.size

    override fun onBindViewHolder(holder: GroupsViewHolder, position: Int) {
        holder.dataBinding.groupId = groups[position].groupId
        holder.dataBinding.groupName = groups[position].displayName
        holder.dataBinding.groupProfessor = groups[position].professor.displayName
    }

}