package com.refnil.uqcard;



import com.refnil.uqcard.view.BluetoothConnectFragment;
import com.refnil.uqcard.view.BoardFragment;
import com.refnil.uqcard.view.CardListFragment;
import com.refnil.uqcard.view.CardView;
import com.refnil.uqcard.view.DeckListFragment;
import com.refnil.uqcard.view.FullCardFragment;
import com.refnil.uqcard.view.GameFragment;
import com.refnil.uqcard.view.HistoryFragment;
import com.refnil.uqcard.view.StatsFragment;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class TabsActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabs);

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}
	
	public void startBoardFragment(boolean host)
	{
		//Lame ai start.
		/*Intent i = new Intent(getApplicationContext(),
				UqcardService.class);
		i.putExtra(IService.TYPE, IService.START_AI_LAME);
		startService(i);*/
		BoardFragment fragment = new BoardFragment();
		if(host)
			fragmentTransaction(fragment,R.id.gameMenuLayout,false);
		else
			fragmentTransaction(fragment,R.id.bluetoothconnectlayout,false);
	}
	
	public void startFullCardFragment(CardView cv)
	{
		FullCardFragment fragment = new FullCardFragment();
		fragment.setCard(cv);		
		fragmentTransaction(fragment,R.id.boardlayout,true);
	}
	
	public void startBluetoothFragment(int id)
	{
		BluetoothConnectFragment fragment = new BluetoothConnectFragment();
		fragmentTransaction(fragment,id,true);
	}
	
	public void fragmentTransaction(Fragment fragment,int id,boolean add)
	{
		android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
	    ft.replace(id, fragment);
	    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
	    if(add)
	    	ft.addToBackStack(null);
	    ft.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			Fragment fragment = null;
			switch(position)
			{
				case 0 : fragment = new DeckListFragment();
						break;
				case 1 : fragment = new GameFragment();
						break;
				case 2 : fragment = new CardListFragment();
						break;
				case 3 : fragment = new StatsFragment();
						break;
				case 4 : fragment = new HistoryFragment();
						break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 5 total pages.
			return 5;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.deckList).toUpperCase();
			case 1:
				return getString(R.string.game).toUpperCase();
			case 2:
				return getString(R.string.cardList).toUpperCase();
			case 3:
				return getString(R.string.stats).toUpperCase();
			case 4:
				return getString(R.string.history).toUpperCase();
			}
			return null;
		}
	}
}
