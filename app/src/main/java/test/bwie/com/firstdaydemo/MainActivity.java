package test.bwie.com.firstdaydemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;
    private HttpURLConnection mHuc;
    private Bean mBean;

    private List<AddressBean> lab = new ArrayList<>();
    private MyBaseAdapter myBaseAdapter = new MyBaseAdapter(lab, MainActivity.this);

    private List<MyLeft> mLml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLv = (ListView) findViewById(R.id.lv);

        MyTask my = new MyTask();
        my.execute(MyUrls.MYURL);
// configure the SlidingMenu
        SlidingMenu menu = new SlidingMenu(this);

        menu.setMode(SlidingMenu.LEFT);

        //设置可以左右滑动
//        menu.setMode(SlidingMenu.LEFT_RIGHT);
//        menu.setSecondaryMenu(getLeftMenu());//设置右侧菜单
//
//        menu.setSecondaryShadowDrawable(R.color.beige);//右侧菜单的阴影图片
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置阴影图片的宽度
        menu.setShadowWidthRes(R.dimen.shadow_width);
        //设置阴影图片
        menu.setShadowDrawable(R.color.colorAccent);

        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        //设置SlidingMenu菜单的宽度
        menu.setBehindWidth(600);
//使SlidingMenu附加在Activity上
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(getLeftMenu());

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, mBean.getList().get(position).getId() + "", Toast.LENGTH_LONG).show();
            }
        });
        mLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lab.remove(position);
                myBaseAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public View getLeftMenu() {
        //从主布局文件绑定的Activity调用另一个布局文件必须调用LayoutInflater
        LayoutInflater inflater = getLayoutInflater();
        //得到menu的View
        View v = inflater.inflate(R.layout.lift_menu, null);
        ListView lv = (ListView) v.findViewById(R.id.lf_lv);
        lv.setAdapter(new MyLifeAdapter(getData(), MainActivity.this));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                //监听listview item 点击
                Toast.makeText(MainActivity.this, "111", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    private List<MyLeft> getData() {
        mLml = new ArrayList<>();
        mLml.add(new MyLeft(R.mipmap.ic_launcher, "好友动态"));
        mLml.add(new MyLeft(R.mipmap.ic_launcher, "我的话题"));
        mLml.add(new MyLeft(R.mipmap.ic_launcher, "收藏"));
        mLml.add(new MyLeft(R.mipmap.ic_launcher, "活动"));
        mLml.add(new MyLeft(R.mipmap.ic_launcher, "商城"));
        return mLml;
    }

    public class MyTask extends AsyncTask<String, Integer, Bean> {
        //进行耗时操作
        @Override
        protected Bean doInBackground(String... params) {
            String bos = UrlsUtils.getJson(params[0]);
            if (bos != null) {
                Gson gson = new Gson();
                mBean = gson.fromJson(bos, Bean.class);
                return mBean;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bean bean) {
            for (int i = 0; i < mBean.getList().size(); i++) {
                String address = bean.getList().get(i).getAddress();
                String site_name = bean.getList().get(i).getSite_name();
                String id = bean.getList().get(i).getId() + "";
                lab.add(new AddressBean(id, site_name, address));
            }
            mLv.setAdapter(myBaseAdapter);
        }
    }
}
