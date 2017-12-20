package com.example.musta.simplyshare;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.musta.simplyshare.ApplicationTab.ApplicationTab;
import com.example.musta.simplyshare.FilesTab.FileTab;
import com.example.musta.simplyshare.Model.File;
import com.example.musta.simplyshare.MusicTab.MusicTab;
import com.example.musta.simplyshare.PicturesTab.PictureTab;
import com.example.musta.simplyshare.VideosTab.VideoTab;
import com.example.musta.simplyshare.fragments.ReceiveFragment;
import com.example.musta.simplyshare.fragments.SendFragment;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
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
        fragments.add(Fragment.instantiate(this, FileTab.class.getName()));
        fragments.add(Fragment.instantiate(this, MusicTab.class.getName()));
        fragments.add(Fragment.instantiate(this, PictureTab.class.getName()));
        fragments.add(Fragment.instantiate(this, VideoTab.class.getName()));

// Attaching fragments into tabLayout with ViewPager
        final SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager(), fragments);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendFragment fragment = SendFragment.newInstance(((ApplicationTab)adapter.getItem(0)).getSelectedItems(),((FileTab)adapter.getItem(1)).getSelectedItems(), ((MusicTab)adapter.getItem(2)).getSelectedItems(),((PictureTab)adapter.getItem(3)).getSelectedItems(),((VideoTab)adapter.getItem(4)).getSelectedItems());
                findViewById(R.id.container).setVisibility(View.VISIBLE);
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.container,fragment).addToBackStack(null).commit();
            }
        });
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        findViewById(R.id.container).setVisibility(View.VISIBLE);
    }

    //Activates the back button function
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
