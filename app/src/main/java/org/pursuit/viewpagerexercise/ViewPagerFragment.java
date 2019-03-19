package org.pursuit.viewpagerexercise;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class ViewPagerFragment extends Fragment {

    private static final String NAME = "name";
    private static final String NUMBER = "number";
    private static final String IMAGE = "image";

    private String name;
    private int number;
    private String image;

    TextView nameTextView;
    TextView numberTextView;
    ImageView imageView;

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    public static ViewPagerFragment newInstance(String name, int number, String image) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putInt(NUMBER, number);
        args.putString(IMAGE, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME);
            number = getArguments().getInt(NUMBER);
            image = getArguments().getString(IMAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameTextView = view.findViewById(R.id.name_text_view);
        numberTextView = view.findViewById(R.id.number_text_view);
        imageView = view.findViewById(R.id.image_view);

        nameTextView.setText(name);
        numberTextView.setText(String.valueOf(number));
        Picasso.get().load(image).into(imageView);
    }
}
