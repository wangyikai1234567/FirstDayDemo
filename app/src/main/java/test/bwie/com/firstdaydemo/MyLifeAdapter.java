package test.bwie.com.firstdaydemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * date: 2017/4/8.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class MyLifeAdapter extends BaseAdapter {
    private List<MyLeft> lml;
    private Context mContext;

    public MyLifeAdapter(List<MyLeft> lml, Context context) {
        this.lml = lml;
        mContext = context;
    }

    @Override
    public int getCount() {
        return lml.size();
    }

    @Override
    public Object getItem(int position) {
        return lml.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lf_lv_nei, null);
            vh = new ViewHolder();
            vh.text = (TextView) convertView.findViewById(R.id.lflvtv);
vh.image = (ImageView) convertView.findViewById(R.id.lflviv);

            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.text.setText(lml.get(position).getText());
        vh.image.setImageResource(lml.get(position).getImage());
        return convertView;
    }

    class ViewHolder {
        TextView text;
        ImageView image;
    }
}
