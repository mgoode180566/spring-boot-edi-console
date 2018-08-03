package com.sml.mgoode.dao;

import com.sml.mgoode.entity.Favourite;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelgoode on 10/04/2018.
 */
@Repository
public class FavouriteDAO implements IFavouriteDAO {
    public List<Favourite> getFavourites() {
        // fixed at the moment
        List<Favourite> favourites = new ArrayList<>();
        favourites.add( new Favourite("Marks and Spencers","MS", "MS", 310, 267) );
//        favourites.add( new Favourite("EU", "B5") );
//        favourites.add( new Favourite("EU", "7N") );
//        favourites.add( new Favourite("EU", "AG") );
//        favourites.add( new Favourite("EU", "B7") );
//        favourites.add( new Favourite("EU", "EX") );
//        favourites.add( new Favourite("EU", "F2") );
//        favourites.add( new Favourite("EU", "E7") );
//        favourites.add( new Favourite("EU", "AJ") );
//        favourites.add( new Favourite("EU", "CZ") );
//        favourites.add( new Favourite("EU", "D5") );
//        favourites.add( new Favourite("EU", "EC") );
//        favourites.add( new Favourite("EU", "EH") );
//        favourites.add( new Favourite("BU", "BU") );
        favourites.add( new Favourite("Dorothy Perkins","RZ", "DP", 753, 148) );
        favourites.add( new Favourite("Topshop","RZ", "TS", 754, 152) );
        favourites.add( new Favourite("Miss Selfridge","RZ", "MS", 755, 150) );
        favourites.add( new Favourite("Topman","RZ", "TM", 750, 144) );
        favourites.add( new Favourite("Evans","RZ", "EV", 748, 149) );
        favourites.add( new Favourite("Warehouse","RZ", "WA", 744, 153) );
        favourites.add( new Favourite("Outfit","RZ", "OU", 747, 151) );
//        favourites.add( new Favourite("GO", "") );
//        favourites.add( new Favourite("J1", "JD") );
//        favourites.add( new Favourite("ML", "ML") );
//        favourites.add( new Favourite("NX", "UP") );
//        favourites.add( new Favourite("T6", "TA") );
//        favourites.add( new Favourite("TS", "") );
//        favourites.add( new Favourite("XI", "MD") );
//        favourites.add( new Favourite("ZA", "ZA") );
//        favourites.add( new Favourite("BU", "BU") );
        favourites.add( new Favourite("NEXT", "NX", "NX", 50017, 129 ) );
//        favourites.add( new Favourite("FC", "FC") );
        return favourites;
    }
}
