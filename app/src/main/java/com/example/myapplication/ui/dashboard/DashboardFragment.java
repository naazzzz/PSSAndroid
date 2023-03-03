package com.example.myapplication.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDashboardBinding;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.user_location.UserLocationLayer;

public class DashboardFragment extends Fragment  {

    private static final int PERMISSIONS_REQUEST_FINE_LOCATION = 1;
    private FragmentDashboardBinding binding;

    private UserLocationLayer userLocationLayer;
    private final Point TARGET_LOCATION = new Point(59.945933, 30.320045);
    private MapView mapView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MapKitFactory.initialize(inflater.getContext());

        com.example.myapplication.ui.dashboard.DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(com.example.myapplication.ui.dashboard.DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);



        super.onCreate(savedInstanceState);
        mapView = (MapView)binding.getRoot().getViewById(R.id.mapview);
        mapView.getMap().move(
                new CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);


        View root = binding.getRoot();

        return root;
    }


    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}