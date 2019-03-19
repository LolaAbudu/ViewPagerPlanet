package org.pursuit.viewpagerexercise;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.pursuit.viewpagerexercise.model.PlanetList;
import org.pursuit.viewpagerexercise.networks.PlanetService;
import org.pursuit.viewpagerexercise.networks.RetrofitSingleton;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends FragmentActivity {

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> fragmentList = new ArrayList<>();

        RetrofitSingleton.getOneInstance()
                .create(PlanetService.class)
                .getPlanet()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        planets->{
                            Log.d("TAG", "onResponse " + planets.getPlanets().get(0).getName());
                            for(int i = 0; i < planets.getPlanets().size(); i++){
                                fragmentList.add(ViewPagerFragment.newInstance(planets.getPlanets().get(i).getName(),
                                        planets.getPlanets().get(i).getNumber(),
                                        planets.getPlanets().get(i).getImage()));


                                ViewPager viewPager = findViewById(R.id.view_pager);
                                viewPager.setAdapter(new ViewPagerAdaper(getSupportFragmentManager(), fragmentList));
                            }

                        },
                        throwable -> Log.d("TAG", "onFailure" + throwable));
    }
}
