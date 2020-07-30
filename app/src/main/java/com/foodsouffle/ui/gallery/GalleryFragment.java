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
        slideModels.add(new SlideModel("http://playventv.byethost7.com/menu_disponible/menu1.jpg","Image 1"));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2019/10/29/14/46/landscape-4587079_960_720.jpg","Image 2"));
        slideModels.add(new SlideModel("http://playventv.byethost7.com/menu_disponible/menu3.jpg","Image 3"));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2019/10/29/14/46/landscape-4587079_960_720.jpg","Image 4"));
        slideModels.add(new SlideModel("http://playventv.byethost7.com/menu_disponible/menu5.jpg","Image 5"));
        slideModels.add(new SlideModel("https://cdn.pixabay.com/photo/2019/10/29/14/46/landscape-4587079_960_720.jpg","Image 6"));
        slideModels.add(new SlideModel("http://playventv.byethost7.com/menu_disponible/menu7.jpg","Image 7"));
        slideModels.add(new SlideModel("http://playventv.byethost7.com/menu_disponible/menu8.jpg","Image 8"));
        imageSlider.setImageList(slideModels,true);
        return root;
    }
}