package org.pursuit.viewpagerexercise.networks;

import org.pursuit.viewpagerexercise.model.PlanetList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PlanetService {

    String END_POINT = "JDVila/storybook/master/planets.json";

    @GET(END_POINT)
    Observable<PlanetList> getPlanet();
}
