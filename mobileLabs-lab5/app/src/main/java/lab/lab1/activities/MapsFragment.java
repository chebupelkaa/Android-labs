package lab.lab1.activities;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import lab.lab1.R;

public class MapsFragment extends Fragment {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private GoogleMap _googleMap;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
            _googleMap = googleMap;
            if (ContextCompat.checkSelfPermission(
                    getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED) {
                requestLocation();
            } else {
                ActivityCompat.requestPermissions(
                        getActivity(),
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_PERMISSION_REQUEST_CODE
                );
            }
            LatLng Minsk = new LatLng(53.904541,  27.561523);
            googleMap.addMarker(new MarkerOptions().position(Minsk).title("штаб1"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(Minsk));

            LatLng Homiel = new LatLng(55.2948401,  28.771430);
            googleMap.addMarker(new MarkerOptions().position(Homiel).title("штаб2"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(Homiel));

            LatLng Bobruisk = new LatLng(53.144890,  29.235331);
            googleMap.addMarker(new MarkerOptions().position(Bobruisk).title("штаб3"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(Bobruisk));

            LatLng Brest = new LatLng(48.389870,  -4.487180);
            googleMap.addMarker(new MarkerOptions().position(Brest).title("штаб4"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(Brest));
        }
    };

    private void requestLocation() {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null)
                            _googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(location.getLatitude(), location.getLongitude()))
                                    .title("Я"));
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE)
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                requestLocation();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}