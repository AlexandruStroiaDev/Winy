package co.alexdev.winy.core.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DishPairingResponse {

    @SerializedName("text")
    public String textDescription;
    @SerializedName("pairings")
    public List<String> pairings;
}
