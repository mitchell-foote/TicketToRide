package com.example.fifteam.tickettoride.views;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fifteam.tickettoride.R;
import com.example.gameModel.classes.Route;
import com.example.gameModel.enums.City;
import com.example.model.enums.SharedColor;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by USER on 10/28/2017.
 */

public class GameMapView extends SupportMapFragment implements OnMapReadyCallback {

    GoogleMap map;
    MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.view_game_map, container, false);

        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        drawCities();
        drawRoutes(new ArrayList<Route>());

        map.setMinZoomPreference(3.2f);
        map.setMaxZoomPreference(5.0f);

        LatLngBounds mapBounds = new LatLngBounds(new LatLng(29, -118), new LatLng(49, -77));
        map.setLatLngBoundsForCameraTarget(mapBounds);

        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(39.6, -97.3)));

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getActivity(), R.raw.style_json));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        //LatLng sydney = new LatLng(-34, 151);
        //map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
    }

    public void drawCities() {

        for (City c : City.values()) {
            LatLng cityLocation = new LatLng(c.getLatitude(), c.getLongitude());
            map.addCircle(new CircleOptions()
                    .center(cityLocation)
                    .radius(30000)
                    .strokeColor(Color.BLACK)
                    .strokeWidth(2)
                    .fillColor(Color.BLUE)
                    .zIndex(1));
        }
    }

    public void drawRoutes(List<Route> routeList) {

        List<Route> routeTestList = setUpRoutes();

        for (Route r : routeTestList) {

            //these are the original city points
            double x1 = r.getEndpoint1().getLatitude();
            double y1 = r.getEndpoint1().getLongitude();
            double x2 = r.getEndpoint2().getLatitude();
            double y2 = r.getEndpoint2().getLongitude();
            double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));

            //these are the points a little distance away from the first points in the direction of each other
            double x3 = x1 + ((0.57/distance) * (x2 - x1));
            double y3 = y1 + ((0.57/distance) * (y2 - y1));
            double x4 = x2 + ((0.57/distance) * (x1 - x2));
            double y4 = y2 + ((0.57/distance) * (y1 - y2));
            double distance2 = Math.sqrt(Math.pow((x4 - x3), 2) + Math.pow((y4 - y3), 2));

            double dx = x1 - x2;
            double dy = y1 - y2;

            double offset = 0.2;
            double normX = (dx / distance2);
            double normY = (dy / distance2);
            double xPerp = (offset * normX);
            double yPerp = (offset * normY);

            //these are the perpendicular points that will be the endpoints of the polygon
            double x5 = x3 + yPerp;
            double y5 = y3 - xPerp;

            double x6 = x3 - yPerp;
            double y6 = y3 + xPerp;

            double x7 = x4 - yPerp;
            double y7 = y4 + xPerp;

            double x8 = x4 + yPerp;
            double y8 = y4 - xPerp;

            LatLng point1 = new LatLng(x5, y5);
            LatLng point2 = new LatLng(x6, y6);
            LatLng point3 = new LatLng(x7, y7);
            LatLng point4 = new LatLng(x8, y8);

            //actual drawing of the polygon on the map
            Polygon polygon = map.addPolygon(new PolygonOptions()
                    .add(point1, point2, point3, point4)
                    .strokeColor(Color.RED)
                    .strokeWidth(2));


            //LatLng point1 = new LatLng(x3, y3);
            //LatLng point2 = new LatLng(x4, y4);

            //Polyline polyline = map.addPolyline(new PolylineOptions()
            //.add(point1, point2)
            //.width(15)
            //.color(Color.parseColor("#9ff08e")));
        }

        //Route newRoute = new Route("1", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles);
    }

    private void drawSingleRoutes(List<Route> routes) {

    }

    private void drawDoubleRoutes(List<Route> routes) {

    }

    public List<Route> setUpRoutes() {

        List<Route> routeList = new ArrayList<>();

        //double route - need to set this up
        routeList.add(new Route("1", SharedColor.RAINBOW, 2, City.Atlanta, City.Raleigh));
        routeList.add(new Route("2", SharedColor.RAINBOW, 2, City.Atlanta, City.Raleigh));

        routeList.add(new Route("3", SharedColor.RAINBOW, 2, City.Atlanta, City.Charleston));
        routeList.add(new Route("4", SharedColor.BLUE, 5, City.Atlanta, City.Miami));

        //double route - need to set this up
        routeList.add(new Route("5", SharedColor.YELLOW, 4, City.Atlanta, City.New_Orleans));
        routeList.add(new Route("6", SharedColor.ORANGE, 4, City.Atlanta, City.New_Orleans));

        routeList.add(new Route("7", SharedColor.RAINBOW, 1, City.Atlanta, City.Nashville));

        //double route - need to set this up
        routeList.add(new Route("8", SharedColor.RAINBOW, 2, City.Boston, City.Montreal));
        routeList.add(new Route("9", SharedColor.RAINBOW, 2, City.Boston, City.Montreal));

        //double route - need to set this up
        routeList.add(new Route("10", SharedColor.YELLOW, 2, City.Boston, City.New_York));
        routeList.add(new Route("11", SharedColor.RED, 2, City.Boston, City.New_York));

        routeList.add(new Route("12", SharedColor.RAINBOW, 3, City.Calgary, City.Vancouver));
        routeList.add(new Route("13", SharedColor.WHITE, 6, City.Calgary, City.Winnipeg));


        /*
        routeList.add(new Route("14", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("15", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("16", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("17", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("18", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("19", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("20", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("21", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("22", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("23", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("24", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("25", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("26", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("27", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("28", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("29", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("30", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("31", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("32", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("33", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("34", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("35", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("36", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("37", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("38", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("39", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("40", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("41", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("42", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("43", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("44", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("45", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("46", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("47", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("48", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("49", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("50", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("51", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("52", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("53", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("54", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("55", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("56", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("57", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("58", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("59", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("60", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("61", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("62", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("63", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("64", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("65", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("66", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("67", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("68", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("69", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("70", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("71", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("72", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("73", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("74", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("75", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("76", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("77", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("78", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("79", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        routeList.add(new Route("80", SharedColor.RED, 3, City.San_Francisco, City.Los_Angeles));
        */

        return routeList;
    }
}
