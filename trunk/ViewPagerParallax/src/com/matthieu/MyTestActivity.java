package com.matthieu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyTestActivity extends FragmentActivity {
    private static final int MAX_PAGES = 10;

    private int num_pages = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final ViewPagerParallax pager = (ViewPagerParallax) findViewById(R.id.pager);
        pager.set_max_pages(MAX_PAGES);
        pager.setBackgroundAsset(R.raw.sanfran);
        pager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));

        final Button add_page_button = (Button) findViewById(R.id.add_page_button);
        add_page_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num_pages = Math.min(num_pages+1, MAX_PAGES);
                pager.getAdapter().notifyDataSetChanged();
            }
        });

        if (savedInstanceState!=null) {
            num_pages = savedInstanceState.getInt("num_pages");
            pager.setCurrentItem(savedInstanceState.getInt("current_page"), false);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("num_pages", num_pages);
        final ViewPagerParallax pager = (ViewPagerParallax) findViewById(R.id.pager);
        outState.putInt("current_page", pager.getCurrentItem());
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
    	
        public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
        public int getCount() {
            return num_pages;
        }

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new DummySectionFragment();
            Bundle args = new Bundle();
            args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
            fragment.setArguments(args);
            return fragment;
		}
		
		@Override
        public CharSequence getPageTitle(int position) {
            return "page " + position;
        }

        /*@Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }*/

        /*@Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }*/

        /*@Override
        public Object instantiateItem(ViewGroup container, int position) {
            View new_view=null;

            LayoutInflater inflater = getLayoutInflater();
            new_view = inflater.inflate(R.layout.page, null);
            TextView num = (TextView) new_view.findViewById(R.id.page_number);
            num.setText(Integer.toString(position));

            container.addView(new_view);

            return new_view;
        }*/

    }

    /**
     * A dummy fragment representing a section of the app, but that simply
     * displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";

        public DummySectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            //View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
            TextView dummyTextView = new TextView(getActivity());// (TextView) rootView.findViewById(R.id.section_label);
            dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return dummyTextView; //rootView;
        }
    }
}

