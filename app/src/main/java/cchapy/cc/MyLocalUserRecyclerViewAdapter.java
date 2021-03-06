package cchapy.cc;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cchapy.cc.LocalUserFragment.OnListFragmentInteractionListener;
import cchapy.cc.User;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link User} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyLocalUserRecyclerViewAdapter extends RecyclerView.Adapter<MyLocalUserRecyclerViewAdapter.ViewHolder> {

    private final List<User> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyLocalUserRecyclerViewAdapter(List<User> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(Integer.toString(position + 1));
        holder.mFriendUsernameView.setText(mValues.get(position).getUserName());
        holder.mFriendCityView.setText(mValues.get(position).getCity());
        holder.mFriendLeafView.setText(String.valueOf(mValues.get(position).getTotalLeafCount()));

        if (position == 0) {
            holder.mView.findViewById(R.id.defaultLeaderboardRow).setVisibility(View.GONE);
        }

        if (mValues.get(position).getId() == UserInfoHelper.getLoggedInId(holder.mView.getContext())){
            holder.mIdView.setTypeface(null, Typeface.BOLD);
            holder.mFriendUsernameView.setTypeface(null, Typeface.BOLD);
            holder.mFriendCityView.setTypeface(null, Typeface.BOLD);
            holder.mFriendLeafView.setTypeface(null, Typeface.BOLD);
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
        public final TextView mIdView;
        public final TextView mFriendUsernameView;
        public final TextView mFriendCityView;
        public final TextView mFriendLeafView;
        public User mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.id);
            mFriendUsernameView = view.findViewById(R.id.leaderboardFriendUsername);
            mFriendCityView  = view.findViewById(R.id.leaderboardFriendCity);
            mFriendLeafView = view.findViewById(R.id.leaderboardFriendLeaves);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mFriendUsernameView.getText() + "'";
        }
    }
}
