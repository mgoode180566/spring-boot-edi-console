package com.sml.mgoode.dao;

import com.sml.mgoode.entity.Lookup;
import com.sml.mgoode.entity.ProductEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by michaelgoode on 02/03/2018.
 */

@Repository
public class ProductDAO {

    @Autowired
    @Qualifier("genericJdbcTemplate")
    private JdbcTemplate genericJdbcTemplate;

    @Autowired
    @Qualifier("msJdbcTemplate")
    private JdbcTemplate msJdbcTemplate;

    private Connection getConnection( long id ) {
        Connection connection = null;
        try {
            if (id == 267) {
                connection = msJdbcTemplate.getDataSource().getConnection();
            } else {
                connection = genericJdbcTemplate.getDataSource().getConnection();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            connection = null;
        }
        return connection;
    }

    public ArrayList<ProductEntry> getProducts( long id ) {

        final String sql = "select * from edi_lookup_value where FK_lookup_ConditionID = ? order by CValue1";

        Connection connection = null;

        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection(id); // return the correct connection
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getResults(resultSet);
        } catch (SQLException ex) {
            ex.getMessage();
            System.out.println(ex.getMessage());
            return null;
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
    }

    private ArrayList<ProductEntry> getResults( ResultSet resultSet ) {
        ArrayList<ProductEntry> products = new ArrayList<>();
        try {
            while (resultSet.next()) {
                ProductEntry productEntry = new ProductEntry();
                productEntry.setId(resultSet.getLong("ValueId"));
                productEntry.setLookupId(resultSet.getLong("FK_lookup_ConditionID"));
                productEntry.setCValue1(resultSet.getString("CValue1"));
                productEntry.setCValue2(resultSet.getString("CValue2"));
                productEntry.setCValue3(resultSet.getString("CValue3"));
                productEntry.setUValue1(resultSet.getString("UValue1"));
                productEntry.setUValue2(resultSet.getString("UValue2"));
                productEntry.setUValue3(resultSet.getString("UValue3"));
                productEntry.setCreatedDate(resultSet.getString("rowCreateTime"));
                products.add(productEntry);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return products;
    }

    public void deleteProduct( long lookupId, long id ) {
        if (lookupId == 267) {
            deleteProduct(getConnection(lookupId), id);
        } else {
            deleteProduct(getConnection(0), id);
        }
    }

    private void deleteProduct( Connection connection, long id ) {
        final String sql = "delete from edi_lookup_value where ValueID = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
            System.out.println(ex.getMessage());
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
    }

    public void saveProduct( ProductEntry productEntry ) {
        if (productEntry.getLookupId() == 267) {
            saveProduct(getConnection(productEntry.getLookupId()), productEntry); // save on server .60
        }
        saveProduct(getConnection(0), productEntry); // save on .52, generic server
    }

    private void saveProduct( Connection connection, ProductEntry productEntry ) {
        // save a new product
        final String sql = "insert into edi_lookup_value (FK_lookup_ConditionID,UValue1,UValue2,UValue3,CValue1,CValue2,CValue3,rowCreateTime,rowUpdateTime)"
                + " values (?,?,?,?,?,?,?,GetDate(),GetDate())";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, productEntry.getLookupId());
            preparedStatement.setString(2, productEntry.getUValue1().toUpperCase());
            preparedStatement.setString(3, productEntry.getUValue2().toUpperCase());
            preparedStatement.setString(4, productEntry.getUValue3().toUpperCase());
            preparedStatement.setString(5, productEntry.getCValue1().toUpperCase());
            preparedStatement.setString(6, productEntry.getCValue2().toUpperCase());
            preparedStatement.setString(7, productEntry.getCValue3().toUpperCase());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.getMessage();
            System.out.println(ex.getMessage());
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
    }


}
