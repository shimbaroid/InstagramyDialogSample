package info.shimbaroid.instagramydialogsample;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MyDialog extends DialogFragment {

    private static final String EXTRA_BITMAP = "extra_bitmap";

    public static MyDialog getInstance(Bitmap mainBitmap) {
        MyDialog dialog = new MyDialog();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_BITMAP, mainBitmap);
        dialog.setArguments(args);
        return dialog;
    }

    private Bitmap mainBitmap;

    public MyDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.MyDialogTheme);
        mainBitmap = getArguments().getParcelable(EXTRA_BITMAP);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.dialog_instagramy, null);
        ImageView imageView = (ImageView) v.findViewById(R.id.image_view);
        imageView.setImageBitmap(mainBitmap);

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.dialog_pop);
        v.startAnimation(animation);
        return v;
    }
}
