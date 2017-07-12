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

import cchapy.cc.VoucherListingFragment.OnListFragmentInteractionListener;
import cchapy.cc.dummy.DummyContent.DummyItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyVoucherListingRecyclerViewAdapter extends RecyclerView.Adapter<MyVoucherListingRecyclerViewAdapter.ViewHolder> {

    private final List<Voucher> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyVoucherListingRecyclerViewAdapter(List<Voucher> vouchers, OnListFragmentInteractionListener listener) {
        mValues = vouchers;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_voucherlisting, parent, false);
        return new ViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        Resources res = holder.context.getResources();
        TypedArray vouchers = res.obtainTypedArray(R.array.vouchers);

        holder.mNameView.setText(holder.mItem.getName());
        holder.mLogoView.setImageResource(vouchers.getResourceId(holder.mItem.getId() - 1, -1));
        holder.mPriceView.setText(Integer.toString(holder.mItem.getPrice()));
        holder.mShopView.setText(holder.mItem.getShop());
        holder.mTimeView.setText(holder.mItem.getTime());


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
        public final TextView mNameView;
        public final TextView mPriceView;
        public final TextView mTimeView;
        public final ImageView mLogoView;
        public final TextView mShopView;
        public Voucher mItem;
        public Context context;

        public ViewHolder(View view, Context context) {
            super(view);
            mView = view;
            mNameView = view.findViewById(R.id.Discount_Name);
            mPriceView = view.findViewById(R.id.Discount_Price);
            mTimeView = view.findViewById(R.id.Discount_Time);
            mLogoView = view.findViewById(R.id.Discount_Image_Ex);
            mShopView = view.findViewById(R.id.Discount_Shop);
            this.context = context;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
