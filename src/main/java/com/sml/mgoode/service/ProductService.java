package com.sml.mgoode.service;

import com.sml.mgoode.dao.ProductDAO;
import com.sml.mgoode.entity.ProductEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelgoode on 02/03/2018.
 */
@Service
public class ProductService {

    @Autowired
    ProductDAO productDAO;

    public List<ProductEntry> getProducts( long id ) {
        return productDAO.getProducts( id );
    }

    public void saveProduct( ProductEntry productEntry ) {
        productDAO.saveProduct( productEntry );

    }

    public void deleteProduct( long lookupId, long id ) {
        productDAO.deleteProduct(lookupId, id);
    }
}
