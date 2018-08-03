package com.sml.mgoode.service;

import com.sml.mgoode.entity.ProductEntry;

import java.util.List;

/**
 * Created by michaelgoode on 02/03/2018.
 */
public interface IProductService {
    List<ProductEntry> getProducts( long id );
    public void saveProduct( ProductEntry productEntry );
    public void deleteProduct( long lookupId, long id );
}
