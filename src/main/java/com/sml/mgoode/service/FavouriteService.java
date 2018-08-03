package com.sml.mgoode.service;

import com.sml.mgoode.dao.FavouriteDAO;
import com.sml.mgoode.entity.Favourite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelgoode on 06/04/2018.
 */

@Service
public class FavouriteService implements IFavouriteService {

    @Autowired
    FavouriteDAO favouriteDAO;

    public List<Favourite> getFavourites() {
        return favouriteDAO.getFavourites();
    }
}
