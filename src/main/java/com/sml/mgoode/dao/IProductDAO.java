package com.sml.mgoode.dao;

import com.sml.mgoode.entity.Lookup;
import com.sml.mgoode.entity.ProductEntry;

import java.util.ArrayList;

/**
 * Created by michaelgoode on 02/03/2018.
 */
public interface IProductDAO {
    public ArrayList<ProductEntry> getProducts( long id );
    public void saveProduct( ProductEntry productEntry );
    public void deleteProduct( long id );
}
