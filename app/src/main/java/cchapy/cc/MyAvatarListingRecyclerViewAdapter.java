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

import java.util.List;

import cchapy.cc.AvatarListingFragment.OnListFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Avatar} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyAvatarListingRecyclerViewAdapter extends RecyclerView.Adapter<MyAvatarListingRecyclerViewAdapter.ViewHolder> {

    private final List<Avatar> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyAvatarListingRecyclerViewAdapter(List<Avatar> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_avatar, parent, false);
        return new ViewHolder(view, parent.getContext());
    }

    /**
     * Given the holder, can find the correct image for the Avatar based off the current logged in
     * user and their gender. The image will be the smaller profile picture
     * @param holder
     * @return Image resource id
     */
    private int getAvatarImage(ViewHolder holder) {
        Resources res = holder.context.getResources();
        TypedArray avatars = res.obtainTypedArray(R.array.avatars); // Access avatars.xml array

        UserFetcher uFetch = new UserFetcher(holder.context);
        AvatarFetcher aFetch = new AvatarFetcher(holder.context);

        // Find the image by look up of database id found through user id and their gender
        return avatars.getResourceId(aFetch.getAvatarMainByAvatarId(holder.mItem.getId(),
                uFetch.getGenderByUserId(UserInfoHelper.getLoggedInId(holder.context))), -1);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);







        holder.mName.setText(holder.mItem.getName());
        holder.mPicture.setImageResource(getAvatarImage(holder));//TODO // FIXME: 12/7/17
        holder.mPrice.setText(Integer.toString(holder.mItem.getPrice()) + " Leaves"); //TODO: Leaf icon
        holder.mRarity.setText("Stars: " + holder.mItem.getRarity()); //TODO: Stars with changing visibility should be used
        holder.mDescription.setText(holder.mItem.getDescription());


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
        public final TextView mName;
        public final TextView mRarity;
        public final TextView mPrice;
        public final TextView mDescription;
        public final ImageView mPicture;
        public final View mView;
        public Avatar mItem;
        public Context context;

        public ViewHolder(View view, Context context) {
            super(view);
            mView = view;
            mName = view.findViewById(R.id.Avatar_Name);
            mRarity = view.findViewById(R.id.Avatar_Rating);
            mPrice = view.findViewById(R.id.Avatar_Price);
            mDescription = view.findViewById(R.id.Avatar_Desc);
            mPicture = view.findViewById(R.id.Avatar_Image_Ex);
            this.context = context;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
