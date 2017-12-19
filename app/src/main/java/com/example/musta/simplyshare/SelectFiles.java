package com.example.musta.simplyshare;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.example.musta.simplyshare.ApplicationTab.ApplicationTab;
import com.example.musta.simplyshare.FilesTab.FileTab;
import com.example.musta.simplyshare.Model.File;
import com.example.musta.simplyshare.MusicTab.MusicTab;
import com.example.musta.simplyshare.PicturesTab.PictureTab;
import com.example.musta.simplyshare.VideosTab.VideoTab;

import java.util.List;
import java.util.Vector;

public class SelectFiles extends AppCompatActivity
{
    private Button share;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Creates a back button
        share = (Button) findViewById(R.id.docked_sendButton);

// Initializing tab and pager views
        TabLayout tabLayout = (TabLayout) findViewById(R.id.my_tab_layout);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.my_view_pager);

// Making new tabs and adding to tabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Apps"));
        tabLayout.addTab(tabLayout.newTab().setText("Music"));
        tabLayout.addTab(tabLayout.newTab().setText("Files"));
        tabLayout.addTab(tabLayout.newTab().setText("Pictures"));
        tabLayout.addTab(tabLayout.newTab().setText("Videos"));

// Adding fragments to a list
        List<Fragment> fragments = new Vector<>();
        fragments.add(Fragment.instantiate(this, ApplicationTab.class.getName()));
        fragments.add(Fragment.instantiate(this, MusicTab.class.getName()));
        fragments.add(Fragment.instantiate(this, FileTab.class.getName()));
        fragments.add(Fragment.instantiate(this, PictureTab.class.getName()));
        fragments.add(Fragment.instantiate(this, VideoTab.class.getName()));

// Attaching fragments into tabLayout with ViewPager
        final SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Fragment fragment = adapter.getItem(position);
                switch (fragment.getClass().getSimpleName()){
                    case "ApplicationTab":
                        ApplicationTab tab = (ApplicationTab) fragment;
                        tab.saveSelectedIndexes();
                        break;
                    case "MusicTab":
                        MusicTab musictab = (MusicTab) fragment;
                        musictab.saveSelectedIndexes();
                        break;
                    case "FileTab":
                        FileTab filetab = (FileTab) fragment;
                        filetab.saveSelectedIndexes();
                        break;
                    case "PictureTab":
                        PictureTab picturetab = (PictureTab) fragment;
                        picturetab.saveSelectedIndexes();
                        break;
                    case "VideoTab":
                        VideoTab videotab = (VideoTab) fragment;
                        videotab.saveSelectedIndexes();
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);

    }

    //Activates the back button function
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
