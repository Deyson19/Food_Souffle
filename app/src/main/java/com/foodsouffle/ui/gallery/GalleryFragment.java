package com.foodsouffle.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.foodsouffle.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final ImageSlider imageSlider = root.findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.menu1,"Menu 1"));
        slideModels.add(new SlideModel(R.drawable.menu2,"Menu 2"));
        slideModels.add(new SlideModel(R.drawable.menu3,"Menu 3"));
        slideModels.add(new SlideModel(R.drawable.menu4,"Menu 4"));
        slideModels.add(new SlideModel(R.drawable.menu5,"Menu 5"));
        slideModels.add(new SlideModel(R.drawable.menu6,"Menu 6"));
        slideModels.add(new SlideModel(R.drawable.menu7,"Menu 7"));
        slideModels.add(new SlideModel(R.drawable.menu8,"Menu 8"));
        imageSlider.setImageList(slideModels,false);
        return root;
    }
}