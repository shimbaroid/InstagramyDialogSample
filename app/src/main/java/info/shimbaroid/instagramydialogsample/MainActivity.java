package info.shimbaroid.instagramydialogsample;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.blurry.Blurry;

public class MainActivity extends AppCompatActivity {

    private static final String DIALOG = "dialog";
    private ViewGroup parentViewGroup;
    private ImageView blurImageView;
    private MyGridLayoutManager gridLayoutManager;

    private List<Bitmap> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentViewGroup = (ViewGroup) findViewById(R.id.parent);
        blurImageView = (ImageView) findViewById(R.id.image_view_blur);

        // ic_launcherの詰まったlistを作成
        final Bitmap icLauncher = ((BitmapDrawable) getResources()
                .getDrawable(R.mipmap.ic_launcher))
                .getBitmap();
        list = new ArrayList<Bitmap>() {
            {
                for (int i = 0; i < 15; i++) {
                    add(icLauncher);
                }
            }
        };

        initUnscrollableSample();
        initScrollableSample();
    }

    private void initUnscrollableSample() {
        ImageView imageView = (ImageView) findViewById(R.id.image_view);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(list.get(0));
                blurOn();
                return true;
            }
        });
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    dismissDialog();
                    blurOff();
                }
                return false;
            }
        });
    }

private void initScrollableSample() {
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    gridLayoutManager = new MyGridLayoutManager(this, 3);
    recyclerView.setLayoutManager(gridLayoutManager);
    MyAdapter adapter = new MyAdapter(list);
    recyclerView.setAdapter(adapter);
    adapter.setOnHoldListener(new OnHoldListener() {
        @Override
        public void onHoldStart(int position) {
            gridLayoutManager.setScrollDisable();
            showDialog(list.get(position));
            blurOn();
        }

        @Override
        public void onHoldEnd() {
            gridLayoutManager.setScrollEnable();
            dismissDialog();
            blurOff();
        }
    });
}

    private void showDialog(Bitmap bitmap) {
        MyDialog dialog = MyDialog.getInstance(bitmap);
        dialog.show(getSupportFragmentManager(), DIALOG);
    }

    private void dismissDialog() {
        Fragment f = getSupportFragmentManager().findFragmentByTag(DIALOG);
        if (f != null && f instanceof MyDialog) {
            ((MyDialog) f).dismiss();
        }
    }

    private void blurOn() {
        blurImageView.setVisibility(View.VISIBLE);
        parentViewGroup.setVisibility(View.INVISIBLE);
        Blurry.with(this)
                .capture(parentViewGroup)
                .into(blurImageView);
    }

    private void blurOff() {
        blurImageView.setVisibility(View.INVISIBLE);
        parentViewGroup.setVisibility(View.VISIBLE);
        Blurry.delete(parentViewGroup);
    }
}
