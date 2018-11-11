package cn.netwins.allmedianews.activity.second_navigate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.netwins.allmedianews.R;
import cn.netwins.allmedianews.base.BaseFragment;


/**
 * Created by desaco on 2017/6/22.
 */
public class SecondNavFragment extends BaseFragment {

    private ViewPager mFragmentVp;
    private ArrayList<Fragment> fragments;
    private VpAdapter vpAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.second_nav_fragment, null);

        mFragmentVp = (ViewPager) v.findViewById(R.id.fragment_vp);

//        TextView showTv = (TextView)v.findViewById(R.id.show_text_tv);
//        showTv.setText("SecondNavFragment,第222个Fragment");

        initData();
        return v;
    }

    private void initData() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new SecondSubFragment1().newInstance("sub 1"));//31 点播
        fragments.add(new SecondSubFragment2().newInstance("sub 2"));//30原创；
        fragments.add(new SecondSubFragment3().newInstance("sub 3"));//32 VR

        vpAdapter = new VpAdapter(getChildFragmentManager(), fragments);//getChildFragmentManager()   getFragmentManager()
//        mFragmentVp.setTransitionEffect(AnimatViewPager.TransitionEffect.ZoomIn);//ZoomIn Accordion//TODO
        mFragmentVp.setAdapter(vpAdapter);
//        vpContent.setPageTransformer(true,new PageTransformerAnimat(mRootLLayout));

        mFragmentVp.setOffscreenPageLimit(2);//设置缓存fragment个数//TODO
        mFragmentVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                mFragmentVp.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class VpAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragments;

        public VpAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
//            vpContent.setObjectForPosition(fragments.get(position), position);//TODO
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
