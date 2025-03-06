package com.example.mcproyecto;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int[] mImageIds;

    public ImageAdapter(Context c, int[] imageIds) {
        mContext = c;
        mImageIds = imageIds;
    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mImageIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Retorna una vista para cada elemento del GridView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            // Si no existe una vista reutilizable, se crea una nueva
            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);

            // Ajustar el tamaño deseado en dp
            int sizeInDp = 130; // puedes modificar este valor según lo requieras
            float scale = mContext.getResources().getDisplayMetrics().density;
            int sizeInPx = (int) (sizeInDp * scale + 0.5f);

            // Establecer el ancho y alto del ImageView
            imageView.setLayoutParams(new GridView.LayoutParams(sizeInPx, sizeInPx));
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mImageIds[position]);
        return imageView;
    }
}
