package com.example.musta.simplyshare;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.musta.simplyshare.Tabs.ApplicationTab;
import com.example.musta.simplyshare.Tabs.FilesTab;
import com.example.musta.simplyshare.Tabs.MusicTab;
import com.example.musta.simplyshare.Tabs.PicturesTab;
import com.example.musta.simplyshare.Tabs.VideosTab;

import java.util.List;
import java.util.Vector;

public class SelectFiles extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Creates a back button


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
        fragments.add(Fragment.instantiate(this, FilesTab.class.getName()));
        fragments.add(Fragment.instantiate(this, PicturesTab.class.getName()));
        fragments.add(Fragment.instantiate(this, VideosTab.class.getName()));

// Attaching fragments into tabLayout with ViewPager
        viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);

    }

    //Activates the back button function
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
