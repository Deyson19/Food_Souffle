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
    int precios = 7500;

    int incremento = (int) (precios*0.05);
    int valorFinal = precios + incremento;
    String menu1 = "Arroz, frijoles, carne desmechada, platano frito en rodajas y jugo de mango..." + " $10000";
    String menu2 = "Pizza peperoni y gaseosa litro y 1/2..." +" $15000";
    String menu3 = "Arroz, Carne de cerdo, ensalada de lechuga y tomate, y jugo de tomate de arbol..." + " $8500";
    String menu4 = "Arroz, sancocho de carne de res, mazorca, zanahoria y jugo de maracuya..." +" $7500";
    String menu5 = "Hamburguesa de carne de cerdo, jamon, aguacate, tocineta y CocaCola..." + " $10000";
    String menu6 = "Arroz, sopa de camarones con mazorca, arberjas y jugo de lulo..." + " $7000";
    String menu7 = "Taco de carne de cerdo, lechuga, queso rayado, salsa de tomate, ají y Pepsi" +" $7000";
    String menu8 = "Taco de carne de res, lechuga, queso parmesano, salsa de tomate, ají y Salpicon" +" $7500";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final ImageSlider imageSlider = root.findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://dc603.4shared.com/img/3Hw05Ws0ea/s24/173c97a72c0/menu1","Menu 1: " +menu1));
        slideModels.add(new SlideModel("https://dc603.4shared.com/img/zMoD6-Suea/s24/173c97a7a90/menu2","Menu 2: "+menu2));
        slideModels.add(new SlideModel("https://dc603.4shared.com/img/F6dro8PPea/s24/173c97a7e78/menu3","Menu 3: "+menu3));
        slideModels.add(new SlideModel("https://dc603.4shared.com/img/TehDb9C2iq/s24/173c97a8648/menu4","Menu 4: "+menu4));
        slideModels.add(new SlideModel("https://dc603.4shared.com/img/hO35kjK4iq/s24/173c97a8a30/menu5","Menu 5: "+menu5));
        slideModels.add(new SlideModel("https://dc603.4shared.com/img/lRlNjEFiea/s24/173c97a9db8/menu6","Menu 6: "+menu6));
        slideModels.add(new SlideModel("https://dc603.4shared.com/img/uqiysTR6ea/s24/173c97aa588/menu7","Menu 7: "+menu7));
        slideModels.add(new SlideModel("https://dc603.4shared.com/img/I8tImqoPiq/s24/173c97ab528/menu8","Menu 8: "+menu8));
        imageSlider.setImageList(slideModels,false);
        return root;
    }
}