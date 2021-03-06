package cchapy.cc;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cchapy.cc.FriendFragment.OnListFragmentInteractionListener;
import cchapy.cc.dummy.UserContent;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link User} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyFriendRecyclerViewAdapter extends RecyclerView.Adapter<MyFriendRecyclerViewAdapter.ViewHolder> {

    private final List<User> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyFriendRecyclerViewAdapter(List<User> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_friend, parent, false);
        return new ViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).id);
        //holder.mImageView.setImageResource(R.drawable.character_boy_luckycat);

        int userID = UserInfoHelper.getLoggedInId(holder.context);

        if (userID != -1) {
            holder.usernamestring.setText(holder.mItem.getUserName());

            int avatarImageID = UserInfoHelper.getUserAvatarAlt(holder.context, holder.mItem.getId());

            Resources res = holder.context.getResources();
            TypedArray avatarIndex = res.obtainTypedArray(R.array.avatars);
            holder.mImageView.setImageResource(avatarIndex.getResourceId(avatarImageID, -1));
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView usernamestring;
        public final ImageView mImageView;
        public User mItem;
        public Context context;

        public ViewHolder(View view, Context context) {
            super(view);
            mView = view;
            usernamestring = view.findViewById(R.id.usernamestring);
            mImageView = view.findViewById(R.id.friend_avatar);
            this.context = context;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + "'";
        }
    }
}
