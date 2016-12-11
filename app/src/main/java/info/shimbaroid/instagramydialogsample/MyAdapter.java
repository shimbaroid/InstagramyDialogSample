package info.shimbaroid.instagramydialogsample;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yuki on 2016/12/01.
 */

public class MyAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private List<Bitmap> bitmapList;
    private OnHoldListener listener;

    public MyAdapter(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.imageView.setImageBitmap(bitmapList.get(position));
        if (listener != null) {
            holder.setOnHoldListener(listener);
        }
    }

    @Override
    public int getItemCount() {
        return bitmapList.size();
    }

    public void setOnHoldListener(OnHoldListener listener) {
        this.listener = listener;
    }
}
