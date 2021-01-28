package com.example.spring.demo.Repository;

import com.example.spring.demo.Model.Cart;
import com.example.spring.demo.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository("cartRepository")
public class CartRepositoryImpl implements CartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Cart> findAllCart() {
        String sql = "select * from cart";
        return jdbcTemplate.query(sql,
                (rs, rowNum) ->
                        new Cart(
                                rs.getString("idCart"),
                                rs.getDate("transactionDate"),
                                rs.getInt("idCustomer"),
                                rs.getBoolean("status"),
                                null
                        ));
    }

    @Override
    public List<Cart> findAllCartSave() {
        String sql = "select * from cart order by idCart desc limit 1";
        return jdbcTemplate.query(sql,
                (rs, rowNum) ->
                        new Cart(
                                rs.getString("idCart"),
                                rs.getDate("transactionDate"),
                                rs.getInt("idCustomer"),
                                rs.getBoolean("status"),
                                null
                        ));
    }

    @Override
    public Cart findById(String idCart) {
        String sql = "select * from cart where idCart=" + idCart + "";
        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) ->
                        new Cart(
                                rs.getString("idCart"),
                                rs.getDate("transactionDate"),
                                rs.getInt("idCustomer"),
                                rs.getBoolean("status"),
                                null
                        ));
    }

    @Override
    public Cart findByCustomerId(int idCustomer) {
        String sql = "select * from cart where idCustomer=" + idCustomer + "";
        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) ->
                        new Cart(
                                rs.getString("idCart"),
                                rs.getDate("transactionDate"),
                                rs.getInt("idCustomer"),
                                rs.getBoolean("status"),
                                null
                        ));
    }

    @Override
    public void saveCart(Cart cart) {
        cart.setIdCart(UUID.randomUUID().toString());
        cart.setTransactionDate(new Date());
        jdbcTemplate.update("insert into cart (idCart, transactionDate, idCustomer, status) " +
                "values (?,?,?,?)",
                cart.getIdCart(),
                cart.getTransactionDate(),
                cart.getIdCustomer(),
                cart.isStatus()
        );
        List<Product> products = cart.getProducts();

        for (int i = 0; i < products.size(); i++) {
            jdbcTemplate.update("insert into cartdet (idCart, id, qty)" +
                    "values (?,?,?)",
                    cart.getIdCart(),
                    products.get(i).getId(),
                    products.get(i).getQty());
        }
    }

    @Override
    public int updateCart(Cart cart) {
        return 0;
    }

    @Override
    public String deleteCartById(String idCart) {
        return null;
    }

    @Override
    public void deleteAllCart() {

    }
}
