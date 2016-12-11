package info.shimbaroid.instagramydialogsample;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ImageViewHolder extends RecyclerView.ViewHolder {

    public final ImageView imageView;

    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.image_view);
    }

    public void setOnHoldListener(final OnHoldListener listener) {
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onHoldStart(getAdapterPosition());
                return true;
            }
        });
        itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    listener.onHoldEnd();
                }
                return false;
            }
        });
    }
}
