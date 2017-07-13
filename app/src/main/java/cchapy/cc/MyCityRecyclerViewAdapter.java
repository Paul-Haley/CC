package cchapy.cc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cchapy.cc.CityFragment.OnListFragmentInteractionListener;
import cchapy.cc.User;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link User} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCityRecyclerViewAdapter extends RecyclerView.Adapter<MyCityRecyclerViewAdapter.ViewHolder> {

    private final List<City> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyCityRecyclerViewAdapter(List<City> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_city, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(Integer.toString(position + 1));
        holder.mCitynameView.setText(mValues.get(position).getCityName());
        holder.mTotalUsersView.setText(Integer.toString(mValues.get(position).getUserCount()));
        holder.mTotalLeavesView.setText(String.valueOf(mValues.get(position).getLeafCount()));

        if (position == 1) {
            //Decoration for first place
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
        public final TextView mCitynameView;
        public final TextView mTotalUsersView;
        public final TextView mTotalLeavesView;
        public City mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.leaderboardCityRank);
            mCitynameView = view.findViewById(R.id.leaderboardCityName);
            mTotalUsersView  = view.findViewById(R.id.leaderboardTotalUsers);
            mTotalLeavesView = view.findViewById(R.id.leaderboardTotalLeaves);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mCitynameView.getText() + "'";
        }
    }
}
