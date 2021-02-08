package com.spray.stock.adapters

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.*
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.spray.stock.R
import com.spray.stock.config.GlideApp
import com.spray.stock.models.item.RecommendedItemComment
import com.spray.stock.utils.diffTime
import com.spray.stock.views.fragments.dialog.CommentActionFragment

class RecommendedItemCommentAdapter(val context: Context, private val supportFragmentManager: FragmentManager) : RecyclerView
.Adapter<RecommendedItemCommentAdapter.ViewHolder>() {

    private lateinit var mView: View
    private var mRecommendedItemComments = mutableListOf<RecommendedItemComment>()
    private lateinit var mCommentPopup: ImageButton
    private var mPosition: Int = 0

    private val diffCallback = object : DiffUtil.ItemCallback<RecommendedItemComment>() {
        override fun areItemsTheSame(oldItem: RecommendedItemComment, newItem: RecommendedItemComment) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: RecommendedItemComment, newItem: RecommendedItemComment) =
            oldItem == newItem
    }

    private val mAsyncDiff = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedItemCommentAdapter.ViewHolder {
        mView = LayoutInflater.from(context).inflate(R.layout.items_recommended_detail_comment, parent, false)
        return ViewHolder(mView)
    }

    override fun getItemCount(): Int = mAsyncDiff.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mPosition = position
        holder.bind(mAsyncDiff.currentList[position])

        // 코멘트 목록의 사이드 메뉴를 누르면 아래에서 bottom dialog 뜨게 했던 부분 -> 이건 중앙 팝업에서 메뉴 선택시 나오게 바꿔야 함.
//        val commentActionButton: ImageButton = holder.itemView.findViewById(R.id.ib_recommended_detail_comment_popup)
//        commentActionButton.setOnClickListener {
//            CommentBottomSheetDialogFragment().apply {
//                arguments = Bundle().apply {
//                    putString("id", mRecommendedItemComments[position].comment)
//                    putString("data", "905")
//                }
//                show(supportFragmentManager, "commentPopup")
//            }
//        }

        val commentActionButton: ImageButton = holder.itemView.findViewById(R.id.ib_recommended_detail_comment_popup)
        commentActionButton.setOnClickListener {
            CommentActionFragment().apply {
                arguments = Bundle().apply {
                    putString("id", mRecommendedItemComments[position].comment)
                    putString("data", "905")
                }
                show(supportFragmentManager, "commentPopup")
            }
        }
    }

    fun submitList(recommendedItemComments: MutableList<RecommendedItemComment>) {
        mAsyncDiff.submitList(recommendedItemComments)
        mRecommendedItemComments.addAll(recommendedItemComments)
    }

    fun clear() {
        mRecommendedItemComments.clear()
        mAsyncDiff.submitList(mRecommendedItemComments)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val commentMemberProfile: ImageView = itemView.findViewById(R.id.civ_recommended_item_comment_member_profile)
        private val commentMemberNickName: TextView = itemView.findViewById(R.id.tv_recommended_item_comment_member_nick_name)
        private val commentDate: TextView = itemView.findViewById(R.id.tv_recommended_item_comment_date)
        private val comment: TextView = itemView.findViewById(R.id.tv_recommended_item_comment)

        fun bind(recommendedItemComment: RecommendedItemComment) {

            commentMemberNickName.text = recommendedItemComment.member.nickName
            commentDate.text = recommendedItemComment.lastModifiedDate.diffTime()
            comment.text = recommendedItemComment.comment.replace("\\n", System.getProperty("line.separator")!!)
            GlideApp.with(context)
                .load(recommendedItemComment.member.profileImageUrl)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transform(CircleCrop())
                .into(commentMemberProfile)


//            mCommentPopup = itemView.findViewById(R.id.ib_recommended_detail_comment_popup)
//
//            val commentPopup = PopupMenu(context,  mCommentPopup)
//            commentPopup.inflate(R.menu.pop_menu_recommended_detail_comment)
//            commentPopup.setOnMenuItemClickListener {
//                when (it.itemId) {
//                    R.id.pm_recommended_detail_comment_update -> {
//                        Toast.makeText(context, "Clicked!!!!", Toast.LENGTH_LONG)
//                        Log.d("Clicked!!!!", "ddddd")
//                        true
//                    }
//                    else -> false
//                }
//            }


        }
    }

//    override fun onClick(v: View?) {
//        // 사이드 팝업 메뉴
////        val cp : ImageButton = v!!.findViewById(R.id.ib_recommended_detail_comment_popup)
////        val commentPopup = PopupMenu(context,  cp)
////        commentPopup.setOnMenuItemClickListener {
////            when (it.itemId) {
////                R.id.pm_recommended_detail_comment_update -> {
////                    Toast.makeText(context, "Clicked!!!!", Toast.LENGTH_LONG)
////                    Log.d("Clicked!!!!", "ddddd")
////                    true
////                }
////                else -> false
////            }
////        }
////        commentPopup.inflate(R.menu.pop_menu_recommended_detail_comment)
////        commentPopup.show()
//
//        //val bottomPopupMenu = CommentBottomSheetDialogFragment()
//
//
//
//    }
}