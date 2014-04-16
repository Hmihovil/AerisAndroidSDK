package com.example.fragment;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.demoaerisproject.R;
import com.hamweather.aeris.location.LocationHelper;
import com.hamweather.aeris.maps.AerisMapOptions;
import com.hamweather.aeris.maps.AerisMapView;
import com.hamweather.aeris.maps.AerisMapView.AerisMapType;
import com.hamweather.aeris.maps.MapOptionsActivity;
import com.hamweather.aeris.maps.MapViewFragment;

public class MapFragment extends MapViewFragment {
	public static final int OPTIONS_ACTIVITY = 1025;
	private LocationHelper locHelper;
	private AerisMapOptions options;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_interactive_maps,
				container, false);
		mapView = (AerisMapView) view.findViewById(R.id.aerisfragment_map);
		mapView.init(savedInstanceState, AerisMapType.GOOGLE);
		initMap();
		setHasOptionsMenu(true);
		return view;
	}

	/**
	 * Inits the map with specific setting
	 */
	private void initMap() {
		locHelper = new LocationHelper(getActivity());
		Location myLocation = locHelper.getCurrentLocation();
		mapView.moveToLocation(myLocation, 7);
		options = AerisMapOptions.getPreference(getActivity());
		options.setPreference(getActivity());
		mapView.displayMapWithOptions(options);
		mapView.loadPointData(options.getPointData());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == R.id.menu_weather_layers) {
			this.startActivityForResult(new Intent(getActivity(),
					MapOptionsActivity.class), OPTIONS_ACTIVITY);
			return false;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onCreateOptionsMenu(android.view.Menu,
	 * android.view.MenuInflater)
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_maps_fragment, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

}